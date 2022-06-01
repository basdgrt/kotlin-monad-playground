package github.basdgrt

class BeanGrinder(private val client: BeansClient) {
    fun grindBeans(amountOfCups: Int): Either<MachineFailure, CoffeeBeans> {
        return try {
            Either.Success(client.getBeans(amountOfCups))
        } catch (exception: OutOfBeansException) {
            Either.Failure(MachineFailure.NotEnoughBeans)
        }
    }
}

// Class from a 3th party library we have no control over.
class BeansClient {
    /**
     * @throws OutOfBeansException
     */
    fun getBeans(amountOfCups: Int): CoffeeBeans = CoffeeBeans
}

object OutOfBeansException : Exception()
