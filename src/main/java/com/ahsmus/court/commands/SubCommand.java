package com.ahsmus.court.commands;

import org.bukkit.entity.Player;

public interface SubCommand {

    boolean execute(Player player, String[] args);
}
