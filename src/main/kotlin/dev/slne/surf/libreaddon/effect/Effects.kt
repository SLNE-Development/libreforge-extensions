package dev.slne.surf.libreaddon.effect

import com.willfp.libreforge.effects.Effects.register
import dev.slne.surf.libreaddon.effect.custom.EffectDropMobHead
import dev.slne.surf.libreaddon.effect.custom.EffectMagnet

object Effects {
    init {
        register(EffectDropMobHead)
        register(EffectMagnet)
    }
}