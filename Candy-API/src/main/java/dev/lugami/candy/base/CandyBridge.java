package dev.lugami.candy.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

/**
 * Candy - An open source CPS API for Spigot 1.8.x
 */
public class CandyBridge {

    /**
     * cpsHandlers - A List for registering multiple dev.lugami.candy.base.CandyCPSHandler
     */
    @Getter private final static List<CandyCPSHandler> CPSHandlers = Lists.newArrayList();

    /**
     * candyPlayers - A Map for mapping org.bukkit.entity.Player to dev.lugami.candy.base.CandyPlayer
     */
    @Getter private final static Map<Player, CandyPlayer> candyPlayers = Maps.newHashMap();

    /**
     * CPSPlayerMap - A Map for mapping dev.lugami.candy.base.CandyPlayer to their CPS (as an integer).
     */
    @Getter private final static Map<CandyPlayer, Integer> CPSPlayerMap = Maps.newHashMap();

    public static void registerHandler(CandyCPSHandler obj) {
        if (exists(obj)) {
            throw new IllegalArgumentException(obj.getClass().getSimpleName() + " is already registered.");
        }
        CPSHandlers.add(obj);
        Bukkit.getLogger().info("[Candy] Registered a new Handler: " + obj.getClass().getSimpleName() + ".class (Cancellable: " + (obj instanceof CancellableCPSHandler) + ")");
    }

    public static void registerHandlers(List<CandyCPSHandler> obj) {
        boolean exists = false;
        if (obj == CPSHandlers) {
            throw new IllegalArgumentException("You cannot register dev.lugami.candy.base.CandyBridge.getCPSHandlers()");
        }
        for (CandyCPSHandler cch : obj) {
            if (exists(cch)) {
                exists = true;
            } else {
                CPSHandlers.add(cch);
            }
        }
        Bukkit.getLogger().info("[Candy] Registered " + obj.size() + " Handlers from a list.");
        if (exists) Bukkit.getLogger().info("[Candy] One or more handlers failed to register from \"" + obj + "\" as they already existed.");
    }

    public static void unregisterHandler(CandyCPSHandler obj) {
        if (!exists(obj)) {
            throw new IllegalArgumentException(obj.getClass().getSimpleName() + " isn't registered.");
        }
        CPSHandlers.remove(obj);
        Bukkit.getLogger().info("[Candy] Unregistered " + obj.getClass().getSimpleName() + ".class");
    }

    public static boolean exists(CandyCPSHandler obj) {
        return CPSHandlers.contains(obj);
    }

}
