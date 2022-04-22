package github.basdgrt

class Barista(private val machine: CoffeeMachine) {
    fun handleOrder() {
        val coffee = machine.makeCoffee().fold(
            onSuccess = { serveToCustomer(it) },
            onFailure = {
                when (it) {
                    MachineFailure.MissingFilter -> TODO()
                    MachineFailure.NotEnoughBeans -> TODO()
                    is MachineFailure.UnknownIssue -> TODO()
                }
            })
    }
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
