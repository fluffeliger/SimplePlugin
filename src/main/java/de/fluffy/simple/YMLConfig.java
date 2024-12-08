package de.fluffy.simple;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YMLConfig {

    private final File file;
    private YamlConfiguration ymlConfiguration;

    public YMLConfig(File file) {
        this.file = file;
    }

    public void load() {
        this.ymlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean save() {
        try {
            this.ymlConfiguration.save(this.file);
            return true;
        } catch (IOException exception) {
            SimplePlugin.getPluginInstance().getLogger().severe(exception.getMessage());
        }
        return false;
    }

    public YamlConfiguration getYmlConfiguration() {
        return this.ymlConfiguration;
    }

    public void setYmlConfiguration(YamlConfiguration ymlConfiguration) {
        this.ymlConfiguration = ymlConfiguration;
    }

}
