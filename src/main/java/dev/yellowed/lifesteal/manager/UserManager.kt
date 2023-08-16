package dev.yellowed.lifesteal.manager

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dev.yellowed.lifesteal.LifeSteal
import dev.yellowed.lifesteal.instance.PlayerData
import java.io.File
import java.io.FileReader

object UserManager {
    val file: File = File(LifeSteal.instance.dataFolder, "users.json")
    val gson = Gson()

    init {
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    fun getPlayersList(): MutableList<PlayerData> {
        val type = object : TypeToken<List<PlayerData>>() {}.type
        FileReader(file).use { reader ->
            return gson.fromJson<List<PlayerData>?>(reader, type)?.toMutableList() ?: mutableListOf()
        }
    }

    private fun savePlayersList(players: List<PlayerData>) {
        file.writer().use { writer ->
            gson.toJson(players, writer)
        }
    }

    @Synchronized
    fun addPlayer(playerData: PlayerData) {
        val players = getPlayersList()
        if (players.none { it.uuid == playerData.uuid }) {
            players += playerData
            savePlayersList(players)
        }
    }

    @Synchronized
    fun editPlayer(playerData: PlayerData) {
        val playersList = getPlayersList()

        val index = playersList.indexOfFirst { it.uuid == playerData.uuid }
        if (index != -1) {
            playersList[index] = playerData
        }

        savePlayersList(playersList)
    }
}
