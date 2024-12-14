package com.ahsmus.court.events;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.core.enums.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final Court plugin;

    public BlockBreakListener(Court plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
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
