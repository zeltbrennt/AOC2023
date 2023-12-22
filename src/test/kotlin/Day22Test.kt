import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {

    private val solver = Day22()

    @Test
    @DisplayName("Part 1")
    fun day22_part01_example01() {
        val solution = solver.part1()
        assertEquals(5, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day22_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
