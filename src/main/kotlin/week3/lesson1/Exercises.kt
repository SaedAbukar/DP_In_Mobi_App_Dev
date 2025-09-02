package week3.lesson1

val map = mapOf<Int,String>(
    0 to "zero", 1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five",
    6 to "six", 7 to "seven", 8 to "eight", 9 to "nine",
)
fun intToText(a : Int, base: Int = 10) : String {
    require(
        a >= 0,
        { "Value must be bigger than zero" }
    )
    if (a / base == 0) return map[a]?: "N/A"
    else return intToText( a / base, base) + "-" + map[a % base]
    }

fun main(args: Array<String>) {
    println(intToText(5, 10))
    println(intToText(5, 2))
}