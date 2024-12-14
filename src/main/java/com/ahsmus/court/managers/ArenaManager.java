package com.ahsmus.court.managers;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Arena;
import com.ahsmus.court.core.enums.ArenaState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArenaManager {

    private final Court plugin;
    public List<Arena> arenas;

    public ArenaManager(Court plugin) {
        this.plugin = plugin;
        arenas = new ArrayList<>();
    }

    public void addArena(Arena arena) {
        arena.state = ArenaState.AVAILABLE;
        //db.saveArena(arena);
        arenas.add(arena);
    }

    public void removeArena(Arena arena) {
        //db.removeArena(arena);
        arenas.remove(arena);
    }

}
