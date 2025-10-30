package xyz.iwolfking.sophisticatedvault.integration;

import net.minecraft.resources.ResourceLocation;
import xyz.iwolfking.sophisticatedvault.SophisticatedVault;
import xyz.iwolfking.sophisticatedvault.config.SophisticatedVaultConfig;
import xyz.iwolfking.vhapi.api.loaders.research.groups.ResearchGroupConfigLoader;
import xyz.iwolfking.vhapi.api.util.VHAPIProcesserUtils;

import java.io.IOException;
import java.io.InputStream;

public class VHAPIIntegration {
    public static void initiateConfigs() {
        if(SophisticatedVaultConfig.COMMON.enableSophisticatedStorageResearch.get()) {
            registerManualConfigFile("/vhapi_configs/sophisticated_storage_research.json", new ResourceLocation("sophisticatedvault","research/researches/sophisticated_storage_research"));
            registerManualConfigFile("/vhapi_configs/sophisticated_storage_research_style.json", new ResourceLocation("sophisticatedvault","research/research_styles/sophisticated_storage_research_style"));
            registerManualConfigFile("/vhapi_configs/block_soph_in_vaults.json", new ResourceLocation("sophisticatedvault","vault/general/block_soph_in_vaults"));
            registerManualConfigFile("/vhapi_configs/mod_box_rewards.json", new ResourceLocation("sophisticatedvault","loot_box/mod_box/mod_box_rewards"));
            registerManualConfigFile("/vhapi_configs/research_group_styles.json", new ResourceLocation("sophisticatedvault","research/group_styles/research_group_styles"));
            registerManualConfigFile("/vhapi_configs/research_descriptions.json", new ResourceLocation("sophisticatedvault","skill/descriptions/research_descriptions"));
        }

    }

    public static void registerManualConfigFile(String filePath, ResourceLocation vhapiRegistryId) {
        try (InputStream stream = SophisticatedVault.class.getResourceAsStream(filePath)) {
            if (stream == null) {
                throw new IOException();
            }
            VHAPIProcesserUtils.addManualConfigFile(stream, vhapiRegistryId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
