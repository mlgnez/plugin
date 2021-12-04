package plugins.jrv1000;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

public class PlayerListener implements Listener {

    private Main main;
    public PlayerListener(Main main){ this.main = main;}


    @EventHandler
    public void onMove(PlayerMoveEvent event){

        Player player = event.getPlayer();
        if(!player.hasPermission("allowmove")){
            event.setCancelled(true);
            player.sendMessage("You haven't been added to the move-list by an operator.");
        }
    }
}
