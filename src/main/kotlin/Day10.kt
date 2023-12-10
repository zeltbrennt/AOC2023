import util.Cell2D
import util.loadAsList
import kotlin.math.abs
import kotlin.time.measureTime

class Day10(input: List<String> = loadAsList(day = 10)) {

    enum class Pipe { VERTICAL, HORIZONTAL, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST, START, GROUND }
    enum class Heading { NORTH, SOUTH, EAST, WEST }

    lateinit var start: Cell2D

    private val last = Cell2D(input[0].lastIndex, input.lastIndex)

    private val pipes = buildMap<Cell2D, Pipe> {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                val pipe = char.toPipe()
                if (pipe == Pipe.START) start = Cell2D(x, y)
                put(Cell2D(x, y), pipe)
            }
        }
    }

    private val loop =
        if (pipes[start.south()] in listOf(Pipe.VERTICAL, Pipe.NORTH_EAST, Pipe.NORTH_WEST)) {
            findLoop(Heading.SOUTH, start.south())
        } else if (pipes[start.west()] in listOf(Pipe.HORIZONTAL, Pipe.SOUTH_EAST, Pipe.NORTH_EAST)) {
            findLoop(Heading.WEST, start.west())
        } else if (pipes[start.north()] in listOf(Pipe.VERTICAL, Pipe.SOUTH_EAST, Pipe.SOUTH_WEST)) {
            findLoop(Heading.SOUTH, start.south())
        } else if (pipes[start.east()] in listOf(Pipe.HORIZONTAL, Pipe.NORTH_WEST, Pipe.SOUTH_WEST)) {
            findLoop(Heading.EAST, start.east())
        } else {
            emptyMap()
        }


    fun part1(): Int {
        return loop.size / 2
    }

    fun part2(): Int {
        var crossing = 0
        var counter = 0
        for (y in 0..last.y) {
            for (x in 0..last.x) {
                val currentCell = Cell2D(x, y)
                if (currentCell in loop && currentCell.south() in loop) {
                    val diff = loop[currentCell]!! - loop[currentCell.south()]!! //safe, because of if-statement
                    if (abs(diff) == 1) crossing += diff
                    //print("\u001B[34m" + pipes[currentCell]?.toASCII() + "\u001B[0m")
                } else if (currentCell !in loop && crossing != 0) {
                    counter++
                    //print("\u001B[32m" + "I" + "\u001B[0m") //green
                } else {
                    //print("\u001B[0m" + pipes[currentCell]?.toASCII() + "\u001B[0m")
                }
            }
            //println()
        }
        return counter
    }

    private fun Pipe.toASCII() = when (this) {
        Pipe.VERTICAL -> 0x02551.toChar()
        Pipe.HORIZONTAL -> 0x2550.toChar()
        Pipe.NORTH_WEST -> 0x255D.toChar()
        Pipe.NORTH_EAST -> 0x0255A.toChar()
        Pipe.SOUTH_WEST -> 0x02557.toChar()
        Pipe.SOUTH_EAST -> 0x02554.toChar()
        Pipe.START -> 'S'
        Pipe.GROUND -> ' '
    }

    private fun Cell2D.getNext(heading: Heading): Pair<Cell2D, Heading>? = when (pipes[this]) {
        Pipe.VERTICAL -> when (heading) {
            Heading.NORTH -> this.north() to heading
            Heading.SOUTH -> this.south() to heading
            else -> null
        }

        Pipe.HORIZONTAL -> when (heading) {
            Heading.WEST -> this.west() to heading
            Heading.EAST -> this.east() to heading
            else -> null
        }

        Pipe.NORTH_WEST -> when (heading) {
            Heading.EAST -> this.north() to Heading.NORTH
            Heading.SOUTH -> this.west() to Heading.WEST
            else -> null
        }

        Pipe.NORTH_EAST -> when (heading) {
            Heading.WEST -> this.north() to Heading.NORTH
            Heading.SOUTH -> this.east() to Heading.EAST
            else -> null
        }

        Pipe.SOUTH_WEST -> when (heading) {
            Heading.EAST -> this.south() to Heading.SOUTH
            Heading.NORTH -> this.west() to Heading.WEST
            else -> null
        }

        Pipe.SOUTH_EAST -> when (heading) {
            Heading.WEST -> this.south() to Heading.SOUTH
            Heading.NORTH -> this.east() to Heading.EAST
            else -> null
        }

        Pipe.START -> when (heading) {
            Heading.WEST -> this.west() to heading
            Heading.NORTH -> this.north() to heading
            Heading.SOUTH -> this.south() to heading
            Heading.EAST -> this.east() to heading
        }

        else -> null
    }

    private fun findLoop(
        heading: Heading,
        position: Cell2D,
    ): Map<Cell2D, Int> {
        var step = 0
        val path: MutableMap<Cell2D, Int> = mutableMapOf(start to step++)
        var (nextPosition, nextHeading) = position to heading
        while (pipes[nextPosition] != Pipe.START) {
            path[nextPosition] = step++
            val temp = nextPosition.getNext(nextHeading) ?: return emptyMap()
            nextPosition = temp.first
            nextHeading = temp.second
        }
        return path
    }

    private fun Char.toPipe() = when (this) {
        '|' -> Pipe.VERTICAL
        '-' -> Pipe.HORIZONTAL
        'L' -> Pipe.NORTH_EAST
        'J' -> Pipe.NORTH_WEST
        '7' -> Pipe.SOUTH_WEST
        'F' -> Pipe.SOUTH_EAST
        'S' -> Pipe.START
        else -> Pipe.GROUND
    }
}

fun main() {
    var solver: Day10
    measureTime { solver = Day10() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
