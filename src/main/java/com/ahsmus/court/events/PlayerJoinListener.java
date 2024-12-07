package com.ahsmus.court.events;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Court plugin;

    public PlayerJoinListener(Court plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CourtPlayer courtPlayer = new CourtPlayer(player);
        plugin.players.add(courtPlayer);
    }
}
