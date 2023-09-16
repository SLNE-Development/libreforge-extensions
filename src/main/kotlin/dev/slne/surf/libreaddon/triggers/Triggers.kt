package dev.slne.surf.libreaddon.triggers

import com.willfp.libreforge.triggers.Triggers.register
import dev.slne.surf.libreaddon.triggers.custom.TriggerBeheading
import dev.slne.surf.libreaddon.triggers.custom.TriggerDropXp
import dev.slne.surf.libreaddon.triggers.custom.TriggerEndermanTarget
import dev.slne.surf.libreaddon.triggers.custom.TriggerSchedule

object Triggers {
    init {
        register(TriggerSchedule) /* Uncomment to enable */
        register(TriggerEndermanTarget)
        register(TriggerDropXp)
        register(TriggerBeheading)
    }
}