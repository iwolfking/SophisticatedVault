package xyz.iwolfking.sophisticatedvault.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SophisticatedVaultConfig {
    public static class Common
    {
        //Features
        //Items
        //Gear
        public final ForgeConfigSpec.ConfigValue<Boolean> enableSophisticatedStorageResearch;

        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("General Settings");
            enableSophisticatedStorageResearch = builder.comment("Whether Sophisticated Storage should be research locked").define("enableSophisticatedStorageResearch", true);
            builder.pop();
        }
    }

    public static class Server {
        public final ForgeConfigSpec.ConfigValue<Boolean> enableVaultifiedSophisticatedStorage;

        public Server(ForgeConfigSpec.Builder builder)
        {
            builder.push("General Settings");
            enableVaultifiedSophisticatedStorage = builder.comment("Whether Sophisticated Storage recipes should be vaultified.").define("enableVaultifiedSophisticatedStorage", true);
            builder.pop();
        }

    }

    public static final Common COMMON;
    public static final Server SERVER;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec SERVER_SPEC;
    static //constructor
    {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        Pair<Server, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(Server::new);
        COMMON = commonSpecPair.getLeft();
        SERVER = serverSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
        SERVER_SPEC = serverSpecPair.getRight();
    }

}
