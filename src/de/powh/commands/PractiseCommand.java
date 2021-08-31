package de.powh.commands;

import de.powh.modes.PractiseManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PractiseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;
        if(args.length == 1 && args[0].equalsIgnoreCase("blockingin")) {
            PractiseManager.coverPractise(player);
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("blockingin")) {
            try {
                Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("§aLayer Amount needs to be a Number Value.");
                return false;
            }
            PractiseManager.coverPractise(player, Integer.parseInt(args[1]));
        }

        sendCommandHelp(player);
        return false;
    }

    private void sendCommandHelp(Player player) {
        player.sendMessage("§b§lCommand Usage");
        player.sendMessage("/practise blockingin [layers] §7- §fPractise blocking into Cakes! Layers Range from 1-2");
    }
}
