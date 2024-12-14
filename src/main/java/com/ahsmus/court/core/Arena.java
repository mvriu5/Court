package com.ahsmus.court.core;

import com.ahsmus.court.core.enums.ArenaState;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    public String name;
    public ArenaState state;
    public List<Kit> compatibleKits;
    public Location spawn1;
    public Location spawn2;

    public Arena(String name, Location spawn1, Location spawn2) {
        this.name = name;
        this.state = ArenaState.PENDING;
        this.compatibleKits = new ArrayList<>();
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
    }
}
