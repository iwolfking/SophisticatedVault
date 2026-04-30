package xyz.iwolfking.sophisticatedvault;

import com.mojang.logging.LogUtils;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.research.group.ResearchGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.ItemBase;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;
import net.p3pp3rf1y.sophisticatedstorage.Config;
import net.p3pp3rf1y.sophisticatedstorage.client.gui.StorageTranslationHelper;
import net.p3pp3rf1y.sophisticatedstorage.init.ModItems;
import xyz.iwolfking.sophisticatedvault.blocks.SophisticatedVaultChestBase;
import xyz.iwolfking.sophisticatedvault.blocks.tiles.SophisticatedVaultChestEntity;
import xyz.iwolfking.sophisticatedvault.config.SophisticatedVaultConfig;
import xyz.iwolfking.sophisticatedvault.init.ModAddons;
import xyz.iwolfking.sophisticatedvault.integration.VHAPIIntegration;
import xyz.iwolfking.vhapi.api.events.VaultConfigEvent;

import java.util.concurrent.atomic.AtomicInteger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("sophisticatedvault")
public class SophisticatedVault {

    public static final String MODID = "sophisticatedvault";

    public SophisticatedVault() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SophisticatedVaultConfig.COMMON_SPEC, "sophisticated-vault-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SophisticatedVaultConfig.SERVER_SPEC, "sophisticated-vault-server.toml");
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModAddons::onAddPackFinders);

        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.addListener(this::onBlockBreak);

        eventBus.addListener(this::onVaultConfigsLoad);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        VHAPIIntegration.initiateConfigs();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private void onVaultConfigsLoad(VaultConfigEvent.End event) {
        if(SophisticatedVaultConfig.COMMON.enableSophisticatedStorageResearch.get()) {
                ResearchGroup storageGroup = ModConfigs.RESEARCH_GROUPS.getGroups().get("Storage");
                storageGroup.getResearch().add("Sophisticated Storage");
        }

    }

    private void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (event.getState().getBlock() instanceof SophisticatedVaultChestBase && !player.isShiftKeyDown()) {
            Level level = player.getLevel();
            WorldHelper.getBlockEntity(level, event.getPos(), SophisticatedVaultChestEntity.class).ifPresent((wbe) -> {
                if (!wbe.isPacked()) {
                    AtomicInteger droppedItemEntityCount = new AtomicInteger(0);
                    InventoryHelper.iterate(wbe.getStorageWrapper().getInventoryHandler(), (slot, stack) -> {
                        if (!stack.isEmpty()) {
                            droppedItemEntityCount.addAndGet((int)Math.ceil((double)stack.getCount() / (double)Math.min(stack.getMaxStackSize(), 20)));
                        }
                    });
                    if (droppedItemEntityCount.get() > (Integer) Config.SERVER.tooManyItemEntityDrops.get()) {
                        event.setCanceled(true);
                        ItemBase packingTapeItem = (ItemBase) ModItems.PACKING_TAPE.get();
                        Component packingTapeItemName = packingTapeItem.getName(new ItemStack(packingTapeItem));
                        if (packingTapeItemName instanceof TranslatableComponent) {
                            TranslatableComponent c = (TranslatableComponent)packingTapeItemName;
                            c.withStyle(ChatFormatting.GREEN);
                        }

                        player.sendMessage(StorageTranslationHelper.INSTANCE.translStatusMessage("too_many_item_entity_drops", new Object[]{event.getState().getBlock().getName().withStyle(ChatFormatting.GREEN), (new TextComponent(String.valueOf(droppedItemEntityCount.get()))).withStyle(ChatFormatting.RED), packingTapeItemName}), Util.NIL_UUID);
                    }

                }
            });
        }
    }

    public static ResourceLocation id(String id) {
        return new ResourceLocation(SophisticatedVault.MODID, id);
    }
}
