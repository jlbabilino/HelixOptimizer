import kotlin.math.pow

class Power(val base: Expression, val expo: Expression) : Expression() {

    override fun eval(bindings: Map<Variable, Double>): Double {
        return base.eval(bindings).pow(expo.eval(bindings))
    }

    override fun differentiate(variable: Variable): Expression {
        return Constant(0.0)
    }

    override fun simplify(): Expression {
        return this
    }

    override fun toString(): String {
        return "($base)^($expo)"
    }
}