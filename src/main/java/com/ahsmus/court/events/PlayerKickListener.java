package com.ahsmus.court.events;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
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

        CourtPlayer courtPlayer = plugin.getPlayers().stream()
                .filter(p -> p.player.getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);

        if (courtPlayer == null) return;

        plugin.getPlayers().remove(courtPlayer);
        plugin.getQueueManager().removePlayer(courtPlayer);
    }
}
