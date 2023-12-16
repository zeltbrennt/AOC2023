import util.loadAsList
import java.util.*
import kotlin.time.measureTime

class Day16(private val input: List<String> = loadAsList(day = 16)) {

    enum class TileType { MIRROR_DOWN, MIRROR_UP, SPLIT_VERTICAL, SPLIT_HORIZONTAL, EMPTY }
    enum class Direction { UP, LEFT, DOWN, RIGHT }

    data class Point(val x: Int, val y: Int)

    data class Tile(val type: TileType, var energy: Int = 0)

    fun getNext(pos: Point, direction: Direction): List<Pair<Point, Direction>> {
        val type = tiles[pos]?.type ?: return emptyList()
        return when (direction) {
            Direction.UP -> when (type) {
                TileType.EMPTY, TileType.SPLIT_VERTICAL -> listOf(Point(pos.x, pos.y - 1) to direction)
                TileType.MIRROR_DOWN -> listOf(Point(pos.x - 1, pos.y) to Direction.LEFT)
                TileType.MIRROR_UP -> listOf(Point(pos.x + 1, pos.y) to Direction.RIGHT)
                TileType.SPLIT_HORIZONTAL -> listOf(
                    Point(pos.x - 1, pos.y) to Direction.LEFT,
                    Point(pos.x + 1, pos.y) to Direction.RIGHT
                )
            }

            Direction.LEFT -> when (type) {
                TileType.EMPTY, TileType.SPLIT_HORIZONTAL -> listOf(Point(pos.x - 1, pos.y) to direction)
                TileType.MIRROR_DOWN -> listOf(Point(pos.x, pos.y - 1) to Direction.UP)
                TileType.MIRROR_UP -> listOf(Point(pos.x, pos.y + 1) to Direction.DOWN)
                TileType.SPLIT_VERTICAL -> listOf(
                    Point(pos.x, pos.y - 1) to Direction.UP,
                    Point(pos.x, pos.y + 1) to Direction.DOWN
                )
            }

            Direction.DOWN -> when (type) {
                TileType.EMPTY, TileType.SPLIT_VERTICAL -> listOf(Point(pos.x, pos.y + 1) to direction)
                TileType.MIRROR_DOWN -> listOf(Point(pos.x + 1, pos.y) to Direction.RIGHT)
                TileType.MIRROR_UP -> listOf(Point(pos.x - 1, pos.y) to Direction.LEFT)
                TileType.SPLIT_HORIZONTAL -> listOf(
                    Point(pos.x - 1, pos.y) to Direction.LEFT,
                    Point(pos.x + 1, pos.y) to Direction.RIGHT
                )
            }

            Direction.RIGHT -> when (type) {
                TileType.EMPTY, TileType.SPLIT_HORIZONTAL -> listOf(Point(pos.x + 1, pos.y) to direction)
                TileType.MIRROR_DOWN -> listOf(Point(pos.x, pos.y + 1) to Direction.DOWN)
                TileType.MIRROR_UP -> listOf(Point(pos.x, pos.y - 1) to Direction.UP)
                TileType.SPLIT_VERTICAL -> listOf(
                    Point(pos.x, pos.y - 1) to Direction.UP,
                    Point(pos.x, pos.y + 1) to Direction.DOWN
                )
            }
        }
    }


    private fun bounceBeam() {
        val queue: Deque<Pair<Point, Direction>> = LinkedList()
        val visited: MutableSet<Pair<Point, Direction>> = mutableSetOf()
        queue.add(Point(0, 0) to Direction.RIGHT)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val tile = tiles[current.first] ?: continue
            if (visited.add(current.first to current.second)) {
                tile.energy++
                queue.addAll(getNext(current.first, current.second))
                //printTiles()
            }
        }
    }

    private val tiles = buildMap<Point, Tile> {
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

    fun printTiles() {
        println()
        for (i in input.indices) {
            for (j in input.first().indices) {
                print(if ((tiles[Point(j, i)]?.energy ?: 0) > 0) "#" else ".")
            }
            println()
        }
    }

    fun part1(): Int {
        bounceBeam()
        return tiles.filter { it.value.energy > 0 }.count()
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day16
    measureTime { solver = Day16() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
