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
        val ex2 = listOf(
            "LR",
            "",
            "11A = (11B, XXX)",
            "11B = (XXX, 11Z)",
            "11Z = (11B, XXX)",
            "22A = (22B, XXX)",
            "22B = (22C, 22C)",
            "22C = (22Z, 22Z)",
            "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)"
        )
        val solver = Day08(ex2)
        val solution = solver.part2()
        assertEquals(6L, solution)
    }
}
        
