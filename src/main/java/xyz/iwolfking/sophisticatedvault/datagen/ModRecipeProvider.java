package xyz.iwolfking.sophisticatedvault.datagen;

import iskallia.vault.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import xyz.iwolfking.sophisticatedvault.SophisticatedVault;
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
                .save(consumer);
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
                .save(consumer);
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
