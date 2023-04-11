package com.kryeit.Listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if (e.getClickedInventory() == null) return;
        String itemStack = e.getCurrentItem().toString();

        if(itemStack == null) return;

        if(itemStack.contains("SPAWN_EGG")) {
            e.setCancelled(true);
        }
    }
}
