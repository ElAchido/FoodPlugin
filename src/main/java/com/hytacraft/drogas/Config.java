package com.hytacraft.drogas;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Config {

    public List<DrogaItem> drogas = new ArrayList<>();

    public List<DrogaItem> searchDrogas(FileConfiguration config) {
        List<DrogaItem> drogas = new ArrayList<>();
        Set<String> drogasStrings = config.getConfigurationSection("drogas").getKeys(false);
        for (String droga : drogasStrings) {
            Material material = Material.valueOf(config.getString("drogas." + droga + ".material"));
            String displayName = ChatColor.translateAlternateColorCodes('&', config.getString("drogas." + droga + ".display-name"));
            List<String> lore = new ArrayList<>();
            for (String line : config.getStringList("drogas." + droga + ".lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            String message = ChatColor.translateAlternateColorCodes('&', config.getString("drogas." + droga + ".message"));
            List<PotionEffect> effects = new ArrayList<>();
            for (String effect : config.getStringList("drogas." + droga + ".effects")) {
                String[] effectSplit = effect.split(":");
                effects.add(new PotionEffect(PotionEffectType.getByName(effectSplit[0]), Integer.parseInt(effectSplit[2]) * 20, Integer.parseInt(effectSplit[1])));
            }
            Boolean isGlow = config.getBoolean("drogas." + droga + ".glow");
            drogas.add(new DrogaItem(droga, displayName, material, lore, message, effects, isGlow));
        }
        return drogas;
    }

    public List<DrogaItem> getDrogas() { return drogas; }
    public void setDrogas(List<DrogaItem> listDrogas) { drogas = listDrogas; }
}
