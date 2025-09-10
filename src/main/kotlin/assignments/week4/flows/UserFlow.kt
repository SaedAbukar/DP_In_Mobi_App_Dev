package assignments.week4.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException
data class User(val id: Int, val name: String, val status: String)
// Mock data source - DO NOT MODIFY
val allUsers = listOf(
    User(1, "Alice", "inactive"),
    User(2, "Bob", "active"),
    User(3, "Charlie", "inactive"),
    User(4, "Diana", "inactive")
)
// A mock function that simulates fetching user data from a network.
// It randomly changes a user's status to "active".
// It will throw an error after a few successful calls.
private var fetchCount = 0
fun fetchUsers(): List<User> {
    println("Fetching user data...")
    fetchCount++
    if (fetchCount > 4) {
        throw IOException("Simulated network error: Server is down.")
    }
// Create a mutable copy to modify
    val updatedUsers = allUsers.map { it.copy() }.toMutableList()
// Randomly set one inactive user to active
    val userToActivate = updatedUsers.filter { it.status == "inactive" }.randomOrNull()
    userToActivate?.let {
        val index = updatedUsers.indexOf(it)
        updatedUsers[index] = it.copy(status = "active")
    }
    return updatedUsers
}
fun users() = flow {
    while (true) {
        delay(2000)
        fetchUsers().forEach { emit(it) }
    }
}.filter { it.status == "active" }
    .distinctUntilChanged{old, new -> old.id == new.id}
    .map { user -> User(user.id, user.name, user.status) }

// Main function where you will write your solution
fun main() = runBlocking {
    val users = users()
    users
        .catch { e -> println("Error fetching updates: ${e.message}") }
        .collect { user -> println("Update: ${user.name} is now ${user.status}!") }
}