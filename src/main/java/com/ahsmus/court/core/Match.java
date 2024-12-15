package com.ahsmus.court.core;

import com.ahsmus.court.Court;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class Match {

    private final Court plugin;

    private final CourtPlayer player1;
    private final CourtPlayer player2;
    private final Kit kit;
    private final Arena arena;

    public Match(Court plugin, CourtPlayer player1, CourtPlayer player2, Kit kit) {
        this.player1 = player1;
        this.player2 = player2;
        this.kit = kit;
        this.plugin = plugin;
        arena = plugin.getArenaManager().getRandomArena();
    }


    public void start() {
        player1.sendMessage("Match found! You are playing against " + player2.getName() + "!");
        player2.sendMessage("Match found! You are playing against " + player1.getName() + "!");
        lobbyCountdown();

        player1.teleport(arena.getSpawn1());
        player2.teleport(arena.getSpawn2());
        player1.setPlaying(kit);
        player2.setPlaying(kit);

        //Player locked & seated
        gameCountdown();
    }

    public void end() {

    }


    private void lobbyCountdown() {
        new BukkitRunnable() {
            int time = 5;

            @Override
            public void run() {
                if (time <= 0) {
                    cancel();
                    return;
                }

                player1.sendMessage("Match beginnt in " + time + " Sekunde(n)!");
                player2.sendMessage("Match beginnt in " + time + " Sekunde(n)!");
                time--;
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    private void gameCountdown() {
        new BukkitRunnable() {
            int time = 5;

            @Override
            public void run() {
                if (time <= 0) {
                    cancel();
                    return;
                }

                player1.sendMessage("Match beginnt in " + time + " Sekunde(n)!");
                player2.sendMessage("Match beginnt in " + time + " Sekunde(n)!");
                time--;
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

}
