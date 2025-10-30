package xyz.iwolfking.sophisticatedvault.datagen;

import iskallia.vault.init.ModItems;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.p3pp3rf1y.sophisticatedcore.crafting.ShapeBasedRecipeBuilder;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;
import net.p3pp3rf1y.sophisticatedstorage.SophisticatedStorage;
import net.p3pp3rf1y.sophisticatedstorage.item.WoodStorageBlockItem;
import xyz.iwolfking.sophisticatedvault.SophisticatedVault;
import xyz.iwolfking.sophisticatedvault.data.VaultBlockFamilies;
import xyz.iwolfking.sophisticatedvault.data.VaultWoodTypes;
import xyz.iwolfking.sophisticatedvault.init.ModBlocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //Recipes for the blocks built-in to this mod.
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_ALTAR_CHEST, ModItems.ALTAR_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_LARIMAR);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_ENIGMA_CHEST, ModItems.ENIGMA_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_BENITOITE);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_FLESH_CHEST, ModItems.FLESH_CHEST_SCROLL, iskallia.vault.init.ModBlocks.VAULT_MEAT_BLOCK, ModItems.PAINITE_GEM);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_GILDED_CHEST, ModItems.GILDED_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_LARIMAR);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_LIVING_CHEST, ModItems.LIVING_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_LARIMAR);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_ORNATE_CHEST, ModItems.ORNATE_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_LARIMAR);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_ORNATE_BARREL, ModItems.ORNATE_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_PAINITE);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_GILDED_BARREL, ModItems.GILDED_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_PAINITE);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_LIVING_BARREL, ModItems.LIVING_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_PAINITE);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_WOODEN_BARREL, ModItems.WOODEN_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_PAINITE);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_HARDENED_CHEST, ModItems.HARDENED_CHEST_SCROLL, iskallia.vault.init.ModBlocks.DRIFTWOOD_PLANKS, ModItems.CHROMATIC_STEEL_INGOT);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_WOODEN_CHEST, ModItems.WOODEN_CHEST_SCROLL, iskallia.vault.init.ModBlocks.WOODEN_PLANKS, ModItems.CHROMATIC_STEEL_INGOT);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_TREASURE_CHEST, ModItems.TREASURE_CHEST_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.POG);
        sophisticatedVaultChestRecipe(consumer, ModBlocks.SOPHISTICATED_VAULT_UNIQUE_CRATE, ModItems.UNIQUE_CRATE_SCROLL, ModItems.BLACK_CHROMATIC_STEEL_INGOT, ModItems.EXTRAORDINARY_ALEXANDRITE);
        strongboxUpgrade(consumer, ModBlocks.SOPHISTICATED_VAULT_GILDED_STRONGBOX, ModBlocks.SOPHISTICATED_VAULT_GILDED_CHEST, ModItems.GILDED_CHEST_SCROLL, ModItems.EXTRAORDINARY_LARIMAR);
        strongboxUpgrade(consumer, ModBlocks.SOPHISTICATED_VAULT_LIVING_STRONGBOX, ModBlocks.SOPHISTICATED_VAULT_LIVING_CHEST, ModItems.LIVING_CHEST_SCROLL, ModItems.EXTRAORDINARY_LARIMAR);
        strongboxUpgrade(consumer, ModBlocks.SOPHISTICATED_VAULT_ORNATE_STRONGBOX, ModBlocks.SOPHISTICATED_VAULT_ORNATE_CHEST, ModItems.ORNATE_CHEST_SCROLL, ModItems.EXTRAORDINARY_LARIMAR);

        woodChestRecipe(consumer, VaultWoodTypes.CHROMATIC, VaultBlockFamilies.CHROMATIC.getBaseBlock());
        woodChestRecipe(consumer, VaultWoodTypes.VAULT_PLANKS, VaultBlockFamilies.VAULT_PLANKS.getBaseBlock());
        woodChestRecipe(consumer, VaultWoodTypes.DRIFTWOOD, VaultBlockFamilies.DRIFTWOOD.getBaseBlock());
        woodChestRecipe(consumer, VaultWoodTypes.TENOS, VaultBlockFamilies.TENOS.getBaseBlock());
        woodChestRecipe(consumer, VaultWoodTypes.VELARA, VaultBlockFamilies.VELARA.getBaseBlock());
        woodChestRecipe(consumer, VaultWoodTypes.OVERGROWN, VaultBlockFamilies.OVERGROWN.getBaseBlock());
        woodBarrelRecipe(consumer, VaultWoodTypes.CHROMATIC, VaultBlockFamilies.CHROMATIC.getBaseBlock(), VaultBlockFamilies.CHROMATIC.get(BlockFamily.Variant.SLAB));
        woodBarrelRecipe(consumer, VaultWoodTypes.VAULT_PLANKS, VaultBlockFamilies.VAULT_PLANKS.getBaseBlock(),VaultBlockFamilies.VAULT_PLANKS.get(BlockFamily.Variant.SLAB));
        woodBarrelRecipe(consumer, VaultWoodTypes.DRIFTWOOD, VaultBlockFamilies.DRIFTWOOD.getBaseBlock(),VaultBlockFamilies.DRIFTWOOD.get(BlockFamily.Variant.SLAB));
        woodBarrelRecipe(consumer, VaultWoodTypes.TENOS, VaultBlockFamilies.TENOS.getBaseBlock(),VaultBlockFamilies.TENOS.get(BlockFamily.Variant.SLAB));
        woodBarrelRecipe(consumer, VaultWoodTypes.VELARA, VaultBlockFamilies.VELARA.getBaseBlock(),VaultBlockFamilies.VELARA.get(BlockFamily.Variant.SLAB));
        woodBarrelRecipe(consumer, VaultWoodTypes.OVERGROWN, VaultBlockFamilies.OVERGROWN.getBaseBlock(),VaultBlockFamilies.OVERGROWN.get(BlockFamily.Variant.SLAB));

    }

    private void sophisticatedVaultChestRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike scrollItem, ItemLike mainCost, ItemLike secondaryCost) {
        ShapedRecipeBuilder.shaped(output)
                .define('S', scrollItem)
                .define('M', secondaryCost)
                .define('I', mainCost)
                .pattern("IMI")
                .pattern("MSM")
                .pattern("IMI")
                .unlockedBy("has_" + scrollItem.asItem().getRegistryName().getPath(), has(scrollItem))
                .save(consumer, SophisticatedVault.id(output.asItem().getRegistryName().getPath()));
    }

    private void strongboxUpgrade(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike chestItem, ItemLike scrollItem, ItemLike tertiaryCost) {
        ShapedRecipeBuilder.shaped(output)
                .define('S', chestItem)
                .define('I', tertiaryCost)
                .define('M', scrollItem)
                .pattern("IMI")
                .pattern("MSM")
                .pattern("IMI")
                .unlockedBy("has_" + chestItem.asItem().getRegistryName().getPath(), has(chestItem))
                .save(consumer, SophisticatedVault.id(output.asItem().getRegistryName().getPath()));
    }

    private void woodChestRecipe(Consumer<FinishedRecipe> consumer, WoodType woodType, Block planks) {
        ShapeBasedRecipeBuilder.shaped(WoodStorageBlockItem.setWoodType(new ItemStack(net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks.CHEST_ITEM.get()), woodType))
                .pattern("PPP")
                .pattern("PRP")
                .pattern("PPP")
                .define('P', planks)
                .define('R', Blocks.REDSTONE_TORCH)
                .unlockedBy("has_" + woodType.name() + "_plank", has(planks))
                .save(consumer, SophisticatedStorage.getRL(woodType.name() + "_chest"));
    }

    private void woodBarrelRecipe(Consumer<FinishedRecipe> consumer, WoodType woodType, Block planks, Block slab) {
        ShapeBasedRecipeBuilder.shaped(WoodStorageBlockItem.setWoodType(new ItemStack(net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks.BARREL_ITEM.get()), woodType))
                .pattern("PSP")
                .pattern("PRP")
                .pattern("PSP")
                .define('P', planks)
                .define('S', slab)
                .define('R', Blocks.REDSTONE_TORCH)
                .unlockedBy("has_" + woodType.name() + "_plank", has(planks))
                .save(consumer, SophisticatedStorage.getRL(woodType.name() + "_barrel"));
        limitedWoodBarrel1Recipe(consumer, woodType, planks, slab);
        limitedWoodBarrel2Recipe(consumer, woodType, planks, slab);
        limitedWoodBarrel3Recipe(consumer, woodType, planks, slab);
        limitedWoodBarrel4Recipe(consumer, woodType, planks, slab);
    }

    private void limitedWoodBarrelRecipe(Consumer<FinishedRecipe> consumer, WoodType woodType, Block planks, Block slab, Consumer<ShapeBasedRecipeBuilder> addPattern, BlockItem item) {
        ShapeBasedRecipeBuilder builder = ShapeBasedRecipeBuilder.shaped(WoodStorageBlockItem.setWoodType(new ItemStack(item), woodType))
                .define('P', planks)
                .define('S', slab)
                .define('R', Blocks.REDSTONE_TORCH)
                .unlockedBy("has_" + woodType.name() + "_plank", has(planks));
        addPattern.accept(builder);
        builder.save(consumer, SophisticatedStorage.getRL(woodType.name() + "_" + RegistryHelper.getRegistryName(item).orElseThrow().getPath()));
    }

    private void limitedWoodBarrel1Recipe(Consumer<FinishedRecipe> consumer, WoodType woodType, Block planks, Block slab) {
        limitedWoodBarrelRecipe(consumer, woodType, planks, slab, builder ->
                        builder.pattern("PSP")
                                .pattern("PRP")
                                .pattern("PPP")
                , net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks.LIMITED_BARREL_1_ITEM.get());
    }

    private void limitedWoodBarrel2Recipe(Consumer<FinishedRecipe> consumer, WoodType woodType, Block planks, Block slab) {
        limitedWoodBarrelRecipe(consumer, woodType, planks, slab, builder ->
                        builder.pattern("PPP")
                                .pattern("SRS")
                                .pattern("PPP")
                , net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks.LIMITED_BARREL_2_ITEM.get());
    }

    private void limitedWoodBarrel3Recipe(Consumer<FinishedRecipe> consumer, WoodType woodType, Block planks, Block slab) {
        limitedWoodBarrelRecipe(consumer, woodType, planks, slab, builder ->
                        builder.pattern("PSP")
                                .pattern("PRP")
                                .pattern("SPS")
                , net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks.LIMITED_BARREL_3_ITEM.get());
    }

    private void limitedWoodBarrel4Recipe(Consumer<FinishedRecipe> consumer, WoodType woodType, Block planks, Block slab) {
        limitedWoodBarrelRecipe(consumer, woodType, planks, slab, builder ->
                        builder.pattern("SPS")
                                .pattern("PRP")
                                .pattern("SPS")
                , net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks.LIMITED_BARREL_4_ITEM.get());
    }

    /**
     * Helper to get your mod's registered items easily.
     */
    private static ItemLike getItem(String name) {
        return net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(new ResourceLocation(SophisticatedVault.MODID, name));
    }

    protected static String getItemName(ItemLike item) {
        return item.asItem().getRegistryName().getPath();
    }
}
