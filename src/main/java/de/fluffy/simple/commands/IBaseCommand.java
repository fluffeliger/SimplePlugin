/*
 * Programmed with <3 by fluffy
 */

package de.fluffy.simple.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface IBaseCommand {

    boolean command(CommandSender sender, String[] arguments);
    List<String> tab(CommandSender sender, String[] arguments);

}
