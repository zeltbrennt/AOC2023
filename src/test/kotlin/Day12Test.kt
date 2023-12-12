import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    private val solver = Day12()

    @Test
    @DisplayName("Part 1")
    fun day12_part01_example01() {
        val solution = solver.part1()
        assertEquals(21, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day12_part02_example01() {
        val solution = solver.part2()
        assertEquals(525152, solution)
    }

    @Test
    @DisplayName("???.### 1,1,3 is 1 Arrangement")
    fun day12_part01_test_parsing_01() {
        val solution = solver.getNumberOfArrangements("???.### 1,1,3")
        assertEquals(1, solution)
    }

    @Test
    @DisplayName(".??..??...?##. 1,1,3 is 4 Arrangements")
    fun day12_part01_test_parsing_02() {
        val solution = solver.getNumberOfArrangements(".??..??...?##. 1,1,3")
        assertEquals(4, solution)
    }

    @Test
    @DisplayName("?#?#?#?#?#?#?#? 1,3,1,6 is 1 Arrangement")
    fun day12_part01_test_parsing_03() {
        val solution = solver.getNumberOfArrangements("?#?#?#?#?#?#?#? 1,3,1,6")
        assertEquals(1, solution)
    }

    @Test
    @DisplayName("????.#...#... 4,1,1 is 1 Arrangement")
    fun day12_part01_test_parsing_04() {
        val solution = solver.getNumberOfArrangements("????.#...#... 4,1,1")
        assertEquals(1, solution)
    }

    @Test
    @DisplayName("????.######..#####. 1,6,5 is 4 Arrangement")
    fun day12_part01_test_parsing_05() {
        val solution = solver.getNumberOfArrangements("????.######..#####. 1,6,5")
        assertEquals(4, solution)
    }

    @Test
    @DisplayName("?###???????? 3,2,1 is 10 Arrangement")
    fun day12_part01_test_parsing_06() {
        val solution = solver.getNumberOfArrangements("?###???????? 3,2,1")
        assertEquals(10, solution)
    }

    @Test
    @DisplayName(".# 1 will become .#?.#?.#?.#?.# 1,1,1,1,1")
    fun day12_part02_test_parsing_01() {
        val solution = solver.unfold(".# 1")
        assertEquals(".#?.#?.#?.#?.# 1,1,1,1,1", solution)
    }

    @Test
    @DisplayName("???.### 1,1,3 will become ???.###????.###????.###????.###????.### 1,1,3,1,1,3,1,1,3,1,1,3,1,1,3")
    fun day12_part02_test_parsing_02() {
        val solution = solver.unfold("???.### 1,1,3")
        assertEquals("???.###????.###????.###????.###????.### 1,1,3,1,1,3,1,1,3,1,1,3,1,1,3", solution)
    }

    @Test
    @DisplayName("???.### 1,1,3 unfolded will become 1 Arrangement")
    fun day12_part02_test_parsing_03() {
        val solution = solver.getNumberOfArrangements(solver.unfold("???.### 1,1,3"))
        assertEquals(1, solution)
    }

    @Test
    @DisplayName(".??..??...?##. 1,1,3 unfolded will become 16384 Arrangements")
    fun day12_part02_test_parsing_04() {
        val solution = solver.getNumberOfArrangements(solver.unfold(".??..??...?##. 1,1,3"))
        assertEquals(16384, solution)
    }

    @Test
    @DisplayName("?#?#?#?#?#?#?#? 1,3,1,6 will become 1 Arrangement")
    fun day12_part02_test_parsing_05() {
        val solution = solver.getNumberOfArrangements(solver.unfold("?#?#?#?#?#?#?#? 1,3,1,6"))
        assertEquals(1, solution)
    }

    @Test
    @DisplayName("????.#...#... 4,1,1 will become 16 Arrangement")
    fun day12_part02_test_parsing_06() {
        val solution = solver.getNumberOfArrangements(solver.unfold("????.#...#... 4,1,1"))
        assertEquals(16, solution)
    }

    @Test
    @DisplayName("????.######..#####. 1,6,5 will become 2500 Arrangement")
    fun day12_part02_test_parsing_07() {
        val solution = solver.getNumberOfArrangements(solver.unfold("????.######..#####. 1,6,5"))
        assertEquals(2500, solution)
    }

    @Test
    @DisplayName("?###???????? 3,2,1 will become 506250 Arrangement")
    fun day12_part02_test_parsing_08() {
        val solution = solver.getNumberOfArrangements(solver.unfold("?###???????? 3,2,1"))
        assertEquals(506250, solution)
    }
} 
        
