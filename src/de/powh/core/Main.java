package de.powh.core;

import de.powh.commands.PractiseCommand;
import de.powh.listeners.ConnectionListener;
import de.powh.modes.PractiseManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {


    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        console.sendMessage("§e[" + Project.NAME_NOSPACE + "] Loading Plugin...");
        File dir = new File("plugins/" + Project.NAME_NOSPACE + "/");
        if(!dir.exists()) dir.mkdirs();;
        Project.DIR = dir;
        registerCommands();
        registerEvents();



        console.sendMessage("§a[" + Project.NAME_NOSPACE + "] Plugin loaded!");
    }

    @Override
    public void onDisable() {
        console.sendMessage("§e[" + Project.NAME_NOSPACE + "] Disabling Plugin...");

        console.sendMessage("§a[" + Project.NAME_NOSPACE + "] Plugin disabled!");
    }

    private void registerCommands() {
        console.sendMessage("§e[" + Project.NAME_NOSPACE + "] Registering Commands...");
        registerCommand(new PractiseCommand());

        console.sendMessage("§a[" + Project.NAME_NOSPACE + "] Registered Commands!");
    }

    private void registerEvents() {
        console.sendMessage("§e[" + Project.NAME_NOSPACE + "] Registering Events...");
        registerEvent(new ConnectionListener());
        registerEvent(new PractiseManager());

        console.sendMessage("§a[" + Project.NAME_NOSPACE + "] Registered Events! ");

    }

    private void registerEvent(Listener listener) {
        console.sendMessage("Registering " + listener.getClass().getName() +"...");
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    private void registerCommand(CommandExecutor commandExecutor) {
        console.sendMessage("Registering " + commandExecutor.getClass().getName() + "...");
        Bukkit.getPluginCommand("practise").setExecutor(commandExecutor);
    }

}
