package github.basdgrt

import arrow.core.continuations.EffectScope
import arrow.core.continuations.effect
import arrow.fx.coroutines.parZip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

class CoffeeMachine {
    context (EffectScope<MachineFailure>)
    suspend fun makeCoffee(): Coffee =
        parZip(
            Dispatchers.IO,
            { grindBeans() },
            { boilWater() }
        ) { beans, water -> brew(beans, water) }

    private suspend fun EffectScope<MachineFailure>.brew(beans: CoffeeBeans, water: Water): Coffee {
        println("Started brewing")
        delay(1.seconds)
        println("finished brewing")
        return Coffee
    }

    private suspend fun EffectScope<MachineFailure>.grindBeans(): CoffeeBeans {
        println("started grinding beans")
        delay(2.seconds)

        if ((1..100).random() < 20) {
            return shift(MachineFailure.NotEnoughBeans)
        }

        println("finished grinding beans")
        return CoffeeBeans
    }

    private suspend fun EffectScope<MachineFailure>.boilWater(): Water {
        println("start boling water")
        delay(1.seconds)

        if ((1..100).random() > 20) {
            return shift(MachineFailure.MissingFilter)
        }

        println("finished boiling water")
        return Water
    }
}

class Barista(private val machine: CoffeeMachine) {
    suspend fun handleOrder() {
       effect { machine.makeCoffee() }
           .toEither()
           .fold(
               ifRight = { println("Huge success")},
               ifLeft = { println("Failed with $it")}
           )
    }
}

fun main() = runBlocking {
    Barista(CoffeeMachine()).handleOrder()
}

object Coffee
object CoffeeBeans
object Water

sealed class MachineFailure {
    object NotEnoughBeans : MachineFailure()
    object MissingFilter : MachineFailure()
}

