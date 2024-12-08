package com.ahsmus.court.menus;

import com.ahsmus.court.core.Arena;
import com.ahsmus.court.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class ArenaCreationMenu {

    public static Inventory getArenaCreationInventory(Arena arena) {
        Inventory inventory = Bukkit.createInventory(null, 9, "§8Arena erstellen");

        ItemBuilder diamond = new ItemBuilder(Material.DIAMOND_BLOCK);
        diamond.setName(arena.name);

        ItemBuilder spawn1;
        if (arena.spawn1 == null) {
            spawn1 = new ItemBuilder(Material.BEDROCK);
            spawn1.setName("§8Spawn 1 not set");
        } else {
            spawn1 = new ItemBuilder(Material.GLOWSTONE);
            spawn1.setName("§8Spawn 1 Set");
        }

        ItemBuilder spawn2;
        if (arena.spawn2 == null) {
            spawn2 = new ItemBuilder(Material.BEDROCK);
            spawn2.setName("§8Spawn 2 not set");
        } else {
            spawn2 = new ItemBuilder(Material.GLOWSTONE);
            spawn2.setName("§8Spawn 2 Set");
        }

        inventory.setItem(0, diamond.build());
        inventory.setItem(1, spawn1.build());
        inventory.setItem(2, spawn2.build());

        return inventory;
    }
}
