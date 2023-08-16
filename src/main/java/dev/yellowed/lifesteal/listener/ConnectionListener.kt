package dev.yellowed.lifesteal.listener

import com.google.gson.Gson
import dev.yellowed.lifesteal.instance.PlayerData
import dev.yellowed.lifesteal.manager.UserManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.io.FileReader
import java.nio.file.attribute.UserPrincipalNotFoundException

class ConnectionListener : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        UserManager.getPlayersList().apply {
            if (none { it.uuid == e.player.uniqueId }) {
                UserManager.addPlayer(PlayerData(e.player.uniqueId, 3))
            }
        }
    }
}