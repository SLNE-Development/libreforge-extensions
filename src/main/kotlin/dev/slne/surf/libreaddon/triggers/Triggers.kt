package dev.slne.surf.libreaddon.triggers

import com.willfp.libreforge.triggers.Triggers.register
import dev.slne.surf.libreaddon.triggers.custom.*

object Triggers {
    init {
        register(TriggerSchedule) /* Uncomment to enable */
        register(TriggerEndermanTarget)
        register(TriggerDropXp)
        register(TriggerBeheading)
        register(TriggerPhantomTarget)
    }
}