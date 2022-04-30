import old.Expression
import old.Variable

private operator fun Double.plus(expression: Expression): Expression {
    return expression + this
}
private operator fun Double.times(expression: Expression): Expression {
    return expression * this
}

fun main(args: Array<String>) {
    var x = Variable("x")

    val ex = x * x
    val d = ex.differentiate(x)

    println(ex)
    println(d)
}