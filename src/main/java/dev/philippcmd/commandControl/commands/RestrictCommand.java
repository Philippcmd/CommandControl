package dev.philippcmd.commandControl.commands;

import dev.philippcmd.commandControl.CommandControl;
import dev.philippcmd.commandControl.util.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RestrictCommand implements CommandExecutor {


    private final CommandControl plugin;

    public RestrictCommand(CommandControl plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /restrict-command <command>");
            return true;
        }

        String cmd = args[0].toLowerCase();
        if (CommandUtils.isCommandRestricted(cmd)) {
            sender.sendMessage("Command '" + cmd + "' is already restricted.");
        } else {
            CommandUtils.restrictCommand(cmd);
            sender.sendMessage("Command '" + cmd + "' has been restricted.");
        }
        return true;
    }
}