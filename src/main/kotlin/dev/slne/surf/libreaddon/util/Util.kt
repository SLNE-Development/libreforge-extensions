package dev.slne.surf.libreaddon.util

import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item
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
            EntityType.WITHER_SKELETON -> ItemStack(Material.WITHER_SKELETON_SKULL)
            else -> ItemStack(Material.AIR)
        }
    }
    return head
}

fun Player.sendPackets(vararg packets: Packet<*>){
    this as CraftPlayer
    val serverPlayer = this.handle

    for (packet in packets){
        serverPlayer.connection.send(packet)
    }
}

fun Player.sendItemPickupPacket(item: Item){
    this.sendPackets(
        ClientboundTakeItemEntityPacket( // https://wiki.vg/Protocol#Pickup_Item
            item.entityId,
            this.entityId,
            item.itemStack.amount
        )
    )
}
