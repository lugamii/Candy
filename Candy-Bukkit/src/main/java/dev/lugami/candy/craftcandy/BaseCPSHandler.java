package dev.lugami.candy.craftcandy;

import dev.lugami.candy.base.CandyBridge;
import dev.lugami.candy.base.CandyCPSHandler;
import dev.lugami.candy.base.CandyPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class BaseCPSHandler implements CandyCPSHandler {

    private final JavaPlugin plugin;

    @Override
    public void onClick(CandyPlayer player) {
        if (player == null || player.getPlayer() == null) return;
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
        task.runTaskLater(plugin, 20);
    }
}
