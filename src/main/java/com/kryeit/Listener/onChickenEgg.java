package com.kryeit.Listener;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class onChickenEgg implements Listener {

    @EventHandler
    public void onEggDrop(EntityDropItemEvent event) {
        if (event.getEntity() instanceof Chicken && event.getItemDrop().getItemStack().getType().name().equalsIgnoreCase("EGG")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        Claim claim = GriefDefender.getCore().getClaimAt(event.getEgg().getLocation());

        assert claim != null;
        if (claim.isWilderness()) return;

        event.setHatching(false); // Cancel the random hatching

        Random random = new Random();
        boolean shouldSpawnChicken = random.nextBoolean(); // 50% chance of being true

        if (shouldSpawnChicken) {
            event.getPlayer().getWorld().spawn(event.getEgg().getLocation(), Chicken.class).setBaby();
        }

    }

    @EventHandler
    public void onChickenDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Chicken && !Objects.requireNonNull(GriefDefender.getCore().getClaimAt(event.getEntity().getLocation())).isWilderness() ) {
            Player killer = event.getEntity().getKiller();
            int lootingLevel = 0;
            if (killer != null) {
                lootingLevel = killer.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
            }
            int numEggs = 0;
            double rand = Math.random();
            if (rand < 0.40 + 0.10 * lootingLevel) {
                numEggs = 1 + lootingLevel;
            } else if (rand < 0.70 + 0.10 * lootingLevel) {
                numEggs = 2 + lootingLevel;
            } else if (rand < 0.80 + 0.05 * lootingLevel) {
                numEggs = 3 + lootingLevel;
            } else if (rand < 0.90) {
                numEggs = 4 + lootingLevel;
            } else {
                numEggs = 5 + lootingLevel;
            }
            ItemStack eggStack = new ItemStack(org.bukkit.Material.EGG, numEggs);
            event.getDrops().add(eggStack);
        }
    }
}
