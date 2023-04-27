package dev.slne.surf.libreaddon.util

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta


fun LivingEntity.getHead(): ItemStack {
    val head: ItemStack

    if (this is Player) {
        head = ItemStack(Material.PLAYER_HEAD)
        head.editMeta { meta -> if (meta is SkullMeta) meta.playerProfile = this.playerProfile}
    }else {
        head = when(this.type) {
            EntityType.ZOMBIE -> ItemStack(Material.ZOMBIE_HEAD)
            EntityType.CREEPER -> ItemStack(Material.CREEPER_HEAD)
            EntityType.SKELETON -> ItemStack(Material.SKELETON_SKULL)
            EntityType.PIGLIN -> ItemStack(Material.PIGLIN_HEAD)
            EntityType.ENDER_DRAGON -> ItemStack(Material.DRAGON_HEAD)
            else -> ItemStack(Material.AIR)
        }
    }
    return head
}
