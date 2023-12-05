import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 05")
class Day05Test {

    private val solver = Day05()

    @Test
    @DisplayName("Part 1")
    fun day05_part01_example01() {
        val solution = solver.part1()
        assertEquals(35, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day05_part02_example01() {
        val solution = solver.part2()
        assertEquals(46, solution)
    }

} 
        
