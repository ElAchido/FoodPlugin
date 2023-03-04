package com.hytacraft.drogas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Drogas extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = new Config();
        config.setDrogas(config.searchDrogas(getConfig()));
        Bukkit.getPluginManager().registerEvents(new Events(this), this);
        getCommand("drogas").setExecutor(new Command(this));
    }

    public Config getConfigManager() { return config; }
}
