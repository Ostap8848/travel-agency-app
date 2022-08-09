package com.travelagency.app.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static Map<String, ActionCommand> commands = new HashMap<>();

    public static ActionCommand getCommand(String commandName) {
        return commands.get(commandName);
    }

    public void addCommand(String name, ActionCommand command) {
        commands.put(name, command);
    }

    @Override
    public String toString() {
        return "CommandContainer=[" + commands + "]";
    }
}
