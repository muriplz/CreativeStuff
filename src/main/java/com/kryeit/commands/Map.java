package com.kryeit.commands;

import com.kryeit.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Map implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        Location l = player.getLocation();

        TextComponent message = new TextComponent(Utils.color("Dynmap -> &9&nhttp://dynmap.kryeit.com/"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
                "http://dynmap.kryeit.com/?zoom=6&nopanel=true&nocompass=true&hidechat=true&x=" +
                l.getBlockX() + "&z=" + l.getBlockZ()));
        player.spigot().sendMessage(message);
        return true;
    }
}
