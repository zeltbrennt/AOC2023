import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    private val solver = Day11()

    @Test
    @DisplayName("Part 1")
    fun day11_part01_example01() {
        val solution = solver.part1()
        assertEquals(374, solution)
    }

    @Test
    @DisplayName("Part 2 with expand 10")
    fun day11_part02_example01() {
        val expanded = solver.expand(solver.galaxies, 10)
        val solution = solver.getSumOfDistances(expanded)
        assertEquals(1030, solution)
    }

    @Test
    @DisplayName("Part 2 with expand 100")
    fun day11_part02_example02() {
        val expanded = solver.expand(solver.galaxies, 100)
        val solution = solver.getSumOfDistances(expanded)
        assertEquals(8410, solution)
    }

} 
        
