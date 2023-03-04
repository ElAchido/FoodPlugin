package com.hytacraft.drogas;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Command implements CommandExecutor {

    private final Drogas plugin;

    public Command(Drogas plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length == 1 && args[0].equals("reload")) {
                plugin.reloadConfig();
                plugin.getConfigManager().setDrogas(plugin.getConfigManager().searchDrogas(plugin.getConfig()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDROGAS &8» &aConfiguración recargada."));
            } else if (args.length == 3 && args[0].equals("give")) {
                for (DrogaItem item : plugin.getConfigManager().getDrogas()) {
                    if (item.getId().equals(args[1])) {
                        Player player = Bukkit.getPlayer(args[2]);
                        if (player != null) {
                            ItemStack droga = new ItemStack(item.getMaterial());
                            ItemMeta meta = droga.getItemMeta();
                            meta.setLore(item.getLore());
                            meta.setDisplayName(item.getDisplayName());
                            if (item.isGlow()) {
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            }
                            droga.setItemMeta(meta);
                            player.getInventory().addItem(droga);
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDROGAS &8» &fHas &adado &fla droga " + item.getDisplayName() + "&f."));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDROGAS &8» &fHas &2recibido &fla droga " + item.getDisplayName() + "&f."));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDROGAS &8» &cJugador " + args[2] + " no válido."));
                        }
                        return false;
                    }
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDROGAS &8» &cNo hay un item con ese nombre."));
            }
        }
        return false;
    }
}
