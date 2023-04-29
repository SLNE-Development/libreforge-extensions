package dev.slne.surf.libreaddon.effect.custom

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.arguments
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent
import kotlin.math.roundToInt

object EffectMoreExperience : Effect<NoCompileData>("more_xp") {
    override val parameters = setOf(
        TriggerParameter.EVENT
    )

    override val arguments = arguments {
        require("level", "You must specify the level of the enchantment")
    }

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val level = config.getIntFromExpression("level")
        val event = data.event?: return false

        if (event is EntityDeathEvent){
            event.droppedExp = calculateDrops(event.droppedExp, level).roundToInt()
            return true

        } else if(event is BlockBreakEvent){
            event.expToDrop = calculateDrops(event.expToDrop, level).roundToInt()
            return true
        }

        return false
    }

    private fun calculateDrops(xp: Int, level: Int) : Double{
        return (xp * (level / 10.0)) + xp
    }
}