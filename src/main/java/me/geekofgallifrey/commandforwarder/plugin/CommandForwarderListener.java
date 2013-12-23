package me.geekofgallifrey.commandforwarder.plugin;

import me.geekofgallifrey.commandforwarder.utilities.ForwardedCommand;
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
        String command = event.getMessage().split(" ")[0].replaceFirst("/", "");
        if(ForwardedCommand.contains(plugin.getCommandConfig(), "", command))
        {
            ForwardedCommand forwardedCommand = new ForwardedCommand(plugin.getCommandConfig(), command, plugin.getCommandConfig().getStringList(command + ".forwarded-commands"));
        }
    }
}
