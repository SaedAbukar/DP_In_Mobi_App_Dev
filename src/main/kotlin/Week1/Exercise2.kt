package Week1

fun main() {
    val numbers = mutableListOf(7)
    for (i in 1..7) {
        val number = (1..40).random()
        if (number in numbers) {
            println("Number is in the list $number")
            continue
        }
        println("Number is not in the list $number")
        numbers.add(number)
    }
    println(numbers)
}
