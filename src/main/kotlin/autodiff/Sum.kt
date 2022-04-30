package autodiff

class Sum(val addend1: Variable, val addend2: Variable) : Variable() {

    init {
        children.add(addend1)
        children.add(addend2)
        addend1.parents.add(this)
        addend2.parents.add(this)
        name = "x+y"
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
        return if (addend1 == addend2) {
            2.0
        } else {
            1.0
        }
    }

//    override fun equals(other: Any?): Boolean {
//        return if (other != null && other is Sum) {
//            other.addend1 == addend1 && other.addend2 == addend2
//        } else {
//            false
//        }
//    }

    override fun toString(): String {
        return "$name = ${addend1.name} + ${addend2.name} -- ${super.toString()}"
    }
}