package com.kryeit.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Survival implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }
        if (args.length != 0) {
            sender.sendMessage("Usage: /survival");
            return false;
        }



        if(player.getGameMode().toString().equals("SURVIVAL")) {
            player.setGameMode(GameMode.CREATIVE);
        }else{
            player.setGameMode(GameMode.SURVIVAL);
        }
        player.sendMessage("Gamemode set to survival");
        return true;
    }
}
