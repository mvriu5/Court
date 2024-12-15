package com.ahsmus.court.menus;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Arena;
import com.ahsmus.court.core.Kit;
import com.ahsmus.court.utils.ItemBuilder;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ArenaKitSelectionMenu extends Menu {

    private final Court plugin;
    public Arena arena;

    public ArenaKitSelectionMenu(Court plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
    }

    @Override
    public String getTitle() {
        return "§8Kit-Selection";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void setMenuItems() {
        for (Kit kit : plugin.getKitManager().getKits()) {
            ItemBuilder item = new ItemBuilder(kit.getDisplayMaterial());
            item.setName(kit.getName());
            inventory.addItem(item.build());
        }
    }

    @Override
    public void handleMenuClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        ItemStack item = event.getCurrentItem();

        if (item.getEnchantments().isEmpty()) {
            item.addEnchantment(Enchantment.LUCK, 1);

            plugin.getKitManager().getKits()
                    .stream()
                    .filter(kit -> Objects.equals(kit.getName(), item.getItemMeta().getDisplayName()))
                    .findFirst()
                    .ifPresent(selectedKit -> arena.getCompatibleKits().add(selectedKit));

        } else {
            item.removeEnchantment(Enchantment.LUCK);

            plugin.getKitManager().getKits()
                    .stream()
                    .filter(kit -> Objects.equals(kit.getName(), item.getItemMeta().getDisplayName()))
                    .findFirst()
                    .ifPresent(selectedKit -> arena.getCompatibleKits().remove(selectedKit));
        }
    }

    @Override
    public void handleMenuClose(InventoryCloseEvent event) {

    }
}
