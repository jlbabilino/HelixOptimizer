import kotlin.math.exp

class Exp(val exponent: Expression) : Expression() {

    override fun eval(bindings: Map<Variable, Double>): Double {
        return exp(exponent.eval(bindings))
    }

    override fun differentiate(variable: Variable): Expression {
        return this * exponent.differentiate(variable)
    }

    override fun simplify(): Expression {
        val simplifiedExponent = exponent.simplify()
        return if (simplifiedExponent is Constant) {
            when (simplifiedExponent.value) {
                1.0 -> {
                    Constant.E
                }
                0.0 -> {
                    Constant.ONE
                }
                else -> {
                    Exp(simplifiedExponent)
                }
            }
        } else {
            Exp(simplifiedExponent)
        }
            return Constant.E
        return Exp(simplifiedExponent)
    }

    override fun toString(): String {
        return "exp($exponent)"
    }
}