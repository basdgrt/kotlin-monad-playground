package github.basdgrt

import arrow.core.continuations.EffectScope
import arrow.core.continuations.effect
import arrow.fx.coroutines.parZip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


class CoffeeMachine {
    context (EffectScope<MachineFailure>)
    suspend fun makeCoffee() = parZip(
        Dispatchers.IO,
        { grindBeans() },
        { boilWater() }
    ) { beans, water -> brew(beans, water) }

    // TODO use context receiver here
    context (EffectScope<MachineFailure>)
    suspend fun grindBeans(): CoffeeBeans = TODO()

    context (EffectScope<MachineFailure>)
    suspend fun boilWater(): Water = TODO()

    context (EffectScope<MachineFailure>)
    suspend fun brew(beans: CoffeeBeans, water: Water): Coffee = TODO()
}

class Barista(private val machine: CoffeeMachine) {
    suspend fun handleOrder() = effect { machine.makeCoffee() }
        .fold(
            transform = { coffee -> serveToCustomer(coffee) },
            recover = { machineFailure -> handleFailure(machineFailure) },
            error = { println("Hope is postponed disappointment") }
        )
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
private fun handleFailure(failure: MachineFailure): Unit = TODO()


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

