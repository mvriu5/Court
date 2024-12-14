package com.ahsmus.court.managers;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.menus.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;

public class MenuManager implements Listener {

    private final Court plugin;
    private Map<CourtPlayer, Menu> openMenus;

    public MenuManager(Court plugin) {
        this.plugin = plugin;
        openMenus = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void openMenu(CourtPlayer player, Menu menu) {
        openMenus.put(player, menu);
        menu.open(player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        CourtPlayer courtPlayer = plugin.players.stream()
                .filter(p -> p.player.getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);

        if (courtPlayer == null) return;

        Menu menu = openMenus.get(courtPlayer);

        if (menu != null && event.getInventory().equals(menu.getInventory())) {
            event.setCancelled(true);
            menu.handleMenuClick(event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;

        Player player = (Player) event.getPlayer();

        CourtPlayer courtPlayer = plugin.players.stream()
                .filter(p -> p.player.getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);

        if (courtPlayer == null) return;

        openMenus.remove(courtPlayer);
    }

}
