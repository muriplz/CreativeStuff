package com.kryeit.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }
        if (args.length != 1) {
            sender.sendMessage("Usage: /gamemode <survival/creative>");
            return false;
        }

        if(args[0].equalsIgnoreCase("survival")) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage("Gamemode set to survival");
            return true;
        }else if(args[0].equalsIgnoreCase("creative")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("Gamemode set to creative");
            return true;
        }else {
            sender.sendMessage("Usage: /gamemode <survival/creative>");
            return false;
        }
    }
}
