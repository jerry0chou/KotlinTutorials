package builtinTypes

fun main() {
    val a = IntArray(5){it*2+1}
    println(a)
    println(a.contentToString())
    println(a.joinToString(";", "!", "!"))
    println(intArrayOf(1,2,3,4,5))

    val d = arrayOf("hello", "world")
    d[1] = "kotlin"
    println("${d[0]} ${d[1]}")

    val f = floatArrayOf(1f, 2f, 3f, 4f)
    for (e in f) println(e)
    f.forEach { println(it) }
}

