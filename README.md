# LifeSteal
LifeSteal is a life-count system influenced by DonutSMP. Players take lives from each other and face bans when they run out. Made for Paper 1.20+ using Kotlin.

# Config
As of now, there are 2 customizable options in config:
```yml
ban-duration: 1 # Ban lenght (in minutes)
default-lives: 2 # Default lives on first join/after ban
```
Note that the player will be ban when he dies with 0 lives in bank, not when the count reaches 0.

# Usage
# In-game commands
- `setlife <player> <lives>` to set a player's lives (requires the `lifesteal.setlife` permission)


