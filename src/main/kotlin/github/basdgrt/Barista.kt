package github.basdgrt

import github.basdgrt.MachineFailure.*

class Barista(private val machine: CoffeeMachine) {
    fun handleOrder() {
        val coffee = machine.makeCoffee().fold(
            onSuccess = { serveToCustomer(it) },
            onFailure = {
                when (it) {
                    is NotEnoughBeans -> TODO()
                    is WaterTooCold -> TODO()
                }
            })
    }
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
