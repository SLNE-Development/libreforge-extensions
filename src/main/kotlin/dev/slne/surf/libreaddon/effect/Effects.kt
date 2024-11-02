package dev.slne.surf.libreaddon.effect

import com.willfp.libreforge.effects.Effects.register
import dev.slne.surf.libreaddon.effect.custom.*

object Effects {
    init {
        register(EffectDropMobHead)
        register(EffectMagnet)
        register(EffectSilentGaze)
        register(EffectMoreExperience)
        register(EffectSheepnesis)
        register(EffectSilentNight)
        register(EffectOxygenBonus)
    }
}