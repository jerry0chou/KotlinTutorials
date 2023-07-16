package Expression

operator fun String.minus(right: String) = replace(right, "")
operator fun String.div(right: Int) = length/right
operator fun String.times(right: Int) = repeat(right)


fun main() {
    val value = "hello world"
    println(value)
    println(value - "l")
    println(value * 10)
    println(value / 2)
}