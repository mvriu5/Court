package com.ahsmus.court.menus;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.entity.Player;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected Player player;

    public Menu() {
        this.inventory = Bukkit.createInventory(this, getSize(), getTitle());
        setMenuItems();
    }

    public abstract String getTitle();
    public abstract int getSize();
    public abstract void setMenuItems();
    public abstract void handleMenuClick(InventoryClickEvent event);
    public abstract void handleMenuClose(InventoryCloseEvent event);

    public void open(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
