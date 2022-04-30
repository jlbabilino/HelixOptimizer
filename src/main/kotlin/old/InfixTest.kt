package old

import kotlin.math.pow

infix fun Double.`^`(x: Double): Double {
    return pow(x)
}
fun main(args: Array<String>) {
    println(3.0 + 2.0 `^` 2.0)
}