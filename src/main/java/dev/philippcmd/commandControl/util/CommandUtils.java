package dev.philippcmd.commandControl.util;


import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CommandUtils {

    private static List<String> deactivatedCommands = new ArrayList<>();
    private static List<String> restrictedCommands = new ArrayList<>();

    // Deactivated Commands
    public static void loadDeactivatedCommands(FileConfiguration config) {
        deactivatedCommands = config.getStringList("deactivatedCommands");
    }

    public static void saveDeactivatedCommands(FileConfiguration config) {
        config.set("deactivatedCommands", deactivatedCommands);
    }

    public static boolean isCommandDeactivated(String command) {
        return deactivatedCommands.contains(command.toLowerCase());
    }

    public static void deactivateCommand(String command) {
        if (!deactivatedCommands.contains(command.toLowerCase())) {
            deactivatedCommands.add(command.toLowerCase());
        }
    }

    public static void reactivateCommand(String command) {
        deactivatedCommands.remove(command.toLowerCase());
    }

    // Restricted Commands
    public static void loadRestrictedCommands(FileConfiguration config) {
        restrictedCommands = config.getStringList("restrictedCommands");
    }

    public static void saveRestrictedCommands(FileConfiguration config) {
        config.set("restrictedCommands", restrictedCommands);
    }

    public static boolean isCommandRestricted(String command) {
        return restrictedCommands.contains(command.toLowerCase());
    }

    public static void restrictCommand(String command) {
        if (!restrictedCommands.contains(command.toLowerCase())) {
            restrictedCommands.add(command.toLowerCase());
        }
    }

    public static void reallowCommand(String command) {
        restrictedCommands.remove(command.toLowerCase());
    }
}