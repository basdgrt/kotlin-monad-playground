package github.basdgrt

sealed class Either<out A, out B> {
    data class Failure<out A>(val value: A) : Either<A, Nothing>()
    data class Success<out B>(val value: B) : Either<Nothing, B>()
}

inline fun <A, B, C> Either<A, B>.map(fn: (B) -> C) = when (this) {
    is Either.Failure -> this
    is Either.Success -> Either.Success(fn(this.value))
}

inline fun <A, B, C> Either<A, B>.flatMap(fn: (B) -> Either<A, C>) = when (this) {
    is Either.Failure -> this
    is Either.Success -> fn(this.value)
}

inline fun <A, B, C> Either<A, B>.fold(onError: (A) -> C, onSuccess: (B) -> C) = when (this) {
    is Either.Failure -> onError(value)
    is Either.Success -> onSuccess(value)
}

inline fun <A, B> Either<A, B>.onFailure(fn: (Either.Failure<A>) -> Nothing): B = when(this) {
    is Either.Failure -> fn(this)
    is Either.Success -> value
}