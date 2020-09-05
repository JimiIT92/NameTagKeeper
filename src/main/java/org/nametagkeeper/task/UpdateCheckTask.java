package org.nametagkeeper.task;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.nametagkeeper.NameTagKeeper;
import org.nametagkeeper.config.PluginObjects;
import org.nametagkeeper.config.PluginSettings;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Update Checker
 *
 * @author JimiIT92
 */
public class UpdateCheckTask implements Consumer<Task> {

    /**
     * Plugin Update URL
     */
    private static final String UPDATE_URL = "https://ore.spongepowered.org/api/v1/projects/" + PluginSettings.ID.toLowerCase() + "/versions?channels=release&limit=1&offset=0";
    /**
     * Plugin User Agent
     */
    private static final String PLUGIN_USER_AGENT = PluginSettings.ID + "/" + PluginSettings.VERSION + "(Sponge " + PluginSettings.VERSION + ")";

    /**
     * Check for Updates
     *
     * @param task Task to run
     */
    @Override
    public void accept(Task task) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection connection = (HttpURLConnection) new URL(UPDATE_URL).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", PLUGIN_USER_AGENT);
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            JsonArray versions = new Gson().fromJson(stringBuilder.toString(), JsonArray.class);
            String latestVersion = versions.get(0).getAsJsonObject().get("name").getAsString();
            if (isNewVersion(latestVersion)) {
                getBroadcastPlayers().forEach(player -> player.sendMessage(Text.of(TextColors.GOLD, "[NameTagKeeper] New version available: ", latestVersion)));
                task.cancel();
            }
        } catch (IOException ex) {
            NameTagKeeper.getInstance().getLogger().error("Error while checking updates", ex);
            task.cancel();
        }
    }

    /**
     * Check if a new version is available
     *
     * @param version Latest version from Sponge APIs
     * @return True if a new version is available, false otherwise
     */
    private boolean isNewVersion(String version) {
        return Float.parseFloat(version.substring(2)) > Float.parseFloat(PluginSettings.VERSION.substring(2));
    }

    /**
     * Get all Command Sources with the update broadcast permission
     *
     * @return List of Command Source with the permissions
     */
    private List<CommandSource> getBroadcastPlayers() {
        List<String> permissions = PluginObjects.UPDATE_BROADCAST_PERMISSIONS;
        List<CommandSource> commandSources = new ArrayList<>();
        commandSources.add(Sponge.getServer().getConsole());
        Sponge.getServer().getOnlinePlayers().forEach(player -> {
            if (permissions.stream().anyMatch(player::hasPermission)) {
                player.getCommandSource().ifPresent(commandSources::add);
            }
        });
        return commandSources;
    }
}
