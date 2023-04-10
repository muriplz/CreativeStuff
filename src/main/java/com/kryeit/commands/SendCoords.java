package com.kryeit.commands;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCoords implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player cordSender)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        int x = cordSender.getLocation().getBlockX();
        int y = cordSender.getLocation().getBlockY();
        int z = cordSender.getLocation().getBlockZ();

        if (args.length != 1) {
            cordSender.sendMessage("You have to enter a player's name");
            return false;
        }

        Player cordReciever = Bukkit.getPlayer(args[0]);

        if (cordReciever == null) {
            cordSender.sendMessage("Player not found");
            return false;
        }

        String cord = "(" + x + " , " + y + " , " + z + ")";
        cordReciever.sendMessage(Utils.color("&6" + cordSender.getName() + "&f has sent you the current coords: &6" + cord));
        cordSender.sendMessage(Utils.color("&6" + cordReciever.getName() + " &fhas recieved your coords."));
        return true;

    }
}
