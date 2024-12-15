package com.ahsmus.court.core;

import com.ahsmus.court.core.enums.PlayerState;
import com.ahsmus.court.utils.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


@Getter
public class CourtPlayer implements Player {

    @Delegate
    public final Player player;

    @Setter
    public PlayerState state;


    public CourtPlayer(Player player) {
        this.player = player;
        this.state = PlayerState.LOBBY;
    }

    public void setLobby() {
        state = PlayerState.LOBBY;

        player.getInventory().clear();
        ItemBuilder builder = new ItemBuilder(Material.CHEST);
        builder.setName("Kit Selector");
        player.getInventory().setItem(4, builder.build());
    }

    public void setPlaying(Kit kit) {
        state = PlayerState.PLAYING;
        player.getInventory().clear();

        for (ItemStack item : kit.getItems()) {
            player.getInventory().addItem(item);
        }
    }
}
