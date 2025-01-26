package dev.slne.surf.libreaddon.effect.custom

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.arguments
import com.willfp.libreforge.effects.templates.AttributeEffect
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

object EffectOxygenBonus : AttributeEffect(
    "generic_oxygen_multiplier",
    Attribute.OXYGEN_BONUS,
    AttributeModifier.Operation.MULTIPLY_SCALAR_1
) {
    override val arguments = arguments {
        require("multiplier", "You must specify the oxygen bonus multiplier!")
    }

    override fun getValue(config: Config, entity: LivingEntity) =
        config.getDoubleFromExpression("multiplier", entity as? Player) - 1
}