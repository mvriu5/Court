package com.ahsmus.court.core;

import com.ahsmus.court.core.enums.ArenaState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Arena {

    private final String name;
    private final List<Kit> compatibleKits;

    @Setter
    private Location spawn1;

    @Setter
    private Location spawn2;

    @Setter
    private ArenaState state;

    public Arena(String name, Location spawn1, Location spawn2) {
        this.name = name;
        this.state = ArenaState.PENDING;
        this.compatibleKits = new ArrayList<>();
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
    }
}
