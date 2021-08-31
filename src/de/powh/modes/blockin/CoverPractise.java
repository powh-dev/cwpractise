package de.powh.modes.blockin;

import de.powh.core.WorldManager;
import de.powh.modes.CakeGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CoverPractise {

    private Player player;
    private Location backTo;
    private CakeGenerator cake;
    private boolean secondLayer = true;
    private List<Block> blocks = new ArrayList<>();
    private long time;

    public CoverPractise(Player player) {
        this.player = player;
    }

    public void layers(int i) {
        if(i == 1) {
            secondLayer = false;
        } else {
            secondLayer = true;
        }
    }

    public void start() {
        backTo = player.getLocation();
        WorldManager.generate(player);
        spawnNewCake();
    }

    public void stop() {
        if(backTo == null) return;
        player.sendMessage("§cEnding Practise");
        player.teleport(backTo);
        cake.clear();
        WorldManager.deleteWorld(player);
    }

    public void spawnNewCake() {
        time = System.currentTimeMillis();
        Location location = player.getLocation().add((20 - new Random().nextInt(40)), 0, (20 - new Random().nextInt(40)));
        cake = new CakeGenerator(location);
        cake.addFirstLayer(Material.ENDER_STONE);
        if(secondLayer) cake.addSecondLayer(Material.WOOD);
        DecimalFormat df = new DecimalFormat("##");
        player.sendMessage("§aA new Cake appeared at §b" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "§a(§b" + df.format(location.distance(player.getLocation())) + "m§a)");

    }

    public void registerBlock(Block block) {
        blocks.add(block);
    }

    public void cakeEaten() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        date.setTime(System.currentTimeMillis()-time);
        player.sendMessage("§aCake Eaten after §b" + sdf.format(date) + "§a, §b" + blocks.size() + " §aBlocks placed.");

        player.sendMessage(isFullyCovered(player) ? "§a§lCover was complete" : "§c§lCover was incomplete");
        cake.clear();
        for (Block block : blocks) {
            player.getInventory().addItem(new ItemStack(block.getType(), 1));
            block.setType(Material.AIR);
        }
        blocks.clear();
        spawnNewCake();
    }

    public boolean isFullyCovered(Player player) {
        Location loc = player.getLocation();
        return isCovered(loc, 1, 0, 0)
                && isCovered(loc, 0, 0, 1)
                && isCovered(loc, -1, 0, 0)
                && isCovered(loc, 0, 0, -1)
                && isCovered(loc, 1, 1, 0)
                && isCovered(loc, 0, 1, 1)
                && isCovered(loc, -1, 1, 0)
                && isCovered(loc, 0, 1, -1)
                && isCovered(loc, 0, 2, 0);
    }

    public boolean isCovered(Location loc, int x, int y, int z) {
        boolean result = false;
        loc.add(x, y, z);
        if(loc.getBlock().getType() != Material.AIR) {
            loc.subtract(x,y,z);
            return true;
        }
        for (Block block : cake.getBlocks()) {
            if(block.getLocation().equals(loc.getBlock().getLocation())) {
                loc.subtract(x,y,z);
                return true;
            }
        }
        return false;
    }


}
