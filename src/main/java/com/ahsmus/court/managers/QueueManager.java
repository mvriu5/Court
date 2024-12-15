package com.ahsmus.court.managers;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.core.Kit;
import com.ahsmus.court.core.Match;
import lombok.Getter;

import java.util.*;

@Getter
public class QueueManager {

    private final Court plugin;
    private final Map<Kit, Queue<CourtPlayer>> queues;

    public QueueManager(Court plugin) {
        this.plugin = plugin;
        this.queues = new HashMap<>();
    }

    public void addPlayer(CourtPlayer player, Kit kit) {
        Queue<CourtPlayer> queue = queues.computeIfAbsent(kit, k -> new LinkedList<>());
        queue.add(player);

        if (queue.size() >= 2) {
            CourtPlayer p1 = queue.poll();
            CourtPlayer p2 = queue.poll();

            Match match = new Match(plugin, p1, p2, kit);
            plugin.getGameManager().addMatch(match);
        }
    }

    public void removePlayer(CourtPlayer player) {
        for(Queue<CourtPlayer> queue : queues.values()) {
            queue.remove(player);
        }
    }
}
