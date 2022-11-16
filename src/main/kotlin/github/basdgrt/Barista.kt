package github.basdgrt

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Barista(private val machine: CoffeeMachine) {
    suspend fun handleOrder() {
        machine.makeCoffee().fold(
            ifRight = ::serveToCustomer,
            ifLeft = {
                when (it) {
                    MachineFailure.MissingFilter -> TODO()
                    MachineFailure.NotEnoughBeans -> TODO()
                    is MachineFailure.UnknownIssue -> TODO()
                }
            }
        )
    }
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
