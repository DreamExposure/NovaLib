package com.novamaday.novalib.api.packets;

import org.bukkit.Bukkit;

@SuppressWarnings("unused")
public class PacketManager {
    private static PacketManager manager;

    private IPing ping;
    private ITabList tabList;
    private ISignEditor signEditor;
    private IActionBar actionBar;
    private IEntityTypes entityTypes;

    private PacketManager() {
    }

    /**
     * Gets the currently loaded instance of the PacketManager
     *
     * @return The current loaded instance of the packet manager
     */
    public static PacketManager getManager() {
        if (manager == null)
            manager = new PacketManager();

        return manager;
    }

    /**
     * Initiates the packet manager. This should not be called by any plugins ever, its automatically handled.
     *
     * @param serverVersion The version of the MC server.
     */
    public void init(String serverVersion) {
        try {
            String packageName = "com.novamaday.novalib.nms." + serverVersion;
            ping = (IPing) Class.forName(packageName + ".Ping").getConstructor().newInstance();
            tabList = (ITabList) Class.forName(packageName + ".TabList").getConstructor().newInstance();
            signEditor = (ISignEditor) Class.forName(packageName + ".SignEditor").getConstructor().newInstance();
            actionBar = (IActionBar) Class.forName(packageName + ".ActionBar").getConstructor().newInstance();
            entityTypes = (IEntityTypes) Class.forName(packageName + ".EntityTypes").getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Could not find support for this CraftBukkit version!");
            Bukkit.getLogger().info("Please make sure you have the latest NovaLib version!");
        }
    }

    /**
     * Gets the {@link IPing} loaded for the current MC version of the server.
     *
     * @return The IPing for this server.
     */
    public IPing getPing() {
        return ping;
    }

    /**
     * Gets the {@link ITabList} loaded for the current MC version of the server.
     *
     * @return The ITabList for this server.
     */
    public ITabList getTabList() {
        return tabList;
    }

    /**
     * Gets the {@link ISignEditor} loaded for the current MC version of the server.
     *
     * @return The ISignEditor for this server.
     */
    public ISignEditor getSignEditor() {
        return signEditor;
    }

    /**
     * Gets the {@link IActionBar} loaded for the current MC version of the server.
     *
     * @return The IActionBar for this server.
     */
    public IActionBar getActionBar() {
        return actionBar;
    }

    /**
     * Gets the {@link IEntityTypes} loaded for the current MC version of the server.
     *
     * @return The IEntityTypes for this server.
     */
    public IEntityTypes getEntityTypes() {
        return entityTypes;
    }
}