package assignments.week3.threadAndCoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

const val N = 100
class Bank {
    class Account {
        private var amount: Double = 0.0
        private val mutex = Mutex()
        suspend fun deposit(amount: Double) {
            mutex.withLock {
                /*
                This part of the code has to be wrapped with mutex.
                Mutex ensures only one coroutine can access a shared resource or critical section of code at a time.
                It acts like a lock, preventing race conditions and ensuring data consistency.
                */
                val x = this.amount
                delay(1) // simulates processing time
                this.amount = x + amount
            }
        }
        /*
        This method can be wrapped with mutex.withLock() too
        but in this example the value is only read and assigned after the writes are done and coroutines finished.
        In this case the retrieved value will be up to date and correct?
        */
        fun saldo(): Double = amount
    }
    /* Approximate measurement of the given block's execution time */
    fun withTimeMeasurement(title:String, isActive:Boolean=true, code:() -> Unit) {
        if(!isActive) return
        val time = measureTimeMillis { code() }
        println("MEASUREMENT: operation in '$title' took ${time} ms")
    }
    data class Saldos(val saldo1: Double, val saldo2: Double)
    fun bankProcess(account: Account): Saldos {
        var saldo1: Double = 0.0
        var saldo2: Double = 0.0
        /* we measure the execution time of one deposit task */
        withTimeMeasurement("Single coroutine deposit $N times") {
            runBlocking {
                launch {
                    for (i in 1..N)
                        account.deposit(1.0)
                }
            }
            saldo1 = account.saldo()
        }
        /* then we measure the execution time of two simultaneous deposit tasks using
        coroutines */
        withTimeMeasurement("Two $N times deposit coroutines together", isActive = true) {
            runBlocking {
                launch {
                    repeat(N) { account.deposit(1.0);}
                }
                launch {
                    repeat(N) { account.deposit(1.0);}
                }
                /*
                saldo2 = account.saldo()
                If you assign the value in this place,
                the value will be assigned before either of the coroutines finishes.
                */
            }
            saldo2 = account.saldo() //Here is the correct place to assign the value, after the coroutines have finished
        }
        return Saldos(saldo1, saldo2)
    }
    fun tester() {
        val bank = Bank()
        val results = bank.bankProcess(Account())
        println("Saldo1: ${results.saldo1} Saldo2: ${results.saldo2}")
    }
}
fun main(args: Array<String>) {
    Bank().tester()
}