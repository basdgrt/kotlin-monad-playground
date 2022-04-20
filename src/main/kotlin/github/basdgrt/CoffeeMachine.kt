package github.basdgrt

/*
 * Although ugly, this works.
 */
class CoffeeMachine {
    fun makeCoffee(): Either<MachineFailure, Coffee> {
        val beansEither = grindBeans()
        return when (beansEither) {
            is Either.Failure -> beansEither // instance of Failure
            is Either.Success -> Either.Success(brew(beansEither.value))
        }
    }
}

private fun grindBeans(): Either<MachineFailure, CoffeeBeans> = TODO()

private fun brew(beans: CoffeeBeans): Coffee = TODO()

object Coffee
object CoffeeBeans

sealed class MachineFailure {
    object NotEnoughBeans
}

