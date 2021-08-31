package de.powh.core;

import net.minecraft.server.v1_8_R1.GameRules;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class WorldManager {
    public static HashMap<String, World> worldList = new HashMap<>();

    public static void generate(Player player) {
        deleteWorld(player);
        World world = Bukkit.createWorld(new WorldCreator(player.getName()).type(WorldType.FLAT));
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
        player.sendMessage("§aPractise World generated!");
        player.teleport(new Location(world, 0, 5, 0));
        worldList.put(player.getUniqueId().toString(), world);
    }

    public static void deleteWorld(Player player) {
        System.out.println("Deleting World");
        if(worldList.containsKey(player.getUniqueId().toString())) {
            World world = worldList.get(player.getUniqueId().toString());
            for (Player worldPlayer : world.getPlayers()) {
                try {
                    worldPlayer.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0));
                } catch (Exception ignored) {
                }
            }
            worldList.remove(player.getUniqueId().toString());
            Bukkit.getServer().unloadWorld(world, false);
            File worldFile = new File(Bukkit.getWorldContainer() + "/" + world.getName());
           // worldFile.renameTo(new File(Project.DIR + "/junk/" + UUID.randomUUID().toString()));
            System.out.println(worldFile.delete());
            worldFile.deleteOnExit();
        }
    }

}
