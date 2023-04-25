package dev.slne.surf.ecoaddon.effect

import com.willfp.libreforge.effects.Effects.register
import dev.slne.surf.ecoaddon.effect.custom.EffectDropMobHead

object Effects {
    init {
        register(EffectDropMobHead)
    }
}