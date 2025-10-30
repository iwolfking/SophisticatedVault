package xyz.iwolfking.sophisticatedvault.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.p3pp3rf1y.sophisticatedcore.crafting.ShapeBasedRecipeBuilder;
import net.p3pp3rf1y.sophisticatedcore.crafting.ShapelessBasedRecipeBuilder;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeNextTierRecipe;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;
import net.p3pp3rf1y.sophisticatedstorage.SophisticatedStorage;
import net.p3pp3rf1y.sophisticatedstorage.crafting.ShulkerBoxFromChestRecipe;
import net.p3pp3rf1y.sophisticatedstorage.crafting.StorageTierUpgradeRecipe;
import net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedstorage.init.ModItems;

import java.util.function.Consumer;

public class StorageRecipeProviderReplacement extends RecipeProvider {
    private static final String HAS_UPGRADE_BASE_CRITERION_NAME = "has_upgrade_base";
    private static final String HAS_REDSTONE_TORCH_CRITERION_NAME = "has_redstone_torch";
    private static final String HAS_SMELTING_UPGRADE = "has_smelting_upgrade";

    public StorageRecipeProviderReplacement(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        addBarrelRecipes(consumer);
        addLimitedBarrelRecipes(consumer);
        addChestRecipes(consumer);
        addShulkerBoxRecipes(consumer);
        addControllerRelatedRecipes(consumer);
        addUpgradeRecipes(consumer);
        addTierUpgradeItemRecipes(consumer);

        ShapelessBasedRecipeBuilder.shapeless(ModItems.PACKING_TAPE.get())
                .requires(Tags.Items.SLIMEBALLS)
                .requires(iskallia.vault.init.ModItems.MAGIC_SILK)
                .unlockedBy("has_slime", has(Tags.Items.SLIMEBALLS))
                .save(consumer);
    }

    private void addLimitedBarrelRecipes(Consumer<FinishedRecipe> consumer) {
        addStorageTierUpgradeRecipes(consumer, ModBlocks.LIMITED_BARREL_1_ITEM.get(), ModBlocks.LIMITED_COPPER_BARREL_1_ITEM.get(), ModBlocks.LIMITED_IRON_BARREL_1_ITEM.get(), ModBlocks.LIMITED_GOLD_BARREL_1_ITEM.get(), ModBlocks.LIMITED_DIAMOND_BARREL_1_ITEM.get(), ModBlocks.LIMITED_NETHERITE_BARREL_1_ITEM.get());
        addStorageTierUpgradeRecipes(consumer, ModBlocks.LIMITED_BARREL_2_ITEM.get(), ModBlocks.LIMITED_COPPER_BARREL_2_ITEM.get(), ModBlocks.LIMITED_IRON_BARREL_2_ITEM.get(), ModBlocks.LIMITED_GOLD_BARREL_2_ITEM.get(), ModBlocks.LIMITED_DIAMOND_BARREL_2_ITEM.get(), ModBlocks.LIMITED_NETHERITE_BARREL_2_ITEM.get());
        addStorageTierUpgradeRecipes(consumer, ModBlocks.LIMITED_BARREL_3_ITEM.get(), ModBlocks.LIMITED_COPPER_BARREL_3_ITEM.get(), ModBlocks.LIMITED_IRON_BARREL_3_ITEM.get(), ModBlocks.LIMITED_GOLD_BARREL_3_ITEM.get(), ModBlocks.LIMITED_DIAMOND_BARREL_3_ITEM.get(), ModBlocks.LIMITED_NETHERITE_BARREL_3_ITEM.get());
        addStorageTierUpgradeRecipes(consumer, ModBlocks.LIMITED_BARREL_4_ITEM.get(), ModBlocks.LIMITED_COPPER_BARREL_4_ITEM.get(), ModBlocks.LIMITED_IRON_BARREL_4_ITEM.get(), ModBlocks.LIMITED_GOLD_BARREL_4_ITEM.get(), ModBlocks.LIMITED_DIAMOND_BARREL_4_ITEM.get(), ModBlocks.LIMITED_NETHERITE_BARREL_4_ITEM.get());
    }

