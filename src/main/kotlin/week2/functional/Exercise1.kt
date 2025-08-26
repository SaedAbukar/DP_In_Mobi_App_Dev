package week2.functional

fun main(args: Array<String>) {
    fun lenFilt(xs: List<String>): List<String> {
        if (xs.isEmpty()) return emptyList()
        return if (xs.first().length > 2)
            listOf(xs.first()) + lenFilt(xs.drop(1))
        else
            lenFilt(xs.drop(1))
    }

    fun mapLen(xs: List<String>): List<Int> {
        if (xs.isEmpty()) return emptyList()
        return listOf(xs.first().length) + mapLen(xs.drop(1))
    }

    var test = listOf("abc", "de", "f", "xyz")
    lenFilt(test)
    mapLen(test)

    test.fold(listOf<String>()) { acc, c -> if (c.length > 2) acc.plus(c) else acc}
    test.fold(listOf<Int>()) { acc, c -> acc.plus(c.length) }
}