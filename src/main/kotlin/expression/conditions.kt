package expression

import java.lang.Exception

fun testIf(a: Int, b: Int) {
    val c = if (a > b) a else b
    println(c)
}
fun testWhen(a: Int) {
    val c = when (a) {
        0 -> 5
        1 -> 100
        3 -> 32
        else -> 20
    }
    println(c)
}

fun <T> testGeneris(x: T) {
    val c = when (x) {
        is String -> x.length
        is Number -> 100
        else -> 20
    }
    println(c)
}

fun testInput() {
    val c = when (val input = readlnOrNull()) {
        null -> 0
        else -> input.length
    }
    println(c)
}

fun testTryCatch(a: Int, b: Int){
    val c =try {
        a / b
    } catch (e: ArithmeticException){
        2
    } catch (e: Exception) {
        e.printStackTrace()
        0
    }
    println(c)
}
fun main() {
    testIf(1, 2)
    testWhen(3)
    testGeneris(1)
    testGeneris("Hello")
    testInput()
    testTryCatch(1, 0)
}