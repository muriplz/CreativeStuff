package com.kryeit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectator implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        if (args.length != 0) {
            sender.sendMessage("Usage: /spectator");
            return false;
        }

        if (player.getGameMode().toString().equals("SPECTATOR")) {
            player.setGameMode(org.bukkit.GameMode.SURVIVAL);
        } else {
            player.setGameMode(org.bukkit.GameMode.SPECTATOR);
        }
        player.sendMessage("Gamemode set to spectator");

        return false;
    }
}
