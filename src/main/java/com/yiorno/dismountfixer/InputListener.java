package com.yiorno.dismountfixer;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import net.minecraft.server.v1_16_R3.PacketPlayInSteerVehicle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class InputListener extends PacketAdapter {

    public InputListener(Plugin plugin, ListenerPriority listenerPriority, PacketType[] types) {
        super(plugin, listenerPriority, types);
    }


    @Override
    public void onPacketReceiving(PacketEvent event) {
        if(event.getPacketType().equals(PacketType.Play.Client.STEER_VEHICLE)){
            PacketPlayInSteerVehicle ppisv = (PacketPlayInSteerVehicle) event.getPacket().getHandle();

            Player player = event.getPlayer();
            boolean shift = ppisv.e();

            if (shift == true) {

                Location loc = event.getPlayer().getLocation();
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

                if (cancel == true) {
                    player.sendMessage(ChatColor.YELLOW + "もう少し壁から離れたところで降りてください");
                    event.setCancelled(true);
                }

            }

        }
    }
}