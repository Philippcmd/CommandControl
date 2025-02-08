package dev.philippcmd.commandControl.commands;

import dev.philippcmd.commandControl.CommandControl;
import dev.philippcmd.commandControl.util.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DeactivateCommand implements CommandExecutor {

    private final CommandControl plugin;

    public DeactivateCommand(CommandControl plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /deactivate-command <command>");
            return true;
        }

        String cmd = args[0].toLowerCase();
        if (cmd.equals("deactivate-command") || cmd.equals("reactivate-command") ||
                cmd.equals("restrict-command") || cmd.equals("reallow-command")) {
            sender.sendMessage("You cannot deactivate this command.");
            return true;
        }

        if (!CommandUtils.isCommandDeactivated(cmd)) {
            CommandUtils.deactivateCommand(cmd);
            sender.sendMessage("Command '" + cmd + "' has been deactivated.");
        } else {
            sender.sendMessage("Command '" + cmd + "' is already deactivated.");
        }
        return true;
    }
}