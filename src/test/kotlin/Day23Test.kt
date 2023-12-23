import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day23Test {

    private val solver = Day23()

    @Test
    @DisplayName("Part 1")
    fun day23_part01_example01() {
        val solution = solver.part1()
        assertEquals(94, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day23_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
