package builtinTypes

fun main() {
    val x: (Foo, String, Long)->Any = Foo::bar
    val x0: Function3<Foo, String, Long, Any> = Foo::bar
    val y: (Foo, String, Long)-> Any = x
    val z: Function3<Foo, String, Long, Any> = x0

    val f: ()->Unit = ::foo
    val g: (Int)-> String = ::foo
    val h: (Foo, String, Long)-> Any = Foo::bar
    multiParams(1,2,4,5,7,7)
    defaultParams(y="jerry")
    val (a, b, c) = multiReturnValues()
    println("$a $b $c")
}

class Foo{
    fun bar(p0: String, p1: Long): Any = { TODO() }
}
fun foo() {}
fun foo(p0: Int): String = "foo"
fun defaultParams(x: Int = 5, y: String, z: Long = 0) {
    println("$x $y $z")
}

fun multiParams(vararg ints: Int){
    println(ints.contentToString())
}
fun multiReturnValues(): Triple<Int, Long, Double> = Triple(1, 2L, 3.0)