package dev.yellowed.lifesteal.manager

import dev.yellowed.lifesteal.LifeSteal

object ConfigManager {
    private val config = LifeSteal.instance.config

    val banDuration: Int
        get() = config.getInt("ban-duration")
    val defaultLives: Int
        get() = config.getInt("default-lives")
}