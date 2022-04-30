package autodiff

import java.util.Collections

open class Variable() {

    protected val childrenMutable = mutableSetOf<Variable>()
    val children = Collections.unmodifiableSet(childrenMutable)
    val superDependencies = mutableSetOf<Variable>()

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
        xBar = 0.0
        for (superDependency in superDependencies) {
            xBar += superDependency.xBar * superDependency.partial(this)
        }
        return xBar
    }

    operator fun plus(addend: Variable): Variable {
        return Sum(this, addend)
    }

    operator fun times(addend: Variable): Variable {
        return Product(this, addend)
    }

    override fun toString(): String {
        return "[$name: $x, $name*: $xDot, $name-: $xBar]"
    }
}