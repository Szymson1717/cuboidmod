package com.cuboiddroid.cuboidmod.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.cuboiddroid.cuboidmod.Config;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.FolderRepositorySource;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

public class RepositorySourceUtils {

    public static RepositorySource getCuboidDatapacks() {
        
        List<Path> packLocations = new ArrayList<>();
        try {
            if (Config.forcedCuboidFlat.get()) {
                Path flatWorld = Path.of(RepositorySourceUtils.class.getClassLoader().getResource("datapacks/cuboid_flat/").toURI());
                packLocations.add(flatWorld);
            }
            if (Config.spawnCuboidHouse.get()) {
                Path starterHouse = Path.of(RepositorySourceUtils.class.getClassLoader().getResource("datapacks/cuboid_house/").toURI());
                packLocations.add(starterHouse);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        RepositorySource p = new RepositorySource() {
            @Override
            public void loadPacks(Consumer<Pack> packRegistrar) {
                discoverResourcePacks(path -> {
                    Pack.ResourcesSupplier resourceSupplier = FolderRepositorySource.detectPackResources(path, false);

                    if (resourceSupplier == null)
                        return;

                    PackSource source = PackSource.create(name -> name.copy().append(" (REQUIRED)")
                            .withStyle(ChatFormatting.RED), true);

                    Pack pack = Pack.readMetaAndCreate(path.getFileName().toString(),
                            Component.literal(path.getFileName().toString()),
                            true, resourceSupplier, PackType.SERVER_DATA, Pack.Position.TOP, source);

                    if (pack != null)
                        packRegistrar.accept(pack);
                });
            }

            private void discoverResourcePacks(Consumer<Path> packCallback) {
                for (Path path : packLocations) {
                    if (Files.isDirectory(path) || Files.notExists(path)) {
                        if (Files.isRegularFile(path.resolve("pack.mcmeta"))) {
                            packCallback.accept(path);
                            continue;
                        }

                        try {
                            if (Files.notExists(path))
                                Files.createDirectories(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try (Stream<Path> fileStream = Files.list(path)) {
                            fileStream.forEach(filePath -> {
                                if (Files.isRegularFile(filePath) || (Files.isDirectory(filePath)
                                        && Files.isRegularFile(filePath.resolve("pack.mcmeta"))))
                                    packCallback.accept(filePath);
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        packCallback.accept(path);
                    }
                }
            }
        };

        return p;

    }

}
