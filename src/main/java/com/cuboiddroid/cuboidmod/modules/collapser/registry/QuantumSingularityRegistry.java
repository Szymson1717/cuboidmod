package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.util.ResourceLocation;
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

    public void registerSingularity(QuantumSingularity singularity)
    {
        singularities.put(singularity.getId(), singularity);
    }

    public List<QuantumSingularity> getSingularities() {
        return Lists.newArrayList(this.singularities.values());
    }

    public QuantumSingularity getSingularityById(ResourceLocation id) {
        QuantumSingularity qs = this.singularities.get(id);
        if (qs != null)
            return qs;

        // not found - check defaults
        for (QuantumSingularity q: defaults()) {
            if (q.getId().toString().equalsIgnoreCase(id.toString())) {
                // found in defaults, use it, and add to in-memory & warn
                CuboidMod.LOGGER.warn("JSON config file missing for Quantum Singularity: " + id.getPath());
                this.singularities.put(q.getId(), q);
                return q;
            }
        }

        return null;
    }

    public static QuantumSingularityRegistry getInstance() {
        return INSTANCE;
    }

    public void loadSingularities() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        File dir = FMLPaths.CONFIGDIR.get().resolve("cuboidmod/quantum_singularities/").toFile();

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
            QuantumSingularity singularity = null;

            try {
                JsonParser parser = new JsonParser();
                reader = new FileReader(file);
                json = parser.parse(reader).getAsJsonObject();
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
                        "cuboidmod.quantum_singularity.andesite",
                        new int[]{0xA8AA9A, 0x7F7F7F}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "basalt"),
                        "cuboidmod.quantum_singularity.basalt",
                        new int[]{0x5C5C5C, 0x353641}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "blackstone"),
                        "cuboidmod.quantum_singularity.blackstone",
                        new int[]{0x3C3947, 0x160F10}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "carbon_nanotube"),
                        "cuboidmod.quantum_singularity.carbon_nanotube",
                        new int[]{0x877787, 0x554A55}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "cellulose"),
                        "cuboidmod.quantum_singularity.cellulose",
                        new int[]{0xC8C0A7, 0xF3E8C8}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "clay"),
                        "cuboidmod.quantum_singularity.clay",
                        new int[] { 0xAFB9D6, 0x9499A4 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "coal"),
                        "cuboidmod.quantum_singularity.coal",
                        new int[] { 0x292828, 0x050505 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "cobblestone"),
                        "cuboidmod.quantum_singularity.cobblestone",
                        new int[] { 0xB5B5B5, 0x525252 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "diorite"),
                        "cuboidmod.quantum_singularity.diorite",
                        new int[]{0xE9E9E9, 0x8B8B8B}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "dirt"),
                        "cuboidmod.quantum_singularity.dirt",
                        new int[] { 0xB9855C, 0x593D29 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "dust"),
                        "cuboidmod.quantum_singularity.dust",
                        new int[] { 0xF3E8C8, 0xC8C0A7 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "glowstone"),
                        "cuboidmod.quantum_singularity.glowstone",
                        new int[] { 0xFBDA74, 0xCC8654 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "granite"),
                        "cuboidmod.quantum_singularity.granite",
                        new int[] { 0xC99781, 0x7F5646 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "gravel"),
                        "cuboidmod.quantum_singularity.gravel",
                        new int[] { 0xa5a5a5, 0x797979 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "kudbebedda"),
                        "cuboidmod.quantum_singularity.kudbebedda",
                        new int[] { 0x8cd4c0, 0x72b09f }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "lapis"),
                        "cuboidmod.quantum_singularity.lapis",
                        new int[] { 0x20509C, 0x1B3588 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "acacia_log"),
                        "cuboidmod.quantum_singularity.acacia_log",
                        new int[] { 0xC26D3F, 0x99502B }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "birch_log"),
                        "cuboidmod.quantum_singularity.birch_log",
                        new int[] { 0xD7CB8D, 0xA59467 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "dark_oak_log"),
                        "cuboidmod.quantum_singularity.dark_oak_log",
                        new int[] { 0x53381A, 0x301E0E }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "jungle_log"),
                        "cuboidmod.quantum_singularity.jungle_log",
                        new int[] { 0xBF8E6B, 0x976A44 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "oak_log"),
                        "cuboidmod.quantum_singularity.oak_log",
                        new int[] { 0xC29D62, 0x967441 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "spruce_log"),
                        "cuboidmod.quantum_singularity.spruce_log",
                        new int[] { 0x886539, 0x614B2E }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "notarfbadium"),
                        "cuboidmod.quantum_singularity.notarfbadium",
                        new int[] { 0xa1d6bf, 0x79a391 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "notsogudium"),
                        "cuboidmod.quantum_singularity.notsogudium",
                        new int[] { 0x9de0d9, 0x7db5af }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "redstone"),
                        "cuboidmod.quantum_singularity.redstone",
                        new int[] { 0xE62008, 0x730C00 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "sand"),
                        "cuboidmod.quantum_singularity.sand",
                        new int[] { 0xE3DBB0, 0xD1BA8A }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "silica_dust"),
                        "cuboidmod.quantum_singularity.silica_dust",
                        new int[]{0xC8C0A7, 0xF3E8C8}),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "thatldu"),
                        "cuboidmod.quantum_singularity.thatldu",
                        new int[] { 0x50a142, 0x6bdb58 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "wikidium"),
                        "cuboidmod.quantum_singularity.wikidium",
                        new int[] { 0x9ed9ac, 0x67b579 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "endstone"),
                        "cuboidmod.quantum_singularity.endstone",
                        new int[] { 0xCDC68B, 0xEEF6B4 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "netherrack"),
                        "cuboidmod.quantum_singularity.netherrack",
                        new int[] { 0x501B1B, 0x854242 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "zinc"),
                        "cuboidmod.quantum_singularity.zinc",
                        new int[] { 0xB5D1BA, 0x6F7262 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "tin"),
                        "cuboidmod.quantum_singularity.tin",
                        new int[] { 0xA1C6C2, 0x517C88 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "copper"),
                        "cuboidmod.quantum_singularity.copper",
                        new int[] { 0xF4AD5B, 0xB44816 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "nickel"),
                        "cuboidmod.quantum_singularity.nickel",
                        new int[] { 0xE7D498, 0x997857 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "silver"),
                        "cuboidmod.quantum_singularity.silver",
                        new int[] { 0xCEDCE2, 0x8593A2 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "aluminium"),
                        "cuboidmod.quantum_singularity.aluminium",
                        new int[] { 0xE0F5F3, 0xA1B0AF }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "iron"),
                        "cuboidmod.quantum_singularity.iron",
                        new int[] { 0xECECEC, 0xB1B0B0 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "gold"),
                        "cuboidmod.quantum_singularity.gold",
                        new int[] { 0xFEFFBD, 0xF9BD23 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "lead"),
                        "cuboidmod.quantum_singularity.lead",
                        new int[] { 0x7D97A6, 0x393C61 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "uranium"),
                        "cuboidmod.quantum_singularity.uranium",
                        new int[] { 0x3C853C, 0x285028 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "uraninite"),
                        "cuboidmod.quantum_singularity.uraninite",
                        new int[] { 0x4CD54C, 0x48A048 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "osmium"),
                        "cuboidmod.quantum_singularity.osmium",
                        new int[] { 0xCAE4E0, 0x9FB1C2 }),

                new QuantumSingularity(
                        new ResourceLocation(CuboidMod.MOD_ID, "cobalt"),
                        "cuboidmod.quantum_singularity.cobalt",
                        new int[] { 0x59A6EF, 0x0753B8 })
        );
    }
}