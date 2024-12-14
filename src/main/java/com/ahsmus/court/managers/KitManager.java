package com.ahsmus.court.managers;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Kit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class KitManager {

    private final Court plugin;
    public List<Kit> kits;

    public KitManager(Court plugin) {
        this.plugin = plugin;
        kits = new ArrayList<>();
    }

    public Kit getKit(String name) {
        for (Kit kit : kits) {
            if (kit.name.equals(name)) {
                return kit;
            }
        }
        return null;
    }

    public void addKit(Kit kit) {
        kits.add(kit);
    }

    public void removeKit(Kit kit) {
        kits.remove(kit);
    }
}
