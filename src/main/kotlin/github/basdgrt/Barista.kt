package github.basdgrt

import arrow.core.continuations.effect
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

//class Barista(private val machine: CoffeeMachine) {
//    suspend fun handleOrder() {
//        effect { machine.makeCoffee() }
//            .toEither()
//            .fold(
//                ifRight = ::serveToCustomer,
//                ifLeft = {
//                    when (it) {
//                        MachineFailure.MissingFilter -> TODO()
//                        MachineFailure.NotEnoughBeans -> TODO()
//                    }
//                }
//            )
//    }
//}

private fun serveToCustomer(coffee: Coffee): Unit = TODO()
