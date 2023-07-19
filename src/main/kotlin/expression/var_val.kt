package expression
class X {
    val b: Int
        get() = (Math.random()*100).toInt()
}
class Vars{
    companion object{
        const val b =3
    }
}

object Vars2{
    const val b =2
}
fun main() {
    val x = X()
    println(x.b)

    var a = 2
    a =3
    val b =3
    val c:Int

    if(a == 2){
        c= 3
    }

    println( Vars2.b)
    println(Vars.b)
//    println(c) // error
}