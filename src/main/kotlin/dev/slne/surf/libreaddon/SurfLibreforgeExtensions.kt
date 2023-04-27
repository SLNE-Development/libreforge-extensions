package dev.slne.surf.libreaddon

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.extensions.Extension
import dev.slne.surf.libreaddon.effect.Effects
import dev.slne.surf.libreaddon.triggers.Triggers

class SurfLibreforgeExtensions(
    plugin: EcoPlugin
) : Extension(
    plugin
) {

    override fun onEnable() {
        instance = this

        Triggers
        Effects
    }

    override fun onDisable() {
    }

    public override fun getPlugin(): EcoPlugin {
        return super.getPlugin()
    }

    companion object{
        private lateinit var instance : SurfLibreforgeExtensions

        fun getExtension() : SurfLibreforgeExtensions {
            return instance
        }
    }
}