package com.ahsmus.court.menus;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Arena;
import com.ahsmus.court.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ArenaCreationMenu extends Menu {

    private final Court plugin;
    private final Arena arena;

    public ArenaCreationMenu(Court plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
    }

    @Override
    public String getTitle() {
        return "Create Arena";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void setMenuItems() {
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

        ItemBuilder kits;
        if (arena.compatibleKits.isEmpty()) {
            kits = new ItemBuilder(Material.BOOK);
            kits.setName("§8Kits not set");
        } else {
            kits = new ItemBuilder(Material.WRITTEN_BOOK);
            kits.setName("§8Kits Set");
        }

        ItemBuilder save = new ItemBuilder(Material.EMERALD_BLOCK);
        save.setName("§aSave Arena");

        inventory.setItem(0, diamond.build());
        inventory.setItem(1, spawn1.build());
        inventory.setItem(2, spawn2.build());
        inventory.setItem(3, kits.build());
        inventory.setItem(8, save.build());
    }

    @Override
    public void handleMenuClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        switch (event.getSlot()) {
            case 1:
                arena.spawn1 = player.getLocation();
                player.sendMessage("§aSpawn 1 set!");
                break;
            case 2:
                arena.spawn2 = player.getLocation();
                player.sendMessage("§aSpawn 2 set!");
                break;
            case 3:
                player.closeInventory();
                ArenaKitSelectionMenu kitSelectionMenu = new ArenaKitSelectionMenu(plugin, arena);
                kitSelectionMenu.open(player);
                break;
            case 8:
                if (arena.spawn1 == null || arena.spawn2 == null || arena.compatibleKits.isEmpty()) {
                    player.sendMessage("§cPlease set all values before saving!");
                    return;
                }

                plugin.getArenaManager().addArena(arena);
                player.sendMessage("§aArena saved!");
                break;
        }

        setMenuItems();
    }

    @Override
    public void handleMenuClose(InventoryCloseEvent event) {
    }
}
