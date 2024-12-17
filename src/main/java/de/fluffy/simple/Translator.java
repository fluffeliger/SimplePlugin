/*
 * Programmed with <3 by fluffy
 */

package de.fluffy.simple;

import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;

public class Translator {

    private final HashMap<String, String> messages = new HashMap<>();
    private final MiniMessage miniMessage = MiniMessage.builder()
            .postProcessor(processor -> processor.decoration(TextDecoration.ITALIC, false))
            .build();

    public Translator(YMLConfig mainConfig) {
        SimplePlugin pluginInstance = SimplePlugin.getPluginInstance();
        YamlConfiguration yamlConfiguration = mainConfig.getYmlConfiguration();
        if (yamlConfiguration == null) {
            pluginInstance.getLogger().severe("Corrupted Configuration: failed to load, disabling now...");
            SimplePlugin.disable();
            return;
        }
        ConfigurationSection messagesSection = yamlConfiguration.getConfigurationSection("messages");
        if (messagesSection == null) {
            pluginInstance.getLogger().severe("Corrupted Configuration: missing field messages, disabling now...");
            SimplePlugin.disable();
            return;
        }

        messagesSection.getKeys(false).forEach(key -> this.messages.put(key, messagesSection.getString(key)));
    }

    public void send(CommandSender sender, String messageID) {
        String message = messages.get(messageID);
        if (message == null) this.sendRaw(sender, "<red>{%s}".formatted(messageID));
        else this.sendRaw(sender, messages.get(messageID));
    }

    public void send(CommandSender sender, String messageID, List<String> arguments) {
        String message = messages.get(messageID);
        if (message == null) this.sendRaw(sender, "<red>{%s}".formatted(messageID));
        else this.sendRaw(sender, messages.get(messageID).formatted(arguments.toArray()));
    }

    public void sendRaw(CommandSender sender, String message) {
        sender.sendMessage(miniMessage.deserialize(message));
    }

}
