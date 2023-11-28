package util

import java.io.File

fun main() {

    val day = 2

    val number = day.toString().padStart(2, '0')
    val code = """
import util.loadAsList

class Day$number {
    
     val input = loadAsList($day)   

    fun part1(): Int {
        TODO("Not yet implemented")
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    val solver = Day01()
    println(solver.part1())
    println(solver.part2())
}
        """
    val test = """
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day $number")
class Day${number}Test {

    private val solver = Day${number}()

    @Test
    @DisplayName("Part 1")
    fun day${number}_part01_example01() {
        val solution = solver.part1()
        assertEquals(, solution)
    }
    
    @Test
    @DisplayName("Part 2")
    fun day${number}_part02_example01() {
        val solution = solver.part2()
        assertEquals(, solution)
    }

} 
        """
    val solutionFile = File("src/main/kotlin/Day${number}.kt")
    val testFile = File("src/test/kotlin/Day${number}Test.kt")
    val inputFile = File("src/main/resources/day${number}.txt")
    val testInputFile = File("src/test/resources/day${number}.txt")

    if (solutionFile.createNewFile()) {
        solutionFile.printWriter().use {
            it.println(code)
        }
    }
    if (testFile.createNewFile()) {
        testFile.printWriter().use {
            it.println(test)
        }
    }
    inputFile.createNewFile()
    testInputFile.createNewFile()

}