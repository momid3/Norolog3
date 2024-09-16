package com.momid.data

open class Data<T>(open val data: T?)

open class Ok<T>(override val data: T): Data<T>(data)

class NoData<T>(): Data<T>(null)

class ErrorData<T>(): Data<T>(null)

fun <T> Data<T>.isOk(): Boolean {
    return this is Ok<T>
}

fun <T> Data<T>.noData(): Boolean {
    return this is NoData<T>
}

fun <T> Data<T>.isError(): Boolean {
    return this is ErrorData<T>
}

fun <T> Data<T>.toAsg(): Data<Asg> {
    return this as Data<Asg>
}

fun <T, R> Data<T>.access(access: T.() -> R): Data<R> {
    if (this is Ok) {
        return Ok(this.data.access())
    } else {
        return NoData()
    }
}

fun <T, R> Data<T>.access(access: T.() -> Data<R>): Data<R> {
    if (this is Ok) {
        return this.data.access()
    } else {
        return NoData()
    }
}
