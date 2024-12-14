package com.ahsmus.court.menus;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Arena;
import com.ahsmus.court.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;

public class ArenaOverviewMenu extends Menu {

    private final Court plugin;

    public ArenaOverviewMenu(Court plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getTitle() {
        return "Arenas";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void setMenuItems() {
        for (Arena arena : plugin.getArenaManager().getArenas()) {
            inventory.addItem(new ItemBuilder(Material.WOOL)
                    .setName("§e" + arena.name)
                    .setLore(List.of("§7Status: " + arena.state.name()))
                    .build());
        }
    }

    @Override
    public void handleMenuClick(InventoryClickEvent event) {

    }

    @Override
    public void handleMenuClose(InventoryCloseEvent event) {

    }
}
