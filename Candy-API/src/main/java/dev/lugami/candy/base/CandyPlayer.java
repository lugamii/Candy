package dev.lugami.candy.base;

import org.bukkit.entity.Player;

public interface CandyPlayer {

    /**
     * getPlayer - An method to return the org.bukkit.entity.Player for this class.
     * @return org.bukkit.entity.Player
     */
    Player getPlayer();

    /**
     * getCPS - An method to return the CPS amount for a player.
     * @return The current CPS amout for the player
     */
    int getCPS();

}
