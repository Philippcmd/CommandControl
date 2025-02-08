package dev.philippcmd.commandControl.events;

import dev.philippcmd.commandControl.util.CommandUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ")[0].substring(1).toLowerCase();

        // Block deactivated commands
        if (CommandUtils.isCommandDeactivated(command)) {
            player.sendMessage("This command is currently deactivated.");
            event.setCancelled(true);
        }

        // Block restricted commands for players without the bypass permission
        if (CommandUtils.isCommandRestricted(command) && !player.hasPermission("commandcontrol.bypass")) {
            player.sendMessage("You do not have permission to use this command.");
            event.setCancelled(true);
        }
    }
}