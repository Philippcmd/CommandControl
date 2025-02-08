package dev.philippcmd.commandControl.commands;

import dev.philippcmd.commandControl.CommandControl;
import dev.philippcmd.commandControl.util.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReactivateCommand implements CommandExecutor {

    private final CommandControl plugin;

    public ReactivateCommand(CommandControl plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /reactivate-command <command>");
            return true;
        }

        String cmd = args[0].toLowerCase();
        if (CommandUtils.isCommandDeactivated(cmd)) {
            CommandUtils.reactivateCommand(cmd);
            sender.sendMessage("Command '" + cmd + "' has been reactivated.");
        } else {
            sender.sendMessage("Command '" + cmd + "' is not deactivated.");
        }
        return true;
    }
}