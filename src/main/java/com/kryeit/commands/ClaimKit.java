package com.kryeit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClaimKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        if (args.length != 0) {
            sender.sendMessage("Usage: /claimkit");
            return false;
        }

        Bukkit.dispatchCommand(player,"give " + player.getName() + " golden_shovel");
        Bukkit.dispatchCommand(player,"give " + player.getName() + " stick");

        return false;
    }
}
