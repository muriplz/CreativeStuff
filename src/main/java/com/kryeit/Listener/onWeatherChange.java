package com.kryeit.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class onWeatherChange implements Listener {

    @EventHandler
    public void onChange (WeatherChangeEvent e) {
        if (e.toWeatherState()) e.setCancelled(ThreadLocalRandom.current().nextBoolean());
    }
}
