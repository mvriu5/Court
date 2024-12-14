package com.ahsmus.court.managers;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.core.Kit;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@Getter
public class QueueManager {

    private final Court plugin;
    public Queue<CourtPlayer> builduhcQueue;
    public Queue<CourtPlayer> soupQueue;

    public QueueManager(Court plugin) {
        this.plugin = plugin;
        this.builduhcQueue = new LinkedList<>();
        this.soupQueue = new LinkedList<>();
    }

    public void addPlayer(CourtPlayer player, Kit kit) {
        if (Objects.equals(kit.name, "BuildUHC")) {
            builduhcQueue.add(player);
        }
        if (Objects.equals(kit.name, "Soup")) {
            soupQueue.add(player);
        }
    }

    public void removePlayer(CourtPlayer player) {
        builduhcQueue.stream().filter(p -> p.equals(player)).forEach(builduhcQueue::remove);
        soupQueue.stream().filter(p -> p.equals(player)).forEach(soupQueue::remove);
    }
}
