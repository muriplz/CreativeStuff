package com.kryeit.Listener;

import com.kryeit.Utils;
import net.lapismc.afkplus.api.AFKStartEvent;
import net.lapismc.afkplus.api.AFKStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onAFKToggle implements Listener {

    @EventHandler
    public void startAfk(AFKStartEvent e) {
        Player p = Bukkit.getPlayer(e.getPlayer().getUUID());

        if(p == null) return;

        if(p.getGameMode().equals(GameMode.SPECTATOR)) {
            return;
        }

        p.setPlayerListName(Utils.color("&7" + p.getName()));
    }
    @EventHandler
    public void stopAfk(AFKStopEvent e) {
        Player p = Bukkit.getPlayer(e.getPlayer().getUUID());

        if(p == null) return;

        if(p.getGameMode().equals(GameMode.SPECTATOR)) {
            return;
        }

        p.setPlayerListName(Utils.color("&f" + p.getName()));
    }
}
