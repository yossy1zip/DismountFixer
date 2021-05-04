package com.yiorno.dismountfixer;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import es.pollitoyeye.vehicles.events.VehicleExitEvent;
import net.minecraft.server.v1_16_R3.PacketPlayInSteerVehicle;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;

public final class DismountFixer extends JavaPlugin implements Listener {

    private ProtocolManager protocolManager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("DFが起動しました");
        //getServer().getPluginManager().registerEvents(Botsu, this);
        protocolManager = ProtocolLibrary.getProtocolManager();
        ProtocolLibrary.getProtocolManager().addPacketListener(new InputListener(this,  ListenerPriority.HIGHEST, new PacketType[]{PacketType.Play.Client.STEER_VEHICLE}));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

