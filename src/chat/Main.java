package chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
 
    public void onEnable() {
    	getConfig().addDefault("Prefix", "&a[&cStaff&3Chat&a]&r");
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
 
    }      
    public boolean onCommand(CommandSender s, Command cmd,    String label, String[] args) {
        if (s instanceof Player) {
            Player p = (Player) s;
            if (cmd.getName().equalsIgnoreCase("staffchat")) {
                if (!s.hasPermission("staffchat.use")) {
                    s.sendMessage(getConfig().getString("Prefix").replace('&', '§') + ChatColor.RED + " je hebt geen permissies om dit te doen!");
                    return true;
                }
                if (args.length == 0) {
                    s.sendMessage(getConfig().getString("Prefix").replace('&', '§') + ChatColor.RED + " Sorry, maar dit kunnen we helaas niet sturen naar de andere staff leden, omdat er niks staat!");
                    return true;
                }
             
                if (args.length > 0) {
                    StringBuilder b = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        b.append(" ");
                        b.append(args[i]);
                    }
                    String message = b.toString();
                    for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                        if (all.hasPermission("staffchat.use")) {
                         
                            all.sendMessage(getConfig().getString("Prefix").replace('&', '§') + ChatColor.RESET + p.getDisplayName() + ": " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', message));
                        }
                    }
                }
            }
        }
     
        return true;      
    }
 
}