package github.basdgrt

import arrow.core.continuations.EffectScope
import arrow.core.continuations.effect
import arrow.core.continuations.either
import arrow.fx.coroutines.parZip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


class CoffeeMachine {
    context (EffectScope<MachineFailure>)
    suspend fun makeCoffee(): Coffee = parZip(
        Dispatchers.IO,
        { grindBeans() },
        { boilWater() }
    ) { beans, water -> brew(beans, water) }

    // TODO use context receiver here
    private suspend fun EffectScope<MachineFailure>.grindBeans(): CoffeeBeans = TODO()
    private suspend fun EffectScope<MachineFailure>.boilWater(): Water = TODO()
    private suspend fun EffectScope<MachineFailure>.brew(beans: CoffeeBeans, water: Water): Coffee = TODO()
}

class Barista(private val machine: CoffeeMachine) {
    suspend fun handleOrderFoo() = effect { machine.makeCoffee() }
        .fold(
            transform = { println("Huge success") },
            recover = { /* pattern match over failures */ },
            error = { /* do something with exception */ }
        )

    suspend fun handleOrder() = either { machine.makeCoffee() }
        .fold(
            ifRight = { println("Huge success") },
            ifLeft = { /* pattern match over failures */ }
        )
}

fun main() = runBlocking {
    Barista(CoffeeMachine()).handleOrder()
}

object Coffee
object CoffeeBeans
object Water

sealed class MachineFailure {
    object NotEnoughBeans : MachineFailure()
    object MissingFilter : MachineFailure()
}

