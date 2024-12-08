package de.fluffy.simple;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;

public class ConfigPermissionHandler {

    private final YMLConfig config;
    private final HashMap<String, List<String>> commands = new HashMap<>();

    public ConfigPermissionHandler(YMLConfig config) {
        this.config = config;
    }

    public void loadPermissions() {
        SimplePlugin pluginInstance = SimplePlugin.getPluginInstance();
        YamlConfiguration yamlConfiguration = this.config.getYmlConfiguration();
        if (yamlConfiguration == null) {
            pluginInstance.getLogger().severe("Corrupted Configuration: failed to load, disabling now...");
            SimplePlugin.disable();
            return;
        }

        ConfigurationSection permissionsSection = yamlConfiguration.getConfigurationSection("permissions");
        if (permissionsSection == null) {
            pluginInstance.getLogger().severe("Corrupted Configuration: missing field permissions, disabling now...");
            SimplePlugin.disable();
            return;
        }

        ConfigurationSection commandsSection = permissionsSection.getConfigurationSection("commands");
        if (commandsSection == null) {
            pluginInstance.getLogger().severe("Corrupted Configuration: missing field permissions.commands, disabling now...");
            SimplePlugin.disable();
            return;
        }

        commandsSection.getKeys(false).forEach(key -> this.commands.put(key, commandsSection.getStringList(key)));
    }

    public List<String> getCommandPermissions(String command) {
        return this.commands.get(command);
    }

}
