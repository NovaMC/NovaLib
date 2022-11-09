package xyz.novaserver.core;

import com.sk89q.worldguard.WorldGuard;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.novaserver.core.handler.RegionHandler;

public final class Core extends JavaPlugin {
    @Override
    public void onEnable() {
        //Will create a better system as more things are added to this plugin but load this here for now
        if (getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
            WorldGuard.getInstance().getPlatform().getSessionManager().registerHandler(RegionHandler.FACTORY, null);
        }
    }
}
