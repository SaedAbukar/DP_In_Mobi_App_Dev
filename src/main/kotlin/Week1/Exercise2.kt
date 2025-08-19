package Week1

fun main() {
    /*val numbers: MutableSet<Int> = mutableSetOf()
    while (numbers.size < 7) {
        val number = (1..40).random()
        numbers.add(number)
    }
    println(numbers.sorted())*/
    val nums1: MutableSet<Int> = mutableSetOf(1,2,3,4,5,6,7,8,9,10) // false
    val nums2: MutableList<Int> = mutableListOf(1,2,3,4,5,6,7) // true
    val nums3: Set<Int> = setOf(1,2,3,4,5,6) // false
    val nums4: List<Int> = listOf(1,2,3,4,5,6,41) // false
    val nums5: MutableSet<Int> = mutableSetOf(0,1,2,3,4,5,6) // false
    val nums6: Set<Int> = setOf(1,2,3,4,5,6,7) // true

    println(isLegalLottoNumbers(nums1))
    println(isLegalLottoNumbers(nums2))
    println(isLegalLottoNumbers(nums3))
    println(isLegalLottoNumbers(nums4))
    println(isLegalLottoNumbers(nums5))
    println(isLegalLottoNumbers(nums6))
}

fun isLegalLottoNumbers(numbers: Collection<Int>): Boolean {
    if (numbers.size != 7 && numbers.toSet().size != 7) return false
    for (number in numbers) {
        if (number !in 1..40) return false
    }
    return true
}
