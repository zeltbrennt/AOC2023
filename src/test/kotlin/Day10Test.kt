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
    @DisplayName("simple example Part 2")
    fun day10_part02_example01() {
        val maze = listOf(
            ".S------7.",
            ".|F----7|.",
            ".||....||.",
            ".||....||.",
            ".|L-7F-J|.",
            ".|..||..|.",
            ".L--JL--J.",
        )
        val solver = Day10(maze)
        val solution = solver.part2()
        assertEquals(4, solution)
    }

    @Test
    @DisplayName("complex example Part 2")
    fun day10_part02_example02() {
        val maze = listOf(
            ".F----7F7F7F7F-7....",
            ".|F--7||||||||FJ....",
            ".||.FJ||||||||L7....",
            "FJL7L7LJLJ||LJ.L-7..",
            "L--J.L7...LJS7F-7L7.",
            "....F-J..F7FJ|L7L7L7",
            "....L7.F7||L7|.L7L7|",
            ".....|FJLJ|FJ|F7|.LJ",
            "....FJL-7.||.||||...",
            "....L---J.LJ.LJLJ..."
        )
        val solver = Day10(maze)
        val solution = solver.part2()
        assertEquals(8, solution)
    }

    @Test
    @DisplayName("more complex example Part 2")
    fun day10_part02_example03() {
        val maze = listOf(
            "FF7FSF7F7F7F7F7F---7",
            "L|LJ||||||||||||F--J",
            "FL-7LJLJ||||||LJL-77",
            "F--JF--7||LJLJ7F7FJ-",
            "L---JF-JLJ.||-FJLJJ7",
            "|F|F-JF---7F7-L7L|7|",
            "|FFJF7L7F-JF7|JL---7",
            "7-L-JL7||F7|L7F-7F7|",
            "L.L7LFJ|||||FJL7||LJ",
            "L7JLJL-JLJLJL--JLJ.L"
        )
        val solver = Day10(maze)
        val solution = solver.part2()
        assertEquals(10, solution)
    }
} 
        
