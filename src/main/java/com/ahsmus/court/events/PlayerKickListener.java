package com.ahsmus.court.events;

import com.ahsmus.court.Court;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKickListener implements Listener {

    private final Court plugin;

    public PlayerKickListener(Court plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();

        plugin.players.stream()
                .filter(p -> p.player.getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .ifPresent(plugin.players::remove);
    }
}
