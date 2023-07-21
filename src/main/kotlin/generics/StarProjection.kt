package generics

class QueryMap<out K: CharSequence, out V: Any>{
    fun getKey(): K = TODO()
    fun getValue(): V = TODO()
}
fun <T: Comparable<T>> max2Of(a: T, b:T): T{
    return if(a> b) a else b
}

class Function<in P1, in P2>{
    fun invoke(p1: P1,p2: P2)= Unit
}

fun main() {
    val query: QueryMap<*, *> = QueryMap<String, Int>()
    query.getKey()
    query.getValue()
    val f: Function<*,*> = Function<Number, Any>()

    if(f is Function)
        (f as Function<Number, Any>).invoke(1, Any())

    max2Of(1,3)
    HashMap<String, List<*>>()
    val hashMap: HashMap<*, *> = HashMap<String, Int>()
}