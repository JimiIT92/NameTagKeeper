package org.nametagkeeper;

import com.google.inject.Inject;
import org.nametagkeeper.config.PluginSettings;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;

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
     * Game Instance
     */
    @Inject
    private Game GAME;

    /**
     * Game PreInitialization Event.
     * Load the Settings
     *
     * @param event GamePreInitialization Event
     */
    @Listener
    public final void onGamePreInitialization(final GamePreInitializationEvent event) {
        // Load settings
        //Excluding entities
        //Excluding names

    }

    /**
     * Game Initialization Event.
     * Register Event Listeners
     *
     * @param event GameInitialization Event
     */
    @Listener
    public final void onGameInitialization(final GameInitializationEvent event) {
        // Listeners
    }

    /**
     * Game Post Initialization Event.
     * Register Plugin Tasks
     *
     * @param event GamePostInitialization Event
     */
    @Listener
    public final void onGamePostInitialization(final GamePostInitializationEvent event) {
        //Update checker
    }

    /**
     * Game Starting Server Event.
     * Register Commands
     *
     * @param event GameStartingServer Event
     */
    @Listener
    public final void onGameStartingServer(final GameStartingServerEvent event) {
        // Commands
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
}
