package de.fluffy.simple.commands;

import de.fluffy.simple.ConfigPermissionHandler;
import de.fluffy.simple.SimplePlugin;
import de.fluffy.simple.Translator;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseCommand implements CommandExecutor, TabCompleter, IBaseCommand {

    private final Translator translator;
    private final List<String> permissions;

    public BaseCommand(SimplePlugin pluginInstance, String commandName) {
        PluginCommand command = pluginInstance.getCommand(commandName);
        assert command != null;
        command.setExecutor(this);
        command.setTabCompleter(this);

        this.translator = pluginInstance.getTranslator();
        ConfigPermissionHandler configPermissionHandler = pluginInstance.getConfigPermissionHandler();
        List<String> permissions = configPermissionHandler.getCommandPermissions(commandName);
        this.permissions = permissions == null ? List.of() : permissions;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command pluginCommand, @NotNull String raw, @NotNull String[] args) {
        if (this.permissions.isEmpty()) return command(sender, args);

        boolean hasPermission = false;
        for (String permission : this.permissions) {
            if (sender.hasPermission(permission)) {
                hasPermission = true;
                break;
            }
        }
        if (!hasPermission) {
            this.translator.send(sender, "missing-permission");
            return false;
        }

        return command(sender, args);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command pluginCommand, @NotNull String raw, @NotNull String[] args) {
        return tab(sender, args);
    }

    public Translator translator() {
        return this.translator;
    }

}
