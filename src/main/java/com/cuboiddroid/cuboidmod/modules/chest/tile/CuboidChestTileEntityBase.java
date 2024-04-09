package com.cuboiddroid.cuboidmod.modules.chest.tile;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestBlockBase;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker ;

@SuppressWarnings("rawtypes")
@OnlyIn(value = Dist.CLIENT, _interface = LidBlockEntity.class)
public class CuboidChestTileEntityBase extends RandomizableContainerBlockEntity implements LidBlockEntity, BlockEntityTicker {

    private NonNullList<ItemStack> chestContents;
    protected float lidAngle;
    protected float prevLidAngle;
    protected int numPlayersUsing;

    private int ticksSinceSync;
    private CuboidChestTypes chestType;
    private Supplier<Block> blockToUse;
    public boolean retainsInventory;
    public boolean canOpenWhenObstructed;

    protected CuboidChestTileEntityBase(BlockEntityType<?> typeIn, CuboidChestTypes chestType, Supplier<Block> blockToUse, boolean retainsInventory, boolean canOpenWhenObstructed) {
        this(typeIn, null, null, chestType, blockToUse, retainsInventory, canOpenWhenObstructed);
    }

    protected CuboidChestTileEntityBase(BlockEntityType<?> typeIn, BlockPos pos, BlockState state, CuboidChestTypes chestType, Supplier<Block> blockToUse, boolean retainsInventory, boolean canOpenWhenObstructed) {
        super(typeIn, pos, state);

        this.chestContents = NonNullList.withSize(chestType.size, ItemStack.EMPTY);
        this.chestType = chestType;
        this.blockToUse = blockToUse;
        this.retainsInventory = retainsInventory;
        this.canOpenWhenObstructed = canOpenWhenObstructed;
    }

    @Override
    public int getContainerSize() {
        return this.getItems().size();
    }

    @Override
    public boolean stillValid(Player player) {
        // player must be within sqrt(64) = 8 blocks - same as vanilla furnace, so
        // going with it.
        return this.hasLevel()
                && this.level.getBlockEntity(this.worldPosition) == this
                && player.distanceToSqr(
                        this.worldPosition.getX() + 0.5,
                        this.worldPosition.getY() + 0.5,
                        this.worldPosition.getZ() + 0.5
                ) <= 64
                && this.canOpenChest();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.chestContents) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void setCustomName(Component newNameComponent) {
        // for some reason still to be figured out, when naming
        // in an anvil, we're always getting a string with []'s - this cleans up
        String newName = newNameComponent.getString().trim();
        if (newName.startsWith("[") && newName.endsWith("]"))
            newName = newName.substring(1, newName.length() - 1).trim();
        super.setCustomName(Component.literal(newName));
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(CuboidMod.MOD_ID + ".container." + this.chestType.getId() + "_chest");
    }

