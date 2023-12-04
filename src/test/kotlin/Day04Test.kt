import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@DisplayName("Day 04")
class Day04Test {

    private val solver = Day04()

    @Test
    @DisplayName("Part 1")
    fun day04_part01_example01() {
        val solution = solver.part1()
        assertEquals(13, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day04_part02_example01() {
        val solution = solver.part2()
        assertEquals(30, solution)
    }

} 
        
