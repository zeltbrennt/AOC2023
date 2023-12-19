import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 19")
class Day19Test {

    private val solver = Day19()

    @Test
    @DisplayName("Part 1")
    fun day19_part01_example01() {
        val solution = solver.part1()
        assertEquals(19114, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day19_part02_example01() {
        val solution = solver.part2()
        assertEquals(167409079868000, solution)
    }

} 
        
