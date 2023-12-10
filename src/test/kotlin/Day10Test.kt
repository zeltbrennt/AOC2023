import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    private val solver = Day10()

    @Test
    @DisplayName("simple Example Part 1")
    fun day10_part01_example01() {
        val complex = listOf(
            "-L|F7",
            "7S-7|",
            "L|7||",
            "-L-J|",
            "L|-JF"
        )
        val solver = Day10(complex)
        val solution = solver.part1()
        assertEquals(4, solution)
    }

    @Test
    @DisplayName("complex Example Part 1")
    fun day10_part01_example02() {
        val complex = listOf(
            "..F7.",
            ".FJ|.",
            "SJ.L7",
            "|F--J",
            "LJ..."
        )
        val solver = Day10(complex)
        val solution = solver.part1()
        assertEquals(8, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day10_part02_example01() {
        val solution = solver.part2()
        assertEquals(0, solution)
    }

} 
        
