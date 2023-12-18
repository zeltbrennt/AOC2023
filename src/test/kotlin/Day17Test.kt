import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 17")
class Day17Test {

    private val solver = Day17()

    @Test
    @DisplayName("Part 1")
    fun day17_part01_example01() {
        val solution = solver.part1()
        assertEquals(102, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day17_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
