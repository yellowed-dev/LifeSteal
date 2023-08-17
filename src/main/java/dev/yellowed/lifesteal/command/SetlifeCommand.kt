import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import dev.yellowed.lifesteal.manager.UserManager

class SetlifeCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        // Check if sender has the required permission
        if (!sender.hasPermission("lifesteal.setlife")) {
            sender.sendMessage("§cYou don't have permission to use this command.")
            return true
        }

        // Check for the correct number of arguments
        if (args.size != 2) {
            sender.sendMessage("§cUsage: /setlife <player> <lives>")
            sender.sendMessage("TEST")
            return true
        }

        val targetPlayer = Bukkit.getPlayer(args[0]) ?: run {
            sender.sendMessage("§cPlayer not found!")
            return true
        }

        val lives = args[1].toIntOrNull() ?: run {
            sender.sendMessage("§cInvalid number format!")
            return true
        }

        // Get player data and set their lives
        UserManager.getPlayersList().find { it.uuid == targetPlayer.uniqueId }?.let { playerData ->
            playerData.lives = lives
            UserManager.editPlayer(playerData)
            sender.sendMessage("§aSet ${targetPlayer.name}'s lives to $lives.")
        } ?: sender.sendMessage("§cError: Player data not found!")

        return true
    }
}
