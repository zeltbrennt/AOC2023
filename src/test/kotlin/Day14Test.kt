import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    private val solver = Day14()

    @Test
    @DisplayName("Part 1")
    fun day14_part01_example01() {
        val solution = solver.part1()
        assertEquals(136, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day14_part02_example01() {
        val solution = solver.part2()
        assertEquals(64, solution)
    }

} 
        
