package dev.slne.surf.libreaddon.effect.custom

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.arguments
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import net.kyori.adventure.sound.Sound
import org.bukkit.entity.Item
import org.bukkit.util.BoundingBox

object EffectMagnet : Effect<NoCompileData>("magnet") {
    override val parameters = setOf(
        TriggerParameter.PLAYER
    )

    override val arguments = arguments {
        require("radius", "You must specify the radius of the BoundingBox")
    }

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val player = data.player?: return false
        val location = player.location
        val radius = config.getDoubleFromExpression("radius", player)
        val items :Collection<Item> = player.world.getNearbyEntities(
            BoundingBox(
            location.x + radius,
            location.y + radius,
            location.z + radius,
            location.x - radius,
            location.y - radius,
            location.z - radius
            )
        ).filterIsInstance<Item>()
            .filter { item -> player.hasLineOfSight(item) }

        items.forEach { item ->
            run {
                if (player.inventory.addItem(item.itemStack).isEmpty()){
                    player.playPickupItemAnimation(item)
                    player.playSound(Sound.sound { builder ->
                        run {
                            builder.type(org.bukkit.Sound.ENTITY_ITEM_PICKUP.key())
                            builder.source(Sound.Source.PLAYER)
                            builder.volume(0.5f)
                            builder.pitch(0.8f)
                        }
                    })
                    item.health = -1
                }
            }
        }
        return true
    }
}