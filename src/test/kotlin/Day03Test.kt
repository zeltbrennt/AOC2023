import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 03")
class Day03Test {

    private val solver = Day03()

    @Test
    @DisplayName("Part 1")
    fun day03_part01_example01() {
        val solution = solver.part1()
        assertEquals(4361, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day03_part02_example01() {
        val solution = solver.part2()
        assertEquals(467835, solution)
    }

} 
        
