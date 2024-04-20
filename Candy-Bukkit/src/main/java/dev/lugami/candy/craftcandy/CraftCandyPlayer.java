package dev.lugami.candy.craftcandy;

import dev.lugami.candy.base.CandyBridge;
import dev.lugami.candy.base.CandyPlayer;
import org.bukkit.entity.Player;

public class CraftCandyPlayer implements CandyPlayer {

    private final Player player;
    private int maxCPS = 20;

    public CraftCandyPlayer(Player p) {
        player = p;
    }

    @Override
    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    @Override
    public int getCPS() {
        return CandyBridge.getCPSPlayerMap().get(this);
    }

    @Override
    public int getMaxCPS() {
        return maxCPS;
    }

    @Override
    public void setMaxCPS(int value) {

    }
}
