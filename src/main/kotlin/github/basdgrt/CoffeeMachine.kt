package github.basdgrt

import arrow.core.left
import arrow.core.Either
import arrow.core.continuations.either

class CoffeeMachine {
    suspend fun makeCoffee(): Either<MachineFailure, Coffee> =
        either {
            val beans = grindBeans().bind()
            val water = boilWater().bind()
            brew(beans, water).bind()
        }
}

private fun grindBeans(): arrow.core.Either<MachineFailure, CoffeeBeans> = TODO()
private fun boilWater(): arrow.core.Either<MachineFailure, Water> = TODO()

private fun brew(beans: CoffeeBeans, water: Water): Either<MachineFailure, Coffee> = TODO()

object Coffee
object CoffeeBeans
object Water

sealed class MachineFailure {
    object NotEnoughBeans : MachineFailure()
    object MissingFilter : MachineFailure()
    data class UnknownIssue(val exception: Exception) : MachineFailure()
}

