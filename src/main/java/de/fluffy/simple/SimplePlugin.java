package de.fluffy.simple;

import de.fluffy.simple.commands.SimpleCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class SimplePlugin extends JavaPlugin {

    private static SimplePlugin pluginInstance;
    private final HashMap<String, YMLConfig> configs = new HashMap<>();
    private Translator translator;
    private ConfigPermissionHandler configPermissionHandler;

    @Override
    public void onEnable() {
        pluginInstance = this;

        if (!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        pluginInstance.saveResource("config.yml", false);
        YMLConfig mainConfig = new YMLConfig(new File(this.getDataFolder(), "config.yml"));
        mainConfig.load();
        configs.put("main", mainConfig);

        this.translator = new Translator(mainConfig);
        this.configPermissionHandler = new ConfigPermissionHandler(mainConfig);
        this.configPermissionHandler.loadPermissions();

        new SimpleCommand(pluginInstance, "simple");
    }

    public static void disable() {
        pluginInstance.getServer().getPluginManager().disablePlugin(pluginInstance);
    }

    public static SimplePlugin getPluginInstance() {
        return pluginInstance;
    }

    public Translator getTranslator() {
        return translator;
    }

    public ConfigPermissionHandler getConfigPermissionHandler() {
        return configPermissionHandler;
    }
}
