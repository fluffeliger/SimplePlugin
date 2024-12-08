package de.fluffy.simple.commands;

import de.fluffy.simple.SimplePlugin;
import de.fluffy.simple.Translator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class SimpleCommand extends BaseCommand {

    public SimpleCommand(SimplePlugin pluginInstance, String commandName) {
        super(pluginInstance, commandName);
    }

    @Override
    public boolean command(CommandSender sender, String[] arguments) {
        Translator translator = translator();
        if (!(sender instanceof Player player)) {
            translator.send(sender, "cmd-need-player");
            return false;
        }

        player.setVelocity(new Vector(0, 3, 0));
        translator.send(sender, "simple-command-feedback", List.of(String.valueOf(arguments.length)));
        return true;
    }

    @Override
    public List<String> tab(CommandSender sender, String[] arguments) {
        return List.of("unendlich", "abc", ":)");
    }
}
