package dev.slne.surf.libreaddon.effect.custom

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import com.willfp.libreforge.triggers.impl.TriggerKill
import dev.slne.surf.libreaddon.util.getHead
import org.bukkit.event.entity.EntityDeathEvent

object EffectDropMobHead : Effect<NoCompileData>("drop_mob_head") {
    override val parameters = setOf(
        TriggerParameter.EVENT,
        TriggerParameter.VICTIM,
        TriggerParameter.LOCATION,
        TriggerParameter.PLAYER,
        TriggerParameter.ITEM
    )

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val head = data.item ?: return false
        val event = data.event.let { it is EntityDeathEvent; it as EntityDeathEvent }

        event.drops.add(head)
        return true
    }
}