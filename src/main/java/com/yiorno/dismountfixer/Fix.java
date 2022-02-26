package com.yiorno.dismountfixer;

import es.pollitoyeye.vehicles.interfaces.Vehicle;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

import static com.yiorno.dismountfixer.DismountFixer.instance;

public class Fix {

    public void fixMobs(Entity player, Entity entity){

        Location playerLoc = player.getLocation();
        Location entityLoc = entity.getLocation();
        float playerYaw = player.getLocation().getYaw();
        float playerPitch = player.getLocation().getPitch();
        Block block = playerLoc.getBlock();
        boolean doFixPane = false;
        boolean doFixBlock = false;

        //人に乗っていた場合
        if(entity instanceof Player) {


            //Detect

            return;

            //LOOP_I:
            //for (int y = -1; y <= 2; y++) {
            //    if (!block.getRelative(0, y, 0).getType().equals(Material.AIR)) {
            //        doFix = true;
            //        break LOOP_I;
            //    }
            //}

            //if(doFix == false){
            //    return;
            //}

            //降りた人をずらす
            //World playerWorld = playerLoc.getWorld();
            //int locx = (int)playerLoc.getX();
            //int locy = (int)playerLoc.getY() - 2;
            //int locz = (int)playerLoc.getZ();

            //Location nloc = new Location(playerWorld, locx, locy, locz);
            //player.teleport(nloc);

            //player.sendMessage("いてっ！頭をぶつけました");
            //return;


        } else {

            LOOP_I:
            for (int z = -1; z <= 1; z++) {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 2; y++) {
                        if (Variable.glassPanes.contains(block.getRelative(x, y, z).getType())) {
                            doFixPane = true;
                            break LOOP_I;
                        }
                    }
                }
            }

            if(!doFixPane) {
                LOOP_I:
                for (int z = -1; z <= 1; z++) {
                    for (int x = -1; x <= 1; x++) {
                        for (int y = 1; y <= 2; y++) {
                            if (Variable.glasses.contains(block.getRelative(x, y, z).getType())) {
                                doFixBlock = true;
                                break LOOP_I;
                            }
                        }
                    }
                }
            }

            if((!doFixPane) && (!doFixBlock)){
                return;
            }

            if(!(entity instanceof Vehicle)) {

                Location nloc = null;

                //乗っていたものをずらす
                World entityWorld = entityLoc.getWorld();
                int locx = (int) entityLoc.getBlockX();
                int locy = (int) entityLoc.getBlockY();
                int locz = (int) entityLoc.getBlockZ();

                World playerWorld = playerLoc.getWorld();
                float locxpd = (float) playerLoc.getX();
                float loczpd = (float) playerLoc.getZ();
                int locxp = (int) Math.round(locxpd);
                int locyp = (int) playerLoc.getBlockY();
                int loczp = (int) Math.round(loczpd);

                if(doFixPane){
                    nloc = new Location(entityWorld, locx, locy, locz);
                }

                if(doFixBlock){
                    nloc = new Location(entityWorld, locx+0.5, locy, locz+0.5);
                }

                Location nlocp = new Location(playerWorld, locxp, locyp+1, loczp, playerYaw, playerPitch);

                entity.teleport(nloc);

                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                scheduler.scheduleSyncDelayedTask(instance, new Runnable() {
                    @Override
                    public void run() {
                        player.teleport(nlocp);
                    }
                }, 1L);

            } else {

                return;

            }

        }

    }

    public void fixVehicles(Player player, Vehicle vehicle){

        if (player instanceof Player) {

            Location loc = player.getLocation();
            Block block = loc.getBlock();
            boolean doFixPane = false;
            boolean doFixBlock = false;

            LOOP_I:
            for(int z = -1; z <= 1; z++) {
                for (int x = -1; x <= 1; x++) {
                    for (int y = 1; y <= 2; y++) {
                        if (Variable.glassPanes.contains(block.getRelative(x, y, z).getType())) {
                            doFixPane = true;
                            break LOOP_I;
                        }
                    }
                }
            }

            if(!doFixPane) {

                LOOP_I:
                for (int z = -1; z <= 1; z++) {
                    for (int y = 1; y <= 2; y++) {
                        for (int x = -1; x <= 1; x++) {
                            if (block.getRelative(x, y, z).getType() != Material.AIR) {
                                doFixBlock = true;
                                break LOOP_I;
                            }
                        }
                    }
                }

            }


            if ((!doFixPane) && (!doFixBlock))  {

                return;

            } else {
                //player.sendMessage(ChatColor.YELLOW + "もう少し壁から離れたところで降りてください");
                //vehicle.dismounted();

                Entity entity = vehicle.getMainStand();
                Location entityLoc = vehicle.getMainStand().getLocation();
                Location nloc = null;

                World entityWorld = entityLoc.getWorld();
                float locxd = (float) entityLoc.getX();
                float loczd = (float) entityLoc.getZ();

                int locx = Math.round(locxd);
                int locy = (int) entityLoc.getBlockY();
                int locz = Math.round(loczd);

                int locxp = (int) entityLoc.getBlockX();
                int loczp = (int) entityLoc.getBlockZ();


                if(doFixPane){
                    nloc = new Location(entityWorld, locx, locy, locz);
                }

                if(doFixBlock){
                    nloc = new Location(entityWorld, locxp+0.5, locy, loczp+0.5);
                }

                if((doFixPane) && (doFixBlock)){
                    nloc = new Location(entityWorld, locx+0.5, locy, locz+0.5);
                }

                entity.teleport(nloc);

                return;
            }

        }

    }

    public void fixRegionEntry(Player player){

        List<Entity> passenger = player.getPassengers();
        int size = passenger.size();

        if(size != 0) {

            player.sendMessage(ChatColor.YELLOW + "肩車しながらここへ入ることはできません");
            player.sendMessage(""+passenger);

            for (Entity p : passenger) {
                player.removePassenger(p);
                p.sendMessage(ChatColor.YELLOW + "肩車しながらここへ入ることはできません");
            }

        }

        return;
    }

}
