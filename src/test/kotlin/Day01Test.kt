
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 01")
class Day01Test {

    private val solver = Day01()

    @Test
    @DisplayName("Part 1")
    fun day01_part01_example01() {
        val solution = solver.part1()
        assertEquals(142, solution)
    }
    
    @Test
    @DisplayName("Part 2")
    fun day01_part02_example01() {
        val solution = solver.part2()
        assertEquals(281, solution)
    }

} 
        
