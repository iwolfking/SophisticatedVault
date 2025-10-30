package xyz.iwolfking.sophisticatedvault.events;

import iskallia.vault.gear.trinket.TrinketEffect;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModContainers;
import iskallia.vault.init.ModEffects;
import iskallia.vault.init.ModTrinkets;
import iskallia.vault.research.group.ResearchGroup;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import xyz.iwolfking.sophisticatedvault.config.SophisticatedVaultConfig;
import xyz.iwolfking.sophisticatedvault.init.ModBlocks;
import xyz.iwolfking.vhapi.api.events.VaultConfigEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        ModBlocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        ModBlocks.registerBlockItems(event);
    }

    @SubscribeEvent
    public static void onTileEntityRegister(RegistryEvent.Register<BlockEntityType<?>> event) {
        ModBlocks.registerTileEntities(event);
    }


    @SubscribeEvent
    public static void ohRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ModBlocks.registerTileEntityRenderers(event);
    }



}
