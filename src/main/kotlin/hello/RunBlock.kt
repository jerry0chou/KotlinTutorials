package hello
fun runBlock(a: String, block: (String) -> Unit) {
    val start = System.currentTimeMillis()
    block(a)
    println(System.currentTimeMillis() - start)
}

fun main() {

    runBlock("122"){
        println(it)
    }
}