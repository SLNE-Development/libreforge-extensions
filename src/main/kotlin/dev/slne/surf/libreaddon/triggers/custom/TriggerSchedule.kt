package dev.slne.surf.libreaddon.triggers.custom

import com.willfp.libreforge.toDispatcher
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import dev.slne.surf.libreaddon.SurfLibreforgeExtensions
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

object TriggerSchedule : Trigger("repeating") { // every second
    private val tasks = mutableMapOf<UUID, Int>()
    override val parameters = setOf(
        TriggerParameter.PLAYER,
    )

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val bukkitTask = Bukkit.getScheduler().runTaskTimer(SurfLibreforgeExtensions.getExtension().plugin, { ->
            dispatch(player.toDispatcher(), TriggerData(player = player, location = player.location))
        }, 5L, 20L)

        tasks.putIfAbsent(player.uniqueId, bukkitTask.taskId)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent){
        val uuid = event.player.uniqueId
        val taskId = tasks[uuid]?: return

        tasks.remove(uuid)
        Bukkit.getScheduler().cancelTask(taskId)
    }
}