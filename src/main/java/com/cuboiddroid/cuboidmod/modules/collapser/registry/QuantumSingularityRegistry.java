package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class QuantumSingularityRegistry {
    private static final boolean VERBOSE_LOGGING = Config.verboseLogging.get();

    private static final QuantumSingularityRegistry INSTANCE = new QuantumSingularityRegistry();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    private final Map<ResourceLocation, QuantumSingularity> singularities = new LinkedHashMap<>();

    public void registerSingularity(TempQuantumSingularity tempSingularity)
    {
        QuantumSingularity singularity = getSingularityById(tempSingularity.id);
    
        singularity.setName(tempSingularity.name);
        singularity.setColors(tempSingularity.colors);
    }

    public List<QuantumSingularity> getSingularities() {
        return Lists.newArrayList(this.singularities.values());
    }

    public QuantumSingularity getSingularityById(ResourceLocation id) {

        QuantumSingularity qs;

        if (!this.singularities.containsKey(id)) {
            qs = new QuantumSingularity(id, String.format(CuboidMod.MOD_ID + ".quantum_singularity.missing", id.toString()),
                new int[] { 0xFF0000, 0xDD0000 });
            this.singularities.put(id, qs);
        } else qs = this.singularities.get(id);
        

        // Optional<QuantumSingularity> q = defaults().stream().filter(singularity -> {
        //     return singularity.getId().toString().equalsIgnoreCase(id.toString());
        // }).findFirst();

        // // not found - check defaults
        // if (q.isPresent()) {
        //     // found in defaults, use it, and add to in-memory & warn
        //     CuboidMod.LOGGER.warn("JSON config file missing for Quantum Singularity: " + id.getPath());
        //     this.singularities.put(q.get().getId(), q.get());
        //     return q.get();
        // }

        return qs;
    }

    public static QuantumSingularityRegistry getInstance() {
        return INSTANCE;
    }

    public void loadSingularities() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        File dir = FMLPaths.CONFIGDIR.get().resolve(CuboidMod.MOD_ID + "/quantum_singularities/").toFile();

        if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Loading quantum singularities");

        if (!dir.exists() && dir.mkdirs()) {
            if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Creating default singularity configs");
            for (QuantumSingularity singularity : defaults()) {
                JsonObject json = QuantumSingularityUtils.writeToJson(singularity);
                FileWriter writer = null;

                try {
                    File file = new File(dir, singularity.getId().getPath() + ".json");
                    writer = new FileWriter(file);
                    GSON.toJson(json, writer);
                    writer.close();
                } catch (Exception e) {
                    CuboidMod.LOGGER.error("An error occurred while generating default singularities", e);
                } finally {
                    IOUtils.closeQuietly(writer);
                }
            }
        }

        if (!dir.mkdirs() && dir.isDirectory()) {
            this.loadFiles(dir);
        }

        stopwatch.stop();
        CuboidMod.LOGGER.info("Loaded {} Quantum Singularity type(s) in {} ms", this.singularities.size(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private void loadFiles(File dir) {
        File[] files = dir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));
        if (files == null)
            return;

        for (File file : files) {
            JsonObject json;
            FileReader reader = null;
            TempQuantumSingularity singularity = null;

            try {
                reader = new FileReader(file);
                json = JsonParser.parseReader(reader).getAsJsonObject();
                String name = file.getName().replace(".json", "");
                if (VERBOSE_LOGGING) CuboidMod.LOGGER.info("Loading Quantum Singularity: " + name);
                singularity = QuantumSingularityUtils.loadFromJson(new ResourceLocation(CuboidMod.MOD_ID, name), json);

                reader.close();
            } catch (Exception e) {
                CuboidMod.LOGGER.error("An error occurred while loading Quantum Singularities", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }

            if (singularity != null) {
                registerSingularity(singularity);
            }
        }
    }

    private static List<QuantumSingularity> defaults() {
        return Lists.newArrayList(
                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "andesite"),
                        CuboidMod.MOD_ID + ".quantum_singularity.andesite",
                        new int[]{0xA8AA9A, 0x7F7F7F}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "basalt"),
                        CuboidMod.MOD_ID + ".quantum_singularity.basalt",
                        new int[]{0x5C5C5C, 0x353641}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "blackstone"),
                        CuboidMod.MOD_ID + ".quantum_singularity.blackstone",
                        new int[]{0x3C3947, 0x160F10}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "carbon_nanotube"),
                        CuboidMod.MOD_ID + ".quantum_singularity.carbon_nanotube",
                        new int[]{0x877787, 0x554A55}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "cellulose"),
                        CuboidMod.MOD_ID + ".quantum_singularity.cellulose",
                        new int[]{0xC8C0A7, 0xF3E8C8}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "clay"),
                        CuboidMod.MOD_ID + ".quantum_singularity.clay",
                        new int[] { 0xAFB9D6, 0x9499A4 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "coal"),
                        CuboidMod.MOD_ID + ".quantum_singularity.coal",
                        new int[] { 0x292828, 0x050505 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "cobblestone"),
                        CuboidMod.MOD_ID + ".quantum_singularity.cobblestone",
                        new int[] { 0xB5B5B5, 0x525252 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "diorite"),
                        CuboidMod.MOD_ID + ".quantum_singularity.diorite",
                        new int[]{0xE9E9E9, 0x8B8B8B}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "dirt"),
                        CuboidMod.MOD_ID + ".quantum_singularity.dirt",
                        new int[] { 0xB9855C, 0x593D29 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "dust"),
                        CuboidMod.MOD_ID + ".quantum_singularity.dust",
                        new int[] { 0xF3E8C8, 0xC8C0A7 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "glowstone"),
                        CuboidMod.MOD_ID + ".quantum_singularity.glowstone",
                        new int[] { 0xFBDA74, 0xCC8654 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "granite"),
                        CuboidMod.MOD_ID + ".quantum_singularity.granite",
                        new int[] { 0xC99781, 0x7F5646 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "gravel"),
                        CuboidMod.MOD_ID + ".quantum_singularity.gravel",
                        new int[] { 0xa5a5a5, 0x797979 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "kudbebedda"),
                        CuboidMod.MOD_ID + ".quantum_singularity.kudbebedda",
                        new int[] { 0x8cd4c0, 0x72b09f }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "lapis"),
                        CuboidMod.MOD_ID + ".quantum_singularity.lapis",
                        new int[] { 0x20509C, 0x1B3588 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "acacia_log"),
                        CuboidMod.MOD_ID + ".quantum_singularity.acacia_log",
                        new int[] { 0xC26D3F, 0x99502B }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "birch_log"),
                        CuboidMod.MOD_ID + ".quantum_singularity.birch_log",
                        new int[] { 0xD7CB8D, 0xA59467 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "dark_oak_log"),
                        CuboidMod.MOD_ID + ".quantum_singularity.dark_oak_log",
                        new int[] { 0x53381A, 0x301E0E }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "jungle_log"),
                        CuboidMod.MOD_ID + ".quantum_singularity.jungle_log",
                        new int[] { 0xBF8E6B, 0x976A44 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "oak_log"),
                        CuboidMod.MOD_ID + ".quantum_singularity.oak_log",
                        new int[] { 0xC29D62, 0x967441 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "spruce_log"),
                        CuboidMod.MOD_ID + ".quantum_singularity.spruce_log",
                        new int[] { 0x886539, 0x614B2E }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "notarfbadium"),
                        CuboidMod.MOD_ID + ".quantum_singularity.notarfbadium",
                        new int[] { 0xa1d6bf, 0x79a391 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "notsogudium"),
                        CuboidMod.MOD_ID + ".quantum_singularity.notsogudium",
                        new int[] { 0x9de0d9, 0x7db5af }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "redstone"),
                        CuboidMod.MOD_ID + ".quantum_singularity.redstone",
                        new int[] { 0xE62008, 0x730C00 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "sand"),
                        CuboidMod.MOD_ID + ".quantum_singularity.sand",
                        new int[] { 0xE3DBB0, 0xD1BA8A }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "silica_dust"),
                        CuboidMod.MOD_ID + ".quantum_singularity.silica_dust",
                        new int[]{0xC8C0A7, 0xF3E8C8}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "thatldu"),
                        CuboidMod.MOD_ID + ".quantum_singularity.thatldu",
                        new int[] { 0x50a142, 0x6bdb58 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "wikidium"),
                        CuboidMod.MOD_ID + ".quantum_singularity.wikidium",
                        new int[] { 0x9ed9ac, 0x67b579 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "endstone"),
                        CuboidMod.MOD_ID + ".quantum_singularity.endstone",
                        new int[] { 0xCDC68B, 0xEEF6B4 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "netherrack"),
                        CuboidMod.MOD_ID + ".quantum_singularity.netherrack",
                        new int[] { 0x501B1B, 0x854242 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "zinc"),
                        CuboidMod.MOD_ID + ".quantum_singularity.zinc",
                        new int[] { 0xB5D1BA, 0x6F7262 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "tin"),
                        CuboidMod.MOD_ID + ".quantum_singularity.tin",
                        new int[] { 0xA1C6C2, 0x517C88 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "copper"),
                        CuboidMod.MOD_ID + ".quantum_singularity.copper",
                        new int[] { 0xF4AD5B, 0xB44816 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "nickel"),
                        CuboidMod.MOD_ID + ".quantum_singularity.nickel",
                        new int[] { 0xE7D498, 0x997857 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "silver"),
                        CuboidMod.MOD_ID + ".quantum_singularity.silver",
                        new int[] { 0xCEDCE2, 0x8593A2 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "aluminium"),
                        CuboidMod.MOD_ID + ".quantum_singularity.aluminium",
                        new int[] { 0xE0F5F3, 0xA1B0AF }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "iron"),
                        CuboidMod.MOD_ID + ".quantum_singularity.iron",
                        new int[] { 0xECECEC, 0xB1B0B0 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "gold"),
                        CuboidMod.MOD_ID + ".quantum_singularity.gold",
                        new int[] { 0xFEFFBD, 0xF9BD23 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "lead"),
                        CuboidMod.MOD_ID + ".quantum_singularity.lead",
                        new int[] { 0x7D97A6, 0x393C61 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "uranium"),
                        CuboidMod.MOD_ID + ".quantum_singularity.uranium",
                        new int[] { 0x3C853C, 0x285028 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "uraninite"),
                        CuboidMod.MOD_ID + ".quantum_singularity.uraninite",
                        new int[] { 0x4CD54C, 0x48A048 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "osmium"),
                        CuboidMod.MOD_ID + ".quantum_singularity.osmium",
                        new int[] { 0xCAE4E0, 0x9FB1C2 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "cobalt"),
                        CuboidMod.MOD_ID + ".quantum_singularity.cobalt",
                        new int[] { 0x59A6EF, 0x0753B8 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "diamond"),
                        CuboidMod.MOD_ID + ".quantum_singularity.diamond",
                        new int[] { 0xb9f4fa, 0x39a7bd }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "emerald"),
                        CuboidMod.MOD_ID + ".quantum_singularity.emerald",
                        new int[] { 0x4bf253, 0x04c70d }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "steel"),
                        CuboidMod.MOD_ID + ".quantum_singularity.steel",
                        new int[] { 0xababab, 0x878a87 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "bronze"),
                        CuboidMod.MOD_ID + ".quantum_singularity.bronze",
                        new int[] { 0xe0b43a, 0xa37a07 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "electrum"),
                        CuboidMod.MOD_ID + ".quantum_singularity.electrum",
                        new int[] { 0xe6da6e, 0xf0d046 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "invar"),
                        CuboidMod.MOD_ID + ".quantum_singularity.invar",
                        new int[] { 0xa6b6ba, 0x778487 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "platinum"),
                        CuboidMod.MOD_ID + ".quantum_singularity.platinum",
                        new int[] { 0x90aba6, 0xccf0ea })
        );
    }
}