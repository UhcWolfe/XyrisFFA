package dev.darkxx.xyriskits.api;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock API for XyrisKits to allow compilation when the dependency is unavailable.
 * This class should be excluded from the final plugin JAR.
 */
public class XyrisKitsAPI {
    private static final KitsAPI kitsAPI = new KitsAPI() {
        @Override
        public void giveKit(Player player, String kitName) {}

        @Override
        public String getLastKit(Player player) {
            return "none";
        }

        @Override
        public List<String> listKits() {
            return new ArrayList<>();
        }
    };

    public static void initialize() {}

    public static KitsAPI getKitsAPI() {
        return kitsAPI;
    }
}
