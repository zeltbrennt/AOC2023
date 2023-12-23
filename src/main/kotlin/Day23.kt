import util.Point
import util.loadAsList
import java.util.*
import kotlin.time.measureTime

class Day23(private val input: List<String> = loadAsList(day = 23)) {

    private val start = Point(input.first().indexOf('.'), 0)
    private val finish = Point(input.last().indexOf('.'), input.lastIndex)

    private fun dfs(): Set<Node> {
        val stack: Deque<Node> = LinkedList()
        val visited = mutableSetOf<Node>()
        stack.push(Node(start, start, 0))
        while (stack.isNotEmpty()) {
            val current = stack.pop()
            visited.add(current)
            //println(current)
            for (next in current.getNextMoves()) {
                if (next !in visited) stack.push(next)
            }
        }
        return visited
    }

    data class Node(val pos: Point, val prev: Point, val step: Int)


    operator fun List<String>.get(point: Point): Char? {
        return try {
            this[point.y][point.x]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }


    private fun Node.getNextMoves() = buildList {
        val pos = this@getNextMoves.pos
        val prev = this@getNextMoves.prev
        val step = this@getNextMoves.step + 1
        if (input[pos.north] in listOf('^', '.') && pos.north != prev) add(Node(pos.north, pos, step))
        if (input[pos.west] in listOf('<', '.') && pos.west != prev) add(Node(pos.west, pos, step))
        if (input[pos.south] in listOf('v', '.') && pos.south != prev) add(Node(pos.south, pos, step))
        if (input[pos.east] in listOf('>', '.') && pos.east != prev) add(Node(pos.east, pos, step))
    }

    fun part1(): Int {
        return dfs().filter { it.pos == finish }.maxOf { it.step }
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day23
    measureTime { solver = Day23() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
