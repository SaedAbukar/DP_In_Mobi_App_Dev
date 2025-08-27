package assignments.week2

import java.util.*

class FractionImmutable(nominatorInput: Int, denominatorInput: Int, val sign: Int = 1) : Comparable<FractionImmutable> {
    val nominator: Int
    val denominator: Int
    init {
        require(denominatorInput != 0) { "Denominator cannot be zero" }
        val GCD = calculateGCD(nominatorInput, denominatorInput)
        if (denominatorInput < 0) {
            nominator = nominatorInput * sign * -1 / GCD
            denominator = denominatorInput * -1 / GCD
        } else {
            nominator = nominatorInput * sign / GCD
            denominator = denominatorInput / GCD
        }
    }
    fun negate() : FractionImmutable {
        return FractionImmutable(nominator * -1, denominator)
    }

    fun add(other: FractionImmutable) : FractionImmutable{
        if (denominator != other.denominator) {
            return FractionImmutable(nominator * other.denominator + other.nominator * denominator, denominator * other.denominator)
        } else return FractionImmutable(nominator + other.nominator, denominator)
    }

    fun mult(other: FractionImmutable) : FractionImmutable{
        return FractionImmutable(nominator * other.nominator, denominator * other.denominator)
    }

    fun div(other: FractionImmutable) : FractionImmutable {
        require(other.nominator != 0) { "Divisors nominator cannot be zero" }
        var a = nominator * other.denominator
        var b = denominator * other.nominator
        if (other.nominator < 0) {
            a *= -1
            b *= -1
        }
        val GCD = calculateGCD(a, b)
        return FractionImmutable(a / GCD,  b / GCD)
    }

    fun intPart(): Int {
        return nominator / denominator
    }


    operator fun plus(other: FractionImmutable) : FractionImmutable {
        if (denominator != other.denominator) {
            return FractionImmutable(nominator * other.denominator + other.nominator * denominator, denominator * other.denominator)
        } else return FractionImmutable(nominator + other.nominator, denominator)
    }

    operator fun times(other: FractionImmutable) : FractionImmutable {
        return FractionImmutable(nominator * other.nominator, denominator * other.denominator)
    }
    operator fun unaryPlus() = FractionImmutable(+nominator, +denominator)

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

    override fun compareTo(other: FractionImmutable): Int {
        val left = nominator * other.denominator
        val right = other.nominator * denominator
        return left.compareTo(right)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FractionImmutable) return false
        return nominator == other.nominator && denominator == other.denominator
    }

    override fun hashCode(): Int {
        return Objects.hash(nominator, denominator, sign)
    }

    operator fun unaryMinus(): FractionImmutable {
        return FractionImmutable(-nominator, denominator)
    }
}

fun main() {
    val a = FractionImmutable(1, 2, -1)
    println(a)
    println(a.add(FractionImmutable(1, 3)))
    println(a.mult(FractionImmutable(5, 2, -1)))
    println(a.div(FractionImmutable(2, 1)))
    println(-FractionImmutable(1, 6) + FractionImmutable(1, 2))
    println(FractionImmutable(2, 3) * FractionImmutable(3, 2))
    println(FractionImmutable(1, 2) > FractionImmutable(2, 3)) // Comparable interface function compareTo()
}