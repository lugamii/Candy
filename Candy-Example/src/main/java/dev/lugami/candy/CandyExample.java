package dev.lugami.candy;

import dev.lugami.candy.base.CancellableCPSHandler;
import dev.lugami.candy.base.CandyBridge;
import dev.lugami.candy.base.CandyPlayer;
import dev.lugami.candy.craftcandy.CraftCandy;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class CandyExample extends JavaPlugin {

    private static CandyExample instance;

    public void onEnable() {
        instance = this;
        new CraftCandy(this).setup();
        CandyBridge.registerHandler(new CPSHandler());
    }

    private static class CPSHandler implements CancellableCPSHandler {

        private boolean cancelled = false;
        private final Map<CandyPlayer, Integer> map = new HashMap<>();

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean value) {
            cancelled = value;
        }

        @Override
        public void onClick(CandyPlayer player) {
            if (player == null || player.getPlayer() == null) return;
            if (!map.containsKey(player)) {
                map.put(player, 0);
            }
            int current = CandyBridge.getCPSPlayerMap().get(player) + 1;
            CandyBridge.getCPSPlayerMap().put(player, current);
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.getPlayer() == null) return;
                    int current = CandyBridge.getCPSPlayerMap().get(player) - 1;
                    CandyBridge.getCPSPlayerMap().put(player, current);
                }
            };
            task.runTaskLater(instance, 20);
            if (player.getCPS() >= 20) {
                if (map.get(player) >= 35) {
                    setCancelled(true);
                    player.getPlayer().kickPlayer("You're doing more than 20 CPS, please click slower!");
                    map.remove(player);
                } else {
                    int i = map.get(player) + 1;
                    map.put(player, i);
                }
            }
        }

        @Override
        public void reset() {
            cancelled = false;
        }
    }

}
