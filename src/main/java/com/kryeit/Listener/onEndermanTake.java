package com.kryeit.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class onEndermanTake implements Listener {

    @EventHandler
    public void onEnderman(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof Enderman) {
            event.setCancelled(true);
        }
    }
}

