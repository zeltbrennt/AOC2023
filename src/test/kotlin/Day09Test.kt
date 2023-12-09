import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 09")
class Day09Test {

    private val solver = Day09()

    @Test
    @DisplayName("Part 1")
    fun day09_part01_example01() {
        val solution = solver.part1()
        assertEquals(114, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day09_part02_example01() {
        val solution = solver.part2()
        assertEquals(2, solution)
    }

} 
        
