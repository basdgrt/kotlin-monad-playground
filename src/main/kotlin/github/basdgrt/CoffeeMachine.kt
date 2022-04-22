package github.basdgrt

class CoffeeMachine {
    fun makeCoffee(): Either<MachineFailure, Coffee> {
        val beans = grindBeans().onFailure { return it }
        val water = boilWater().onFailure { return it }
        return brew(beans, water)
    }
}

private fun grindBeans(): Either<MachineFailure, CoffeeBeans> = TODO()
private fun boilWater(): Either<MachineFailure, Water> = TODO()

private fun brew(beans: CoffeeBeans, water: Water): Either<MachineFailure, Coffee> = TODO()

object Coffee
object CoffeeBeans
object Water

sealed class MachineFailure {
    object NotEnoughBeans : MachineFailure()
    object MissingFilter : MachineFailure()
    data class UnknownIssue(val exception: Exception) : MachineFailure()
}

