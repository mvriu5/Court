package com.ahsmus.court.menus;

import com.ahsmus.court.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.PlayerInventory;

public class SpawnItems {

    public static void setSpawnInventory(PlayerInventory inventory) {
        inventory.clear();

        ItemBuilder builder = new ItemBuilder(Material.CHEST);
        builder.setName("Kit Selector");
        inventory.setItem(4, builder.build());
    }
}
