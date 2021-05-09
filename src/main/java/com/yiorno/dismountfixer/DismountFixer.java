package com.yiorno.dismountfixer;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import es.pollitoyeye.vehicles.events.VehicleExitEvent;
import es.pollitoyeye.vehicles.interfaces.Vehicle;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;
import java.util.List;

import static com.bergerkiller.bukkit.common.utils.EntityUtil.teleport;

public final class DismountFixer extends JavaPlugin implements Listener {

    //private ProtocolManager protocolManager;

    public static List<Material> glassPanes = new ArrayList<>();
    public static List<Material> glasses = new ArrayList<>();
    private ArrayList<String> cooldown = new ArrayList<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("DFが起動しました");
        //getServer().getPluginManager().registerEvents(Botsu, this);
        getServer().getPluginManager().registerEvents(this, this);
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

            Location playerLoc = player.getLocation();
            Location entityLoc = entity.getLocation();
            Block block = playerLoc.getBlock();
            Boolean doFixPane = false;
            Boolean doFixBlock = false;

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

                //Detect
                //Entity mat = entity.getVehicle();

                glassPanes.add(Material.GLASS_PANE);
                glassPanes.add(Material.WHITE_STAINED_GLASS_PANE);
                glassPanes.add(Material.ORANGE_STAINED_GLASS_PANE);
                glassPanes.add(Material.MAGENTA_STAINED_GLASS_PANE);
                glassPanes.add(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                glassPanes.add(Material.YELLOW_STAINED_GLASS_PANE);
                glassPanes.add(Material.LIME_STAINED_GLASS_PANE);
                glassPanes.add(Material.PINK_STAINED_GLASS_PANE);
                glassPanes.add(Material.GRAY_STAINED_GLASS_PANE);
                glassPanes.add(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
                glassPanes.add(Material.CYAN_STAINED_GLASS_PANE);
                glassPanes.add(Material.PURPLE_STAINED_GLASS_PANE);
                glassPanes.add(Material.BLUE_STAINED_GLASS_PANE);
                glassPanes.add(Material.BROWN_STAINED_GLASS_PANE);
                glassPanes.add(Material.GREEN_STAINED_GLASS_PANE);
                glassPanes.add(Material.RED_STAINED_GLASS_PANE);
                glassPanes.add(Material.BLACK_STAINED_GLASS_PANE);

                glasses.add(Material.IRON_BARS);

                glasses.add(Material.OAK_FENCE);
                glasses.add(Material.SPRUCE_FENCE);
                glasses.add(Material.BIRCH_FENCE);
                glasses.add(Material.JUNGLE_FENCE);
                glasses.add(Material.ACACIA_FENCE);
                glasses.add(Material.DARK_OAK_FENCE);
                glasses.add(Material.CRIMSON_FENCE);
                glasses.add(Material.WARPED_FENCE);
                glasses.add(Material.NETHER_BRICK_FENCE);

                glasses.add(Material.COBBLESTONE_WALL);
                glasses.add(Material.MOSSY_COBBLESTONE_WALL);
                glasses.add(Material.BRICK_WALL);
                glasses.add(Material.RED_SANDSTONE_WALL);
                glasses.add(Material.MOSSY_STONE_BRICK_WALL);
                glasses.add(Material.GRANITE_WALL);
                glasses.add(Material.STONE_BRICK_WALL);
                glasses.add(Material.NETHER_BRICK_WALL);
                glasses.add(Material.ANDESITE_WALL);
                glasses.add(Material.RED_NETHER_BRICK_WALL);
                glasses.add(Material.SANDSTONE_WALL);
                glasses.add(Material.END_STONE_BRICK_WALL);
                glasses.add(Material.DIORITE_WALL);
                glasses.add(Material.BLACKSTONE_WALL);
                glasses.add(Material.POLISHED_BLACKSTONE_WALL);
                glasses.add(Material.POLISHED_BLACKSTONE_BRICK_WALL);

                glasses.add(Material.GLASS);
                glasses.add(Material.WHITE_STAINED_GLASS);
                glasses.add(Material.ORANGE_STAINED_GLASS);
                glasses.add(Material.MAGENTA_STAINED_GLASS);
                glasses.add(Material.LIGHT_BLUE_STAINED_GLASS);
                glasses.add(Material.YELLOW_STAINED_GLASS);
                glasses.add(Material.LIME_STAINED_GLASS);
                glasses.add(Material.PINK_STAINED_GLASS);
                glasses.add(Material.GRAY_STAINED_GLASS);
                glasses.add(Material.LIGHT_GRAY_STAINED_GLASS);
                glasses.add(Material.CYAN_STAINED_GLASS);
                glasses.add(Material.PURPLE_STAINED_GLASS);
                glasses.add(Material.BLUE_STAINED_GLASS);
                glasses.add(Material.BROWN_STAINED_GLASS);
                glasses.add(Material.GREEN_STAINED_GLASS);
                glasses.add(Material.RED_STAINED_GLASS);
                glasses.add(Material.BLACK_STAINED_GLASS);

                String materialStr;

                LOOP_I:
                for (int z = -1; z <= 1; z++) {
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 2; y++) {
                            if (glassPanes.contains(block.getRelative(x, y, z).getType())) {
                                doFixPane = true;
                                break LOOP_I;
                            }
                        }
                    }
                }

