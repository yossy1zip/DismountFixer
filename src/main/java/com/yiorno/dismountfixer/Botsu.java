package com.yiorno.dismountfixer;

import es.pollitoyeye.vehicles.events.VehicleExitEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

public class Botsu {

    private ArrayList<String> cooldown = new ArrayList<String>();

    @EventHandler
    public void onDismountVehicle(VehicleExitEvent e) {
        Player entity = e.getPlayer();

        if (entity instanceof Player) {

            //World world = entity.getWorld();
            Location loc = entity.getLocation();

            //int locx = (int)loc.getBlockX();
            //int locy = (int)loc.getBlockY();
            //int locz = (int)loc.getBlockZ();

            //Location nloc = new Location(world, locx, locy, locz);
            //Block block = nloc.getBlock();

            //if(block.getType().equals(Material.GLASS_PANE)) {
            //    entity.sendMessage("テストメッセージ3です");
            //}

            Block block = loc.getBlock();
            Boolean cancel = false;

            LOOP_I:
            for(int z = -1; z <= 1; z++) {
                for(int x = -1; x <= 1; x++) {
                    for(int y = -1; y <= 1; y++) {
                        if(block.getRelative(x, y, z).getType().equals(Material.GLASS_PANE)) {
                            cancel = true;
                            break LOOP_I;
                        }
                    }
                }
            }

            if ((cancel == false) && (cooldown.contains(entity.getName()))) {
                e.setCancelled(true);
            }


            if ((cancel == true) && (!cooldown.contains(entity.getName()))) {
                entity.sendMessage(ChatColor.YELLOW + "もう少し壁から離れたところで降りてください");
                e.setCancelled(true);
                cooldown.add(entity.getName());
                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                //scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                //    @Override
                //    public void run() {
                //        cooldown.remove(entity.getName());
                //    }
                //}, 20L);

            }



        }
    }
}
