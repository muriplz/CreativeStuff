package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onNewPlayerJoin (PlayerJoinEvent e){

        Player p = e.getPlayer();

        if (p.hasPlayedBefore()) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(p.getName())) {
                p.sendMessage(Utils.color("&bWelcome to Kryeit! see /rules\nAdditional rules:\n- Don't schematic illegal blocks or items."));
                continue;
            }
            player.sendMessage(Utils.color("&b" + p.getName()
                    + " has joined Kryeit for the first time! Welcome!"));
        }
    }
}
