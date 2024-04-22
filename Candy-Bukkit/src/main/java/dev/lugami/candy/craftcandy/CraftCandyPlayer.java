package dev.lugami.candy.craftcandy;

import dev.lugami.candy.base.CandyBridge;
import dev.lugami.candy.base.CandyPlayer;
import org.bukkit.entity.Player;

public class CraftCandyPlayer implements CandyPlayer {

    private final Player player;

    public CraftCandyPlayer(Player p) {
        player = p;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int getCPS() {
        return CandyBridge.getCPSPlayerMap().get(this);
    }
}
