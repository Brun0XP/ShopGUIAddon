package com.github.brun0xp.shopguibank.commands.manager;

import com.github.brun0xp.shopguibank.Main;
import com.github.brun0xp.shopguibank.commands.subcommand.*;
import com.github.brun0xp.shopguibank.resource.Message;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Getter
public class CommandManager implements CommandExecutor, TabCompleter {

    private Main main;

    private java.util.Set<AbstractSubCommand> abstractSubCommands = new HashSet<>();

    public CommandManager(){
        this.main = Main.getMain();
        this.getAbstractSubCommands().add(new Add());
        this.getAbstractSubCommands().add(new Balance());
        this.getAbstractSubCommands().add(new Set());
        this.getAbstractSubCommands().add(new Transfer());
        this.getMain().getCommand("bank").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if (strings.length == 0) {
            new Message("messages.help", true).colored().send(commandSender);
            return true;
        }
        AbstractSubCommand abstractSubCommand = this.getSubCommand(strings[0]);
        if (abstractSubCommand == null) {
            new Message("messages.help", true).colored().send(commandSender);
            return true;
        }

        try {
            if (commandSender.hasPermission(abstractSubCommand.getPermission())) {
                if (!abstractSubCommand.onCommand(commandSender, Arrays.copyOfRange(strings, 1, strings.length)))
                    new Message("messages.error.usage").set("usage", abstractSubCommand.getUsage(command.getName()))
                            .colored().send(commandSender);
            } else
                new Message("messages.error.permission").set("permission", abstractSubCommand.getPermission())
                        .colored().send(commandSender);
        } catch (Exception e){
            new Message("messages.error.unknown").colored().send(commandSender);
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] strings) {
        if (strings.length == 1) {
            return Arrays.asList("set", "balance", "deposit");
        }
        AbstractSubCommand abstractSubCommand = this.getSubCommand(strings[1]);
        if (abstractSubCommand == null) {
            return new ArrayList<>();
        }
        try {
            if (commandSender.hasPermission(abstractSubCommand.getPermission())) {
                return abstractSubCommand.onTabComplete(commandSender, Arrays.copyOfRange(strings, 1, strings.length));
            }
        } catch (Exception e){
            new Message("messages.error.unknown").colored().send(commandSender);
            e.printStackTrace();
        }
        return null;
    }

    private AbstractSubCommand getSubCommand(String name) {
        for (AbstractSubCommand command : this.getAbstractSubCommands()) {
            if (command.getName().equalsIgnoreCase(name))
                return command;
            for (String aliases : command.getAliases())
                if (name.equalsIgnoreCase(aliases))
                    return command;
        }
        return null;
    }

}
