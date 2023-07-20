package generics

import kotlin.math.max

fun <T: Comparable<T>> maxOf(a: T, b: T): T{
    return if(a> b) a else b
}

fun <T, R> callMax(a:T, b: T): R
    where T : Comparable<T>, T: ()-> R, R: Number {
    return if(a> b) a() else b()
}

class Map<K, V> where K: java.io.Serializable, V: Comparable<V>

fun main() {
    val max = maxOf("hello", "world")
    println(max)
}