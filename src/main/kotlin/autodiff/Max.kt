package autodiff

import kotlin.math.max

class Max(val expression1: Variable, val expression2: Variable) : Variable() {

    init {
        children.add(expression1)
        children.add(expression2)
        expression1.parents.add(this)
        expression2.parents.add(this)
        name = "max(x,y)"
    }

    override fun updateX(): Double {
        x = max(expression1.x, expression2.x)
        return x
    }

    override fun updateXDot(): Double {
        xDot = if (expression1.x >= expression2.x) {
            expression1.xDot
        } else {
            expression2.xDot
        }
        return xDot
    }

    override fun partial(variable: Variable): Double {
        return if ((expression1.x >= expression2.x && variable == expression1) ||
                    expression1.x < expression2.x && variable == expression2) {
            1.0
        } else {
            0.0
        }
    }

    override fun toString(): String {
        return "$name = max(${expression1.name},${expression2.name}) -- ${super.toString()}"
    }
}