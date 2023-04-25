package dev.slne.surf.ecoaddon

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.extensions.Extension
import dev.slne.surf.ecoaddon.effect.Effects

class AddonExtension(
    plugin: EcoPlugin
) : Extension(
    plugin
) {

    override fun onEnable() {
        Effects
    }

    override fun onDisable() {

    }
}