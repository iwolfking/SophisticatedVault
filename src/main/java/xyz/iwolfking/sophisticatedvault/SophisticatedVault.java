package xyz.iwolfking.sophisticatedvault;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.ItemBase;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;
import net.p3pp3rf1y.sophisticatedstorage.Config;
import net.p3pp3rf1y.sophisticatedstorage.block.StorageBlockBase;
import net.p3pp3rf1y.sophisticatedstorage.block.WoodStorageBlockBase;
import net.p3pp3rf1y.sophisticatedstorage.block.WoodStorageBlockEntity;
import net.p3pp3rf1y.sophisticatedstorage.client.gui.StorageTranslationHelper;
import net.p3pp3rf1y.sophisticatedstorage.init.ModItems;
import org.slf4j.Logger;
import xyz.iwolfking.sophisticatedvault.blocks.SophisticatedVaultChestBase;
import xyz.iwolfking.sophisticatedvault.blocks.tiles.SophisticatedVaultChestEntity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("sophisticatedvault")
public class SophisticatedVault {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SophisticatedVault() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.addListener(this::onBlockBreak);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
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
}