    private void addStorageTierUpgradeRecipes(Consumer<FinishedRecipe> consumer, BlockItem baseTierItem, BlockItem copperTierItem, BlockItem ironTierItem, BlockItem goldTierItem, BlockItem diamondTierItem, BlockItem netheriteTierItem) {
        ShapeBasedRecipeBuilder.shaped(copperTierItem, StorageTierUpgradeRecipe.SERIALIZER)
                .pattern("ECE")
                .pattern("CSC")
                .pattern("ECE")
                .define('C', Tags.Items.INGOTS_COPPER)
                .define('E', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('S', baseTierItem)
                .unlockedBy("has_" + RegistryHelper.getRegistryName(baseTierItem).orElseThrow().getPath(), has(baseTierItem))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ironTierItem, StorageTierUpgradeRecipe.SERIALIZER)
                .pattern(" I ")
                .pattern("ISI")
                .pattern(" I ")
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('S', copperTierItem)
                .unlockedBy("has_" + RegistryHelper.getRegistryName(copperTierItem).orElseThrow().getPath(), has(copperTierItem))
                .save(consumer, SophisticatedStorage.getRL(RegistryHelper.getRegistryName(ironTierItem).orElseThrow().getPath() + "_from_" + RegistryHelper.getRegistryName(copperTierItem).orElseThrow().getPath()));

        ShapeBasedRecipeBuilder.shaped(ironTierItem, StorageTierUpgradeRecipe.SERIALIZER)
                .pattern("III")
                .pattern("ISI")
                .pattern("III")
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('S', baseTierItem)
                .unlockedBy("has_" + RegistryHelper.getRegistryName(baseTierItem).orElseThrow().getPath(), has(baseTierItem))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(goldTierItem, StorageTierUpgradeRecipe.SERIALIZER)
                .pattern("VGV")
                .pattern("GSG")
                .pattern("VGV")
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('S', ironTierItem)
                .unlockedBy("has_" + RegistryHelper.getRegistryName(ironTierItem).orElseThrow().getPath(), has(ironTierItem))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(diamondTierItem, StorageTierUpgradeRecipe.SERIALIZER)
                .pattern("DDD")
                .pattern("DSD")
                .pattern("DDD")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('S', goldTierItem)
                .unlockedBy("has_" + RegistryHelper.getRegistryName(goldTierItem).orElseThrow().getPath(), has(goldTierItem))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(netheriteTierItem, StorageTierUpgradeRecipe.SERIALIZER)
                .pattern("VDV")
                .pattern("DSD")
                .pattern("VDV")
                .define('D', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('S', diamondTierItem)
                .unlockedBy("has_" + RegistryHelper.getRegistryName(diamondTierItem).orElseThrow().getPath(), has(diamondTierItem))
                .save(consumer);

    }

    private void addControllerRelatedRecipes(Consumer<FinishedRecipe> consumer) {
        ShapeBasedRecipeBuilder.shaped(ModBlocks.CONTROLLER_ITEM.get())
                .pattern("SCS")
                .pattern("PBP")
                .pattern("SCS")
                .define('S', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('C', iskallia.vault.init.ModItems.POG)
                .define('P', iskallia.vault.init.ModItems.EXTRAORDINARY_LARIMAR)
                .define('B', ModBlocks.BASE_TIER_WOODEN_STORAGE_TAG)
                .unlockedBy("has_base_tier_wooden_storage", has(ModBlocks.BASE_TIER_WOODEN_STORAGE_TAG))
                .save(consumer);

        ShapelessBasedRecipeBuilder.shapeless(ModBlocks.STORAGE_LINK_ITEM.get(), 3)
                .requires(ModBlocks.CONTROLLER_ITEM.get())
                .requires(Tags.Items.ENDER_PEARLS)
                .unlockedBy("has_controller", has(ModBlocks.CONTROLLER_ITEM.get()))
                .save(consumer, SophisticatedStorage.getRL("storage_link_from_controller"));

        ShapeBasedRecipeBuilder.shaped(ModBlocks.STORAGE_LINK_ITEM.get())
                .pattern("EP")
                .pattern("RS")
                .define('E', Tags.Items.ENDER_PEARLS)
                .define('P', iskallia.vault.init.ModBlocks.CHROMATIC_STEEL_BLOCK)
                .define('R', Items.REPEATER)
                .define('S', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .unlockedBy("has_repeater", has(Items.REPEATER))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.STORAGE_TOOL.get())
                .pattern(" EI")
                .pattern(" SR")
                .pattern("S  ")
                .define('E', Tags.Items.ENDER_PEARLS)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('R', Items.REDSTONE_TORCH)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);
    }

    private void addShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
        ShapeBasedRecipeBuilder.shaped(ModBlocks.SHULKER_BOX_ITEM.get())
                .pattern(" S")
                .pattern("RC")
                .pattern(" S")
                .define('R', Items.REDSTONE_TORCH)
                .define('S', Items.SHULKER_SHELL)
                .define('C', Tags.Items.CHESTS)
                .unlockedBy("has_shulker_shell", has(Items.SHULKER_SHELL))
                .save(consumer);

        ShapelessBasedRecipeBuilder.shapeless(ModBlocks.SHULKER_BOX_ITEM.get())
                .requires(Items.SHULKER_BOX).requires(Items.REDSTONE_TORCH)
                .save(consumer, "shulker_box_from_vanilla_shulker_box");

        ShapeBasedRecipeBuilder.shaped(ModBlocks.SHULKER_BOX_ITEM.get(), ShulkerBoxFromChestRecipe.SERIALIZER)
                .pattern("S")
                .pattern("C")
                .pattern("S")
                .define('C', ModBlocks.CHEST_ITEM.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy("has_chest", has(ModBlocks.CHEST_ITEM.get()))
                .save(consumer, SophisticatedStorage.getRL("shulker_from_chest"));

        addStorageTierUpgradeRecipes(consumer, ModBlocks.SHULKER_BOX_ITEM.get(), ModBlocks.COPPER_SHULKER_BOX_ITEM.get(), ModBlocks.IRON_SHULKER_BOX_ITEM.get(), ModBlocks.GOLD_SHULKER_BOX_ITEM.get(), ModBlocks.DIAMOND_SHULKER_BOX_ITEM.get(), ModBlocks.NETHERITE_SHULKER_BOX_ITEM.get());

        ShapeBasedRecipeBuilder.shaped(ModBlocks.COPPER_SHULKER_BOX_ITEM.get(), ShulkerBoxFromChestRecipe.SERIALIZER)
                .pattern("S")
                .pattern("C")
                .pattern("S")
                .define('C', ModBlocks.COPPER_CHEST_ITEM.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy("has_copper_chest", has(ModBlocks.COPPER_CHEST_ITEM.get()))
                .save(consumer, SophisticatedStorage.getRL("copper_shulker_from_copper_chest"));


        ShapeBasedRecipeBuilder.shaped(ModBlocks.IRON_SHULKER_BOX_ITEM.get(), ShulkerBoxFromChestRecipe.SERIALIZER)
                .pattern("S")
                .pattern("C")
                .pattern("S")
                .define('C', ModBlocks.IRON_CHEST_ITEM.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy("has_iron_chest", has(ModBlocks.IRON_CHEST_ITEM.get()))
                .save(consumer, SophisticatedStorage.getRL("iron_shulker_from_iron_chest"));

        ShapeBasedRecipeBuilder.shaped(ModBlocks.GOLD_SHULKER_BOX_ITEM.get(), ShulkerBoxFromChestRecipe.SERIALIZER)
                .pattern("S")
                .pattern("C")
                .pattern("S")
                .define('C', ModBlocks.GOLD_CHEST_ITEM.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy("has_gold_chest", has(ModBlocks.GOLD_CHEST_ITEM.get()))
                .save(consumer, SophisticatedStorage.getRL("gold_shulker_from_gold_chest"));

        ShapeBasedRecipeBuilder.shaped(ModBlocks.DIAMOND_SHULKER_BOX_ITEM.get(), ShulkerBoxFromChestRecipe.SERIALIZER)
                .pattern("S")
                .pattern("C")
                .pattern("S")
                .define('C', ModBlocks.DIAMOND_CHEST_ITEM.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy("has_diamond_chest", has(ModBlocks.DIAMOND_CHEST_ITEM.get()))
                .save(consumer, SophisticatedStorage.getRL("diamond_shulker_from_diamond_chest"));

        ShapeBasedRecipeBuilder.shaped(ModBlocks.NETHERITE_SHULKER_BOX_ITEM.get(), ShulkerBoxFromChestRecipe.SERIALIZER)
                .pattern("S")
                .pattern("C")
                .pattern("S")
                .define('C', ModBlocks.NETHERITE_CHEST_ITEM.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy("has_netherite_chest", has(ModBlocks.NETHERITE_CHEST_ITEM.get()))
                .save(consumer, SophisticatedStorage.getRL("netherite_shulker_from_netherite_chest"));
    }

    private void addTierUpgradeItemRecipes(Consumer<FinishedRecipe> consumer) {
        ShapeBasedRecipeBuilder.shaped(ModItems.BASIC_TIER_UPGRADE.get())
                .pattern(" S ")
                .pattern("SRS")
                .pattern(" S ")
                .define('R', Items.REDSTONE_TORCH)
                .define('S', iskallia.vault.init.ModItems.DRIFTWOOD)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.BASIC_TO_COPPER_TIER_UPGRADE.get())
                .pattern("ECE")
                .pattern("CRC")
                .pattern("ECE")
                .define('R', Items.REDSTONE_TORCH)
                .define('C', Tags.Items.INGOTS_COPPER)
                .define('E', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.BASIC_TO_IRON_TIER_UPGRADE.get())
                .pattern("III")
                .pattern("IRI")
                .pattern("III")
                .define('R', Items.REDSTONE_TORCH)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.BASIC_TO_GOLD_TIER_UPGRADE.get())
                .pattern("VGV")
                .pattern("GTG")
                .pattern("VGV")
                .define('T', ModItems.BASIC_TO_IRON_TIER_UPGRADE.get())
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy("has_basic_to_iron_tier_upgrade", has(ModItems.BASIC_TO_IRON_TIER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.BASIC_TO_DIAMOND_TIER_UPGRADE.get())
                .pattern("DDD")
                .pattern("DTD")
                .pattern("DDD")
                .define('T', ModItems.BASIC_TO_GOLD_TIER_UPGRADE.get())
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .unlockedBy("has_basic_to_gold_tier_upgrade", has(ModItems.BASIC_TO_GOLD_TIER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.BASIC_TO_NETHERITE_TIER_UPGRADE.get())
                .pattern("VDV")
                .pattern("DTD")
                .pattern("VDV")
                .define('T', ModItems.BASIC_TO_DIAMOND_TIER_UPGRADE.get())
                .define('D', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy("has_basic_to_diamond_tier_upgrade", has(ModItems.BASIC_TO_DIAMOND_TIER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.COPPER_TO_IRON_TIER_UPGRADE.get())
                .pattern(" I ")
                .pattern("IRI")
                .pattern(" I ")
                .define('R', Items.REDSTONE_TORCH)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.COPPER_TO_GOLD_TIER_UPGRADE.get())
                .pattern("VGV")
                .pattern("GTG")
                .pattern("VGV")
                .define('T', ModItems.COPPER_TO_IRON_TIER_UPGRADE.get())
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy("has_copper_to_iron_tier_upgrade", has(ModItems.COPPER_TO_IRON_TIER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.COPPER_TO_DIAMOND_TIER_UPGRADE.get())
                .pattern("DDD")
                .pattern("DTD")
                .pattern("DDD")
                .define('T', ModItems.COPPER_TO_GOLD_TIER_UPGRADE.get())
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .unlockedBy("has_copper_to_gold_tier_upgrade", has(ModItems.COPPER_TO_GOLD_TIER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.COPPER_TO_NETHERITE_TIER_UPGRADE.get())
                .pattern("VDV")
                .pattern("DTD")
                .pattern("VDV")
                .define('T', ModItems.COPPER_TO_DIAMOND_TIER_UPGRADE.get())
                .define('D', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy("has_copper_to_diamond_tier_upgrade", has(ModItems.COPPER_TO_DIAMOND_TIER_UPGRADE.get()))
                .save(consumer);


        ShapeBasedRecipeBuilder.shaped(ModItems.IRON_TO_GOLD_TIER_UPGRADE.get())
                .pattern("VGV")
                .pattern("GRG")
                .pattern("VGV")
                .define('R', Items.REDSTONE_TORCH)
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.IRON_TO_DIAMOND_TIER_UPGRADE.get())
                .pattern("DDD")
                .pattern("DTD")
                .pattern("DDD")
                .define('T', ModItems.IRON_TO_GOLD_TIER_UPGRADE.get())
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .unlockedBy("has_iron_to_gold_tier_upgrade", has(ModItems.IRON_TO_GOLD_TIER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.IRON_TO_NETHERITE_TIER_UPGRADE.get())
                .pattern("VDV")
                .pattern("DTD")
                .pattern("VDV")
                .define('T', ModItems.IRON_TO_DIAMOND_TIER_UPGRADE.get())
                .define('D', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy("has_iron_to_diamond_tier_upgrade", has(ModItems.IRON_TO_DIAMOND_TIER_UPGRADE.get()))
                .save(consumer);


        ShapeBasedRecipeBuilder.shaped(ModItems.GOLD_TO_DIAMOND_TIER_UPGRADE.get())
                .pattern("DDD")
                .pattern("DRD")
                .pattern("DDD")
                .define('R', Items.REDSTONE_TORCH)
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.GOLD_TO_NETHERITE_TIER_UPGRADE.get())
                .pattern("VDV")
                .pattern("DRD")
                .pattern("VDV")
                .define('R', Items.REDSTONE_TORCH)
                .define('D', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy("has_gold_to_diamond_tier_upgrade", has(ModItems.GOLD_TO_DIAMOND_TIER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.DIAMOND_TO_NETHERITE_TIER_UPGRADE.get())
                .pattern("VDV")
                .pattern("DRD")
                .pattern("VDV")
                .define('R', Items.REDSTONE_TORCH)
                .define('D', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('V', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .unlockedBy(HAS_REDSTONE_TORCH_CRITERION_NAME, has(Items.REDSTONE_TORCH))
                .save(consumer);
    }

    private void addUpgradeRecipes(Consumer<FinishedRecipe> consumer) {
        ShapeBasedRecipeBuilder.shaped(ModItems.UPGRADE_BASE.get())
                .pattern("PIP")
                .pattern("IPI")
                .pattern("PIP")
                .define('P', iskallia.vault.init.ModItems.DRIFTWOOD)
                .define('I', iskallia.vault.init.ModItems.LARIMAR_GEM)
                .unlockedBy("has_larimar", has(iskallia.vault.init.ModItems.LARIMAR_GEM))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.PICKUP_UPGRADE.get())
                .pattern(" P ")
                .pattern("LBL")
                .pattern("RRR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('L', iskallia.vault.init.ModItems.DRIFTWOOD)
                .define('P', Blocks.STICKY_PISTON)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_PICKUP_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern(" D ")
                .pattern("GPG")
                .pattern("RRR")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('P', ModItems.PICKUP_UPGRADE.get())
                .unlockedBy("has_pickup_upgrade", has(ModItems.PICKUP_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.FILTER_UPGRADE.get())
                .pattern("RSR")
                .pattern("SBS")
                .pattern("RSR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('S', Tags.Items.STRING)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_FILTER_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern("GPG")
                .pattern("RRR")
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('P', ModItems.FILTER_UPGRADE.get())
                .unlockedBy("has_filter_upgrade", has(ModItems.FILTER_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.MAGNET_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern("EIE")
                .pattern("IPI")
                .pattern("R L")
                .define('E', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('R', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('L', iskallia.vault.init.ModItems.MAGIC_SILK)
                .define('P', ModItems.PICKUP_UPGRADE.get())
                .unlockedBy("has_pickup_upgrade", has(ModItems.PICKUP_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_MAGNET_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern("EIE")
                .pattern("IPI")
                .pattern("R L")
                .define('E', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('R', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('L', iskallia.vault.init.ModItems.MAGIC_SILK)
                .define('P', ModItems.ADVANCED_PICKUP_UPGRADE.get())
                .unlockedBy("has_advanced_pickup_upgrade", has(ModItems.ADVANCED_PICKUP_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_MAGNET_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern(" D ")
                .pattern("GMG")
                .pattern("RRR")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('M', ModItems.MAGNET_UPGRADE.get())
                .unlockedBy("has_magnet_upgrade", has(ModItems.MAGNET_UPGRADE.get()))
                .save(consumer, SophisticatedStorage.getRL("advanced_magnet_upgrade_from_basic"));

        ShapeBasedRecipeBuilder.shaped(ModItems.FEEDING_UPGRADE.get())
                .pattern(" C ")
                .pattern("ABM")
                .pattern(" E ")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('C', Items.GOLDEN_CARROT)
                .define('A', Items.GOLDEN_APPLE)
                .define('M', Items.GLISTERING_MELON_SLICE)
                .define('E', iskallia.vault.init.ModBlocks.VAULT_DIAMOND_BLOCK)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.COMPACTING_UPGRADE.get())
                .pattern("IPI")
                .pattern("PBP")
                .pattern("RPR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('P', Items.PISTON)
                .define('R', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_COMPACTING_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern(" D ")
                .pattern("GCG")
                .pattern("RRR")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('C', ModItems.COMPACTING_UPGRADE.get())
                .unlockedBy("has_compacting_upgrade", has(ModItems.COMPACTING_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.VOID_UPGRADE.get())
                .pattern(" E ")
                .pattern("OBO")
                .pattern("ROR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('E', iskallia.vault.init.ModItems.ECHO_GEM)
                .define('O', iskallia.vault.init.ModItems.MAGIC_SILK)
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_VOID_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern(" D ")
                .pattern("GVG")
                .pattern("RRR")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.MAGIC_SILK)
                .define('R', iskallia.vault.init.ModItems.PERFECT_LARIMAR)
                .define('V', ModItems.VOID_UPGRADE.get())
                .unlockedBy("has_void_upgrade", has(ModItems.VOID_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.SMELTING_UPGRADE.get())
                .pattern("RIR")
                .pattern("IBI")
                .pattern("RFR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('R', iskallia.vault.init.ModItems.PERFECT_LARIMAR)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('F', Items.FURNACE)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.AUTO_SMELTING_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern("DHD")
                .pattern("RSH")
                .pattern("GHG")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.EXTRAORDINARY_LARIMAR)
                .define('R', iskallia.vault.init.ModBlocks.MAGIC_SILK_BLOCK)
                .define('H', Items.HOPPER)
                .define('S', ModItems.SMELTING_UPGRADE.get())
                .unlockedBy(HAS_SMELTING_UPGRADE, has(ModItems.SMELTING_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.CRAFTING_UPGRADE.get())
                .pattern(" T ")
                .pattern("IBI")
                .pattern(" C ")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('C', Tags.Items.CHESTS)
                .define('I', iskallia.vault.init.ModItems.BLACK_CHROMATIC_STEEL_INGOT)
                .define('T', Items.CRAFTING_TABLE)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.STONECUTTER_UPGRADE.get())
                .pattern(" S ")
                .pattern("IBI")
                .pattern(" R ")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('S', Items.STONECUTTER)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);


        ShapeBasedRecipeBuilder.shaped(ModItems.STACK_UPGRADE_TIER_1.get())
                .pattern("LLL")
                .pattern("LBL")
                .pattern("LLL")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('L', iskallia.vault.init.ModItems.DRIFTWOOD)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.STACK_UPGRADE_TIER_1_PLUS.get())
                .pattern("CCC")
                .pattern("CSC")
                .pattern("BCB")
                .define('S', ModItems.STACK_UPGRADE_TIER_1.get())
                .define('C', Tags.Items.INGOTS_COPPER)
                .define('B', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.STACK_UPGRADE_TIER_1.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.STACK_UPGRADE_TIER_2.get())
                .pattern(" I ")
                .pattern("ISI")
                .pattern(" I ")
                .define('S', ModItems.STACK_UPGRADE_TIER_1_PLUS.get())
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.STACK_UPGRADE_TIER_1_PLUS.get()))
                .save(consumer, SophisticatedStorage.getRL("stack_upgrade_tier_2_from_tier_1_plus"));

        ShapeBasedRecipeBuilder.shaped(ModItems.STACK_UPGRADE_TIER_2.get())
                .pattern("III")
                .pattern("ISI")
                .pattern("III")
                .define('S', ModItems.STACK_UPGRADE_TIER_1.get())
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.STACK_UPGRADE_TIER_1.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.STACK_UPGRADE_TIER_3.get())
                .pattern("GBG")
                .pattern("GSG")
                .pattern("BGB")
                .define('S', ModItems.STACK_UPGRADE_TIER_2.get())
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('B', iskallia.vault.init.ModItems.PERFECT_LARIMAR)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.STACK_UPGRADE_TIER_2.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.STACK_UPGRADE_TIER_4.get())
                .pattern("DDD")
                .pattern("DSD")
                .pattern("BDB")
                .define('S', ModItems.STACK_UPGRADE_TIER_3.get())
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('B', iskallia.vault.init.ModItems.EXTRAORDINARY_LARIMAR)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.STACK_UPGRADE_TIER_3.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.JUKEBOX_UPGRADE.get())
                .pattern(" J ")
                .pattern("IBI")
                .pattern(" R ")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('R', iskallia.vault.init.ModItems.MAGIC_SILK)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('J', Items.JUKEBOX)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_FEEDING_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern(" D ")
                .pattern("GVG")
                .pattern("RRR")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.PERFECT_LARIMAR)
                .define('R', iskallia.vault.init.ModBlocks.MAGIC_SILK_BLOCK)
                .define('V', ModItems.FEEDING_UPGRADE.get())
                .unlockedBy("has_feeding_upgrade", has(ModItems.FEEDING_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.SMOKING_UPGRADE.get())
                .pattern("RIR")
                .pattern("IBI")
                .pattern("RSR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('S', Items.SMOKER)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.SMOKING_UPGRADE.get())
                .pattern(" L ")
                .pattern("LSL")
                .pattern(" L ")
                .define('S', ModItems.SMELTING_UPGRADE.get())
                .define('L', iskallia.vault.init.ModItems.DRIFTWOOD)
                .unlockedBy(HAS_SMELTING_UPGRADE, has(ModItems.SMELTING_UPGRADE.get()))
                .save(consumer, SophisticatedStorage.getRL("smoking_upgrade_from_smelting_upgrade"));

        ShapeBasedRecipeBuilder.shaped(ModItems.AUTO_SMOKING_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern("DHD")
                .pattern("RSH")
                .pattern("GHG")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.EXTRAORDINARY_LARIMAR)
                .define('R', iskallia.vault.init.ModBlocks.MAGIC_SILK_BLOCK)
                .define('H', Items.HOPPER)
                .define('S', ModItems.SMOKING_UPGRADE.get())
                .unlockedBy("has_smoking_upgrade", has(ModItems.SMOKING_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.AUTO_SMOKING_UPGRADE.get())
                .pattern(" L ")
                .pattern("LSL")
                .pattern(" L ")
                .define('S', ModItems.AUTO_SMELTING_UPGRADE.get())
                .define('L', iskallia.vault.init.ModItems.DRIFTWOOD)
                .unlockedBy("has_auto_smelting_upgrade", has(ModItems.AUTO_SMELTING_UPGRADE.get()))
                .save(consumer, SophisticatedStorage.getRL("auto_smoking_upgrade_from_auto_smelting_upgrade"));

        ShapeBasedRecipeBuilder.shaped(ModItems.BLASTING_UPGRADE.get())
                .pattern("RIR")
                .pattern("IBI")
                .pattern("RFR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('R', iskallia.vault.init.ModItems.VAULT_ESSENCE)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('F', Items.BLAST_FURNACE)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.BLASTING_UPGRADE.get())
                .pattern("III")
                .pattern("ISI")
                .pattern("TTT")
                .define('S', ModItems.SMELTING_UPGRADE.get())
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('T', Items.SMOOTH_STONE)
                .unlockedBy(HAS_SMELTING_UPGRADE, has(ModItems.SMELTING_UPGRADE.get()))
                .save(consumer, SophisticatedStorage.getRL("blasting_upgrade_from_smelting_upgrade"));

        ShapeBasedRecipeBuilder.shaped(ModItems.AUTO_BLASTING_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern("DHD")
                .pattern("RSH")
                .pattern("GHG")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.EXTRAORDINARY_LARIMAR)
                .define('R', iskallia.vault.init.ModBlocks.MAGIC_SILK_BLOCK)
                .define('H', Items.HOPPER)
                .define('S', ModItems.BLASTING_UPGRADE.get())
                .unlockedBy("has_blasting_upgrade", has(ModItems.BLASTING_UPGRADE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.AUTO_BLASTING_UPGRADE.get())
                .pattern("III")
                .pattern("ISI")
                .pattern("TTT")
                .define('S', ModItems.AUTO_SMELTING_UPGRADE.get())
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('T', Items.SMOOTH_STONE)
                .unlockedBy("has_auto_smelting_upgrade", has(ModItems.AUTO_SMELTING_UPGRADE.get()))
                .save(consumer, SophisticatedStorage.getRL("auto_blasting_upgrade_from_auto_smelting_upgrade"));

        ShapeBasedRecipeBuilder.shaped(ModItems.COMPRESSION_UPGRADE.get())
                .pattern(" I ")
                .pattern("PBP")
                .pattern("RIR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_STEEL_INGOT)
                .define('P', Items.PISTON)
                .define('R', iskallia.vault.init.ModBlocks.MAGIC_SILK_BLOCK)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.HOPPER_UPGRADE.get())
                .pattern(" H ")
                .pattern("IBI")
                .pattern("RRR")
                .define('B', ModItems.UPGRADE_BASE.get())
                .define('H', Items.HOPPER)
                .define('I', iskallia.vault.init.ModItems.CHROMATIC_IRON_INGOT)
                .define('R', iskallia.vault.init.ModItems.PERFECT_LARIMAR)
                .unlockedBy(HAS_UPGRADE_BASE_CRITERION_NAME, has(ModItems.UPGRADE_BASE.get()))
                .save(consumer);

        ShapeBasedRecipeBuilder.shaped(ModItems.ADVANCED_HOPPER_UPGRADE.get(), UpgradeNextTierRecipe.SERIALIZER)
                .pattern(" D ")
                .pattern("GHG")
                .pattern("ROR")
                .define('D', iskallia.vault.init.ModItems.VAULT_DIAMOND)
                .define('G', iskallia.vault.init.ModItems.PERFECT_LARIMAR)
                .define('R', iskallia.vault.init.ModItems.MAGIC_SILK)
                .define('O', Items.DROPPER)
                .define('H', ModItems.HOPPER_UPGRADE.get())
                .unlockedBy("has_feeding_upgrade", has(ModItems.HOPPER_UPGRADE.get()))
                .save(consumer);
    }

    private void addChestRecipes(Consumer<FinishedRecipe> consumer) {
        addStorageTierUpgradeRecipes(consumer, ModBlocks.CHEST_ITEM.get(), ModBlocks.COPPER_CHEST_ITEM.get(), ModBlocks.IRON_CHEST_ITEM.get(), ModBlocks.GOLD_CHEST_ITEM.get(), ModBlocks.DIAMOND_CHEST_ITEM.get(), ModBlocks.NETHERITE_CHEST_ITEM.get());
    }

    private Block getBlock(String registryName) {
        //noinspection ConstantConditions - could only fail in dev environment and crashing is preferred here to fix issues early
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(registryName));
    }

    private void addBarrelRecipes(Consumer<FinishedRecipe> consumer) {
        addStorageTierUpgradeRecipes(consumer, ModBlocks.BARREL_ITEM.get(), ModBlocks.COPPER_BARREL_ITEM.get(), ModBlocks.IRON_BARREL_ITEM.get(), ModBlocks.GOLD_BARREL_ITEM.get(), ModBlocks.DIAMOND_BARREL_ITEM.get(), ModBlocks.NETHERITE_BARREL_ITEM.get());
    }
}
