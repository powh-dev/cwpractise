package de.powh.modes;

import de.powh.modes.blockin.CoverPractise;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class PractiseManager implements Listener {

    private static HashMap<String, CoverPractise> coverPractiseMap = new HashMap<>();

    public static void coverPractise(Player player, int i) {
        if(coverPractiseMap.containsKey(player.getUniqueId().toString())) {
            CoverPractise coverPractise = coverPractiseMap.get(player.getUniqueId().toString());
            coverPractise.stop();
            coverPractiseMap.remove(player.getUniqueId().toString());
        } else {
            CoverPractise coverPractise = new CoverPractise(player);
            coverPractise.layers(i);
            coverPractiseMap.put(player.getUniqueId().toString(), coverPractise);
            coverPractise.start();
        }
    }
    public static void coverPractise(Player player) {
        coverPractise(player, 2);
    }

    public static boolean isPlayingCoverPractise(Player player) {
        return coverPractiseMap.containsKey(player.getUniqueId().toString());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(isPlayingCoverPractise(event.getPlayer())) {
            coverPractiseMap.get(event.getPlayer().getUniqueId().toString()).registerBlock(event.getBlock());
        }
    }

    @EventHandler
    public void onCakeEat(PlayerInteractEvent event) {
        if(isPlayingCoverPractise(event.getPlayer())) {
            if(event.getClickedBlock() == null) return;
            if(event.getClickedBlock().getType().equals(Material.CAKE_BLOCK)) {
                coverPractiseMap.get(event.getPlayer().getUniqueId().toString()).cakeEaten();
            }
        }
    }

}
