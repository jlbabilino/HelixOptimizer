package autodiff

class Sum(val addend1: Variable, val addend2: Variable) : Variable() {

    init {
        childrenMutable.add(addend1)
        childrenMutable.add(addend2)
    }

    override fun updateX(): Double {
        x = addend1.x + addend2.x
        return x
    }

    override fun updateXDot(): Double {
        xDot = addend1.xDot + addend2.xDot
        return xDot
    }

    override fun partial(variable: Variable): Double {
        return 1.0
    }
}