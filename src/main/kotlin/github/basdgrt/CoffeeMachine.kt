package github.basdgrt

import arrow.core.continuations.EffectScope
import arrow.core.continuations.effect
import arrow.fx.coroutines.parZip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

suspend fun brew(beans: CoffeeBeans, water: Water): Coffee {
    println("Started brewing")
    delay(1.seconds)
    println("finished brewing")
    return Coffee
}

suspend fun EffectScope<MachineFailure>.grindBeans(): CoffeeBeans {
    println("started grinding beans")
    delay(2.seconds)

    if ((1..100).random() < 20) {
        return shift(MachineFailure.NotEnoughBeans)
    }

    println("finished grinding beans")
    return CoffeeBeans
}

suspend fun EffectScope<MachineFailure>.boilWater(): Water {
    println("start boling water")
    delay(1.seconds)

    if ((1..100).random() < 20) {
        return shift(MachineFailure.MissingFilter)
    }

    println("finished boiling water")
    return Water
}

suspend fun EffectScope<MachineFailure>.makeCoffee(): Coffee =
    parZip(
        Dispatchers.IO,
        { grindBeans() },
        { boilWater() }
    ) { beans, water -> brew(beans, water) }


fun main() = runBlocking {
    effect { makeCoffee() }
        .toEither()
        .fold(
            ifRight = { println("Huge success")},
            ifLeft = { println("Failed with $it")}
        )
}

object Coffee
object CoffeeBeans
object Water

sealed class MachineFailure {
    object NotEnoughBeans : MachineFailure()
    object MissingFilter : MachineFailure()
}

