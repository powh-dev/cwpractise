package de.powh.modes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CakeGenerator {

    private Location cakeLocation;
    private List<Block> blocks = new ArrayList<>();

    public CakeGenerator(Location location) {
        cakeLocation = location.getWorld().getHighestBlockAt(location).getLocation();
        editBlock(cakeLocation, Material.CAKE_BLOCK, 0, 0, 0);
    }

    public void addFirstLayer(Material material) {
        editBlock(cakeLocation, material, 0, 1, 0);
        editBlock(cakeLocation, material, 1, 0, 0);
        editBlock(cakeLocation, material, -1, 0, 0);
        editBlock(cakeLocation, material, 0, 0, 1);
        editBlock(cakeLocation, material, 0, 0, -1);
    }

    public void addSecondLayer(Material material) {
        editBlock(cakeLocation, material, 0, 2, 0);
        editBlock(cakeLocation, material, 2, 0, 0);
        editBlock(cakeLocation, material, -2, 0, 0);
        editBlock(cakeLocation, material, 0, 0, 2);
        editBlock(cakeLocation, material, 0, 0, -2);
        editBlock(cakeLocation, material, 1, 0, 1);
        editBlock(cakeLocation, material, 1, 0, -1);
        editBlock(cakeLocation, material, -1, 0, 1);
        editBlock(cakeLocation, material, -1, 0, -1);
        editBlock(cakeLocation, material, 1, 1, 0);
        editBlock(cakeLocation, material, -1, 1, 0);
        editBlock(cakeLocation, material, 0, 1, 1);
        editBlock(cakeLocation, material, 0, 1, -1);
    }


    public void editBlock(Location loc, Material material, int x, int y, int z) {
        Block block = loc.add(x, y, z).getBlock();
        block.setType(material);
        blocks.add(block);
        loc.subtract(x, y, z);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void clear() {
        for (Block block : blocks) {
            block.setType(Material.AIR);
        }
    }
}
