package com.kryeit.tab;

import com.griefdefender.api.GriefDefender;
import com.kryeit.CreativeStuff;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class PlayerTab implements TabCompleter {
    @Override
    public List<String> onTabComplete (CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            // completions is the returned Lists, starts empty
            List<String> completions = new ArrayList<>();

            // Initialize allTabs
            List<String> allTabs = new ArrayList<>();

            // Get the name of all online players and add it to allTabs
            Bukkit.getOnlinePlayers().forEach(p -> allTabs.add(p.getName()));

            List <String> offlinePlayers = CreativeStuff.getInstance().offlinePlayers;

            if (offlinePlayers.isEmpty()) {
                for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                    if (offlinePlayer == null) continue;
                    if (!GriefDefender.getCore().getUser(offlinePlayer.getUniqueId()).getPlayerData().getClaims().isEmpty()) offlinePlayers.add(offlinePlayer.getName());
                }
            } else {
                allTabs.addAll(offlinePlayers);
            }

            // Add to "completions" all words that have letters that are contained on "commands" list
            for (String allTab : allTabs) {
                if (allTab == null) continue;
                if (allTab.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(allTab);
                }
            }
            return completions;
        }
        return new ArrayList<>();
    }
}