                if(doFixPane == false) {
                    LOOP_I:
                    for (int z = -1; z <= 1; z++) {
                        for (int x = -1; x <= 1; x++) {
                            for (int y = 1; y <= 2; y++) {
                                if (glasses.contains(block.getRelative(x, y, z).getType())) {
                                    doFixBlock = true;
                                    break LOOP_I;
                                }
                            }
                        }
                    }
                }

                if((doFixPane == false) && (doFixBlock == false)){
                    return;
                }

                if(!(entity instanceof Vehicle) && !(entity instanceof Vehicle)) {

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

                    if(doFixPane == true){
                        nloc = new Location(entityWorld, locx, locy, locz);
                    }

                    if(doFixBlock == true){
                        nloc = new Location(entityWorld, locx+0.5, locy, locz+0.5);
                    }

                    Location nlocp = new Location(playerWorld, locxp, locyp, loczp);

                    entity.teleport(nloc);

                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            player.teleport(nlocp);
                        }
                    }, 5L);

                } else {

                    return;

                }

            }

        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDismountVehicle(VehicleExitEvent e) {
        Player player = e.getPlayer();

        if (player instanceof Player) {

            Location loc = player.getLocation();
            Block block = loc.getBlock();
            Boolean doFixPane = false;
            Boolean doFixBlock = false;
            Vehicle vehicle = e.getVehicle();

            glassPanes.add(Material.GLASS_PANE);
            glassPanes.add(Material.WHITE_STAINED_GLASS_PANE);
            glassPanes.add(Material.ORANGE_STAINED_GLASS_PANE);
            glassPanes.add(Material.MAGENTA_STAINED_GLASS_PANE);
            glassPanes.add(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            glassPanes.add(Material.YELLOW_STAINED_GLASS_PANE);
            glassPanes.add(Material.LIME_STAINED_GLASS_PANE);
            glassPanes.add(Material.PINK_STAINED_GLASS_PANE);
            glassPanes.add(Material.GRAY_STAINED_GLASS_PANE);
            glassPanes.add(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
            glassPanes.add(Material.CYAN_STAINED_GLASS_PANE);
            glassPanes.add(Material.PURPLE_STAINED_GLASS_PANE);
            glassPanes.add(Material.BLUE_STAINED_GLASS_PANE);
            glassPanes.add(Material.BROWN_STAINED_GLASS_PANE);
            glassPanes.add(Material.GREEN_STAINED_GLASS_PANE);
            glassPanes.add(Material.RED_STAINED_GLASS_PANE);
            glassPanes.add(Material.BLACK_STAINED_GLASS_PANE);

            LOOP_I:
            for(int z = -1; z <= 1; z++) {
                for (int x = -1; x <= 1; x++) {
                    for (int y = 1; y <= 2; y++) {
                        if (glassPanes.contains(block.getRelative(x, y, z).getType())) {
                            doFixPane = true;
                            break LOOP_I;
                        }
                    }
                }
            }

            if(doFixPane == false) {

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


            if ((doFixPane == false) && (doFixBlock == false))  {

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


                if(doFixPane == true){
                    nloc = new Location(entityWorld, locx, locy, locz);
                }

                if(doFixBlock == true){
                    nloc = new Location(entityWorld, locxp+0.5, locy, loczp+0.5);
                }

                if((doFixPane == true) && (doFixBlock == true)){
                    nloc = new Location(entityWorld, locx+0.5, locy, locz+0.5);
                }

                entity.teleport(nloc);

                return;
            }



        }
    }

    //@EventHandler
    //public void onRegionEnter(RegionEnterEvent e) {
    //    e.getPlayer().sendMessage("You just entered " + e.getRegion().getId());
    //}

}

