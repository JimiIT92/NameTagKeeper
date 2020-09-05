package org.nametagkeeper;

import com.google.inject.Inject;
import org.nametagkeeper.config.PluginObjects;
import org.nametagkeeper.config.PluginSettings;
import org.nametagkeeper.listener.DeathListener;
import org.nametagkeeper.task.UpdateCheckTask;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scheduler.Task;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

/**
 * NameTagKeeper.
 * Make living entities drop a nametag with their name on dead, so at you won't lost this precious item...
 */
@Plugin(
        id = PluginSettings.ID,
        name = PluginSettings.NAME,
        description = PluginSettings.DESCRIPTION,
        url = PluginSettings.URL,
        authors = PluginSettings.AUTHORS,
        version = PluginSettings.VERSION
)
public class NameTagKeeper {

    /**
     * Logger Instance
     */
    @Inject
    private Logger LOGGER;
    /**
     * Plugin Instance
     */
    private static NameTagKeeper INSTANCE;
    /**
     * Game Instance
     */
    @Inject
    private Game GAME;
    /**
     * Plugin Container Instance
     */
    @Inject
    protected PluginContainer PLUGIN_CONTAINER;
    /**
     * Default configuration folder
     */
    @Inject
    @ConfigDir(sharedRoot = true)
    private Path CONFIG_DIRECTORY;

    /**
     * Constructor.
     * Set the Plugin Instance
     */
    protected NameTagKeeper() {
        INSTANCE = this;
    }

    /**
     * Game PreInitialization Event.
     * Load the Settings
     *
     * @param event GamePreInitialization Event
     */
    @Listener
    public final void onGamePreInitialization(final GamePreInitializationEvent event) {
        PluginObjects.load();
    }

    /**
     * Game Initialization Event.
     * Register Event Listeners
     *
     * @param event GameInitialization Event
     */
    @Listener
    public final void onGameInitialization(final GameInitializationEvent event) {
        Sponge.getEventManager().registerListeners(PLUGIN_CONTAINER, new DeathListener());
    }

    /**
     * Game Post Initialization Event.
     * Register Plugin Tasks
     *
     * @param event GamePostInitialization Event
     */
    @Listener
    public final void onGamePostInitialization(final GamePostInitializationEvent event) {
        if(PluginObjects.UPDATE_CHECK) {
            Task.builder().execute(new UpdateCheckTask())
                    .async()
                    .delay(10, TimeUnit.SECONDS)
                    .interval(PluginObjects.UPDATE_CHECK_TIME, PluginObjects.UPDATE_CHECK_TIME_UNIT)
                    .name(PluginSettings.ID.toLowerCase() + "-update_check")
                    .submit(this);
        }
    }

    /**
     * Game Stopping Server Event.
     * Unregister all tasks
     *
     * @param event GameStoppingServer Event
     */
    @Listener
    public final void onGameStoppingServer(final GameStoppingServerEvent event) {
        GAME.getScheduler().getScheduledTasks(this).forEach(Task::cancel);
    }

    /**
     * Get the Plugin Instance
     *
     * @return Plugin Instance
     */
    public static NameTagKeeper getInstance() {
        return INSTANCE;
    }

    /**
     * Get the Plugin Container
     *
     * @return Plugin Container
     */
    public final PluginContainer getPluginContainer() {
        return PLUGIN_CONTAINER;
    }

    /**
     * Get the Config Directory
     *
     * @return Config Directory
     */
    public final Path getConfigDirectory() {
        return CONFIG_DIRECTORY;
    }

    /**
     * Get the Logger Instance
     *
     * @return Logger Instance
     */
    public final Logger getLogger() {
        return LOGGER;
    }
}
