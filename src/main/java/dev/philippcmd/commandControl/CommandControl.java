package dev.philippcmd.commandControl;

import dev.philippcmd.commandControl.commands.DeactivateCommand;
import dev.philippcmd.commandControl.commands.ReactivateCommand;
import dev.philippcmd.commandControl.commands.ReallowCommand;
import dev.philippcmd.commandControl.commands.RestrictCommand;
import dev.philippcmd.commandControl.events.CommandListener;
import dev.philippcmd.commandControl.util.CommandUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandControl extends JavaPlugin {

    @Override
    public void onEnable() {
        // Save default config (if it doesn't exist)
        this.saveDefaultConfig();

        // Load restricted commands from config
        CommandUtils.loadRestrictedCommands(this.getConfig());

        // Register commands
        this.getCommand("deactivate-command").setExecutor(new DeactivateCommand(this));
        this.getCommand("reactivate-command").setExecutor(new ReactivateCommand(this));
        this.getCommand("restrict-command").setExecutor(new RestrictCommand(this));
        this.getCommand("reallow-command").setExecutor(new ReallowCommand(this));

        // Register event listener
        this.getServer().getPluginManager().registerEvents(new CommandListener(), this);
    }

    @Override
    public void onDisable() {
        // Save restricted commands to config
        CommandUtils.saveRestrictedCommands(this.getConfig());
        this.saveConfig();
    }
}