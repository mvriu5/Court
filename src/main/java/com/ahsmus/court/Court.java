package com.ahsmus.court;

import com.ahsmus.court.core.CourtPlayer;
import com.ahsmus.court.events.*;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Court extends JavaPlugin {

    public List<CourtPlayer> players;

    @Override
    public void onEnable() {
        players = new ArrayList<>();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerKickListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
