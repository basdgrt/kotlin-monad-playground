package github.basdgrt

/*
 * If we modify the coffeeMachine to throw another exception, the code will still compile.
 * We won't be forced to handle the exception here.
 * (example: adding a PowerNotPluggedInException or MissingFilterException)
 */
class Barista(private val machine: CoffeeMachine) {
    fun handleOrder() {
        try {
            val coffee = machine.makeCoffee()
            serveToCustomer(coffee)
        } catch (e: NotEnoughBeansException) {
            // do some specific error handling
            // like refilling the beans compartment and retrying
        } catch (e: Exception) {
            // since the exception can be anything we
            // can't do any valuable here
        }
    }
}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
