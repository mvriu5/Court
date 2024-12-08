package com.ahsmus.court.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand implements CommandExecutor {

    public abstract boolean onExecute(Player player, Command command, String s, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        return onExecute(player, command, s, args);
    }
}
