import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 06")
class Day06Test {

    private val solver = Day06()

    @Test
    @DisplayName("Part 1")
    fun day06_part01_example01() {
        val solution = solver.part1()
        assertEquals(288, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day06_part02_example01() {
        val solution = solver.part2()
        assertEquals(71503, solution)
    }

} 
        
