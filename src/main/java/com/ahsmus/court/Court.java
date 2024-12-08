package com.ahsmus.court;

import com.ahsmus.court.commands.ArenaCommand;
import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.events.*;
import com.ahsmus.court.managers.ArenaManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Court extends JavaPlugin {

    public List<CourtPlayer> players;
    public ArenaManager arenaManager;

    @Override
    public void onEnable() {
        players = new ArrayList<>();
        arenaManager = new ArenaManager();
        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerKickListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    private void registerCommands() {
        getCommand("arena").setExecutor(new ArenaCommand(this));
    }
}
