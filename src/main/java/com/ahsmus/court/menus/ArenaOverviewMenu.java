package com.ahsmus.court.menus;

import com.ahsmus.court.core.Arena;
import com.ahsmus.court.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class ArenaOverviewMenu {

    public static Inventory getArenaOverviewMenu(List<Arena> arenas) {
        Inventory inventory = Bukkit.createInventory(null, 9, "§eArenen");

        for (Arena arena : arenas) {
            inventory.addItem(new ItemBuilder(Material.WOOL)
                    .setName("§e" + arena.name)
                    .setLore(List.of("§7Status: " + arena.state.name()))
                    .build());
        }

        return inventory;

    }
}
