package com.ahsmus.court.menus;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.core.Kit;
import com.ahsmus.court.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class KitMenu extends Menu {

    private final Court plugin;

    public KitMenu(Court plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getTitle() {
        return "Kits";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void setMenuItems() {
        for (Kit kit : plugin.getKitManager().getKits()) {
            ItemBuilder item = new ItemBuilder(kit.displayMaterial);
            item.setName(kit.name);
            inventory.addItem(item.build());
        }
    }

    @Override
    public void handleMenuClick(InventoryClickEvent event) {
        player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) return;

        CourtPlayer courtPlayer = plugin.getPlayers().stream()
                .filter(p -> p.player.getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);

        if (courtPlayer == null) return;

        String kitName = event.getCurrentItem().getItemMeta().getDisplayName();
        Kit kit = plugin.getKitManager().getKit(kitName);

        if (kit == null) return;

        plugin.getQueueManager().addPlayer(courtPlayer, kit);
        player.closeInventory();
        player.sendMessage("You have joined the " + kit.name + " queue.");
    }

    @Override
    public void handleMenuClose(InventoryCloseEvent event) {

    }
}
