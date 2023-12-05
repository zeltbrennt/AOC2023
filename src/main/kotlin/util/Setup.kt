package util

import java.io.File

fun main() {

    val day = 6

    val number = day.toString().padStart(2, '0')
    val code = """
import util.loadAsList
import kotlin.time.measureTime

class Day$number(
    private val input : List<String> = loadAsList(day = $day)
) {
    
    fun part1(): Int {
        TODO("Not yet implemented")
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day${number}
    measureTime { solver = Day${number}() }.also { println("${'$'}{"init".padEnd(40, ' ')}${'$'}{it}") }
    measureTime { solver.part1().also { print("Part 1: ${'$'}it".padEnd(40, ' ')) } }.also { println("${'$'}it") }
    measureTime { solver.part2().also { print("Part 2: ${'$'}it".padEnd(40, ' ')) } }.also { println("${'$'}it") }
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
        assertEquals(0, solution)
    }
    
    @Test
    @DisplayName("Part 2")
    fun day${number}_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
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