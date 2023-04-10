package com.kryeit.commands;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

public class LastOnline implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        if(args.length != 1) {
            player.sendMessage("Usage: /lastonline <Player>");
            return false;
        }
        
        Player other = Bukkit.getPlayer(args[0]);

        if(Utils.isOffline(args[0]) && other == null) {
            for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                if(Objects.equals(offlinePlayer.getName(), args[0])) {
                    player.sendMessage(getMessage(offlinePlayer.getUniqueId(),offlinePlayer.getName()));
                    return true;
                }
            }
        }

        if(other == null) {
            player.sendMessage("Player not found");
            return false;
        }

        if(other.isOnline()) {
            player.sendMessage(Utils.color("&6" + other.getName() + "&f is online"));
            return false;
        }

        player.sendMessage(getMessage(other.getUniqueId(),other.getName()));

        return true;
    }

    public String getMessage(UUID id , String name) {

        if(GriefDefender.getCore().getUser(id).getPlayerData().getClaims().isEmpty()) return Utils.color("&6" + name + "&f doesn't have any claim, therefore there are no records on the database");
        String howLong = "";
        StringBuilder sb = new StringBuilder();
        for(Claim claim : GriefDefender.getCore().getUser(id).getPlayerData().getClaims()) {
            LocalDateTime date = LocalDateTime.now();
            LocalDateTime lastonline = LocalDateTime.ofInstant(claim.getData().getDateLastActive(), ZoneOffset.UTC);
            String[] chars = claim.getData().getDateLastActive().toString().split("");

            int i = 1;
            for (String ch : chars) {

                sb.append(ch);

                if(i == 10) break;

                i++;
            }

            howLong = Utils.getTimeBetween(lastonline,date);

            break;
        }
        return Utils.color("The last time &6" + name + "&f has been online is &6" + sb +
                "&f.\nOffline for: &6" + howLong);
    }
}
