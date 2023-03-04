package com.hytacraft.drogas;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class DrogaItem {

    private final String id;
    private final String displayName;
    private final Material material;
    private final List<String> lore;
    private final String message;
    private final List<PotionEffect> potionEffects;
    private final Boolean glow;

    public DrogaItem
            (String id,
             String displayName,
             Material material,
             List<String> lore,
             String message,
             List<PotionEffect> potionEffects,
             Boolean glow)
    {
        this.id = id;
        this.displayName = displayName;
        this.material = material;
        this.lore = lore;
        this.message = message;
        this.potionEffects = potionEffects;
        this.glow = glow;
    }

    public List<PotionEffect> getPotionEffects() {
        return potionEffects;
    }

    public List<String> getLore() {
        return lore;
    }

    public Boolean isGlow() {
        return glow;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }
}
