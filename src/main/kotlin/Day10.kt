import util.Cell2D
import util.loadAsList
import kotlin.time.measureTime

class Day10(input: List<String> = loadAsList(day = 10)) {

    enum class Pipe { VERTICAL, HORIZONTAL, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST, START, GROUND }
    enum class Heading { NORTH, SOUTH, EAST, WEST }

    lateinit var start: Cell2D

    private val pipes = buildMap<Cell2D, Pipe> {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                when (char.toPipe()) {
                    Pipe.START -> {
                        start = Cell2D(x, y)
                        put(start, Pipe.START)
                    }

                    Pipe.GROUND -> {}
                    else -> put(Cell2D(x, y), char.toPipe())
                }
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
            emptyList()
        }


    fun part1(): Int {
        return loop.size / 2
    }

    fun part2(): Int {
        TODO("Not yet implemented")
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
    ): List<Cell2D> {
        val path: MutableList<Cell2D> = mutableListOf(start)
        var (nextPosition, nextHeading) = position to heading
        while (pipes[nextPosition] != Pipe.START) {
            path.add(nextPosition)
            val temp = nextPosition.getNext(nextHeading) ?: return emptyList()
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
        
