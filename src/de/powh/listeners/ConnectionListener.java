package de.powh.listeners;

import de.powh.core.WorldManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        WorldManager.deleteWorld(event.getPlayer());
    }
}
