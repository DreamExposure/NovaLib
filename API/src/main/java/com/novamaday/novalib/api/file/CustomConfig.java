package com.novamaday.novalib.api.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CustomConfig {
    private final JavaPlugin plugin;
    private YamlConfiguration config;
    private File configFile;
    private final String folder;
    private final String file;

    public CustomConfig(JavaPlugin _plugin, String _folder, String _file) {
        plugin = _plugin;
        folder = _folder;
        file = _file;
    }

    public void create(String message, String header) {
        reload();
        save();
        load(header);

        if (message != null)
            plugin.getLogger().info(message);
    }

    public YamlConfiguration get() {
        if (config == null)
            reload();

        return config;
    }

    public void load(String header) {
        config.options().header(header);
        config.options().copyDefaults(true);
        save();
    }

    public void reload() {
        if (configFile == null)
            configFile = new File(plugin.getDataFolder() + folder, file);

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        if (config == null || configFile == null)
            return;

        try {
            get().save(configFile);
        } catch (final IOException e) {
            plugin.getLogger().severe("Could not save config to " + configFile);
            e.printStackTrace();
        }
    }

    public void update(Map<String, Object> settings) {
        for (String path : settings.keySet()) {
            get().addDefault(path, settings.get(path));
        }
        get().options().copyDefaults(true);
        save();
    }
}