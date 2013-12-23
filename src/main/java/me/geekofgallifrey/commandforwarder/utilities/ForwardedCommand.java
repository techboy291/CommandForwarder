package me.geekofgallifrey.commandforwarder.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author techboy291
 */
public class ForwardedCommand
{
    private final String command;
    private final List<String> forwardedCommands;
    private final List<String> args;
    
    public ForwardedCommand(FileConfiguration config, String commandKey, List<String> forwardedCommands)
    {
        List<String> keys = Arrays.asList(commandKey.split("."));
        args = Arrays.asList(keys.get(keys.size() - 1).split("-"));
        command = args.get(0);
        args.remove(0);
        
        this.forwardedCommands = forwardedCommands;
    }
    
    public static boolean contains(FileConfiguration config, String key, String command)
    {
        List<String> keys = new ArrayList<>(config.getConfigurationSection(key).getKeys(false));
        for(String childKey : keys)
        {
            if(command.equals(Arrays.asList(childKey.split("-")).get(0)) && config.getStringList(key + ".forwarded-commands") != null)
            {
                return true;
            }
        }
        return false;
    }
    
    public void run(Player player, boolean useConsole)
    {
        String playerName = player.getName();
        StringBuilder argsStrBuilder = new StringBuilder();
        for(String arg : args)
        {
            argsStrBuilder.append(arg);
        }
        String argsStr = argsStrBuilder.toString();
        for(String forwardedCommand : forwardedCommands)
        {
            forwardedCommand = forwardedCommand.replaceAll("%args%", argsStr).replaceAll("%player%", playerName);
            CommandSender sender;
            if(useConsole)
            {
                sender = Bukkit.getConsoleSender();
            } else
            {
                sender = player;
            }
            Bukkit.dispatchCommand(sender, forwardedCommand);
        }
    }
    
    public List<String> getForwardedCommands()
    {
        return forwardedCommands;
    }
    
    public List<String> getArgs()
    {
        return args;
    }
}
