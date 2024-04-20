package dev.lugami.candy.craftcandy;

import dev.lugami.candy.base.*;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

@AllArgsConstructor
public class CraftCandy implements Candy {

    private JavaPlugin plugin;

    @Override
    public CraftCandy setup() {
        plugin.getServer().getPluginManager().registerEvents(getListener(), plugin);
        return this;
    }

    public static Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onJoin(PlayerJoinEvent event) {
                Player player = event.getPlayer();
                CraftCandyPlayer candyPlayer = new CraftCandyPlayer(player);
                CandyBridge.getCandyPlayers().put(player, candyPlayer);
                CandyBridge.getCPSPlayerMap().put(candyPlayer, 0);
            }

            @EventHandler
            public void onClick(PlayerInteractEvent event) {
                Player player = event.getPlayer();
                if (event.getAction() == Action.LEFT_CLICK_AIR ||
                    event.getAction() == Action.LEFT_CLICK_BLOCK ||
                    event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    CandyPlayer candyPlayer = CandyBridge.getCandyPlayers().get(player);
                    for (CandyCPSHandler cch : CandyBridge.getCPSHandlers()) {
                        if (cch instanceof CancellableCPSHandler) {
                            CancellableCPSHandler cch2 = (CancellableCPSHandler) cch;
                            if (cch2.isCancelled()) event.setCancelled(true);
                            else cch2.reset();
                        }
                        cch.onClick(candyPlayer);
                    }
                }
            }

        };
    }
}
