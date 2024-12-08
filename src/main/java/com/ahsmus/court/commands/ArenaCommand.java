package com.ahsmus.court.commands;

import com.ahsmus.court.Court;
import com.ahsmus.court.core.Arena;
import com.ahsmus.court.core.ArenaState;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

import static com.ahsmus.court.menus.ArenaCreationMenu.getArenaCreationInventory;
import static com.ahsmus.court.menus.ArenaOverviewMenu.getArenaOverviewMenu;

public class ArenaCommand extends PlayerCommand {

    private final Court plugin;
    private final HashMap<String, SubCommand> subCommands = new HashMap<>();
    private Arena arena;

    public ArenaCommand(Court plugin) {
        this.plugin = plugin;
        subCommands.put("help", this::handleHelp);
        subCommands.put("list", this::handleList);
        subCommands.put("create", this::handleCreate);
        subCommands.put("remove", this::handleRemove);
        subCommands.put("status", this::handleStatus);
        subCommands.put("spawn1", this::handleSpawn1);
        subCommands.put("spawn2", this::handleSpawn2);
        subCommands.put("finish", this::handleFinish);
        subCommands.put("spectate", this::handleSpectate);
    }

    @Override
    public boolean onExecute(Player player, Command command, String s, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cBitte gib einen gültigen Unterbefehl ein! Nutze /" + s + " help.");
            return false;
        }

        String subCommand = args[0].toLowerCase();
        if (subCommands.containsKey(subCommand)) {
            String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
            return subCommands.get(subCommand).execute(player, args);
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
        player.sendMessage("§7/arena status - Zeigt den Status der aktuellen Arena an.");
        player.sendMessage("§7/arena spawn1 - Setzt den ersten Spawn.");
        player.sendMessage("§7/arena spawn2 - Setzt den zweiten Spawn.");
        player.sendMessage("§7/arena finish - Beendet die Arena-Erstellung.");
        player.sendMessage("§7/arena spectate <name> - Setzt den Zuschauer-Spawn.");
        return true;
    }

    private boolean handleList(Player player, String[] args) {
        player.openInventory(getArenaOverviewMenu(plugin.getArenaManager().arenas));
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
        player.openInventory(getArenaCreationInventory(arena));
        return true;
    }

    private boolean handleRemove(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cBitte gib einen gültigen Arena-Namen ein!");
            return false;
        }

        if (arena.state == ArenaState.AVAILABLE) {
            plugin.getArenaManager().arenas.removeIf(arena -> arena.name.equalsIgnoreCase(args[0]));
            player.sendMessage("§aArena entfernt!");
            return true;
        } else {
            player.sendMessage("§cDie Arena ist noch in Benutzung!");
            return false;
        }
    }

    private boolean handleStatus(Player player, String[] args) {
        if (arena == null) {
            player.sendMessage("§cKeine Arena in der Erstellung!");
            return false;
        }

        player.openInventory(getArenaCreationInventory(arena));
        return true;
    }

    private boolean handleSpawn1(Player player, String[] args) {
        if (arena == null) {
            player.sendMessage("§cKeine Arena in der Erstellung!");
            return false;
        }

        arena.spawn1 = player.getLocation();
        player.sendMessage("§aSpawn 1 gesetzt!");
        return true;
    }

    private boolean handleSpawn2(Player player, String[] args) {
        if (arena == null) {
            player.sendMessage("§cKeine Arena in der Erstellung!");
            return false;
        }

        arena.spawn2 = player.getLocation();
        player.sendMessage("§aSpawn 2 gesetzt!");
        return true;
    }

    private boolean handleFinish(Player player, String[] args) {
        if (arena == null) {
            player.sendMessage("§cKeine Arena in der Erstellung!");
            return false;
        }

        if (arena.spawn1 == null || arena.spawn2 == null) {
            player.sendMessage("§cBitte setze beide Spawns!");
            return false;
        }

        plugin.getArenaManager().arenas.add(arena);
        player.sendMessage("§aArena erstellt!");
        arena = null;
        return true;
    }

    private boolean handleSpectate(Player player, String[] args) {
        //spectate
        return true;
    }
}
