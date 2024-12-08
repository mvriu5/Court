package com.ahsmus.court.managers;

import com.ahsmus.court.core.Arena;
import com.ahsmus.court.core.ArenaState;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    public List<Arena> arenas;

    public ArenaManager() {
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
