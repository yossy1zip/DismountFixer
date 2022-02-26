package com.yiorno.dismountfixer;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import es.pollitoyeye.vehicles.events.VehicleExitEvent;
import es.pollitoyeye.vehicles.interfaces.Vehicle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.spigotmc.event.entity.EntityDismountEvent;

import static com.sk89q.worldguard.protection.flags.StateFlag.State.DENY;

public class Event implements Listener {

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
    }

    //private WorldGuardPlugin getWorldGuard() {
    //    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
    //    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) return null;
    //    return (WorldGuardPlugin) plugin;
    //}
}
