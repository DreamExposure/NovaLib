package org.dreamexposure.novalib.api.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class CommandCenter implements CommandExecutor {
    private final JavaPlugin plugin;

    private final ArrayList<ICommand> commands = new ArrayList<>();

    /**
     * Creates a new CommandCenter for your plugin.
     *
     * @param _plugin The plugin to create the CommandCenter for.
     */
    public CommandCenter(JavaPlugin _plugin) {
        plugin = _plugin;
    }


    /**
     * Enables the CommandCenter. <strong>Only call this after registering your commands!!!!</strong>
     */
    public void enable() {
        for (ICommand c: commands) {
            plugin.getCommand(c.getCommand().toLowerCase()).setExecutor(this);
            for (String a: c.getAliases()) {
                plugin.getCommand(a.toLowerCase()).setExecutor(this);
            }
        }
    }

    /**
     * Registers a command that can be executed.
     *
     * @param _command The command to register.
     */
    public void registerCommand(ICommand _command) {
        commands.add(_command);
    }

    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (ICommand c: commands) {
            if (c.getCommand().equalsIgnoreCase(command.getName()) || c.getAliases().contains(command.getName().toLowerCase()))
                return c.issueCommand(sender, args);
        }
        return false;
    }
}
