package com.ahsmus.court.managers;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Match;
import com.ahsmus.court.core.enums.ArenaState;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final Court plugin;
    private final List<Match> matches;

    public GameManager(Court plugin) {
        this.plugin = plugin;
        this.matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        matches.add(match);
        match.getArena().setState(ArenaState.INGAME);
        match.start();
    }

    public void removeMatch(Match match) {
        matches.remove(match);
        match.getArena().setState(ArenaState.RESTARTING);
        match.end();
    }

}
