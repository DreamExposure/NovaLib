package com.novamaday.novalib.api.file;

import net.md_5.bungee.api.plugin.Plugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomConfig {
    private final org.bukkit.plugin.Plugin plugin;
    private final Plugin bungeePlugin;
    private YamlConfiguration config;
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
    public CustomConfig(org.bukkit.plugin.Plugin _plugin, String _folder, String _file) {
        plugin = _plugin;
        bungeePlugin = null;
        folder = _folder;
        file = _file;
    }

    public CustomConfig(Plugin _plugin, String _folder, String _file) {
        bungeePlugin = _plugin;
        plugin = null;
        folder = _folder;
        file = _file;
    }

    /**
     * Creates the file on disk.
     * @param message The message to log.
     * @param header The file header in the file.
     */
    public void create(String message, String header) {
        reload();
        save();
        load(header);

        if (message != null) {
            if (plugin != null)
                plugin.getLogger().info(message);
            else
                bungeePlugin.getLogger().info(message);
        }
    }

    /**
     * Gets the loaded instance of the Yaml file
     * @return The loaded instance of the Yaml file
     */
    public YamlConfiguration get() {
        if (config == null)
            reload();

        return config;
    }

    /**
     * Loads the file into memory
     * @param header The file header.
     */
    public void load(String header) {
        config.options().header(header);
        config.options().copyDefaults(true);
        save();
    }

    /**
     * Reloads the file from disk
     */
    public void reload() {
        if (configFile == null) {
            if (plugin != null)
                configFile = new File(plugin.getDataFolder() + folder, file);
            else
                configFile = new File(bungeePlugin.getDataFolder() + folder, file);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Saves the file to disk
     */
    public void save() {
        if (config == null || configFile == null)
            return;

        try {
            get().save(configFile);
        } catch (final IOException e) {
            if (plugin != null)
                plugin.getLogger().severe("Could not save config to " + configFile);
            else
                bungeePlugin.getLogger().severe("Count not save config to " + configFile);
            e.printStackTrace();
        }
    }

    /**
     * Updates the paths and values in the config
     * @param settings A hash map of the paths and default values.
     */
    public void update(LinkedHashMap<String, Object> settings) {
        for (Map.Entry<String, Object> ent : settings.entrySet()) {
            get().addDefault(ent.getKey(), ent.getValue());
        }
        get().options().copyDefaults(true);
        save();
    }
}