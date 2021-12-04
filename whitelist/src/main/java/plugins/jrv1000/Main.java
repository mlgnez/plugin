package plugins.jrv1000;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(player.isOp()){
            if(cmd.getName().equals("addplayer")){
                if(args.length == 0){
                    player.sendMessage("/addplayer [playername]");
                }
                if(args.length == 1){
                    Player playeradd = Bukkit.getPlayerExact(args[0]);
                    try{
                        PermissionAttachment attachment = playeradd.addAttachment(instance);
                        attachment.setPermission("allowmove", true);
                    }catch (NullPointerException e){
                        player.sendMessage("Failed to add player: " + args[0] + " to the whitelist.");
                    }

                    instance.getConfig().addDefault("allowedPlayers", args[0]);
                    player.sendMessage("Added Player: " + args[0] + " to the whitelist.");
                }
            }

            if(cmd.getName().equals("removeplayer")){ //doesnt work bruHH also i gotta make it so only op people can run the add and remove people commands :flushed:
                if(args.length == 0){
                    player.sendMessage("/removeplayer [playername]");
                }
                if(args.length == 1){
                    Player playeradd = Bukkit.getPlayerExact(args[0]);
                    try{
                        PermissionAttachment attachment = playeradd.addAttachment(instance);
                        attachment.setPermission("allowmove", false);
                    }catch(NullPointerException e){
                        player.sendMessage("Failed to remove player: " + args[0] + " to the whitelist.");
                    }
                    player.sendMessage("Removed Player: " + args[0] + " from the whitelist.");
                }
            }
        }
        return false;
    }
}
