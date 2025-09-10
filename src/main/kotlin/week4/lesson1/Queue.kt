package week4.lesson1

data class Queue(var leading: List<Int> = listOf(), var trailing: List<Int> = listOf()) {

    fun enqueue(i: Int) {
        trailing = trailing + listOf(i)
    }
    fun dequeue() {
        if (leading.isEmpty()) {
            trailingToLeading()
        }
        if (leading.isNotEmpty()) {
            leading = leading.drop(1)
        }
    }


    fun head(): Int? {
        if (leading.isEmpty()) trailingToLeading()
        return leading.firstOrNull()
    }

    private fun trailingToLeading() {
        if (trailing.isNotEmpty()) {
            leading = trailing.reversed()
            trailing = listOf()
        }
    }

}

fun main() {
    val q = Queue()
    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    q.enqueue(4)
    q.enqueue(5)
    println(q)
    println(q.head())
    println(q)
    println(q.dequeue())
    println(q)
    println(q.head())
    println(q)
    println(q.enqueue(6))
    println(q)
    println(q.head())
}