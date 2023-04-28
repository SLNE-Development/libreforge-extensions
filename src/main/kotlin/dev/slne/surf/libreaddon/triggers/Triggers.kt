package dev.slne.surf.libreaddon.triggers

import com.willfp.libreforge.triggers.Triggers.register
import dev.slne.surf.libreaddon.triggers.custom.TriggerEndermanTarget
import dev.slne.surf.libreaddon.triggers.custom.TriggerSchedule

object Triggers {
    init {
        register(TriggerSchedule)
        register(TriggerEndermanTarget)
    }
}