package autodiff
fun main(args: Array<String>) {
    val x1 =  Variable()
    val x2 =  Variable()
    val x3 =  Variable()
    val f = Function(x1*(x2+x3) + x1*x2)
    x1.x = 3.0
    x2.x = -4.0
    x3.x = 9.0
    x1.xDot = 0.0
    x2.xDot = 0.0
    x3.xDot = 1.0
    f.constructTable()
    println(f.forwardAutoDiff())
    f.reverseAutoDiff()
}