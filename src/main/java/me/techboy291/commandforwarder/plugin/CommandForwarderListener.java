package me.techboy291.commandforwarder.plugin;

import me.techboy291.commandforwarder.utilities.ForwardedCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @author techboy291
 */
public class CommandForwarderListener implements Listener
{
    private final CommandForwarderPlugin plugin;
    
    public CommandForwarderListener(CommandForwarderPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        String command = event.getMessage().replaceAll(" ", "-").replaceFirst("/", "");
        if(ForwardedCommand.contains(plugin.getCommandConfig(), "", command))
        {
            new ForwardedCommand(plugin.getCommandConfig(), command, plugin.getCommandConfig().getStringList(command + ".forwarded-commands")).run(event.getPlayer());
        }
    }
}
