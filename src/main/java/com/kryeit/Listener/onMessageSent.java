package com.kryeit.Listener;

import com.kryeit.CreativeStuff;
import com.kryeit.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.kryeit.Utils.isPlayerInGroup;

public class onMessageSent implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player p = e.getPlayer();

        if (message.contains("trapped") || message.contains("stuck") || message.contains("get out")) {
            if (!CreativeStuff.getInstance().sentTrapped.contains(p.getUniqueId())){
                p.sendMessage(Utils.color("&bIf you can't get out of somewhere, use /trapped"));
                CreativeStuff.getInstance().sentTrapped.add(p.getUniqueId());
            }
        }

        if(!p.hasPermission("stuff.muted") || p.isOp()) {
            e.setFormat(Utils.color(getColouredName(p) + "&f: " + message));
        } else {
            e.setCancelled(true);
            p.sendMessage("You are soft muted from the general chat.");
        }

    }

    public static String getColouredName(Player p) {

        String name = p.getName();
        String color;
        String group;

        if (isPlayerInGroup(p,"staff")) {
            color = "&a";
            group = "Staff";
        }
        else if (isPlayerInGroup(p,"helper")) {
            color = "&b";
            group = "Helper";
        }
        else if (isPlayerInGroup(p,"collaborator")) {
            color = "&6";
            group = "Collaborator";
        }
        else if (isPlayerInGroup(p,"booster")) {
            color = "&5";
            group = "Booster";
        }
        else {
            color = "&7";
            group = "Default";
        }

        return color + name;
    }
}
