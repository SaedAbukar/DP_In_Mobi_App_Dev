package week4.lesson1

import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.thread
import kotlin.math.min
import kotlin.random.Random

val arr: MutableList<Int> = mutableListOf(1,2,3,4,5,6,7,8,9,10)

fun myShuffle(a: MutableList<Int>): MutableList<Int> {
    for (i in a.indices) {
        val random = Math.floor(Math.random() * a.size).toInt()
        val temp = a[i]
        a[i] = a[random]
        a[random] = temp
    }
    return a
}

fun yatesFP(a: List<Int>): List<Int> {
    if (a.isEmpty()) return a

    val rand = (0 until a.size).random()
    val chosen = a.drop(rand).first()

    // remove the chosen element
    val remaining = a.take(rand) + a.drop(rand + 1)

    // recurse on the remaining list and prepend chosen
    return listOf(chosen) + yatesFP(remaining)
}

fun testShuffle(f: (MutableList<Int>) -> Unit) {
    val sums = MutableList(10) {-5.0}
    val upper = 50000000
    (1..upper).forEach {
        val a = (0..10).toMutableList()
        yatesFP(a)
        for (i in sums.indices) {
            sums[i] += a[i].toDouble() / upper
        }
        println(sums)
    }
}


fun main(args: Array<String>) {
    val globalSums: MutableMap<Int, Int> = mutableMapOf()
    val lock = ReentrantReadWriteLock()
    val writeLock = lock.writeLock()
    val threadAmount = 4
    val guesses = 120
    val upTo = guesses / threadAmount
    val threads = (1..threadAmount).map {
        Thread {
            val sums = MutableList(10) {0}
            val n = guesses/threadAmount
            println("Thread $it tests $n")
            (1..n).forEach { _ ->
                val a = (0..10).toMutableList()
                yatesFP(a)
                for (i in sums.indices) {
                    sums[i] += a[i]
                }
            }
            println("Thread $it locking")
            writeLock.lock()
            for (i in sums.indices) {
                globalSums[i] = sums[i]
            }
            writeLock.unlock()
        }
    }
    threads.forEach { it.start() }
    threads.forEach { it.join() }
    globalSums.forEach {
        println("${it.key} = ${it.value}")
    }
}