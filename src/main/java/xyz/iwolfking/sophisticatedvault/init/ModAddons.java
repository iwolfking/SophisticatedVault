package xyz.iwolfking.sophisticatedvault.init;

import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.resource.PathResourcePack;
import xyz.iwolfking.sophisticatedvault.SophisticatedVault;
import xyz.iwolfking.sophisticatedvault.config.SophisticatedVaultConfig;

import java.nio.file.Path;

public class ModAddons {
    public static void onAddPackFinders(final AddPackFindersEvent event) {
        if(event.getPackType() == PackType.SERVER_DATA) {
            if(SophisticatedVaultConfig.SERVER.enableVaultifiedSophisticatedStorage.get()) {
                registerAddon(event, "sophisticated_storage_vaultified");
            }
        }
    }

    private static void registerAddon(final AddPackFindersEvent event, final String packName) {
        event.addRepositorySource((packConsumer, constructor) -> {
            Pack pack = Pack.create(SophisticatedVault.MODID + ":" + packName, true, () -> {
                Path path = ModList.get().getModFileById(SophisticatedVault.MODID).getFile().findResource("/" + packName);
                return new PathResourcePack(packName, path);
            }, constructor, Pack.Position.TOP, PackSource.DEFAULT);

            if (pack != null) {
                packConsumer.accept(pack);
            }
        });
    }
}
