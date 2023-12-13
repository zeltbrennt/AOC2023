import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
class Day13Test {

    private val solver = Day13()

    @Test
    @DisplayName("Part 1")
    fun day13_part01_example01() {
        val solution = solver.part1()
        assertEquals(405, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day13_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
