package xyz.herberto.bungeeUtils;

import co.aikar.commands.BungeeCommandManager;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import xyz.herberto.bungeeUtils.commands.BungeeUtilsCommand;
import xyz.herberto.bungeeUtils.commands.HubCommand;
import xyz.herberto.bungeeUtils.commands.StaffChatCommand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;

public final class BungeeUtils extends Plugin {
    @Getter private static BungeeUtils instance;
    @Getter private static Configuration config;

    @Override
    public void onEnable() {

        /* Initialize the instance */
        instance = this;

        /* Setup config file */
        saveDefaultConfig();
        try {
            loadConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Register commands */

        BungeeCommandManager manager = new BungeeCommandManager(this);

        Arrays.asList(
                new HubCommand(),
                new BungeeUtilsCommand(),
                new StaffChatCommand()
        ).forEach(manager::registerCommand);


    }

    public static void saveDefaultConfig() {
        File configFile = new File(instance.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            instance.getDataFolder().mkdirs();
            try (InputStream in = instance.getResourceAsStream("config.yml")) {
                if (in != null) {
                    Files.copy(in, Paths.get(configFile.toURI()));
                    instance.getLogger().info("Default config file created.");
                } else {
                    instance.getLogger().warning("Default config file not found in the JAR.");
                }
            } catch (IOException e) {
                instance.getLogger().log(Level.SEVERE, "Could not create default config file", e);
            }
        }
    }

    public static void loadConfig() throws IOException {
        File configFile = new File(instance.getDataFolder(), "config.yml");

        if (configFile.exists()) {
            instance.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            instance.getLogger().info("Configuration loaded.");
        }
    }


}
