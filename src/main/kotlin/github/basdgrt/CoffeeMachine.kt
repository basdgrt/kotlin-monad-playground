package github.basdgrt

class CoffeeMachine {
    fun makeCoffee(): Either<MachineFailure, Coffee> {
        return grindBeans().flatMap { brew(it) }
    }
}

private fun grindBeans(): Either<MachineFailure, CoffeeBeans> = TODO()

private fun brew(beans: CoffeeBeans): Either<MachineFailure, Coffee> = TODO()

object Coffee
object CoffeeBeans

sealed class MachineFailure {
    object NotEnoughBeans
    object MissingFilter
}

