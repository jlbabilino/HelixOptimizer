data class Variable(val name: String) : Expression() {

    init {
        dependencies.add(this)
    }

    override fun eval(bindings: Map<Variable, Double>): Double {
        return if (bindings.containsKey(this)) {
            bindings[this] ?: Double.NaN
        } else {
            Double.NaN
        }
    }

    override fun differentiate(variable: Variable): Expression {
        return Constant(if (dependsOn(variable)) 1.0 else 0.0)
    }

    override fun simplify(): Expression {
        return this
    }

    override fun toString(): String {
        return name
    }
}