package github.basdgrt

class Barista(private val machine: CoffeeMachine) {
    fun handleOrder() {
        val coffee = machine.makeCoffee()
        serveToCustomer(coffee)
    }
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
