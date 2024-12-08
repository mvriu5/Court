package com.ahsmus.court.core;

import org.bukkit.Location;

public class Arena {

    public String name;
    public ArenaState state;
    public Location spawn1;
    public Location spawn2;

    public Arena(String name, Location spawn1, Location spawn2) {
        this.name = name;
        this.state = ArenaState.PENDING;
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
    }
}
