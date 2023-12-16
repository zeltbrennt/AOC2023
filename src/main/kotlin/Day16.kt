import util.Point
import util.loadAsList
import java.util.*
import kotlin.math.max
import kotlin.time.measureTime

class Day16(private val input: List<String> = loadAsList(day = 16)) {

    enum class TileType { MIRROR_DOWN, MIRROR_UP, SPLIT_VERTICAL, SPLIT_HORIZONTAL, EMPTY }
    enum class Direction { UP, LEFT, DOWN, RIGHT }

    data class Heading(val position: Point, val direction: Direction) {
        constructor(x: Int, y: Int, direction: Direction) : this(Point(x, y), direction)
    }


    data class Tile(val type: TileType, var energy: Int = 0)

    private fun getNext(heading: Heading): List<Heading> {
        val type = tiles[heading.position]?.type ?: return emptyList()
        return buildList {
            val pos = heading.position
            val direction = heading.direction
            when (heading.direction) {
                Direction.UP -> when (type) {
                    TileType.EMPTY, TileType.SPLIT_VERTICAL -> add(Heading(pos.north, direction))
                    TileType.MIRROR_DOWN -> add(Heading(pos.west, Direction.LEFT))
                    TileType.MIRROR_UP -> add(Heading(pos.east, Direction.RIGHT))
                    TileType.SPLIT_HORIZONTAL -> {
                        add(Heading(pos.west, Direction.LEFT))
                        add(Heading(pos.east, Direction.RIGHT))
                    }
                }

                Direction.LEFT -> when (type) {
                    TileType.EMPTY, TileType.SPLIT_HORIZONTAL -> add(Heading(pos.west, direction))
                    TileType.MIRROR_DOWN -> add(Heading(pos.north, Direction.UP))
                    TileType.MIRROR_UP -> add(Heading(pos.south, Direction.DOWN))
                    TileType.SPLIT_VERTICAL -> {
                        add(Heading(pos.north, Direction.UP))
                        add(Heading(pos.south, Direction.DOWN))
                    }
                }

                Direction.DOWN -> when (type) {
                    TileType.EMPTY, TileType.SPLIT_VERTICAL -> add(Heading(pos.south, direction))
                    TileType.MIRROR_DOWN -> add(Heading(pos.east, Direction.RIGHT))
                    TileType.MIRROR_UP -> add(Heading(pos.west, Direction.LEFT))
                    TileType.SPLIT_HORIZONTAL -> {
                        add(Heading(pos.west, Direction.LEFT))
                        add(Heading(pos.east, Direction.RIGHT))
                    }
                }

                Direction.RIGHT -> when (type) {
                    TileType.EMPTY, TileType.SPLIT_HORIZONTAL -> add(Heading(pos.east, direction))
                    TileType.MIRROR_DOWN -> add(Heading(pos.south, Direction.DOWN))
                    TileType.MIRROR_UP -> add(Heading(pos.north, Direction.UP))
                    TileType.SPLIT_VERTICAL -> {
                        add(Heading(pos.south, Direction.DOWN))
                        add(Heading(pos.north, Direction.UP))
                    }
                }
            }
        }
    }


    private fun bounceBeam(start: Heading) {
        val queue: Deque<Heading> = LinkedList()
        val visited: MutableSet<Heading> = mutableSetOf()
        queue.add(start)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val tile = tiles[current.position] ?: continue
            if (visited.add(current)) {
                tile.energy++
                queue.addAll(getNext(current))
                //printTiles()
            }
        }
    }

    private val tiles = buildMap {
        for (i in input.indices) {
            for (j in input.first().indices) {
                val type = when (input[i][j]) {
                    '|' -> TileType.SPLIT_VERTICAL
                    '-' -> TileType.SPLIT_HORIZONTAL
                    '/' -> TileType.MIRROR_UP
                    '\\' -> TileType.MIRROR_DOWN
                    else -> TileType.EMPTY
                }
                put(Point(j, i), Tile(type))
            }
        }
    }

    private fun printTiles() {
        println()
        for (i in input.indices) {
            for (j in input.first().indices) {
                print(
                    when (tiles[Point(j, i)]?.energy) {
                        1 -> Char(0x00b7)
                        2 -> '+'
                        else -> ' '
                    }
                )
            }
            println()
        }
    }

    fun part1(): Int {
        bounceBeam(Heading(0, 0, Direction.RIGHT))
        return tiles.filter { it.value.energy > 0 }.count()
    }

    private fun resetTiles() = tiles.onEach { it.value.energy = 0 }

    fun part2(): Int {
        var maxEnergy = 0
        for (x in input.first().indices) {
            for (direction in listOf(Direction.UP, Direction.DOWN)) {
                resetTiles()
                val y = if (direction == Direction.UP) input.lastIndex else 0
                bounceBeam(Heading(x, y, direction))
                maxEnergy = max(maxEnergy, tiles.count { it.value.energy > 0 })
            }
        }
        for (y in input.indices) {
            for (direction in listOf(Direction.LEFT, Direction.RIGHT)) {
                resetTiles()
                val x = if (direction == Direction.LEFT) input.first().lastIndex else 0
                bounceBeam(Heading(x, y, direction))
                maxEnergy = max(maxEnergy, tiles.count { it.value.energy > 0 })
            }
        }
        return maxEnergy
    }
}

fun main() {
    var solver: Day16
    measureTime { solver = Day16() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
