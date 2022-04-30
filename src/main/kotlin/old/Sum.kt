package old

class Sum(val addends: List<Expression>) : Expression() {

    init {
        for (addend in addends) {
            dependencies.addAll(addend.dependencies)
        }
    }

    fun collectTerms(): Sum {
        val collectedAddends = mutableListOf<Expression>()
        for (addend in addends) {
            if (addend is Sum) {
                collectedAddends.addAll(addend.addends)
            } else {
                collectedAddends.add(addend)
            }
        }
        return Sum(collectedAddends)
    }

    override fun eval(bindings: Map<Variable, Double>): Double {
        return addends.sumOf { e -> e.eval(bindings) }
    }

    override fun differentiate(variable: Variable): Expression {
        val differentiatedAddends = mutableListOf<Expression>()
        for (addend in addends) {
            if (addend.dependsOn(variable)) {
                differentiatedAddends.add(addend.differentiate(variable))
            }
        }
        return Sum(differentiatedAddends)
    }

    override fun simplify(): Expression {
        return this
    }

    override fun toString(): String {
        var str = ""
        for (addend in addends) {
            str += " + $addend"
        }
        return str.substring(3)
    }
}