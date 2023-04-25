package dev.slne.surf.ecoaddon.effect.custom

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import dev.slne.surf.ecoaddon.util.getHead

object EffectDropMobHead : Effect<NoCompileData>("drop_mob_head") {
    override val parameters = setOf(
        TriggerParameter.LOCATION,
    )

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val location = data.location ?: return false
        val victim = data.victim ?: return false
        val dropItem = victim.getHead()

        location.world?.dropItem(location, dropItem)

        return true
    }
}