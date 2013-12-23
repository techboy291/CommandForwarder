package me.geekofgallifrey.commandforwarder.plugin;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author techboy291
 */
public class CommandForwarderPlugin extends JavaPlugin
{
    private FileConfiguration commandConfig;
    
    @Override
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(new CommandForwarderListener(this), this);
        logInfo("Now enabled!");
        setConfig();
    }
    
    @Override
    public void onDisable()
    {
        logInfo("Now disabled.");
    }
    
    public FileConfiguration getCommandConfig()
    {
        return commandConfig;
    }
    
    public void setConfig()
    {
        File commandFile = new File(this.getDataFolder(), "commandconfig.yml");
        if(!commandFile.exists())
        {
            logInfo("Command config file does not exist: creating one.");
            try
            {
                commandFile.createNewFile();
            } catch(IOException ioe)
            {
                logInfo("Error: Could not create command config.");
            }
        }
        commandConfig = YamlConfiguration.loadConfiguration(commandFile);
    }
    
    private void logInfo(String info)
    {
        PluginDescriptionFile pdf = this.getDescription();
        this.getServer().getLogger().info("[" + pdf.getName() + "v" + pdf.getVersion() + "] " + info);
    }
}
