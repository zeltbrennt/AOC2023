import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 20")
class Day20Test {

    private val solver = Day20()

    @Test
    @DisplayName("Part 1")
    fun day20_part01_example01() {
        val solution = solver.part1()
        assertEquals(11687500, solution)
    }
} 
        
