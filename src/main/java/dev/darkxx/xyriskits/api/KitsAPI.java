package dev.darkxx.xyriskits.api;

import org.bukkit.entity.Player;
import java.util.List;

/**
 * Interface for XyrisKits API
 */
public interface KitsAPI {
    void giveKit(Player player, String kitName);
    String getLastKit(Player player);
    List<String> listKits();
}
