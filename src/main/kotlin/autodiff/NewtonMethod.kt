package autodiff

import kotlin.math.abs

fun main(args: Array<String>) {
    val x = Variable()
    // 7x^3-4x^2-x-4
    val fEx = x*x*x*7.0 - x*x*4.0 - x - 4.0
    val f = Function(listOf(x), listOf(fEx))
    x.x = -70.0
    println("Starting value: ${x.x}")
//    var time = System.currentTimeMillis()
    for (i in 0..50) {
        var derivative = f.forwardAutoDiff(x)[0]
        var yVal = fEx.x
        x.x -= yVal / derivative
        println("$i: ${x.x}")
    }
//    time = System.currentTimeMillis() - time
//    println("Finished in $time")
}