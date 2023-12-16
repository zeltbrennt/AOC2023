import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
class Day16Test {

    private val solver = Day16()

    @Test
    @DisplayName("Part 1")
    fun day16_part01_example01() {
        val solution = solver.part1()
        assertEquals(46, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day16_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
