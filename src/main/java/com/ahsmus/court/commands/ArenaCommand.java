package com.ahsmus.court.commands;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Arena;
import com.ahsmus.court.core.enums.ArenaState;
import com.ahsmus.court.menus.ArenaCreationMenu;
import com.ahsmus.court.menus.ArenaOverviewMenu;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

public class ArenaCommand extends PlayerCommand {

    private final Court plugin;
    private final HashMap<String, SubCommand> subCommands = new HashMap<>();
    private Arena arena;
    private ArenaCreationMenu creationMenu;

    public ArenaCommand(Court plugin) {
        this.plugin = plugin;
        subCommands.put("help", this::handleHelp);
        subCommands.put("list", this::handleList);
        subCommands.put("create", this::handleCreate);
        subCommands.put("remove", this::handleRemove);
        subCommands.put("edit", this::handleEdit);
        subCommands.put("spectate", this::handleSpectate);
    }

    @Override
    public boolean onExecute(Player player, Command command, String s, String[] args) {
        if (!(player.isOp())) return false;

        if (args.length < 1) {
            player.sendMessage("§cBitte gib einen gültigen Unterbefehl ein! Nutze /" + s + " help.");
            return false;
        }

        String subCommand = args[0].toLowerCase();
        if (subCommands.containsKey(subCommand)) {
            String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
            return subCommands.get(subCommand).execute(player, subArgs);
        } else {
            player.sendMessage("§cUnbekannter Unterbefehl: " + subCommand + ". Nutze /" + s + " help.");
            return false;
        }
    }

    private boolean handleHelp(Player player, String[] args) {
        player.sendMessage("§eArena Befehle:");
        player.sendMessage("§7/arena help - Zeigt diese Hilfe an.");
        player.sendMessage("§7/arena list - Zeigt alle Arenen an.");
        player.sendMessage("§7/arena create <name> - Erstellt eine Arena.");
        player.sendMessage("§7/arena remove <name> - Entfernt eine Arena.");
        player.sendMessage("§7/arena edit - Zeigt den Status der aktuellen Arena an.");
        player.sendMessage("§7/arena spectate <name> - Setzt den Zuschauer-Spawn.");
        return true;
    }

    private boolean handleList(Player player, String[] args) {
        ArenaOverviewMenu overviewMenu = new ArenaOverviewMenu(plugin);
        overviewMenu.open(player);
        return true;
    }

    private boolean handleCreate(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cBitte gib einen gültigen Arena-Namen ein!");
            return false;
        }

        //schauen ob name existiert

        String name = args[0];
        arena = new Arena(name, null, null);
        creationMenu = new ArenaCreationMenu(plugin, arena);
        creationMenu.open(player);
        return true;
    }

    private boolean handleRemove(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cBitte gib einen gültigen Arena-Namen ein!");
            return false;
        }

        if (arena.getState() == ArenaState.AVAILABLE) {
            plugin.getArenaManager().getArenas().removeIf(arena -> arena.getName().equalsIgnoreCase(args[0]));
            player.sendMessage("§aArena entfernt!");
            return true;
        } else {
            player.sendMessage("§cDie Arena ist noch in Benutzung!");
            return false;
        }
    }

    private boolean handleEdit(Player player, String[] args) {
        if (arena == null || creationMenu == null) {
            player.sendMessage("§cKeine Arena in der Erstellung!");
            return false;
        }

        creationMenu.open(player);
        return true;
    }

    private boolean handleSpectate(Player player, String[] args) {
        //spectate
        return true;
    }
}
