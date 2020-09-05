package org.nametagkeeper.utils;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import org.nametagkeeper.NameTagKeeper;
import org.nametagkeeper.config.PluginSettings;
import org.spongepowered.api.asset.Asset;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author JimiIT92
 */
public final class SettingUtils {

    /**
     * Load the Settings File from the Configuration Directory
     *
     * @return Settings Configuration Node
     */
    public static ConfigurationNode loadSettingsFile() {
        Path configDirectory = NameTagKeeper.getInstance().getConfigDirectory();
        String fileName = PluginSettings.ID.toLowerCase() + ".conf";
        try {
            File configFile = new File(configDirectory.toFile(), fileName);
            HoconConfigurationLoader configurationLoader = HoconConfigurationLoader
                    .builder()
                    .setFile(configFile)
                    .build();
            Optional<Asset> optionalAsset = NameTagKeeper.getInstance().getPluginContainer().getAsset(fileName);
            if (optionalAsset.isPresent()) {
                if (!configFile.exists()) {
                    Files.createDirectories(Paths.get(configFile.getParent()));
                    optionalAsset.get().copyToFile(configFile.toPath());
                } else {
                    ConfigurationNode configuration = configurationLoader.load();
                    ConfigurationNode newConfiguration = HoconConfigurationLoader
                            .builder()
                            .setURL(optionalAsset.get().getUrl())
                            .build()
                            .load();
                    configuration.mergeValuesFrom(newConfiguration);
                    configurationLoader.save(configuration);
                }
            }
            return configurationLoader.load(ConfigurationOptions.defaults().setShouldCopyDefaults(true));
        } catch (IOException ex) {
            NameTagKeeper.getInstance().getLogger().error("Error while loading configuration", ex);
        }
        return null;
    }
}
