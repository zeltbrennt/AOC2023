import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 21")
class Day21Test {

    private val solver = Day21()

    @Test
    @DisplayName("Part 1")
    fun day21_part01_example01() {
        solver.maxSteps = 6
        val solution = solver.part1()
        assertEquals(16, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day21_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
