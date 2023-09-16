package dev.slne.surf.libreaddon.enchant

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.data.keys.PersistentDataKey
import com.willfp.eco.core.data.keys.PersistentDataKeyType
import com.willfp.eco.core.data.profile
import com.willfp.eco.core.drops.DropQueue
import com.willfp.eco.core.fast.fast
import com.willfp.eco.core.items.Items
import com.willfp.ecoenchants.EcoEnchantsPlugin
import com.willfp.ecoenchants.enchants.EcoEnchant
import com.willfp.ecoenchants.target.EnchantLookup.getActiveEnchantLevelInSlot
import com.willfp.ecoenchants.target.EnchantLookup.hasEnchantActive
import dev.slne.surf.libreaddon.SurfLibreforgeExtensions
import net.william278.husksync.event.BukkitDataSaveEvent
import net.william278.husksync.event.BukkitSyncCompleteEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class EnchantmentSoulbound(
    plugin: EcoEnchantsPlugin = EcoEnchantsPlugin.instance
) : EcoEnchant(
    "soulbound",
    plugin,
    force = false
) {
    override fun onInit() {
        this.registerListener(SoulboundHandler(plugin, this))
    }

    private class SoulboundHandler(
        private val plugin: EcoPlugin,
        private val enchant: EcoEnchant
    ) : Listener {
        private val savedSoulboundItems = PersistentDataKey(
            plugin.namespacedKeyFactory.create("soulbound_items"),
            PersistentDataKeyType.STRING_LIST,
            emptyList()
        )

        private val soulboundKey = plugin.namespacedKeyFactory.create("soulbound")

        @EventHandler(priority = EventPriority.HIGHEST)
        fun handle(event: PlayerDeathEvent) {
            if (event.keepInventory) {
                return
            }

            val player = event.player
            val items = mutableListOf<ItemStack>()

            if (!player.hasEnchantActive(enchant)) {
                return
            }

            for ((slot, item) in player.inventory.withIndex()) {
                if (item == null) {
                    continue
                }

                val level = player.getActiveEnchantLevelInSlot(enchant, slot)

                if (level == 0) {
                    continue
                }

                items.add(item)
            }

            if (items.isEmpty()) {
                return
            }

            event.drops.removeAll(items)

            for (item in items) {
                item.fast().persistentDataContainer.set(soulboundKey, PersistentDataType.INTEGER, 1)

                if (enchant.config.getBool("single-use")) {
                    item.editMeta { it.removeEnchant(enchant) }
                }
            }

            player.profile.write(savedSoulboundItems, items.map { Items.toSNBT(it) })
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        fun onDataSync(event: BukkitSyncCompleteEvent) {
            Bukkit.getPlayer(event.user.uuid)?.let { giveItems(it) }
        }

        private fun giveItems(player: Player) {
            val itemStrings = player.profile.read(savedSoulboundItems)

            if (itemStrings.isEmpty()) {
                return
            }

            val items = itemStrings.map { Items.fromSNBT(it) }

            plugin.scheduler.run {
                DropQueue(player)
                    .addItems(items)
                    .forceTelekinesis()
                    .push()
            }

            player.profile.write(savedSoulboundItems, emptyList())
        }
    }
}