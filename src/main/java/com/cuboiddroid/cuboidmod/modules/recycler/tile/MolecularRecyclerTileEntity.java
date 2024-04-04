package com.cuboiddroid.cuboidmod.modules.recycler.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.recycler.config.BlacklistConfig;
import com.cuboiddroid.cuboidmod.modules.recycler.inventory.MolecularRecyclerContainer;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import com.cuboiddroid.cuboidmod.util.Pair;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntityTicker ;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class MolecularRecyclerTileEntity extends BlockEntity implements BlockEntityTicker  {
    private static final Dictionary<String, RecyclingRecipe> AUTO_RECIPES = new Hashtable<>();
    private static final HashMap<String, Boolean> REJECTED_RECIPES = new HashMap<>();
    private static final Random RANDOM = new Random();

    private static final int MAX_RECYCLING_STEPS = Config.molecularRecyclerMaxRecyclingSteps.get();
    private static final int GUARANTEED_AMOUNT = Config.molecularRecyclerGuaranteedAmount.get();
    private static final int GUARANTEED_COUNT = Config.molecularRecyclerGuaranteedCount.get();

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_OUTPUT_1 = 1;
    public static final int SLOT_OUTPUT_2 = 2;
    public static final int SLOT_OUTPUT_3 = 3;
    public static final int SLOT_OUTPUT_4 = 4;
    public static final int SLOT_OUTPUT_5 = 5;
    public static final int SLOT_OUTPUT_6 = 6;
    private static final int INPUT_SLOTS = 1;
    private static final int OUTPUT_SLOTS = 6;
    public static final int TOTAL_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS;
    private ItemStackHandler inputItemHandler = createInputHandler();
    private ItemStackHandler outputItemHandler = createOutputHandler();
    private CombinedInvWrapper combinedItemHandler = new CombinedInvWrapper(inputItemHandler, outputItemHandler);
    private CuboidEnergyStorage energyStorage;

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> inputItemHandler);
    private LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> outputItemHandler);
    private LazyOptional<IItemHandler> combinedHandler = LazyOptional.of(() -> combinedItemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int energyCapacity = Config.molecularRecyclerEnergyCapacity.get();
    private int maxEnergyReceivedPerTick = Config.molecularRecyclerMaxEnergyInputPerTick.get();

    private int processingTime = 0;
    private int recipeTime = -1;
    private int energyConsumed = 0;

    public MolecularRecyclerTileEntity() {
        this(BlockPos.ZERO, ModBlocks.MOLECULAR_RECYCLER.get().defaultBlockState());
    }

    public MolecularRecyclerTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.MOLECULAR_RECYCLER.get(), pos, state);
        energyStorage = createEnergy();
    }

    public Component getDisplayName() {
        return new TranslatableComponent("cuboidmod.container.molecular_recycler");
    }

    public static void gameTick(Level level, BlockPos worldPosition, BlockState blockState, MolecularRecyclerTileEntity entity) {
        entity.tick(level, worldPosition, blockState, entity);
    }

    @Override
    public void tick(Level level, BlockPos worldPosition, BlockState blockState, BlockEntity entity) {
        if (level == null || level.isClientSide)
            return;

        boolean didWorkThisTick = false;

        RecyclingRecipe recipe = getRecipe();

        if (recipe == null) {
            if (processingTime > 0 || energyConsumed > 0) {
                stopWork();
                setChanged();
            }
        } else {
            if (processingTime <= 0) {
                if (energyConsumed + energyStorage.getEnergyStored() < recipe.getEnergyRequired())
                    // not enough energy to do anything - do nothing!
                    return;

                if (energyConsumed < recipe.getEnergyRequired()) {
                    // consume the required amount of energy
                    int energyNeeded = recipe.getEnergyRequired() - energyConsumed;

                    if (energyNeeded > 0) {
                        int energyToConsume = Math.min(energyNeeded, energyStorage.getEnergyStored());
                        energyStorage.consumeEnergy(energyToConsume);
                        energyConsumed += energyToConsume;
                        setChanged();
                    }
                }
            }

            if (processingTime <= 0 && energyConsumed >= recipe.getEnergyRequired()) {
                // we've consumed enough energy but have not started working - start working!
                this.processingTime = recipe.getWorkTicks();
                this.recipeTime = processingTime;
                setChanged();
            }

            if (processingTime > 0 && energyConsumed >= recipe.getEnergyRequired()) {
                didWorkThisTick = doWork(recipe);
                setChanged();
            }
        }

        if (blockState.getValue(BlockStateProperties.LIT) != (processingTime > 0 || didWorkThisTick)) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.LIT, processingTime > 0), Block.UPDATE_ALL);
        }
    }

    private boolean doWork(RecyclingRecipe recipe) {
        assert this.level != null;

        List<ItemStack> recipeOutputs = getWorkOutput(recipe);

        boolean hasRoomForOutputs = false;
        int outputSlotIndex = 0;
        while (!hasRoomForOutputs && outputSlotIndex < OUTPUT_SLOTS) {
            ItemStack outputSlot = this.outputItemHandler.getStackInSlot(outputSlotIndex);
            hasRoomForOutputs = outputSlot.isEmpty() ||
                    recipeOutputs.stream().anyMatch((recipeOutput) ->
                            !recipeOutput.isEmpty() &&
                                    recipeOutput.sameItem(outputSlot) &&
                                    recipeOutput.getCount() + outputSlot.getCount() <= recipeOutput.getMaxStackSize());

            outputSlotIndex++;
        }

        if (!hasRoomForOutputs) {
            stopWork();
            return false;
        }

        if (processingTime > 0)
            processingTime--;

        if (processingTime <= 0) {
            finishWork(recipe, recipeOutputs);

            // return true so that we don't get "flicker" for the core between recipes
            return true;
        }

        return false;
    }

    private void stopWork() {
        this.processingTime = 0;
        this.recipeTime = -1;
    }

    private void finishWork(RecyclingRecipe recipe, List<ItemStack> recipeOutputs) {
        recipeOutputs.forEach(this::moveStackToOutputs);

        this.energyConsumed = 0;
        this.processingTime = 0;
        this.recipeTime = -1;

        // consume the ingredients
        ItemStack inputStack = inputItemHandler.getStackInSlot(0).copy();
        inputStack.shrink(recipe.getIngredient().getItems()[0].getCount());

        inputItemHandler.setStackInSlot(0, inputStack);
    }

    private void moveStackToOutputs(ItemStack stack) {
        int firstEmptyIndex = -1;
        for (int outputSlotIndex = 0; outputSlotIndex < OUTPUT_SLOTS; outputSlotIndex++) {
            ItemStack outputStack = outputItemHandler.getStackInSlot(outputSlotIndex).copy();
            if (outputStack.sameItem(stack) && outputStack.getCount() + stack.getCount() <= outputStack.getMaxStackSize()) {
                outputStack.grow(stack.getCount());
                outputItemHandler.setStackInSlot(outputSlotIndex, outputStack);
                return;
            }

            if (outputStack.isEmpty() && firstEmptyIndex < 0) {
                firstEmptyIndex = outputSlotIndex;
            }
        }

        if (firstEmptyIndex >= 0) {
            outputItemHandler.setStackInSlot(firstEmptyIndex, stack.copy());
        } else {
            CuboidMod.LOGGER.error("Attempted to move stack to output slots without checking if there is room first!");
        }
    }

    @Nullable
    public RecyclingRecipe getRecipe() {
        if (this.level == null
                || this.inputItemHandler.getStackInSlot(0).isEmpty())
            return null;

        // check if has damage, if so, we won't process it
        if (this.inputItemHandler.getStackInSlot(0).getDamageValue() > 0)
            return null;

        // make an inventory
        Container inv = getInputsAsInventory();

        // look for a specific recipe and use it if found (i.e. json overrides)
        RecyclingRecipe recipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.RECYCLING.get(), inv, this.level).orElse(null);

        // there is a (JSON) recipe override, so use it
        if (recipe != null)
            return recipe;

        ItemStack inputItem = inv.getItem(0);
        return getOrCreateAutoRecipe(inputItem);
    }

    /**
     * Checks if we've already created an auto-recipe for recycling this item, and
     * creates it if needed.
     *
     * @param inputItem the input item we're trying to recycle
     * @return the dynamically created RecyclingRecipe
     */
    private RecyclingRecipe getOrCreateAutoRecipe(ItemStack inputItem) {
        // there is no JSON-based recipe, so we're going to try and "un-craft" the
        // ingredient instead...

        String autoRecipeId = CuboidMod.MOD_ID + ":recycler_auto_" + inputItem.getItem().getRegistryName().toString().replace(':', '_');

        // check our cache to see if we've already rejected this one...
        if (REJECTED_RECIPES.containsKey(autoRecipeId))
            return null;

        // then check if we've already calculated it
        RecyclingRecipe recipe = AUTO_RECIPES.get(autoRecipeId);
        if (recipe != null)
            return recipe;

        // Not in cache, so we need to try and create a new automatic recipe

        // get the complete list of possible results (i.e. recycle everything)
        // and the maximum number of steps (depth of the tree)
        Map<Item, ItemStack> possibleResults = new HashMap<>();
        int recyclingSteps = getAutoRecipeResults(inputItem, possibleResults, 0);

        if (possibleResults.isEmpty() || (possibleResults.size() == 1 && possibleResults.containsKey(inputItem.getItem()))) {
            // cache the rejected recipes too so we don't recalculate it each time!
            REJECTED_RECIPES.put(autoRecipeId, true);
            return null;
        }

        // determine, based on config, how many of each item we get, and what the chances
        // are in each case - will return a maximum of 6 unique ingredient items, but could be more
        // than 6 entries
        Map<ItemStack, Float> recyclingResults = determineRecyclingRecipeResults(possibleResults);

        // recipes have been calculated against 1 input, so
        // make sure the recipe expects just the 1 input too
        ItemStack recipeInput = inputItem.copy();
        recipeInput.setCount(1);

        // create the automatic recipe
        recipe = new RecyclingRecipe(new ResourceLocation(autoRecipeId)) {
            private final Map<ItemStack, Float> results = recyclingResults;
            private final Ingredient ingredient = Ingredient.of(recipeInput);

            @Override
            public Ingredient getIngredient() {
                return this.ingredient;
            }

            @Override
            public List<ItemStack> getResults(Container inv) {
                return this.results.entrySet().stream()
                        .filter(e -> RANDOM.nextDouble() < e.getValue())
                        .map(e -> e.getKey().copy())
                        .collect(Collectors.toList());
            }

            @Override
            public Set<ItemStack> getPossibleResults(Container inv) {
                return this.results.keySet();
            }

            @Override
            public List<Pair<ItemStack, Float>> getPossibleResultsWithChances() {
                return this.results.entrySet().stream()
                        .map(e -> new Pair<>(e.getKey(), e.getValue()))
                        .collect(Collectors.toList());
            }

            @Override
            public int getEnergyRequired() {
                int baseEnergyRequired = Config.molecularRecyclerBaseEnergyRequired.get();
                int stepEnergyRequired = Config.molecularRecyclerStepEnergyRequired.get();

                return baseEnergyRequired + recyclingSteps * stepEnergyRequired;
            }

            @Override
            public int getWorkTicks() {
                int baseWorkTicks = Config.molecularRecyclerBaseWorkTicksRequired.get();
                int stepWorkTicks = Config.molecularRecyclerStepWorkTicksRequired.get();

                return baseWorkTicks + recyclingSteps * stepWorkTicks;
            }
        };

        // save the new recipe in the cache
        AUTO_RECIPES.put(autoRecipeId, recipe);

        // and return it
        return recipe;
    }

    /**
     * Recursively "uncrafts" the input item and puts the ingredients into the possibleResults map.
     *
     * @param inputStack       The input item stack to uncraft
     * @param possibleResults the map of possible results to populate - should be empty when first called
     * @param currentStep     the current "depth" or number of recycling steps - should be 0 when first called
     * @return the maximum "depth" or number of recycling steps achieved in the recursive function
     */
    private int getAutoRecipeResults(ItemStack inputStack, Map<Item, ItemStack> possibleResults, int currentStep) {
        // ensure we're not trying to recycle a blacklisted item or ingredient

        ItemStack inputItem = inputStack.copy();

        if (currentStep <= 0) {
            // work with a single item as input when calculating the recipe
            // but only on the first step (original input)
            inputItem.setCount(1);
        }

        if (itemIsBlacklisted(inputItem)) {
            addItemStackToPossibleResults(inputItem, possibleResults);

            return currentStep;
        }

        List<Recipe<?>> potentialRecipes = null;
        if (currentStep < MAX_RECYCLING_STEPS && level != null) {
            // find suitable standard crafting recipe(s) that result in this item
            MinecraftServer server = level.getServer();
            if (server != null) {
                RecipeManager recipeManager = server.getRecipeManager();

                Collection<Recipe<?>> allRecipes = recipeManager.getRecipes();

                potentialRecipes = allRecipes.stream().filter(rec ->
                        rec != null &&
                                rec.getType() == RecipeType.CRAFTING &&
                                !rec.getResultItem().isEmpty() &&
                                rec.getResultItem().getCount() <= inputItem.getCount() &&
                                !recipeIsBlacklisted(rec) &&
                                inputItem.getItem() == rec.getResultItem().getItem() &&
                                ItemStack.isSameIgnoreDurability(inputItem, rec.getResultItem()))
                        .collect(Collectors.toList());
            }
        }

        if (potentialRecipes == null
                || currentStep >= MAX_RECYCLING_STEPS
                || potentialRecipes.size() <= 0) {
            // if no suitable recipe found:
            //   add the input ingredients to the possibleResults - adding to existing stacks of same item type
            //   return the current step
            addItemStackToPossibleResults(inputItem, possibleResults);
            return currentStep;
        }

        //   pick the one with the most input ingredients as they are hopefully more "basic" ingredients
        Recipe<?> selectedRecipe = null;
        int selectInputCount = -1;
        for (Recipe<?> rec : potentialRecipes) {
            int inputCount = 0;
            for (Ingredient i : rec.getIngredients()) {
                ItemStack[] stacks = i.getItems();
                for (ItemStack stack : stacks)
                    inputCount += stack.isEmpty() ? 0 : 1;
            }

            if (selectedRecipe == null || inputCount > selectInputCount)
                selectedRecipe = rec;
        }

        if (selectedRecipe == null) {
            // safety check!
            addItemStackToPossibleResults(inputItem, possibleResults);
            return currentStep;
        }

        // got a recipe, so now we need to try and recycle the ingredients it too!
        // keep track of the maximum depth reached

        int maxSteps = currentStep;

        NonNullList<Ingredient> ingredients = selectedRecipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            // for each input ingredient, iterate through the ingredient stacks
            // and find the first one that's not empty
            ItemStack stack = ItemStack.EMPTY;
            for (ItemStack s : ingredient.getItems())
                if (stack.isEmpty() && !s.isEmpty()) {
                    stack = s;
                }

            if (!stack.isEmpty()) {
                // recursively call this for each ingredient, passing in currentStep+1
                int stackSteps = getAutoRecipeResults(stack, possibleResults, currentStep + 1);
                // compare maxSteps with the return value from recursive call and note maximum
                if (stackSteps > maxSteps)
                    maxSteps = stackSteps;
            }
        }

        // done! return the maximum depth / number of recycling steps achieved.
        return maxSteps;
    }

    /**
     * Adds the input item to the possible results. If there are already some of the
     * input items in the possible results, then increments the size of the stack
     *
     * @param inputItem       the item to be added to the possible results
     * @param possibleResults the current map of possible results to add to
     */
    private void addItemStackToPossibleResults(ItemStack inputItem, Map<Item, ItemStack> possibleResults) {
        Item item = inputItem.getItem();
        ItemStack stack = possibleResults.get(item);
        if (stack == null || stack.isEmpty()) {
            // only add this new output if it is not blacklisted
            if (!resultItemIsBlacklisted(item))
                possibleResults.put(item, inputItem.copy());
        } else {
            // existing output, so presumably we don't need to check whether it's blacklisted again
            ItemStack newStack = stack.copy();
            newStack.setCount(stack.getCount() + inputItem.getCount());
            possibleResults.put(item, newStack);
        }
    }

    /**
     * Checks if the recipe is blacklisted in config
     *
     * @param recipe the recipe to check
     * @return true if it is blacklisted, otherwise false
     */
    private boolean recipeIsBlacklisted(Recipe<?> recipe) {
        return BlacklistConfig.getInstance().isBlacklistedRecipe(recipe.getId().toString());
    }

    /**
     * Checks if the item is blacklisted from recycling in config
     *
     * @param item the item to check
     * @return true if it is blacklisted, otherwise false
     */
    private boolean itemIsBlacklisted(ItemStack item) {
        // check the item type
        if (BlacklistConfig.getInstance().isBlacklistedItem(item.getItem().getRegistryName().toString()))
            return true;

        // check the item's tags
        for (TagKey<Item> tag : item.getTags().toList())
        {
            if (BlacklistConfig.getInstance().isBlacklistedTag(tag.location().toString()))
                return true;
        }

        // any items with containers (e.g. smooshers) should be excluded!
        if (item.hasContainerItem())
            return true;

        // no matches - not blacklisted
        return false;
    }

    /**
     * Checks if the item is a blacklisted output from recycling in config
     *
     * @param item the item to check
     * @return true if it is blacklisted, otherwise false
     */
    private boolean resultItemIsBlacklisted(Item item) {
        if (BlacklistConfig.getInstance().isBlacklistedResultItem(item.getRegistryName().toString()))
            return true;

        // check the item's tags
        for (TagKey<Item> tag : item.getDefaultInstance().getTags().toList())
        {
            if (BlacklistConfig.getInstance().isBlacklistedResultTag(tag.location().toString()))
                return true;
        }

        // any items with containers (e.g. smooshers) should be excluded!
        if (item.getDefaultInstance().hasContainerItem())
            return true;

        // no matches - not blacklisted
        return false;
    }

    /**
     * Given a map of all possible automatic recycling recipe outputs, trim it
     * down to a list of 6 (or fewer) actual output item types to fit in the tile
     * entity, with various percentage chances of them being obtained.
     *
     * @param possibleResults the complete list of possible results
     * @return a map with percentage chances of items being obtained - maximum
     * of 6 unique item types, but could include significantly more individual
     * item stacks - e.g. iron ingot x 2 - 100%,
     */
    private Map<ItemStack, Float> determineRecyclingRecipeResults(Map<Item, ItemStack> possibleResults) {
        Map<ItemStack, Float> resultStacks = new HashMap<>();

        // sort the possible results based on number of items in the item stack
        // and keep first 6 (or fewer)
        List<ItemStack> sorted = possibleResults.values().stream()
                .sorted((o1, o2) ->
                        o1.getCount() > o2.getCount() ? -1 : 1)
                .collect(Collectors.toList());

        // note: to_index is exclusive!
        List<ItemStack> trimmed = sorted
                .subList(0, Math.min(possibleResults.size(), 6));

        for (ItemStack stack : trimmed) {
            int stackSize = stack.getCount();
            if (stackSize > GUARANTEED_AMOUNT) {
                // stack size > guaranteed amount
                // add result stack with guaranteed count and chance 1.0 (100%)
                // reduce stack size by guaranteed count
                //   e.g. guaranteed amount of 4 and count of 2 means that for
                //        first 4 inputs of this type, there's a 100% chance of getting
                //        2 back (i.e. guaranteed 50% return)
                ItemStack guaranteed = stack.copy();
                guaranteed.setCount(GUARANTEED_COUNT);
                resultStacks.put(guaranteed, 1.0F);
                stackSize -= GUARANTEED_AMOUNT;
            }

            if (stackSize > 0) {
                // add individual result stack of (up to) next 2 with 80% chance
                ItemStack stack80 = stack.copy();
                stack80.setCount(Math.min(stackSize, 2));
                resultStacks.put(stack80, 0.8F);
                stackSize -= 2;
            }

            if (stackSize > 0) {
                // add individual result stack of (up to) next 2 with 60% chance
                ItemStack stack60 = stack.copy();
                stack60.setCount(Math.min(stackSize, 2));
                resultStacks.put(stack60, 0.60F);
                stackSize -= 2;
            }

            if (stackSize > 0) {
                // add individual result stack of (up to) next 2 with 50% chance
                ItemStack stack50 = stack.copy();
                stack50.setCount(Math.min(stackSize, 2));
                resultStacks.put(stack50, 0.5F);
                stackSize -= 2;
            }

            while (stackSize > 0) {
                // add result stacks of (up to 2) for remainder with 25% chance
                ItemStack stack25 = stack.copy();
                stack25.setCount(Math.min(stackSize, 2));
                resultStacks.put(stack25, 0.25F);
                stackSize -= 2;
            }
        }

        // return the map of results
        return resultStacks;
    }

    private SimpleContainer getInputsAsInventory() {
        return new SimpleContainer(this.inputItemHandler.getStackInSlot(0).copy());
    }

    private List<ItemStack> getWorkOutput(@Nullable RecyclingRecipe recipe) {
        if (recipe != null) {
            return recipe.getResults(getInputsAsInventory());
        }

        return new ArrayList<>(0);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            // if side is null, then it's not via automation, so provide access to everything
            if (side == null)
                return combinedHandler.cast();

            // if side is not null, then it's automation
            // BOTTOM = output, UP = base item input, sides = additional
            switch (side) {
                case UP:
                    return inputHandler.cast();

                default:
                    return outputHandler.cast();
            }
        }

        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag tag) {
		super.load(tag);
		inputItemHandler.deserializeNBT(tag.getCompound("invIn"));
        outputItemHandler.deserializeNBT(tag.getCompound("invOut"));
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        processingTime = tag.getInt("procTime");
        recipeTime = tag.getInt("recTime");
        energyConsumed = tag.getInt("feConsumed");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("invIn", inputItemHandler.serializeNBT());
        tag.put("invOut", outputItemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putInt("procTime", processingTime);
        tag.putInt("recTime", recipeTime);
        tag.putInt("feConsumed", energyConsumed);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        inputHandler.invalidate();
        outputHandler.invalidate();
        combinedHandler.invalidate();
        energy.invalidate();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        this.load(tag);
        this.setChanged();
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
    }

    @Override
    public CompoundTag getUpdateTag() { 
        CompoundTag nbtTag = super.getUpdateTag();
        this.saveAdditional(nbtTag);
        this.setChanged();
        return nbtTag;
    }

    private CuboidEnergyStorage createEnergy() {
        return new CuboidEnergyStorage(energyCapacity, maxEnergyReceivedPerTick, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    private ItemStackHandler createInputHandler() {
        return new ItemStackHandler(INPUT_SLOTS) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return this.getStackInSlot(slot).isEmpty() || this.getStackInSlot(slot).sameItem(stack);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private ItemStackHandler createOutputHandler() {
        return new ItemStackHandler(OUTPUT_SLOTS) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                // can't insert into the output slot
                return stack;
            }
        };
    }

    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new MolecularRecyclerContainer(i, level, pos, playerInventory, playerEntity);
    }

    public int getEnergyCapacity() {
        return this.energyCapacity;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public void setProcessingTime(int value) {
        this.processingTime = value;
    }

    public int getRecipeTime() {
        return this.recipeTime == 0 ? -1 : this.recipeTime;
    }
}
