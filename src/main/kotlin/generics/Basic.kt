//package generics
//



sealed class List<out T>

object Nil: List<Nothing>(){
    override fun toString(): String {
        return "Nil"
    }
}
data class Cons<T>(val head: T, val tail: List<T>): List<T>(){
    override fun toString(): String {
        return "$head, $tail"
    }
}

fun <T> List<T>.joinToString(sep: Char = ','): String{
    tailrec fun recursiveJoin(list:List<T>, acc: String): String{
        return when(list){
            is Cons -> {
                val separator = if(acc.isEmpty()) "" else "$sep"
                recursiveJoin(list.tail, "$acc$separator${list.head}")
            }
            else -> acc
        }
    }
    return recursiveJoin(this, "")
}

fun main() {
    val nilList = Nil
    val consList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
    println(nilList.joinToString())
    println(consList.joinToString())
}
