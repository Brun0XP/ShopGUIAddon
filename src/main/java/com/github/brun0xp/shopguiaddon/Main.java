package com.github.brun0xp.shopguiaddon;

import com.github.brun0xp.shopguiaddon.commands.manager.CommandManager;
import com.github.brun0xp.shopguiaddon.listener.ListenerManager;
import com.github.brun0xp.shopguiaddon.resource.LanguageFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@Getter @Setter
public class Main extends JavaPlugin {

    @Getter @Setter(value = AccessLevel.PRIVATE)
    private static Main main;

    private LanguageFile language;
    private CommandManager commandManager;
    private ListenerManager listenerManager;
    private String bankName;

    @Getter
    private static Economy econ = null;

    @Override
    public void onEnable() {
        Main.setMain(this);
        this.setupFiles();
        this.setCommandManager(new CommandManager());
        this.setListenerManager(new ListenerManager());
        this.setupEconomy();
        this.setBankName(this.getConfig().getString("bank.name"));
        if (!this.getEcon().hasAccount(this.getBankName())) {
            this.getEcon().createPlayerAccount(this.getBankName());
        }
    }

    @Override
    public void onDisable() {

    }

    private void setupFiles() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.setLanguage(new LanguageFile(Main.getMain(), "lang-" + this.getConfig().getString("language") + ".yml"));
        this.getLanguage().getFile().options().copyDefaults(true);
        this.getLanguage().saveFile();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}
