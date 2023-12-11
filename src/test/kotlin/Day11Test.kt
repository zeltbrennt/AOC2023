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
    @DisplayName("Part 2")
    fun day11_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
