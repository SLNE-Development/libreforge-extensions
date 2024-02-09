package dev.slne.surf.libreaddon.triggers.custom

import com.willfp.libreforge.toDispatcher
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import dev.slne.surf.libreaddon.util.getHead
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDeathEvent

object TriggerBeheading : Trigger("beheading") {
    override val parameters: Set<TriggerParameter>
        get() = setOf(
            TriggerParameter.EVENT,
            TriggerParameter.VICTIM,
            TriggerParameter.LOCATION,
            TriggerParameter.PLAYER,
            TriggerParameter.ITEM
        )

    @EventHandler
    fun onMobKill(event: EntityDeathEvent) {
        val killedEntity = event.entity
        val killer = killedEntity.killer ?: return
        val head = killedEntity.getHead()

        if (head.type == Material.AIR) return

        dispatch(
            killer.toDispatcher(), TriggerData(
                event = event,
                victim = killedEntity,
                location = killedEntity.location,
                player = killer,
                item = head
            )
        )
    }
}