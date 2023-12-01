
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 01")
class Day01Test {

    @Test
    @DisplayName("Part 1")
    fun day01_part01_example01() {
        val solver = Day01()
        val solution = solver.part1()
        assertEquals(142, solution)
    }
    
    @Test
    @DisplayName("Part 2")
    fun day01_part02_example01() {
        val example2 = listOf("two1nine",
                "eightwo",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen")
        val solver = Day01(example2)
        val solution = solver.part2()
        assertEquals(280, solution)
    }

} 
        
