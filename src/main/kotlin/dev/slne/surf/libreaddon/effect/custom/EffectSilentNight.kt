package dev.slne.surf.libreaddon.effect.custom

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.entity.Enderman
import org.bukkit.entity.Phantom
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityTargetEvent
import org.bukkit.event.entity.EntityTargetLivingEntityEvent

object EffectSilentNight : Effect<NoCompileData>("silent_night") {
    override val parameters = setOf(
        TriggerParameter.PLAYER,
        TriggerParameter.EVENT
    )

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val player = data.player?: return false
        val event = data.event?: return false

        if(event !is EntityTargetLivingEntityEvent) return false

        val target = event.target

        if (event.entity !is Phantom) return false
        if (target !is Player) return false
        if (target.uniqueId != player.uniqueId) return false

        event.target = null

        return true
    }
}