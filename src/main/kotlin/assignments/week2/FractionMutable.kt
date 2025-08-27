package assignments.week2

class FractionMutable(var nominator: Int, var denominator: Int, var sign: Int = 1) {

    init {
        require(denominator != 0) { "Denominator cannot be zero" }
        val GCD = calculateGCD(nominator, denominator)
        nominator *= sign
        nominator /= GCD
        denominator /= GCD
    }
    fun negate() {
        sign *= -1
        nominator *= -1
    }

    fun add(fractionMutable: FractionMutable) {
        if (denominator != fractionMutable.denominator) {
            nominator = nominator * fractionMutable.denominator + fractionMutable.nominator * denominator
            denominator *= fractionMutable.denominator
        } else nominator += fractionMutable.nominator
    }

    fun mult(fractionMutable: FractionMutable) {
        nominator *= fractionMutable.nominator
        denominator *= fractionMutable.denominator
    }

    fun div(fractionMutable: FractionMutable) {
        require(fractionMutable.nominator != 0) { "Divisors nominator cannot be zero" }
        nominator *= fractionMutable.denominator
        denominator *= fractionMutable.nominator
        val GCD = calculateGCD(nominator, denominator)
        nominator /= GCD
        denominator /= GCD
    }

    fun intPart(): Int {
        return nominator / denominator
    }

    fun calculateGCD(a: Int, b: Int): Int {
        var num1 = Math.abs(a)
        var num2 = Math.abs(b)
        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }


    override fun toString(): String {
        return "$nominator/$denominator"
    }
}