package com.hytacraft.drogas;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class Events implements Listener {

    private final Drogas plugin;

    public Events(Drogas plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        ItemStack itemExample = e.getItem();
        if (itemExample == null) return;
        for (DrogaItem item : plugin.getConfigManager().getDrogas()) {
            if (itemExample.hasItemMeta() && itemExample.getType().equals(item.getMaterial())) {
                ItemMeta meta = itemExample.getItemMeta();
                if (meta != null && meta.hasLore() && meta.hasDisplayName() && meta.getDisplayName().equals(item.getDisplayName()) && Objects.equals(meta.getLore(), item.getLore())) {
                    itemExample.setAmount(itemExample.getAmount() - 1);
                    Player player = e.getPlayer();
                    for (PotionEffect effect : item.getPotionEffects()) {
                        player.addPotionEffect(effect);
                    }
                    player.sendMessage(item.getMessage());
                }
            }
        }
    }
}
