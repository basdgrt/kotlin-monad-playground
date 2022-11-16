package github.basdgrt

class Barista(private val machine: CoffeeMachine) {
    suspend fun handleOrder() = machine.makeCoffee()
        .fold(
            transform = ::serveToCustomer,
            recover = { /* Pattern match over possible failures */ },
            error = { println(it.message) }
        )
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
private fun handleFailure(failure: MachineFailure): Unit = TODO()