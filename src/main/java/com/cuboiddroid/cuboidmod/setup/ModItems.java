package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.item.*;
import com.cuboiddroid.cuboidmod.modules.food.*;
import com.cuboiddroid.cuboidmod.modules.tools.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.fmllegacy.RegistryObject;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

public class ModItems {
    // Notsogudium

    public static final RegistryObject<Item> NOTSOGUDIUM_INGOT = Registration.ITEMS.register(
            "notsogudium_ingot", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTSOGUDIUM_NUGGET = Registration.ITEMS.register(
            "notsogudium_nugget", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTSOGUDIUM_PIECE = Registration.ITEMS.register(
            "notsogudium_piece", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTSOGUDIUM_CHUNK = Registration.ITEMS.register(
            "notsogudium_chunk", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTSOGUDIUM_DUST = Registration.ITEMS.register(
            "notsogudium_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTSOGUDIUM_ROD = Registration.ITEMS.register(
            "notsogudium_rod", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<SwordItem> NOTSOGUDIUM_SWORD = Registration.ITEMS.register(
            "notsogudium_sword", () -> new SwordItem(
                    ModItemTiers.NOTSOGUDIUM,
                    3,
                    -2.4F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<PickaxeItem> NOTSOGUDIUM_PICKAXE = Registration.ITEMS.register(
            "notsogudium_pickaxe", () -> new PickaxeItem(
                    ModItemTiers.NOTSOGUDIUM,
                    1,
                    -2.8F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<AxeItem> NOTSOGUDIUM_AXE = Registration.ITEMS.register(
            "notsogudium_axe", () -> new AxeItem(
                    ModItemTiers.NOTSOGUDIUM,
                    5.0F,
                    -3.2F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ShovelItem> NOTSOGUDIUM_SHOVEL = Registration.ITEMS.register(
            "notsogudium_shovel", () -> new ShovelItem(
                    ModItemTiers.NOTSOGUDIUM,
                    0.5F,
                    -3.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<HoeItem> NOTSOGUDIUM_HOE = Registration.ITEMS.register(
            "notsogudium_hoe", () -> new HoeItem(
                    ModItemTiers.NOTSOGUDIUM,
                    0,
                    -3.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTSOGUDIUM_HELMET = Registration.ITEMS.register(
            "notsogudium_helmet", () -> new ArmorItem(
                    ModArmorMaterials.NOTSOGUDIUM,
                    EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTSOGUDIUM_CHESTPLATE = Registration.ITEMS.register(
            "notsogudium_chestplate", () -> new ArmorItem(
                    ModArmorMaterials.NOTSOGUDIUM,
                    EquipmentSlot.CHEST,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTSOGUDIUM_LEGGINGS = Registration.ITEMS.register(
            "notsogudium_leggings", () -> new ArmorItem(
                    ModArmorMaterials.NOTSOGUDIUM,
                    EquipmentSlot.LEGS,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTSOGUDIUM_BOOTS = Registration.ITEMS.register(
            "notsogudium_boots", () -> new ArmorItem(
                    ModArmorMaterials.NOTSOGUDIUM,
                    EquipmentSlot.FEET,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<Item> NOTSOGUDIUM_SMASHER = Registration.ITEMS.register(
            "notsogudium_smasher", () -> new NotsogudiumSmasher());

    // Kudbebedda

    public static final RegistryObject<Item> KUDBEBEDDA_INGOT = Registration.ITEMS.register(
            "kudbebedda_ingot", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> KUDBEBEDDA_NUGGET = Registration.ITEMS.register(
            "kudbebedda_nugget", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> KUDBEBEDDA_PIECE = Registration.ITEMS.register(
            "kudbebedda_piece", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> KUDBEBEDDA_CHUNK = Registration.ITEMS.register(
            "kudbebedda_chunk", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> KUDBEBEDDA_DUST = Registration.ITEMS.register(
            "kudbebedda_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> KUDBEBEDDA_ROD = Registration.ITEMS.register(
            "kudbebedda_rod", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<SwordItem> KUDBEBEDDA_SWORD = Registration.ITEMS.register(
            "kudbebedda_sword", () -> new SwordItem(
                    ModItemTiers.KUDBEBEDDA,
                    3,
                    -2.4F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<PickaxeItem> KUDBEBEDDA_PICKAXE = Registration.ITEMS.register(
            "kudbebedda_pickaxe", () -> new PickaxeItem(
                    ModItemTiers.KUDBEBEDDA,
                    1,
                    -2.8F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<AxeItem> KUDBEBEDDA_AXE = Registration.ITEMS.register(
            "kudbebedda_axe", () -> new AxeItem(
                    ModItemTiers.KUDBEBEDDA,
                    5.0F,
                    -3.1F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ShovelItem> KUDBEBEDDA_SHOVEL = Registration.ITEMS.register(
            "kudbebedda_shovel", () -> new ShovelItem(
                    ModItemTiers.KUDBEBEDDA,
                    1.0F,
                    -3.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<HoeItem> KUDBEBEDDA_HOE = Registration.ITEMS.register(
            "kudbebedda_hoe", () -> new HoeItem(
                    ModItemTiers.KUDBEBEDDA,
                    -1,
                    -2.5F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> KUDBEBEDDA_HELMET = Registration.ITEMS.register(
            "kudbebedda_helmet", () -> new ArmorItem(
                    ModArmorMaterials.KUDBEBEDDA,
                    EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> KUDBEBEDDA_CHESTPLATE = Registration.ITEMS.register(
            "kudbebedda_chestplate", () -> new ArmorItem(
                    ModArmorMaterials.KUDBEBEDDA,
                    EquipmentSlot.CHEST,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> KUDBEBEDDA_LEGGINGS = Registration.ITEMS.register(
            "kudbebedda_leggings", () -> new ArmorItem(
                    ModArmorMaterials.KUDBEBEDDA,
                    EquipmentSlot.LEGS,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> KUDBEBEDDA_BOOTS = Registration.ITEMS.register(
            "kudbebedda_boots", () -> new ArmorItem(
                    ModArmorMaterials.KUDBEBEDDA,
                    EquipmentSlot.FEET,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<Item> KUDBEBEDDA_SMASHER = Registration.ITEMS.register(
            "kudbebedda_smasher", () -> new KudbebeddaSmasher());

    // Notarfbadium

    public static final RegistryObject<Item> NOTARFBADIUM_INGOT = Registration.ITEMS.register(
            "notarfbadium_ingot", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTARFBADIUM_NUGGET = Registration.ITEMS.register(
            "notarfbadium_nugget", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTARFBADIUM_PIECE = Registration.ITEMS.register(
            "notarfbadium_piece", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTARFBADIUM_CHUNK = Registration.ITEMS.register(
            "notarfbadium_chunk", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTARFBADIUM_DUST = Registration.ITEMS.register(
            "notarfbadium_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> NOTARFBADIUM_ROD = Registration.ITEMS.register(
            "notarfbadium_rod", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<SwordItem> NOTARFBADIUM_SWORD = Registration.ITEMS.register(
            "notarfbadium_sword", () -> new SwordItem(
                    ModItemTiers.NOTARFBADIUM,
                    3,
                    -2.4F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<PickaxeItem> NOTARFBADIUM_PICKAXE = Registration.ITEMS.register(
            "notarfbadium_pickaxe", () -> new PickaxeItem(
                    ModItemTiers.NOTARFBADIUM,
                    1,
                    -2.8F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<AxeItem> NOTARFBADIUM_AXE = Registration.ITEMS.register(
            "notarfbadium_axe", () -> new AxeItem(
                    ModItemTiers.NOTARFBADIUM,
                    5.0F,
                    -3.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ShovelItem> NOTARFBADIUM_SHOVEL = Registration.ITEMS.register(
            "notarfbadium_shovel", () -> new ShovelItem(
                    ModItemTiers.NOTARFBADIUM,
                    1.0F,
                    -3.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<HoeItem> NOTARFBADIUM_HOE = Registration.ITEMS.register(
            "notarfbadium_hoe", () -> new HoeItem(
                    ModItemTiers.NOTARFBADIUM,
                    -2,
                    -2.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTARFBADIUM_HELMET = Registration.ITEMS.register(
            "notarfbadium_helmet", () -> new ArmorItem(
                    ModArmorMaterials.NOTARFBADIUM,
                    EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTARFBADIUM_CHESTPLATE = Registration.ITEMS.register(
            "notarfbadium_chestplate", () -> new ArmorItem(
                    ModArmorMaterials.NOTARFBADIUM,
                    EquipmentSlot.CHEST,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTARFBADIUM_LEGGINGS = Registration.ITEMS.register(
            "notarfbadium_leggings", () -> new ArmorItem(
                    ModArmorMaterials.NOTARFBADIUM,
                    EquipmentSlot.LEGS,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> NOTARFBADIUM_BOOTS = Registration.ITEMS.register(
            "notarfbadium_boots", () -> new ArmorItem(
                    ModArmorMaterials.NOTARFBADIUM,
                    EquipmentSlot.FEET,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<Item> NOTARFBADIUM_SMASHER = Registration.ITEMS.register(
            "notarfbadium_smasher", () -> new NotarfbadiumSmasher());

    // Wikidium

    public static final RegistryObject<Item> WIKIDIUM_INGOT = Registration.ITEMS.register(
            "wikidium_ingot", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> WIKIDIUM_NUGGET = Registration.ITEMS.register(
            "wikidium_nugget", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> WIKIDIUM_PIECE = Registration.ITEMS.register(
            "wikidium_piece", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> WIKIDIUM_CHUNK = Registration.ITEMS.register(
            "wikidium_chunk", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> WIKIDIUM_DUST = Registration.ITEMS.register(
            "wikidium_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> WIKIDIUM_ROD = Registration.ITEMS.register(
            "wikidium_rod", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<SwordItem> WIKIDIUM_SWORD = Registration.ITEMS.register(
            "wikidium_sword", () -> new SwordItem(
                    ModItemTiers.WIKIDIUM,
                    3,
                    -2.4F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<PickaxeItem> WIKIDIUM_PICKAXE = Registration.ITEMS.register(
            "wikidium_pickaxe", () -> new PickaxeItem(
                    ModItemTiers.WIKIDIUM,
                    1,
                    -2.8F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<AxeItem> WIKIDIUM_AXE = Registration.ITEMS.register(
            "wikidium_axe", () -> new AxeItem(
                    ModItemTiers.WIKIDIUM,
                    5.0F,
                    -2.9F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ShovelItem> WIKIDIUM_SHOVEL = Registration.ITEMS.register(
            "wikidium_shovel", () -> new ShovelItem(
                    ModItemTiers.WIKIDIUM,
                    1.0F,
                    -3.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<HoeItem> WIKIDIUM_HOE = Registration.ITEMS.register(
            "wikidium_hoe", () -> new HoeItem(
                    ModItemTiers.WIKIDIUM,
                    -3,
                    -1.5F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> WIKIDIUM_HELMET = Registration.ITEMS.register(
            "wikidium_helmet", () -> new ArmorItem(
                    ModArmorMaterials.WIKIDIUM,
                    EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> WIKIDIUM_CHESTPLATE = Registration.ITEMS.register(
            "wikidium_chestplate", () -> new ArmorItem(
                    ModArmorMaterials.WIKIDIUM,
                    EquipmentSlot.CHEST,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> WIKIDIUM_LEGGINGS = Registration.ITEMS.register(
            "wikidium_leggings", () -> new ArmorItem(
                    ModArmorMaterials.WIKIDIUM,
                    EquipmentSlot.LEGS,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> WIKIDIUM_BOOTS = Registration.ITEMS.register(
            "wikidium_boots", () -> new ArmorItem(
                    ModArmorMaterials.WIKIDIUM,
                    EquipmentSlot.FEET,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<Item> WIKIDIUM_SMASHER = Registration.ITEMS.register(
            "wikidium_smasher", () -> new WikidiumSmasher());

    // Thatldu

    public static final RegistryObject<Item> THATLDU_INGOT = Registration.ITEMS.register(
            "thatldu_ingot", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> THATLDU_NUGGET = Registration.ITEMS.register(
            "thatldu_nugget", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> THATLDU_PIECE = Registration.ITEMS.register(
            "thatldu_piece", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> THATLDU_CHUNK = Registration.ITEMS.register(
            "thatldu_chunk", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> THATLDU_DUST = Registration.ITEMS.register(
            "thatldu_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> THATLDU_ROD = Registration.ITEMS.register(
            "thatldu_rod", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<SwordItem> THATLDU_SWORD = Registration.ITEMS.register(
            "thatldu_sword", () -> new SwordItem(
                    ModItemTiers.THATLDU,
                    3,
                    -2.4F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<PickaxeItem> THATLDU_PICKAXE = Registration.ITEMS.register(
            "thatldu_pickaxe", () -> new PickaxeItem(
                    ModItemTiers.THATLDU,
                    1,
                    -2.6F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<AxeItem> THATLDU_AXE = Registration.ITEMS.register(
            "thatldu_axe", () -> new AxeItem(
                    ModItemTiers.THATLDU,
                    5.5F,
                    -2.8F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ShovelItem> THATLDU_SHOVEL = Registration.ITEMS.register(
            "thatldu_shovel", () -> new ShovelItem(
                    ModItemTiers.THATLDU,
                    1.0F,
                    -3.0F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<HoeItem> THATLDU_HOE = Registration.ITEMS.register(
            "thatldu_hoe", () -> new HoeItem(
                    ModItemTiers.THATLDU,
                    -3,
                    -1.4F,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> THATLDU_HELMET = Registration.ITEMS.register(
            "thatldu_helmet", () -> new ArmorItem(
                    ModArmorMaterials.THATLDU,
                    EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> THATLDU_CHESTPLATE = Registration.ITEMS.register(
            "thatldu_chestplate", () -> new ArmorItem(
                    ModArmorMaterials.THATLDU,
                    EquipmentSlot.CHEST,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> THATLDU_LEGGINGS = Registration.ITEMS.register(
            "thatldu_leggings", () -> new ArmorItem(
                    ModArmorMaterials.THATLDU,
                    EquipmentSlot.LEGS,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<ArmorItem> THATLDU_BOOTS = Registration.ITEMS.register(
            "thatldu_boots", () -> new ArmorItem(
                    ModArmorMaterials.THATLDU,
                    EquipmentSlot.FEET,
                    new Item.Properties()
                            .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
            )
    );

    public static final RegistryObject<Item> THATLDU_SMASHER = Registration.ITEMS.register(
            "thatldu_smasher", () -> new ThatlduSmasher());


    // Smoosher
    public static final RegistryObject<Item> SMOOSHER = Registration.ITEMS.register(
            "smoosher", () -> new Smoosher());

    // Silica Dust
    public static final RegistryObject<Item> SILICA_DUST = Registration.ITEMS.register(
            "silica_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> ZINC_DUST = Registration.ITEMS.register(
            "zinc_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<Item> COBALT_DUST = Registration.ITEMS.register(
            "cobalt_dust", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    // Carbon Deposit
    public static final RegistryObject<Item> CARBON_DEPOSIT = Registration.ITEMS.register(
            "carbon_deposit", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    // Carbon Nanotube
    public static final RegistryObject<Item> CARBON_NANOTUBE = Registration.ITEMS.register(
            "carbon_nanotube", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    // Stick Bundle
    public static final RegistryObject<Item> STICK_BUNDLE = Registration.ITEMS.register(
            "stick_bundle", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    // Protein Paste
    public static final RegistryObject<Item> PROTEIN_PASTE = Registration.ITEMS.register(
            "protein_paste", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).meat().build())));

    // Protein Fiber
    public static final RegistryObject<Item> PROTEIN_FIBER = Registration.ITEMS.register(
            "protein_fiber", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    // Cellulose
    public static final RegistryObject<Item> CELLULOSE = Registration.ITEMS.register(
            "cellulose", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    // FOOD ITEMS
    public static final RegistryObject<Item> NOTSOGUDIUM_BOWL = Registration.ITEMS.register(
            "notsogudium_bowl", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));

    public static final RegistryObject<ArachnuggetItem> ARACHNUGGET = Registration.ITEMS.register(
            ArachnuggetItem.ID_STRING, ArachnuggetItem::new);

    public static final RegistryObject<BrothItem> BROTH = Registration.ITEMS.register(
            BrothItem.ID_STRING, BrothItem::new);

    public static final RegistryObject<CookedKebabItem> KEBAB_COOKED = Registration.ITEMS.register(
            CookedKebabItem.ID_STRING, CookedKebabItem::new);

    public static final RegistryObject<GruelItem> GRUEL = Registration.ITEMS.register(
            GruelItem.ID_STRING, GruelItem::new);

    public static final RegistryObject<ProteinBarItem> PROTEIN_BAR = Registration.ITEMS.register(
            ProteinBarItem.ID_STRING, ProteinBarItem::new);

    public static final RegistryObject<RationPackItem> RATION_PACK = Registration.ITEMS.register(
            RationPackItem.ID_STRING, RationPackItem::new);

    public static final RegistryObject<RawKebabItem> KEBAB_RAW = Registration.ITEMS.register(
            RawKebabItem.ID_STRING, RawKebabItem::new);

    public static final RegistryObject<RottenAppleItem> ROTTEN_APPLE = Registration.ITEMS.register(
            RottenAppleItem.ID_STRING, RottenAppleItem::new);

    public static final RegistryObject<AppleCiderVinegarItem> APPLE_CIDER_VINEGAR = Registration.ITEMS.register(
            AppleCiderVinegarItem.ID_STRING, AppleCiderVinegarItem::new);

    public static final RegistryObject<CuredBeefItem> CURED_BEEF = Registration.ITEMS.register(
            CuredBeefItem.ID_STRING, CuredBeefItem::new);

    public static final RegistryObject<CuredFleshItem> CURED_FLESH = Registration.ITEMS.register(
            CuredFleshItem.ID_STRING, CuredFleshItem::new);

    public static final RegistryObject<BeefBiltongItem> BEEF_BILTONG = Registration.ITEMS.register(
            BeefBiltongItem.ID_STRING, BeefBiltongItem::new);

    public static final RegistryObject<ZombieBiltongItem> ZOMBIE_BILTONG = Registration.ITEMS.register(
            ZombieBiltongItem.ID_STRING, ZombieBiltongItem::new);

    public static final RegistryObject<HardfiskurItem> HARDFISKUR = Registration.ITEMS.register(
            HardfiskurItem.ID_STRING, HardfiskurItem::new);

    public static final RegistryObject<Item> SALT = Registration.ITEMS.register(
            "salt", () -> new Item(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)));


    // Quantum Singularities
    public static final RegistryObject<Item> NOTSOGUDIUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "notsogudium_quantum_singularity", NotsogudiumQuantumSingularityItem::new);

    public static final RegistryObject<Item> KUDBEBEDDA_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "kudbebedda_quantum_singularity", KudbebeddaQuantumSingularityItem::new);

    public static final RegistryObject<Item> NOTARFBADIUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "notarfbadium_quantum_singularity", NotarfbadiumQuantumSingularityItem::new);

    public static final RegistryObject<Item> WIKIDIUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "wikidium_quantum_singularity", WikidiumQuantumSingularityItem::new);

    public static final RegistryObject<Item> THATLDU_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "thatldu_quantum_singularity", ThatlduQuantumSingularityItem::new);


    public static final RegistryObject<Item> ANDESITE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "andesite_quantum_singularity", AndesiteQuantumSingularityItem::new);

    public static final RegistryObject<Item> BASALT_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "basalt_quantum_singularity", BasaltQuantumSingularityItem::new);

    public static final RegistryObject<Item> BLACKSTONE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "blackstone_quantum_singularity", BlackstoneQuantumSingularityItem::new);

    public static final RegistryObject<Item> CARBON_NANOTUBE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "carbon_nanotube_quantum_singularity", CarbonNanotubeQuantumSingularityItem::new);

    public static final RegistryObject<Item> CELLULOSE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "cellulose_quantum_singularity", CelluloseQuantumSingularityItem::new);

    public static final RegistryObject<Item> CLAY_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "clay_quantum_singularity", ClayQuantumSingularityItem::new);

    public static final RegistryObject<Item> COAL_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "coal_quantum_singularity", CoalQuantumSingularityItem::new);

    public static final RegistryObject<Item> COBBLESTONE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "cobblestone_quantum_singularity", CobblestoneQuantumSingularityItem::new);

    public static final RegistryObject<Item> DIORITE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "diorite_quantum_singularity", DioriteQuantumSingularityItem::new);

    public static final RegistryObject<Item> DIRT_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "dirt_quantum_singularity", DirtQuantumSingularityItem::new);

    public static final RegistryObject<Item> DUST_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "dust_quantum_singularity", DustQuantumSingularityItem::new);

    public static final RegistryObject<Item> GLOWSTONE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "glowstone_quantum_singularity", GlowstoneQuantumSingularityItem::new);

    public static final RegistryObject<Item> GRANITE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "granite_quantum_singularity", GraniteQuantumSingularityItem::new);

    public static final RegistryObject<Item> GRAVEL_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "gravel_quantum_singularity", GravelQuantumSingularityItem::new);

    public static final RegistryObject<Item> LAPIS_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "lapis_quantum_singularity", LapisQuantumSingularityItem::new);

    public static final RegistryObject<Item> REDSTONE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "redstone_quantum_singularity", RedstoneQuantumSingularityItem::new);

    public static final RegistryObject<Item> SAND_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "sand_quantum_singularity", SandQuantumSingularityItem::new);

    public static final RegistryObject<Item> SILICA_DUST_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "silica_dust_quantum_singularity", SilicaDustQuantumSingularityItem::new);

    public static final RegistryObject<Item> OAK_LOG_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "oak_log_quantum_singularity", LogOakQuantumSingularityItem::new);

    public static final RegistryObject<Item> ACACIA_LOG_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "acacia_log_quantum_singularity", LogAcaciaQuantumSingularityItem::new);

    public static final RegistryObject<Item> BIRCH_LOG_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "birch_log_quantum_singularity", LogBirchQuantumSingularityItem::new);

    public static final RegistryObject<Item> DARK_OAK_LOG_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "dark_oak_log_quantum_singularity", LogDarkOakQuantumSingularityItem::new);

    public static final RegistryObject<Item> JUNGLE_LOG_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "jungle_log_quantum_singularity", LogJungleQuantumSingularityItem::new);

    public static final RegistryObject<Item> SPRUCE_LOG_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "spruce_log_quantum_singularity", LogSpruceQuantumSingularityItem::new);

    public static final RegistryObject<Item> ENDSTONE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "endstone_quantum_singularity", EndStoneQuantumSingularityItem::new);

    public static final RegistryObject<Item> NETHERRACK_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "netherrack_quantum_singularity", NetherrackQuantumSingularityItem::new);

    public static final RegistryObject<Item> ZINC_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "zinc_quantum_singularity", ZincQuantumSingularityItem::new);

    public static final RegistryObject<Item> TIN_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "tin_quantum_singularity", TinQuantumSingularityItem::new);

    public static final RegistryObject<Item> COPPER_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "copper_quantum_singularity", CopperQuantumSingularityItem::new);

    public static final RegistryObject<Item> NICKEL_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "nickel_quantum_singularity", NickelQuantumSingularityItem::new);

    public static final RegistryObject<Item> SILVER_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "silver_quantum_singularity", SilverQuantumSingularityItem::new);

    public static final RegistryObject<Item> ALUMINIUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "aluminium_quantum_singularity", AluminiumQuantumSingularityItem::new);

    public static final RegistryObject<Item> IRON_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "iron_quantum_singularity", IronQuantumSingularityItem::new);

    public static final RegistryObject<Item> GOLD_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "gold_quantum_singularity", GoldQuantumSingularityItem::new);

    public static final RegistryObject<Item> LEAD_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "lead_quantum_singularity", LeadQuantumSingularityItem::new);

    public static final RegistryObject<Item> URANIUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "uranium_quantum_singularity", UraniumQuantumSingularityItem::new);

    public static final RegistryObject<Item> URANINITE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "uraninite_quantum_singularity", UraniniteQuantumSingularityItem::new);

    public static final RegistryObject<Item> OSMIUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "osmium_quantum_singularity", OsmiumQuantumSingularityItem::new);

    public static final RegistryObject<Item> COBALT_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "cobalt_quantum_singularity", CobaltQuantumSingularityItem::new);

    public static final RegistryObject<Item> DIAMOND_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "diamond_quantum_singularity", DiamondQuantumSingularityItem::new);

    public static final RegistryObject<Item> EMERALD_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "emerald_quantum_singularity", EmeraldQuantumSingularityItem::new);

    public static final RegistryObject<Item> STEEL_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "steel_quantum_singularity", SteelQuantumSingularityItem::new);

    public static final RegistryObject<Item> BRONZE_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "bronze_quantum_singularity", BronzeQuantumSingularityItem::new);

    public static final RegistryObject<Item> ELECTRUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "electrum_quantum_singularity", ElectrumQuantumSingularityItem::new);

    public static final RegistryObject<Item> INVAR_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "invar_quantum_singularity", InvarQuantumSingularityItem::new);

    public static final RegistryObject<Item> PLATINUM_QUANTUM_SINGULARITY = Registration.ITEMS.register(
            "platinum_quantum_singularity", PlatinumQuantumSingularityItem::new);

    // this register() is only used to load the class so that the deferred register stuff works
    static void register() {}
}
