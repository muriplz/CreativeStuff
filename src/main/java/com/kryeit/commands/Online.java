package com.kryeit.commands;

import com.kryeit.Utils;
import net.lapismc.afkplus.playerdata.AFKPlusPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.kryeit.CreativeStuff.afkPlusPlayerAPI;


public class Online implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder message= new StringBuilder(" ");
        AFKPlusPlayer player;
        for(Player p : Bukkit.getOnlinePlayers()){
            player = afkPlusPlayerAPI.getPlayer(p.getUniqueId());
            if(player.isAFK()){
                message.append("&7" + p.getName()+" ");

            } else{
                message.append("&f" + p.getName()+" ");

            }
        }
        Bukkit.getConsoleSender().sendMessage(Utils.color(message.toString()));
        return false;
    }
}
