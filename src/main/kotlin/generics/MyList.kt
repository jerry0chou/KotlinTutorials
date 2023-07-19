package generics

// Sealed class to represent List
sealed class List<out T>

// Node class (Cons)
data class Cons<T>(val head: T, val tail: List<T>) : List<T>()

// End of List (Nil)
object Nil : List<Nothing>()

fun <T> buildList(index: Int, acc: List<T>, vararg elements: T): List<T> {
    return if (index < elements.size) {
        buildList(index + 1, Cons(elements[index], acc))
    } else {
        acc
    }
}
//// Extension function to create a List from vararg
fun <T> list(vararg elements: T): List<T> {
    tailrec fun buildList(index: Int, acc: List<T>): List<T> {
        return if (index < elements.size) {
            buildList(index + 1, Cons(elements[index], acc))
        } else {
            acc
        }
    }
    return buildList(0, Nil)
}

// Extension function to get the size of the List
fun <T> List<T>.size(): Int = when (this) {
    is Nil -> 0
    is Cons -> 1 + tail.size()
}

// Extension function to get the element at the specified index
infix operator fun <T> List<T>.get(index: Int): T? = when (this) {
    is Nil -> null
    is Cons -> if (index == 0) head else tail[index - 1]
}

// Extension function to check if the List is empty
fun <T> List<T>.isEmpty(): Boolean = this is Nil

// Extension function to convert the List to a regular Kotlin List
fun <T> List<T>.toList(): kotlin.collections.List<T> = when (this) {
    is Nil -> emptyList()
    is Cons -> listOf(head) + tail.toList()
}

fun main() {
//    val myList = Cons(1, Cons(2, Cons(3, Nil)))
    val myList = list(1,2,3,4,5,6,7,8)
    println("List: $myList")
    println("Size: ${myList.size()}")
    println("Element at index 2: ${myList.get(2)}")
    println("Element at index 2: ${myList[2]}")
    println("Is the list empty? ${myList.isEmpty()}")
    println("Converted to Kotlin List: ${myList.toList()}")
}