    @Override
    public void load(CompoundTag tags) {
        super.load(tags);

        this.chestContents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(tags)) {
            ContainerHelper.loadAllItems(tags, this.chestContents);
        }
    }

    @Override
    public void saveAdditional(CompoundTag tags) {
        super.saveAdditional(tags);

        if (!this.trySaveLootTable(tags)) {
            ContainerHelper.saveAllItems(tags, this.chestContents);
        }
    }

    public static <T extends CuboidChestTileEntityBase> void gameTick(Level level, BlockPos worldPosition, BlockState blockState, T entity) {
        entity.tick(level, worldPosition, blockState, entity);
    }

    @Override
    public void tick(Level level, BlockPos worldPosition, BlockState blockState, BlockEntity entity) {
        int x = this.worldPosition.getX();
        int y = this.worldPosition.getY();
        int z = this.worldPosition.getZ();
        ++this.ticksSinceSync;
        this.numPlayersUsing = getNumberOfPlayersUsing(level, this, this.ticksSinceSync, x, y, z, this.numPlayersUsing);
        this.prevLidAngle = this.lidAngle;
        // float f = 0.1F;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            this.playSound(SoundEvents.CHEST_OPEN);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;
            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            }
            else {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            // float f2 = 0.5F;
            if (this.lidAngle < 0.5F && f1 >= 0.5F) {
                this.playSound(SoundEvents.CHEST_CLOSE);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    public static int getNumberOfPlayersUsing(Level level, BaseContainerBlockEntity lockableTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayersUsing) {
        if (!level.isClientSide && numPlayersUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
            numPlayersUsing = getNumberOfPlayersUsing(level, lockableTileEntity, x, y, z);
        }

        return numPlayersUsing;
    }

    public static int getNumberOfPlayersUsing(Level level, BaseContainerBlockEntity lockableTileEntity, int x, int y, int z) {
        int i = 0;

        for (Player playerentity : level.getEntitiesOfClass(Player.class, new AABB((double) ((float) x - 5.0F), (double) ((float) y - 5.0F), (double) ((float) z - 5.0F), (double) ((float) (x + 1) + 5.0F), (double) ((float) (y + 1) + 5.0F), (double) ((float) (z + 1) + 5.0F)))) {
            if (playerentity.containerMenu instanceof CuboidChestContainer) {
                ++i;
            }
        }

        return i;
    }

    private void playSound(SoundEvent soundIn) {
        double d0 = (double) this.worldPosition.getX() + 0.5D;
        double d1 = (double) this.worldPosition.getY() + 0.5D;
        double d2 = (double) this.worldPosition.getZ() + 0.5D;

        this.level.playSound((Player) null, d0, d1, d2, soundIn, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        }
        else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    protected void onOpenOrClose() {
        if (this.canOpenChest()) {
            Block block = this.getBlockState().getBlock();
            this.level.blockEvent(this.worldPosition, block, 1, this.numPlayersUsing);
            this.level.updateNeighborsAt(this.worldPosition, block);
        }
    }

    private boolean canOpenChest()
    {
        Block block = this.getBlockState().getBlock();

        if (block instanceof CuboidChestBlockBase) {
            if (this.canOpenWhenObstructed || !((CuboidChestBlockBase)block).isObstructed(this.level, this.worldPosition)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.chestContents = NonNullList.<ItemStack>withSize(this.getChestType().size, ItemStack.EMPTY);

        for (int i = 0; i < itemsIn.size(); i++) {
            if (i < this.chestContents.size()) {
                this.getItems().set(i, itemsIn.get(i));
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getOpenNess(float partialTicks) {
        return Mth.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    public static int getPlayersUsing(BlockGetter reader, BlockPos posIn) {
        BlockState blockstate = reader.getBlockState(posIn);
        if (blockstate.hasBlockEntity()) {
            BlockEntity tileentity = reader.getBlockEntity(posIn);
            if (tileentity instanceof CuboidChestTileEntityBase) {
                return ((CuboidChestTileEntityBase) tileentity).numPlayersUsing;
            }
        }

        return 0;
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory playerInventory) {
        return CuboidChestContainer.createNotsogudiumContainer(windowId, playerInventory, this);
    }

    public void wasPlaced(LivingEntity livingEntity, ItemStack stack) {
    }

    public void removeAdornments() {
    }

    public CuboidChestTypes getChestType() {
        CuboidChestTypes type = CuboidChestTypes.NOTSOGUDIUM;

        if (this.hasLevel()) {
            CuboidChestTypes typeFromBlock = CuboidChestBlockBase.getTypeFromBlock(this.getBlockState().getBlock());

            if (typeFromBlock != null) {
                type = typeFromBlock;
            }
        }

        return type;
    }

    public Block getBlockToUse() {
        return this.blockToUse.get();
    }

    private void deserializeChestContentsNBT(CompoundTag nbt)
    {
        this.chestContents = NonNullList.withSize(chestType.size, ItemStack.EMPTY);
        ListTag tagList = nbt.getList("Items", 10);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundTag itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");

            if (slot >= 0 && slot < this.chestContents.size())
            {
                this.chestContents.set(slot, ItemStack.of(itemTags));
            }
        }
        onLoad();
    }

    public CompoundTag serializeChestContentsNBT()
    {
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < this.chestContents.size(); i++)
        {
            if (!this.chestContents.get(i).isEmpty())
            {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                this.chestContents.get(i).save(itemTag);
                nbtTagList.add(itemTag);
            }
        }
        CompoundTag nbt = new CompoundTag();
        nbt.put("Items", nbtTagList);
        return nbt;
    }

}
