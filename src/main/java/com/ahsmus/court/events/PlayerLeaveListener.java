package com.ahsmus.court.events;

import com.ahsmus.court.Court;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    private final Court plugin;

    public PlayerLeaveListener(Court plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        plugin.players.stream()
            .filter(p -> p.player.getUniqueId().equals(player.getUniqueId()))
            .findFirst()
            .ifPresent(plugin.players::remove);
    }
}
