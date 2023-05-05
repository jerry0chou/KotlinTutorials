package expression

typealias FN = (Int) -> Unit
fun main() {
    val func: () -> Unit = fun() {
        println("Hello")
    }

    val lambda: FN = { p: Int ->
        println(p)
        println("Hello")
    }

    val f1 = { p: Int ->
        println(p)
        "Hello"
    }

    println(f1(1))
    println(lambda(1))
}