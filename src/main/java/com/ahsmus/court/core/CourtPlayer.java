package com.ahsmus.court.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.bukkit.entity.Player;

import static com.ahsmus.court.menus.SpawnItems.setSpawnInventory;


@Getter
public class CourtPlayer implements Player {

    @Delegate
    public final Player player;

    @Setter
    public PlayerState state;


    public CourtPlayer(Player player) {
        this.player = player;
        this.state = PlayerState.LOBBY;
        setSpawnInventory(this.player.getInventory());
    }
}