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

    /**
     * getCPS - An method to return the max CPS amount/CPS limit for a player.
     * @return The player's max CPS amount/CPS limit
     */
    int getMaxCPS();

    /**
     * setCPS - An method to set the max CPS amount/CPS limit for a player.
     * @param value The CPS limit
     */
    void setMaxCPS(int value);

}
