package com.yiorno.dismountfixer;

import es.pollitoyeye.vehicles.events.VehicleExitEvent;
import es.pollitoyeye.vehicles.listeners.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class DismountFixer extends JavaPlugin implements Listener {

    private ArrayList<String> cooldown = new ArrayList<>();
    static DismountFixer instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("DFが起動しました");
        getServer().getPluginManager().registerEvents(new Event(), this);
        //getServer().getPluginManager().registerEvents(new EventListener(), this);
        instance = this;
        Variable val = new Variable();
        val.registerMaterials();
        //WorldGuardPlugin wg = getWorldGuard();
        //protocolManager = ProtocolLibrary.getProtocolManager();
        //ProtocolLibrary.getProtocolManager().addPacketListener(new InputListener(this,  ListenerPriority.HIGHEST, new PacketType[]{PacketType.Play.Client.STEER_VEHICLE}));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

