package com.ahsmus.court.events;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.core.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final Court plugin;

    public BlockPlaceListener(Court plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        CourtPlayer courtPlayer = plugin.players.stream()
                .filter(p -> p.player.getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);

        if (courtPlayer == null) return;

        if (courtPlayer.state == PlayerState.SPECTATING || courtPlayer.state == PlayerState.LOBBY) {
            event.setCancelled(true);
        }
    }
}
