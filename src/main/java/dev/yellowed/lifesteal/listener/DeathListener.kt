package dev.yellowed.lifesteal.listener

import com.destroystokyo.paper.profile.PlayerProfile
import dev.yellowed.lifesteal.manager.UserManager
import org.apache.maven.model.Profile
import org.bukkit.BanList
import org.bukkit.Bukkit
import org.bukkit.ban.IpBanList
import org.bukkit.ban.ProfileBanList
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.*
import kotlin.system.exitProcess

class DeathListener : Listener {

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        // REMOVE A LIFE
        val player = e.entity

        val playerData = UserManager.getPlayersList().find { it.uuid == player.uniqueId } ?: return
        playerData.lives -= 1

        if (playerData.lives < 0) {
            player.banPlayer("No more lives!", Calendar.getInstance().apply { add(Calendar.MINUTE, 1) }.time)
            playerData.lives = 3
        }

        UserManager.editPlayer(playerData)

        // ADD A LIFE IF KILLER
        val killer = e.player.killer ?: return
        if (killer.uniqueId == player.uniqueId) return

        val killerData = UserManager.getPlayersList().find { it.uuid == killer.uniqueId }
        killerData!!.lives += 1
        UserManager.editPlayer(killerData!!)
    }
}