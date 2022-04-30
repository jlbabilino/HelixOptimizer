package autodiff

class Product(val factor1: Variable, val factor2: Variable) : Variable() {

    init {
        childrenMutable.add(factor1)
        childrenMutable.add(factor2)
    }

    override fun updateX(): Double {
        x = factor1.x * factor2.x
        return x
    }

    override fun updateXDot(): Double {
        xDot = factor1.x * factor2.xDot + factor1.xDot * factor2.x
        return xDot
    }

    override fun partial(variable: Variable): Double {
        return if (variable == factor1) factor2.x else factor1.x
    }
}