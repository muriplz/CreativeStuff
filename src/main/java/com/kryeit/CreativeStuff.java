package com.kryeit;

import com.kryeit.Listener.*;
import com.kryeit.commands.*;
import com.kryeit.tab.BasicPlayerTab;
import com.kryeit.tab.GamemodeTab;
import com.kryeit.tab.PlayerTab;
import com.kryeit.tab.ReturnEmptyTab;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CreativeStuff extends JavaPlugin {

    public static AFKPlusPlayerAPI afkPlusPlayerAPI = new AFKPlusPlayerAPI();
    public final List<UUID> sentTrapped = new ArrayList<>();
    public static CreativeStuff instance;
    public final List<UUID> warned = new ArrayList<>();
    public final List<UUID> flyEnabled = new ArrayList<>();
    public final List<String> offlinePlayers = new ArrayList<>();

    public void onEnable () {

        instance = this;

        registerEvent(new onMessageSent());
        registerEvent(new onAFKToggle());
        registerEvent(new onJoin());
        registerEvent(new onWeatherChange());
        registerEvent(new onEndermanTake());
        registerEvent(new onChickenEgg());

        registerBasicCommand("online", new Online());
        registerBasicCommand("discord", new Discord());
        registerBasicCommand("forum", new Forum());
        registerBasicCommand("rules", new Rules());
        registerBasicCommand("patreon", new Patreon());
        registerBasicCommand("kill", new Kill());

        registerCommand("invsee", new InvSee(), new BasicPlayerTab());
        registerCommand("enderinvsee", new EnderInvSee(), new BasicPlayerTab());
        registerCommand("sendcoords", new SendCoords(), new BasicPlayerTab());
        registerCommand("timeplayed", new TimePlayed(), new BasicPlayerTab());
        registerCommand("lastonline", new LastOnline(), new PlayerTab());
        registerCommand("gamemode", new Gamemode(), new GamemodeTab());

    }

    public void registerEvent (Listener listener) {
        getServer().getPluginManager().registerEvents(listener,this);
    }

    public void registerCommand (String command, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        Objects.requireNonNull(getCommand(command)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(tabCompleter);
    }

    public void registerBasicCommand (String command, CommandExecutor commandExecutor) {
        Objects.requireNonNull(getCommand(command)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(new ReturnEmptyTab());
    }



    public void onDisable() {
    }

    public static CreativeStuff getInstance() {
        return instance;
    }
}
