package old

abstract class Expression {

    internal val dependencies: MutableList<Variable> = mutableListOf()

    abstract fun eval(bindings: Map<Variable, Double>): Double

    abstract fun differentiate(variable: Variable): Expression

    abstract fun simplify(): Expression

    fun dependsOn(variable: Variable): Boolean {
        return dependencies.contains(variable)
    }

    operator fun unaryMinus(): Expression {
        return this * -1.0
    }

    operator fun plus(addend: Expression): Expression {
        return Sum(listOf(this, addend)).collectTerms()
    }
    operator fun plus(addend: Double): Expression {
        return this + Constant(addend)
    }

    operator fun minus(addend: Expression): Expression {
        return this + (-addend)
    }
    operator fun minus(addend: Double): Expression {
        return this + (-addend)
    }

    operator fun times(factor: Expression): Expression {
        return Product(listOf(this, factor)).collectFactors()
    }
    operator fun times(factor: Double): Expression {
        return this * Constant(factor)
    }

    operator fun div(divisor: Expression): Expression {
        return this * divisor(-1.0)
    }
    operator fun div(divisor: Double): Expression {
        return this * (1.0 / divisor)
    }

    operator fun invoke(expo: Expression): Expression {
        return Power(this, expo)
    }
    operator fun invoke(expo: Double): Expression {
        return this(Constant(expo))
    }
}