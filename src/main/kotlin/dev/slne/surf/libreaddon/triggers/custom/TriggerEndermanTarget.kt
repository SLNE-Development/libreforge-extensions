package dev.slne.surf.libreaddon.triggers.custom

import com.willfp.libreforge.toDispatcher
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.entity.Enderman
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityTargetLivingEntityEvent

object TriggerEndermanTarget : Trigger("enderman_target") {
    override val parameters = setOf(
        TriggerParameter.PLAYER,
        TriggerParameter.EVENT
    )

    @EventHandler
    fun onEndermanTarget(event: EntityTargetLivingEntityEvent){
        if (event.entity !is Enderman) return
        if (event.target !is Player) return

        dispatch(
            (event.target as Player).toDispatcher(),
            TriggerData(player = event.target as Player, event = event),
        )
    }
}