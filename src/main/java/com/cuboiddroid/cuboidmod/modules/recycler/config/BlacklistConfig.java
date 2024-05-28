package com.cuboiddroid.cuboidmod.modules.recycler.config;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * For reading / writing configuration file containing lists of blacklisted items and recipes
 */
public class BlacklistConfig {

    private static final boolean VERBOSE_LOGGING = Config.verboseLogging.get();

    private static final HashSet<String> RECIPES = new HashSet<>();
    private static final HashSet<String> ITEMS = new HashSet<>();
    private static final HashSet<String> ITEM_TAGS = new HashSet<>();
    private static final HashSet<String> RESULT_ITEMS = new HashSet<>();
    private static final HashSet<String> RESULT_ITEM_TAGS = new HashSet<>();

    private static final BlacklistConfig INSTANCE = new BlacklistConfig();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    public static BlacklistConfig getInstance() {
        return INSTANCE;
    }

    private BlacklistConfig()
    {
        // private constructor - must use BlacklistConfig.INSTANCE to access the config.
    }

    public void loadConfig()
    {
        Stopwatch stopwatch = Stopwatch.createStarted();
        File dir = FMLPaths.CONFIGDIR.get().resolve(CuboidMod.MOD_ID + "/recycler/").toFile();

        CuboidMod.LOGGER.info("Loading Molecular Recycler blacklists");

        if (!dir.exists() && dir.mkdirs()) {
            if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Creating default Molecular Recycler blacklists");

            // recipe blacklist
            FileWriter writer = null;
            try {
                File file = new File(dir, "recipe_blacklist.json");
                writer = new FileWriter(file);
                GSON.toJson(getDefaultRecipeBlacklist(), writer);
                writer.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while generating default recipe blacklist.", e);
            } finally {
                IOUtils.closeQuietly(writer);
                writer = null;
            }

            // item blacklist
            try {
                File file = new File(dir, "item_blacklist.json");
                writer = new FileWriter(file);
                GSON.toJson(getDefaultItemBlacklist(), writer);
                writer.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while generating default item blacklist.", e);
            } finally {
                IOUtils.closeQuietly(writer);
            }

            // item tag blacklist
            try {
                File file = new File(dir, "item_tag_blacklist.json");
                writer = new FileWriter(file);
                GSON.toJson(getDefaultItemTagBlacklist(), writer);
                writer.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while generating default item tag blacklist.", e);
            } finally {
                IOUtils.closeQuietly(writer);
            }

            // result item blacklist
            try {
                File file = new File(dir, "result_item_blacklist.json");
                writer = new FileWriter(file);
                GSON.toJson(getDefaultResultItemBlacklist(), writer);
                writer.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while generating default result item blacklist.", e);
            } finally {
                IOUtils.closeQuietly(writer);
            }

            // item tag
            try {
                File file = new File(dir, "result_item_tag_blacklist.json");
                writer = new FileWriter(file);
                GSON.toJson(getDefaultResultItemTagBlacklist(), writer);
                writer.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while generating default result item tag blacklist.", e);
            } finally {
                IOUtils.closeQuietly(writer);
            }
        }

        if (!dir.mkdirs() && dir.isDirectory()) {
            this.loadFiles(dir);
        }

        stopwatch.stop();
        CuboidMod.LOGGER.info("Loaded Molecular Recycler blacklists in {} ms [{} recipes, {} items, {} item tags]", stopwatch.elapsed(TimeUnit.MILLISECONDS), RECIPES.size(), ITEMS.size(), ITEM_TAGS.size());
    }

    private void loadFiles(File dir) {
        File[] files = dir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));
        if (files == null)
            return;

        if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Loading Molecular Recycler Blacklist: " + files.length + " files found");
        for (File file : files) {
            if (file.getName().startsWith("recipe_blacklist.json"))
                RECIPES.addAll(loadBlacklist(file));
            else if (file.getName().startsWith("item_blacklist.json"))
                ITEMS.addAll(loadBlacklist(file));
            else if (file.getName().startsWith("item_tag_blacklist.json"))
                ITEM_TAGS.addAll(loadBlacklist(file));
            else if (file.getName().startsWith("result_item_blacklist.json"))
                RESULT_ITEMS.addAll(loadBlacklist(file));
            else if (file.getName().startsWith("result_item_tag_blacklist.json"))
                RESULT_ITEM_TAGS.addAll(loadBlacklist(file));
        }
    }

    private Collection<String> loadBlacklist(File file) {
        JsonArray jsonArray;
        FileReader reader = null;
        ArrayList<String> result = new ArrayList<>();
        String name = file.getName().replace(".json", "");

        try {
            reader = new FileReader(file);
            jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Loading Molecular Recycler Blacklist: " + name);

            int length = jsonArray.size();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    result.add(jsonArray.get(i).getAsString());
                }
            }

            reader.close();
        } catch (Exception e) {
            CuboidMod.LOGGER.error("Error loading Molecular Recycler Blacklist: " + name, e);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return result;
    }

    private ArrayList<String> getDefaultRecipeBlacklist()
    {
        ArrayList<String> result = new ArrayList<>();

        result.add(CuboidMod.MOD_ID + ":cured_flesh_from_acv_and_rotten_flesh");

        return result;
    }

    private ArrayList<String> getDefaultItemBlacklist()
    {
        ArrayList<String> result = new ArrayList<>();

        result.add(CuboidMod.MOD_ID + ":cured_beef");
        result.add(CuboidMod.MOD_ID + ":cured_flesh");
        result.add("exnihilosequentia:hammer_stone");

        return result;
    }

    private ArrayList<String> getDefaultItemTagBlacklist()
    {
        ArrayList<String> result = new ArrayList<>();

        result.add("c:ingots");
        result.add("c:dyes");

        return result;
    }

    private ArrayList<String> getDefaultResultItemBlacklist()
    {
        ArrayList<String> result = new ArrayList<>();

        result.add("minecraft:cobblestone");
        result.add("minecraft:stone");
        result.add("minecraft:stick");
        result.add("minecraft:bamboo");
        result.add("minecraft:acacia_sapling");
        result.add("minecraft:birch_sapling");
        result.add("minecraft:dark_oak_sapling");
        result.add("minecraft:jungle_sapling");
        result.add("minecraft:oak_sapling");
        result.add("minecraft:spruce_sapling");

        return result;
    }

    private ArrayList<String> getDefaultResultItemTagBlacklist()
    {
        ArrayList<String> result = new ArrayList<>();

        result.add("c:rods/wooden");
        result.add("c:ingots/iron");
        result.add("c:storage_blocks/iron");
        result.add("c:dusts/iron");
        result.add("c:nuggets/iron");
        result.add("c:ores/iron");

        return result;
    }

    public boolean isBlacklistedRecipe(String recipeId) {
        return RECIPES.stream().anyMatch((r) -> r.equalsIgnoreCase(recipeId));
    }

    public boolean isBlacklistedItem(String itemId) {
        return ITEMS.stream().anyMatch((i) -> i.equalsIgnoreCase(itemId));
    }

    public boolean isBlacklistedTag(String tag) {
        return ITEM_TAGS.stream().anyMatch((t) -> t.equalsIgnoreCase(tag));
    }

    public boolean isBlacklistedResultItem(String itemId) {
        return RESULT_ITEMS.stream().anyMatch((i) -> i.equalsIgnoreCase(itemId));
    }

    public boolean isBlacklistedResultTag(String tag) {
        return RESULT_ITEM_TAGS.stream().anyMatch((t) -> t.equalsIgnoreCase(tag));
    }
}
