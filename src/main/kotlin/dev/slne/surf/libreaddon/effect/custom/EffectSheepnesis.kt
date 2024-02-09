package dev.slne.surf.libreaddon.effect.custom

import com.willfp.eco.core.drops.DropQueue
import com.willfp.eco.core.integrations.antigrief.AntigriefManager
import com.willfp.eco.util.TelekinesisUtils
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.effects.Effect
import io.papermc.paper.event.block.PlayerShearBlockEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerShearEntityEvent

object EffectSheepnesis :  Effect<NoCompileData>("sheepnesis") {

    @EventHandler(
        priority = EventPriority.HIGH,
        ignoreCancelled = true
    )
    fun handle(event: PlayerShearEntityEvent) {
        val player = event.player
        val sheared = event.entity

        if (!TelekinesisUtils.testPlayer(player)) {
            return
        }

        if (sheared is LivingEntity) {
            if (!AntigriefManager.canInjure(player, sheared)) {
                return
            }
        }

        val drops = event.drops
        event.drops = mutableListOf()

        DropQueue(player)
            .setLocation(sheared.location)
            .addItems(drops)
            .push()
    }

    @EventHandler(
        priority = EventPriority.HIGH,
        ignoreCancelled = true
    )
    fun handle(event: PlayerShearBlockEvent) {
        val player = event.player
        val shearedBlock = event.block

        if (!TelekinesisUtils.testPlayer(player)) {
            return
        }

        if (!AntigriefManager.canBreakBlock(player, shearedBlock)) {
            return
        }

        val drops = ArrayList(event.drops)
        event.drops.clear()

        DropQueue(player)
            .setLocation(shearedBlock.location)
            .addItems(drops)
            .push()
    }
}