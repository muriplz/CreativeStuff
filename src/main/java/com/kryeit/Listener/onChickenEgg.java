package com.kryeit.Listener;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.User;
import com.griefdefender.api.claim.Claim;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class onChickenEgg implements Listener {

    @EventHandler
    public void onEggDrop(EntityDropItemEvent event) {
        if (event.getEntity() instanceof Chicken && event.getItemDrop().getItemStack().getType().name().equals("EGG")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        Claim claim = GriefDefender.getCore().getClaimAt(event.getEgg().getLocation());

        assert claim != null;
        if(!claim.isWilderness()) {
            event.setHatching(false); // Cancel the random hatching
            event.setNumHatches((byte) 1); // Set the number of chickens to spawn to 1
            Chicken chicken = event.getPlayer().getWorld().spawn(event.getEgg().getLocation(), Chicken.class);
            event.getEgg().remove(); // Remove the egg entity
        }
    }

    @EventHandler
    public void onChickenDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Chicken && !Objects.requireNonNull(GriefDefender.getCore().getClaimAt(event.getEntity().getLocation())).isWilderness() ) {
            int numEggs = 0;
            double rand = Math.random();
            if (rand < 0.90) {
                numEggs = 1;
            } else {
                numEggs = 3;
            }
            ItemStack eggStack = new ItemStack(org.bukkit.Material.EGG, numEggs);
            event.getDrops().add(eggStack);
        }
    }

}
