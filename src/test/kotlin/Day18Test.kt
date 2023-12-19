import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
class Day18Test {

    private val solver = Day18()

    @Test
    @DisplayName("Part 1")
    fun day18_part01_example01() {
        val solution = solver.part1()
        assertEquals(62, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day18_part02_example01() {
        val solution = solver.part2()
        assertEquals(952408144115, solution)
    }

} 
        
