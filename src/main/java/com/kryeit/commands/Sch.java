package com.kryeit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sch implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage("You can't execute this command from console.");
            return false;
        }
        if (args.length != 0) {
            sender.sendMessage("Usage: /sch");
            return false;
        }
        Bukkit.dispatchCommand(player,"give " + player.getName() + " create:schematic_and_quill");
        Bukkit.dispatchCommand(player,"give " + player.getName() + " create:schematic_table");
        return false;
    }
}

