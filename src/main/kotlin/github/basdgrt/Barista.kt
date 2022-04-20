package github.basdgrt

/*
 * Compilation fails because serveToCustomer expects coffee as input, but it receives an Either instead.
 * AKA compilation fails because we are not handling possible failures.
 */
class Barista(private val machine: CoffeeMachine) {
    fun handleOrder() {
        val coffee = machine.makeCoffee()
        serveToCustomer(coffee)
    }
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
