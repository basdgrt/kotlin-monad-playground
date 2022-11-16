package github.basdgrt

import arrow.core.Either
import arrow.core.continuations.either
import arrow.fx.coroutines.parZip
import kotlinx.coroutines.Dispatchers

class CoffeeMachine {
    suspend fun makeCoffee(): Either<MachineFailure, Coffee> = either {
        parZip(
            Dispatchers.IO,
            { grindBeans().bind() },
            { boilWater().bind() }
        ) { beans, water -> brew(beans, water).bind() }
    }
}

private suspend fun grindBeans(): Either<MachineFailure, CoffeeBeans> = TODO()
private suspend fun boilWater(): Either<MachineFailure, Water> = TODO()

private suspend fun brew(beans: CoffeeBeans, water: Water): Either<MachineFailure, Coffee> = TODO()

object Coffee
object CoffeeBeans
object Water

sealed class MachineFailure {
    object NotEnoughBeans : MachineFailure()
    object MissingFilter : MachineFailure()
    data class UnknownIssue(val exception: Exception) : MachineFailure()
}

