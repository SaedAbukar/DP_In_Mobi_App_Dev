package Week1
import kotlin.math.max
import kotlin.math.min

fun main(){
    var examscore: Double
    var exercisescore: Double
    var check: String
    var total: Double
    var grade: Double
    var totalGrade = 0.0
    var gradeamount = 0
    do {
        println("Exam points (0-100)?")
        examscore = max(
            min( readlnOrNull()?.toDoubleOrNull() ?: 0.0, 100.0), 0.0)
        println("Exercise points (0-100)?")
        exercisescore = max(
            min( readlnOrNull()?.toDoubleOrNull() ?: 0.0, 100.0), 0.0)
        println("Press 'q' to quit. Enter to continue")
        total = 0.75 * examscore + 0.25 * exercisescore
        grade = if (examscore >= 40.0 && total >= 40) 0.5 + (total - 40.0) * ((5.49 - 0.5) / 60) else 0.0
        totalGrade += grade
        ++gradeamount
        check = readln().trim().lowercase()

    } while (check != "q")
    //println("Exam score: $examscore")
    //println("Exercise score: $exercisescore")
    //println("Flag codition: $check")
    //println("Grade: $grade")
    //println("Amount of Student: $gradeamount")
    println("Avg of Grades: ${String.format("%.3f", totalGrade / gradeamount)}")
}