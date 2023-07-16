package Expression

infix fun String.rotate(count: Int): String {
    val index = count % length
    return substring(index) + substring(0, index)
}

class Book
data class Desk(val name: String)
infix fun Book.on(desk: Desk): String {
    return "Book on $desk"
}

fun main() {
    val list = listOf(1, 2, 3, 4)
    println(2 in list)
    println(list.contains(2))
    println(list.containsAll(listOf(1, 3)))

    println("hello" === "hello")

    val map = mapOf(
        "Hello" to 2,
        "World" to 3
    )
    println(map["Hello"])
    map.forEach { (key, value) ->
        println("$key -> $value")
    }

    println(2.compareTo(3) > 0)

    val value = "helloworld" rotate 5
    println(value)

    println(Book().on(Desk("desk")))
}