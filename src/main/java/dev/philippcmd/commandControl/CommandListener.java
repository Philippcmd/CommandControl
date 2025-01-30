package dev.philippcmd.commandControl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private final CommandControl plugin;

    public CommandListener(CommandControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0].substring(1).toLowerCase();
        if (plugin.isCommandDeactivated(command)) {
            event.getPlayer().sendMessage("This command is currently deactivated.");
            event.setCancelled(true);
        }
    }
}