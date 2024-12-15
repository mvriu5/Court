package com.ahsmus.court.core;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class Kit {

    private final String name;
    private final List<ItemStack> items;
    private final Material displayMaterial;

    public Kit(String name, List<ItemStack> items, Material displayMaterial) {
        this.name = name;
        this.items = items;
        this.displayMaterial = displayMaterial;
    }

}
