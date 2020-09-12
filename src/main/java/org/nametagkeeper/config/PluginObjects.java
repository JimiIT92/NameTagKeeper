package org.nametagkeeper.config;

import ninja.leaping.configurate.ConfigurationNode;
import org.nametagkeeper.utils.SettingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Plugin Objects
 *
 * @author JimiIT92
 */
public final class PluginObjects {
    /**
     * If the Plugin should check for Updates
     */
    public static boolean UPDATE_CHECK;
    /**
     * How Frequently the Plugin should check for Updates
     */
    public static int UPDATE_CHECK_TIME;
    /**
     * Time Unit for the Update Checker
     */
    public static TimeUnit UPDATE_CHECK_TIME_UNIT;
    /**
     * Update Broadcast Permissions
     */
    public static List<String> UPDATE_BROADCAST_PERMISSIONS;
    /**
     * Mob Entries
     */
    public static HashMap<String, MobEntry> ENTRIES;

    /**
     * Load the Plugin settings
     */
    public static void load() {
        ConfigurationNode config = SettingUtils.loadSettingsFile();
        if(config != null && !config.isVirtual()) {
            ConfigurationNode updateNode = config.getNode("update_check");
            if(!updateNode.isVirtual()) {
                UPDATE_CHECK = updateNode.getNode("check").getBoolean();
                UPDATE_CHECK_TIME = updateNode.getNode("time").getInt();
                UPDATE_CHECK_TIME_UNIT = TimeUnit.valueOf(Objects.requireNonNull(updateNode.getNode("unit").getString()).toUpperCase());
                UPDATE_BROADCAST_PERMISSIONS = getList(updateNode.getNode("broadcast_permissions"), true);
                UPDATE_BROADCAST_PERMISSIONS.add("*");
            }
            loadEntries(config.getNode("entries"));
        }
    }

    /**
     * Get a String List from a Configuration Node
     *
     * @param node Configuration Node
     * @param toLower If the values should be lower cased
     * @return String List
     */
    private static List<String> getList(ConfigurationNode node, boolean toLower) {
        return node.isVirtual() ? new ArrayList<>() : node.getChildrenList().stream().map(x -> {
            String value = Objects.requireNonNull(x.getValue()).toString();
            return toLower ? value.toLowerCase() : value;
        }).collect(Collectors.toList());
    }

    /**
     * Load Mob Entries
     *
     * @param node Configuration Node
     */
    private static void loadEntries(ConfigurationNode node) {
        ENTRIES = new HashMap<>();
        node.getChildrenMap().values().forEach(x -> {
            MobEntry entry = new MobEntry();
            entry.DROP = getList(x.getNode("drop"), false);
            entry.NODROP = getList(x.getNode("nodrop"), false);
            ENTRIES.put(Objects.requireNonNull(x.getKey()).toString(), entry);
        });
    }
}
