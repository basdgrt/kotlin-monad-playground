package github.basdgrt

class CoffeeMachine {
    fun makeCoffee(): Coffee {
        val beans = grindBeans()
        return brew(beans)
    }
}

private fun grindBeans(): CoffeeBeans = TODO()
private fun brew(beans: CoffeeBeans): Coffee = TODO()

object Coffee
object CoffeeBeans

