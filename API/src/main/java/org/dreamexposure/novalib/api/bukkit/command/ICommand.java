package org.dreamexposure.novalib.api.bukkit.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;

@SuppressWarnings("unused")
public interface ICommand {
    /**
     * Gets the command this Object is responsible for.
     *
     * @return The command this Object is responsible for.
     */
    String getCommand();

    /**
     * Gets the short aliases of the command this object is responsible for.
     * This will return an empty ArrayList if none are present
     *
     * @return The aliases of the command.
     */
    ArrayList<String> getAliases();

    /**
     * Gets the info on the command (not sub command) to be used in help menus.
     *
     * @return The command info.
     */
    CommandInfo getCommandInfo();

    /**
     * Issues the command this Object is responsible for.
     *
     * @param sender The sender of the command
     * @param args   The command arguments.
     * @return <code>true</code> if successful, else <code>false</code>.
     */
    boolean issueCommand(CommandSender sender, String[] args);
}