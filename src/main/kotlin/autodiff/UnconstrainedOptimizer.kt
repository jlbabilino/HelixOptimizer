package autodiff

class UnconstrainedOptimizer(val function: Function) {
    val objective = function.expressions[0]

    fun onePass() {

    }
}