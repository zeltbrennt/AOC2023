import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 08")
class Day08Test {

    private val solver = Day08()

    @Test
    @DisplayName("Part 1")
    fun day08_part01_example01() {
        val solution = solver.part1()
        assertEquals(6, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day08_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
