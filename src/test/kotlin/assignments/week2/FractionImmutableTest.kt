package assignments.week2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
class FractionImmutableTest {

    @Test
    fun consBasic1() {
        val a = FractionImmutable(1,2)
        assert(a.toString() == "1/2")
    }

    @Test
    fun consBasic2() {
        val a = FractionImmutable(6,12)
        assert(a.toString() == "1/2")
    }

    @Test
    fun consNeg() {
        val a = FractionImmutable(1,2, -1)
        assert(a.toString() == "-1/2")
    }

    @Test
    fun consReduce() {
        val a = FractionImmutable(4,20)
        assert(a.toString() == "1/5")
    }

    @Test
    fun consReduceNeg() {
        val a = FractionImmutable(4,20, -1)
        assert(a.toString() == "-1/5")
    }

    @Test
    fun compareToEq() {
        assert(FractionImmutable(1,2).compareTo(FractionImmutable(2,4)) == 0)
    }

    @Test
    fun compareToGt() {
        assert(FractionImmutable(1,2).compareTo(FractionImmutable(2,5)) == 1)
    }

    @Test
    fun compareToLt() {
        assert(FractionImmutable(1,3).compareTo(FractionImmutable(2,5)) == -1)
    }

    @Test
    fun negate1() {
        assert(FractionImmutable(1,2).negate().toString() == "-1/2")
    }

    @Test
    fun negate2() {
        assert(FractionImmutable(1,2, -1).negate().toString() == "1/2")
    }

    @Test
    fun add() {
        assert(FractionImmutable(11,12).add(FractionImmutable(1,6)) == FractionImmutable(13,12))
    }

    @Test
    fun plus() {
        assert(FractionImmutable(2,3) + FractionImmutable(1,3) == FractionImmutable(1,1))
    }

    @Test
    fun subtr() {
    }

    @Test
    fun minus() {
    }

    @Test
    fun mult() {
    }

    @Test
    fun div() {
    }
}