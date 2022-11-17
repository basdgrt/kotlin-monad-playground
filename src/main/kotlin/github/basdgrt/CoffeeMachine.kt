package github.basdgrt

import arrow.core.continuations.Effect
import arrow.core.continuations.effect
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class CoffeeMachine {
    // MakeCoffee is a function that returns coffee, interrupts with MachineFailure, or throws a throwable
    // Also show the updated barista
    suspend fun makeCoffee(): Effect<MachineFailure, Coffee> =
        effect {
            val beans = grindBeans().bind()
            val water = boilWater().bind()
            brew(beans, water).bind()
        }
}

suspend fun grindBeans(): Effect<MachineFailure, CoffeeBeans> = effect {
    if (hasEnoughBeans()) {
        delay(5.seconds)
        CoffeeBeans
    } else {
        shift(MachineFailure.NotEnoughBeans)
    }
}

suspend fun hasEnoughBeans(): Boolean = TODO()

suspend fun boilWater(): Effect<MachineFailure, Water> = TODO()
suspend fun brew(beans: CoffeeBeans, water: Water): Effect<MachineFailure, Coffee> = TODO()

object Coffee
object CoffeeBeans
object Water

sealed class MachineFailure {
    object NotEnoughBeans : MachineFailure()
    object MissingFilter : MachineFailure()
    data class UnknownIssue(val exception: Exception) : MachineFailure()
}

