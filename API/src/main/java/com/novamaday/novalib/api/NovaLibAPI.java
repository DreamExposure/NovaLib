package com.novamaday.novalib.api;

import com.novamaday.novalib.api.bukkit.file.CustomConfig;
import com.novamaday.novalib.api.network.crosstalk.client.ClientSocketHandler;
import com.novamaday.novalib.api.network.crosstalk.server.ServerSocketHandler;
import com.novamaday.novalib.api.packets.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;

public class NovaLibAPI {
    private static NovaLibAPI instance;
    public JavaPlugin plugin;

    public CustomConfig config;

    private NovaLibAPI() {
    } //Prevent initialization

    /**
     * Gets the current loaded instance of the API.
     *
     * @return The  current loaded instance of the API.
     */
    public static NovaLibAPI getApi() {
        if (instance == null)
            instance = new NovaLibAPI();
        return instance;
    }

    /**
     * Initializes all parts of the API. This is automatically handled on server boot and SHOULD NOT be called by any plugins.
     */
    public void initAPI(JavaPlugin _plugin) {
        plugin = _plugin;
        PacketManager.getManager().init(Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]);

        config = new CustomConfig(plugin, "", "config.yml");

        config.update(getSettings());

        //Start CrossTalk
        if (config.get().getBoolean("CrossTalk.Enabled")) {
            ClientSocketHandler.initListener();
        }
    }

    /**
     * Initializes all parts of the API. This is automatically handled on server boot and SHOULD NEVER be called by any plugins.
     *
     * @param dataFolder Plugin data folder provided by NovaLib itself.
     */
    public void initAPIForBungee(String dataFolder) {
        plugin = null;

        //TODO: Figure this shit out for bungee!!!!!!!
        //config = new CustomConfig(dataFolder, "config.yml");

        config.update(getSettings());

        //Start CrossTalk
        if (config.get().getBoolean("CrossTalk.Enabled")) {
            ServerSocketHandler.initListener();
        }
    }

    /**
     * Shuts down the API gracefully. This is automatically handled on server shutdown and SHOULD NOT be called by any plugins.
     */
    public void shutdownAPI() {
        if (config.get().getBoolean("CrossTalk.Enabled")) {
            if (plugin != null)
                ClientSocketHandler.shutdownListener();
            else
                ServerSocketHandler.shutdownListener();
        }
    }

    /**
     * Hook a Bukkit plugin into NovaLibAPI. This will be needed more in the future to handle certain functions!
     *
     * @param _bPlugin The plugin to hook.
     */
    public void hookBukkitPlugin(JavaPlugin _bPlugin) {
        plugin.getLogger().info("Plugin hooked: " + _bPlugin);
    }

    private LinkedHashMap<String, Object> getSettings() {
        LinkedHashMap<String, Object> s = new LinkedHashMap<>();

        s.put("DO NOT DELETE", "NovaLib and NovaLibAPI are developed and managed by NovaFox161 (Shades161");
        s.put("Updates.Check", true);
        s.put("Updates.Download", false);

        s.put("Console.Debug", false);
        s.put("Console.Verbose", false);

        s.put("CrossTalk.Enabled", true);
        s.put("CrossTalk.Server.Hostname", "localhost");
        s.put("CrossTalk.Server.Port", 5200);
        s.put("CrossTalk.Client.Hostname", "localhost");
        s.put("CrossTalk.Client.Port", 5301);

        return s;
    }

    /**
     * Gets whether or not NovaLib is in Debug Mode.
     *
     * @return Whether or not NovaLib is in Debug Mode.
     */
    public static boolean debug() {
        return getApi().config.get().getBoolean("Console.Debug");
    }

    /**
     * Gets whether or not NovaLib is in Verbose Mode.
     *
     * @return Whether or not NovaLib is in Verbose Mode.
     */
    public static boolean verbose() {
        return getApi().config.get().getBoolean("Console.Verbose");
    }
}