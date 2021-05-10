package com.yiorno.dismountfixer;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.netzkronehd.WGRegionEvents.events.RegionEnterEvent;
import es.pollitoyeye.vehicles.events.VehicleExitEvent;
import es.pollitoyeye.vehicles.interfaces.Vehicle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;

import static com.sk89q.worldguard.protection.flags.StateFlag.State.DENY;

public final class DismountFixer extends JavaPlugin implements Listener {

    private ArrayList<String> cooldown = new ArrayList<>();
    static DismountFixer instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("DFが起動しました");
        getServer().getPluginManager().registerEvents(this, this);
        instance = this;
        Variable val = new Variable();
        val.registerMaterials();
        //protocolManager = ProtocolLibrary.getProtocolManager();
        //ProtocolLibrary.getProtocolManager().addPacketListener(new InputListener(this,  ListenerPriority.HIGHEST, new PacketType[]{PacketType.Play.Client.STEER_VEHICLE}));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onD(EntityDismountEvent e){

        //乗っていた人
        Entity player = e.getEntity();
        //乗られていたエンティティ
        Entity entity = e.getDismounted();

        if (player instanceof Player) {
            Fix fix = new Fix();
            fix.fixMobs(player, entity);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDismountVehicle(VehicleExitEvent e) {
        Player player = e.getPlayer();
        Vehicle vehicle = e.getVehicle();

        Fix fix = new Fix();
        fix.fixVehicles(player, vehicle);
    }

    @EventHandler
    public void onRegionEnter(RegionEnterEvent e) {
        ProtectedRegion region = e.getRegion();
        StateFlag.State flagEntry = region.getFlag(Flags.ENTRY);
        Player player = e.getPlayer();

        if(flagEntry == DENY){
            Fix fix = new Fix();
            fix.fixRegionEntry(player);
        }
        return;
    }

}

