package github.basdgrt

class CoffeeMachine {
    fun makeCoffee(): Either<MachineFailure, Coffee> {
        return grindBeans().map { brew(it) }
    }
}

private fun grindBeans(): Either<MachineFailure, CoffeeBeans> = TODO()

private fun brew(beans: CoffeeBeans): Coffee = TODO()

object Coffee
object CoffeeBeans

sealed class MachineFailure {
    object NotEnoughBeans
}

