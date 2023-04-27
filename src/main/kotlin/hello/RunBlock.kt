package hello

fun runBlock(block: ()-> Unit){
    val start = System.currentTimeMillis()
    block()
    println(System.currentTimeMillis() - start)
}

fun main() {
    runBlock{
        val li = List(100){
            println(it)
            println("HelloWorld")
            it*2
        }
        println(li)
    }
}