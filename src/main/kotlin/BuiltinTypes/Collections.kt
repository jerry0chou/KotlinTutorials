package BuiltinTypes

fun main() {

    val intList = listOf<Int>(1, 2, 3, 4, 5)
    val intList2: MutableList<Int> = mutableListOf(1, 2, 3, 4)

    val map: Map<String, Any> = mapOf("name" to "jerry", "age" to 20)
    val map2: Map<String, Any> = mutableMapOf("name" to "jerry", "age" to 20)

    val stringList = ArrayList<String>()

    for (i in 0..10) stringList.add("num: $i")

    for (i in 0..10) stringList += "num: $i"

    for (i in 0..10) stringList -= "num: $i"

    stringList[5] = "helloworld"
    println(stringList)

    val hashMap = HashMap<String, Int>()
    hashMap["hello"]= 10
    println(hashMap["hello"])

    val triple = Triple("x", 2, 3.0)
    val first = triple.first
    val second = triple.second
    val third = triple.third
    val (x, y, z) = triple

    println("$first $second $third, $x $y $z")
}