package com.novamaday.novalib.api.command;

import java.util.HashMap;
import java.util.Map;

public class CommandInfo {
    private final String name;
    private String description;
    private String example;

    private final Map<String, String> subCommands = new HashMap<>();

    /**
     * Creates a new CommandInfo object for the specified command.
     *
     * @param _name The name of the command
     */
    public CommandInfo(String _name) {
        name = _name;
    }

    /**
     * Gets the name of the command.
     *
     * @return The command's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the command.
     *
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets an example usage of the command.
     *
     * @return An example usage of the command.
     */
    public String getExample() {
        return example;
    }

    /**
     * Gets the sub commands and their descriptions. <strong>Sub Commands MUST be lowercase!!!</strong>
     *
     * @return The sub commands and their descriptions.
     */
    public Map<String, String> getSubCommands() {
        return subCommands;
    }

    /**
     * Sets the description for the command.
     *
     * @param _description The description for the command.
     */
    public void setDescription(String _description) {
        description = _description;
    }

    /**
     * Sets the example usage for the command.
     *
     * @param _example The example usage for the command.
     */
    public void setExample(String _example) {
        example = _example;
    }
}