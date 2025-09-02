import week3.lesson1.map
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.locks.ReentrantReadWriteLock

class Lotto {
    private val numbers: MutableSet<Int> = mutableSetOf()
    var guesses: Int = 0
    var results: MutableMap<Int, Int> = mutableMapOf()
    private val lock = ReentrantReadWriteLock()
    private val writeLock = lock.writeLock()
    init {
        while (numbers.size < 7) {
            val number = (1..40).random()
            numbers.add(number)
        }
    }

    fun check(other: Collection<Int>){
        if (!isLegalLottoNumbers(other)) return
        writeLock.lock()
        val index = numbers.intersect(other.toSet()).size
        val value = results.get(index)?: 0
        results.put(index, value + 1)
        guesses++
        writeLock.unlock()
    }

    fun addToMap(other: Collection<Int>) {
        val index = numbers.intersect(other.toSet()).size
        val value = results.get(index)?: 0
        results.put(index, value + 1)
    }

    private fun isLegalLottoNumbers(numbers: Collection<Int>): Boolean {
        if (numbers.size != 7 && numbers.toSet().size != 7) return false
        for (number in numbers) {
            if (number !in 1..40) return false
        }
        return true
    }
}

fun generateLottoNumbers(): MutableSet<Int> {
    val numbers = mutableSetOf<Int>()
    while (numbers.size < 7) {
        val number = ThreadLocalRandom.current().nextInt(1, 41)
        numbers.add(number)
    }
    return numbers
}

fun main() {
    val threadAmount = 4
    val guesses = 13500000
    val upTo = guesses / threadAmount
    val lotto = Lotto()
    val threads = ( 1 .. 4).map {
        Thread {
            println("Thread $it/4 starting for $upTo guesses")
            (1..upTo).forEach { _ ->
                lotto.check(generateLottoNumbers())
            }
        }
    }
    val start = System.currentTimeMillis()
    threads.forEach { it.start() }
    threads.forEach { it.join() }
    val end = System.currentTimeMillis()
    println("All joined")
    println("Running time ${end - start}ms")
    println("Checksum: ${lotto.guesses} should be $guesses")
    lotto.results.forEach{ (keys, values) -> println("$keys: $values") }
}
