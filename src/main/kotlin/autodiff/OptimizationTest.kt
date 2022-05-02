package autodiff

import kotlin.math.abs

fun main(args: Array<String>) {
    val x = Variable()
    // 7x^{4}-4x^{2}-x-4
    val mainEx = x*x*x*(-1.0) - x
    val penalty1 = (x-3.0) max 0.0
    val penaltyConstant = Variable()
    penaltyConstant.x = 100.0
    val penaltySquared = penalty1*penalty1
    val ex = mainEx + penaltySquared * penaltyConstant
    val f = Function(listOf(x), listOf(ex))
    x.x = 0.0
    println("Starting value: ${x.x}")
//    var time = System.currentTimeMillis()
    var multiplier = .1
    var lastDeriv = 0.0
    for (j in 0..6) {
        multiplier = .1
//        for (k in 0..j) multiplier /= 10.0
        for (i in 0..200) {
            lastDeriv = penaltySquared.xDot
            var derivative = f.forwardAutoDiff(x)[0]
            println("${penaltySquared.xDot * penaltyConstant.x} + ${mainEx.xDot}")
            //        if (abs(derivative) >= abs(lastDeriv)) {
            //            println("decreasing multiplier")
            //            multiplier *= .005
            //        } else if (abs(derivative / lastDeriv) >= 0.8) {
            //            println("increasing multiplier")
            //            multiplier *= 1.2
            //        }
            var oldX = x.x
            x.x -= multiplier * derivative
            if (x.x == oldX) {
                println("Converged")
                break
            }
            println("$i: ${x.x}")
//            if (lastDeriv > 0.0 && penaltySquared.xDot > 0.0) {
//                multiplier *= 0.6
//            }
        }
        penaltyConstant.x *= 10.0
    }
}