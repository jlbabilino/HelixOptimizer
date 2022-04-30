package autodiff

class Function(val expression: Variable) {

    val table = mutableListOf<Variable>()

    fun constructTable() {
        addChild(expression)
    }

    private fun addChild(variable: Variable) {
        for (child in variable.children) {
            child.superDependencies.add(variable)
            addChild(child)
        }
        if (!table.contains(variable)) {
            variable.name = "v${table.size}"
            table.add(variable)
        }
    }

    fun forwardAutoDiff(): Double {
        println("Forward")
        for (row in table) {
            row.updateX()
            row.updateXDot()
            println(row)
        }
        return table[table.size - 1].xDot
    }

    fun reverseAutoDiff() {
        println("Reverse")
        for (row in table) {
            row.updateX()
        }
        table[table.size - 1].xBar = 1.0
        for (i in (table.size - 2) downTo 0) {
            table[i].updateXBar()
        }
        for (row in table) {
            println(row)
        }
    }
}