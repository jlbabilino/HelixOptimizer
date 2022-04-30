package old

class Product(val factors: List<Expression>) : Expression() {

    init {
        for (factor in factors) {
            dependencies.addAll(factor.dependencies)
        }
    }

    fun collectFactors(): Product {
        val collectedFactors = mutableListOf<Expression>()
        for (factor in factors) {
            if (factor is Product) {
                collectedFactors.addAll(factor.factors)
            } else {
                collectedFactors.add(factor)
            }
        }
        return Product(collectedFactors)
    }
    override fun eval(bindings: Map<Variable, Double>): Double {
        var product = 1.0
        for (e in factors) {
            product *= e.eval(bindings)
        }
        return product
    }

    override fun differentiate(variable: Variable): Expression {
        val constants = mutableListOf<Expression>()
        val functions = mutableListOf<Expression>()
        for (factor in factors) {
            if (factor.dependsOn(variable)) {
                functions
            } else {
                constants
            }.add(factor)
        }
        return Product(constants.plus(productRule(variable, functions)))
    }

    private fun productRule(variable: Variable, functions: List<Expression>): Sum {
        val functionsList = functions.toList()
        val n = functionsList.size
        val differentiatedAddends = mutableListOf<Expression>()
        for (i in 0 until n) {
            val differentiatedFactors = mutableListOf<Expression>()
            for (j in 0 until n) {
                var expression = functionsList[j]
                if (j == i) {
                    expression = expression.differentiate(variable)
                }
                differentiatedFactors.add(expression)
            }
            differentiatedAddends.add(Product(differentiatedFactors))
        }
        return Sum(differentiatedAddends)
    }

    override fun simplify(): Expression {

        return this
    }

    override fun toString(): String {
        var str = ""
        for (factor in factors) {
            str += " * $factor"
        }
        return str.substring(3)
    }
}