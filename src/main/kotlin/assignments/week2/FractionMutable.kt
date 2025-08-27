package assignments.week2

class FractionMutable(var nominator: Int, var denominator: Int, var sign: Int = 1) {

    init {
        nominator *= sign
    }
    fun negate() {
        TODO("Not yet implemented")
    }

    fun add(fractionMutable: FractionMutable) {
        TODO("Not yet implemented")
    }

    fun mult(fractionMutable: FractionMutable) {
        TODO("Not yet implemented")
    }

    fun div(fractionMutable: FractionMutable) {
        TODO("Not yet implemented")
    }

    fun intPart(): Any {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "$nominator/$denominator"
    }
}