package com.novamaday.novalib.api.bungee.file;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomConfig {
    private final Plugin plugin;
    private Configuration config;
    private File configFile;
    private final String folder;
    private final String file;

    /**
     * Creates a new instance of the CustomConfig
     *
     * @param _plugin The plugin creating the config.
     * @param _folder The folder for the file (excluding the plugin folder).
     * @param _file   The file to create (with extension).
     */
    public CustomConfig(Plugin _plugin, String _folder, String _file) {
        plugin = _plugin;
        folder = _folder;
        file = _file;
    }

    /**
     * Creates the file on disk.
     *
     * @param message The message to log.
     */
    public void create(String message) {
        reload();
        save();

        if (message != null) {
            plugin.getLogger().info(message);
        }
    }

    /**
     * Gets the loaded instance of the Yaml file
     *
     * @return The loaded instance of the Yaml file
     */
    public Configuration get() {
        if (config == null)
            reload();

        return config;
    }

    /**
     * Loads the file into memory
     */
    public void load() {
        reload();
        save();
    }

    /**
     * Reloads the file from disk
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void reload() {
        if (configFile == null)
            configFile = new File(plugin.getDataFolder() + folder, file);

        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            }

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not reload config!");
            e.printStackTrace();
        }
    }

    /**
     * Saves the file to disk
     */
    public void save() {
        if (config == null || configFile == null)
            return;

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(get(), configFile);
        } catch (final IOException e) {
            plugin.getLogger().severe("Could not save config to " + configFile);
            e.printStackTrace();
        }
    }

    /**
     * Updates the paths and values in the config
     *
     * @param settings A hash map of the paths and default values.
     */
    public void update(LinkedHashMap<String, Object> settings) {
        for (Map.Entry<String, Object> ent: settings.entrySet()) {
            if (!get().contains(ent.getKey()))
                get().set(ent.getKey(), ent.getValue());
        }
        save();
    }
}