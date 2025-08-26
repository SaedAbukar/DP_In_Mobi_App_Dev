package week1.Classes_And_Objects

/*
 * Implement missing classes so that the following main function works
 *
 * Good intro to Kotlin: https://kotlinlang.org/docs/getting-started.html
 *      Classes: https://kotlinlang.org/docs/kotlin-tour-classes.html and
 *               https://kotlinlang.org/docs/kotlin-tour-intermediate-classes-interfaces.html
 */
fun main() {
    /*
     * We should have a human class who has a name and age as instance variables. Name and age should be only readable
     * Operations needed:
     *  toString() to print the name and the age
     *  getOlder() to increase the age by one year
     */
    val albert = Human("Alberto", 18)
    println(albert); albert.getOlder(); println(albert)

    /*
     * There should be CourseRecord data class to hold
     *  name - name of the course
     *  yearCompleted - integer to tell when the course has been done
     *  credits - integer to tell how many credits points to get (default value 5)
     *  grade - double specifying the grade of the course
     *
     * There should also be a Student class who inherits the Human class, and adds the following variables
     *  courses - a readable only set of courses
     * and the following operations:
     *  addCourse(course: CourseRecord) adds the given course to the student's data structure
     *  weightedAverage() returns double type value of weighted averages of the courses
     *  minMaxGrades() returns a Pair of minimum and maximum grades of the courses taken
     */
    val student1 = Student("John", 19)
    student1.addCourse(CourseRecord("Kotlin Intro", 2023, 5, 4.5))
    student1.addCourse(CourseRecord("Kotlin Coroutines", 2024, 5, 4.0))
    student1.addCourse(CourseRecord("Kotlin DSL", 2025, 5, 3.8))
    println("Weighted average of the student ${student1.name} is ${student1.weightedAverage()}")

    val student2 = Student("Mary", 21)
    student2.addCourse(CourseRecord("C++ Intro", 2022, 5, 5.0))
    student2.addCourse(CourseRecord("C++ Coroutines", 2024, 5, 4.25))
    println("Minimum and maximum averages of the student ${student2.name} are ${student2.minMaxGrades()}")

    val student3 = Student("Enzio Benzino", 23)

    /*
     * We should also have a class Major who stores a set of students subjecting this major
     * The following operations are needed:
     *  addStudent(student: Student) - add a new student to the major
     *  stats() - returns a Triple containing minimum, maximum and average of all grades of every student
     */
    val major = Major("Computer Science")
    major.addStudent(student1); major.addStudent(student2)
    println("Minumum, maximum and average of all grades in this major ${major.stats()}")
}

open class Human(val name: String, initialAge: Int) {
    var age = initialAge
        private set
    fun getOlder() {
        ++age
    }

    override fun toString(): String {
        return "$name $age"
    }
}

class Student(name: String, age: Int): Human(name, age) {
    private var ModifiableCourses: MutableSet<CourseRecord> = mutableSetOf()
    val courses: Set<CourseRecord> get() = ModifiableCourses
    fun weightedAverage(): Double {
        var totalWeight = 0.0
        var totalCredits = 0.0
        for (course in ModifiableCourses) {
            totalWeight += course.grade * course.credits
            totalCredits += course.credits
        }
        return totalWeight / totalCredits
    }

    fun addCourse(courseRecord: CourseRecord) {
        ModifiableCourses.add(courseRecord)
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val max = ModifiableCourses.maxOf { it.grade }
        val min = ModifiableCourses.minOf { it.grade }
        return Pair(min, max)
    }
}

data class CourseRecord(val name: String, val yearCompleted: Int, val credits: Int = 5, var grade: Double)

class Major(var name: String) {
    var students: MutableSet<Student> = mutableSetOf()
    fun addStudent(student: Student) {
        students.add(student)
    }
    fun stats(): Triple<Double, Double, Double> {
        var max = 0.0
        var min = 5.0
        var avg = 0.0
        var totalGrades = 0.0
        var totalCourses = 0.0
        for (student in students) {
            var (student_min, student_max) = student.minMaxGrades()
            if (student_max > max) max = student_max
            if (student_min < min) min = student_min
            for (course in student.courses) {
                totalGrades += course.grade
                totalCourses += 1
            }
        }
        avg = String.format("%.3f", totalGrades / totalCourses).toDouble()

        return Triple(min, max, avg)
    }
}
