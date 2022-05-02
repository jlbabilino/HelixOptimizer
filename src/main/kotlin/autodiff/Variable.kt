package autodiff

import java.util.Collections

open class Variable() {

    internal val children = mutableSetOf<Variable>()
    internal val parents = mutableSetOf<Variable>()

    var name = "x"

    var x = 0.0
    var xDot = 0.0
    var xBar = 0.0

    open fun updateX(): Double {
        return x
    }

    open fun updateXDot(): Double {
        return xDot
    }

    open fun partial(variable: Variable): Double {
        return 1.0
    }

    fun updateXBar(): Double {
        for (parent in parents) {
            xBar += parent.xBar * parent.partial(this)
        }
        return xBar
    }

    operator fun plus(addend: Variable): Variable {
        return Sum(this, addend)
    }
    operator fun plus(addend: Double): Variable {
        val constant = Variable()
        constant.x = addend
        return Sum(this, constant)
    }

    operator fun minus(subtrahend: Variable): Variable {
        return Difference(this, subtrahend)
    }
    operator fun minus(subtrahend: Double): Variable {
        val constant = Variable()
        constant.x = subtrahend
        return Difference(this, constant)
    }

    operator fun times(factor: Variable): Variable {
        return Product(this, factor)
    }
    operator fun times(factor: Double): Variable {
        val constant = Variable()
        constant.x = factor
        return Product(this, constant)
    }

    operator fun div(divisor: Variable): Variable {
        return Quotient(this, divisor)
    }
    operator fun div(divisor: Double): Variable {
        val constant = Variable()
        constant.x = divisor
        return Quotient(this, constant)
    }

    infix fun max(expression2: Variable): Variable {
        return Max(this, expression2)
    }
    infix fun max(expression2: Double): Variable {
        val constant = Variable()
        constant.x = expression2
        return Max(this, constant)
    }

    override fun toString(): String {
        return "[$name: $x, $name*: $xDot, $name-: $xBar]"
    }
}