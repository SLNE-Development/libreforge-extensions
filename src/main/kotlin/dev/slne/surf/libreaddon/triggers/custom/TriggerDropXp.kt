package dev.slne.surf.libreaddon.triggers.custom

import com.willfp.libreforge.toDispatcher
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent

object TriggerDropXp : Trigger("drop_xp") {
    override val parameters = setOf(
        TriggerParameter.EVENT,
        TriggerParameter.PLAYER,
        TriggerParameter.LOCATION,
        TriggerParameter.VALUE
    )

    @EventHandler
    fun onMobKill(event: EntityDeathEvent){
        val killer = event.entity.killer?: return

        dispatch(
            killer.toDispatcher(), TriggerData(
                event = event,
                victim = event.entity,
                location = event.entity.location,
                player = killer,
                value = event.droppedExp.toDouble(),
            )
        )
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent){
        dispatch(
            event.player.toDispatcher(), TriggerData(
                event = event,
                player = event.player,
                block = event.block,
                location = event.block.location,
                value = event.expToDrop.toDouble()
            )
        )
    }

}