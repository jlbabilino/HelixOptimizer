package old

data class Constant(val value: Double) : Expression() {

    override fun eval(bindings: Map<Variable, Double>): Double {
        return value
    }

    override fun differentiate(variable: Variable): Expression {
        return Constant(0.0)
    }

    override fun simplify(): Expression {
        return this
    }

    override fun toString(): String {
        return if (value < 0) {
            "($value)"
        } else {
            "$value"
        }
    }

    companion object {
        val E = Constant(Math.E)
        val PI = Constant(Math.PI)
        val ZERO = Constant(0.0)
        val ONE = Constant(1.0)
    }
}