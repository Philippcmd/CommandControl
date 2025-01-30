package dev.philippcmd.commandControl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CommandControl extends JavaPlugin {

    private List<String> deactivatedCommands;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Load or create the config file
        this.saveDefaultConfig();
        config = this.getConfig();

        // Load deactivated commands from config
        deactivatedCommands = config.getStringList("deactivatedCommands");

        // Register the command executor
        this.getCommand("deactivate-command").setExecutor(this);
        this.getCommand("reactivate-command").setExecutor(this);

        // Register the event listener
        this.getServer().getPluginManager().registerEvents(new CommandListener(this), this);
    }

    @Override
    public void onDisable() {
        // Save deactivated commands to config
        config.set("deactivatedCommands", deactivatedCommands);
        this.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("deactivate-command")) {
            if (args.length == 0) {
                sender.sendMessage("Usage: /deactivate-command <command>");
                return true;
            }

            String cmd = args[0].toLowerCase();
            if (cmd.equals("deactivate-command") || cmd.equals("reactivate-command")) {
                sender.sendMessage("You cannot deactivate this command.");
                return true;
            }

            if (!deactivatedCommands.contains(cmd)) {
                deactivatedCommands.add(cmd);
                sender.sendMessage("Command '" + cmd + "' has been deactivated.");
            } else {
                sender.sendMessage("Command '" + cmd + "' is already deactivated.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("reactivate-command")) {
            if (args.length == 0) {
                sender.sendMessage("Usage: /reactivate-command <command>");
                return true;
            }

            String cmd = args[0].toLowerCase();
            if (deactivatedCommands.contains(cmd)) {
                deactivatedCommands.remove(cmd);
                sender.sendMessage("Command '" + cmd + "' has been reactivated.");
            } else {
                sender.sendMessage("Command '" + cmd + "' is not deactivated.");
            }
            return true;
        }
        return false;
    }

    public boolean isCommandDeactivated(String command) {
        return deactivatedCommands.contains(command.toLowerCase());
    }
}