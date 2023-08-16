package dev.yellowed.lifesteal

import dev.yellowed.lifesteal.listener.ConnectionListener
import dev.yellowed.lifesteal.listener.DeathListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class LifeSteal : JavaPlugin() {
    companion object {
        lateinit var instance: LifeSteal
            private set
    }

    override fun onEnable() {
        instance = this
        logger.info("LifeSteal starting...")

        saveDefaultConfig()
        Bukkit.getPluginManager().registerEvents(ConnectionListener(), this)
        Bukkit.getPluginManager().registerEvents(DeathListener(), this)
    }
}