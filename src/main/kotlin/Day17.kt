import util.Point
import util.loadAsList
import java.util.*
import kotlin.time.measureTime

class Day17(private val input: List<String> = loadAsList(day = 17)) {

    private val target = Point(input.first().lastIndex, input.lastIndex)

    private val crucible = buildList {
        input.forEach { line ->
            add(line.map { it.digitToInt() })
        }
    }

    private operator fun List<List<Int>>.get(point: Point) = this[point.y][point.x]

    enum class Direction {
        NORTH, SOUTH, WEST, EAST;

        fun left(): Direction {
            return when (this) {
                NORTH -> WEST
                SOUTH -> EAST
                WEST -> SOUTH
                EAST -> NORTH
            }
        }

        fun right(): Direction {
            return when (this) {
                NORTH -> EAST
                SOUTH -> WEST
                WEST -> NORTH
                EAST -> SOUTH
            }
        }
    }

    data class Node(val position: Point, val direction: Direction, val steps: Int)

    data class State(val node: Node, val cost: Int, val last: State?) : Comparable<State> {

        override fun compareTo(other: State): Int {
            return this.cost.compareTo(other.cost)
        }
    }

    private fun State.getNextStates(part: Int): List<State> {
        val next = mutableListOf<State>()
        if (node.steps < if (part == 1) 3 else 10) {
            addWhenLegal(node.direction, next)
        }
        if (node.steps > if (part == 1) 0 else 3) {
            addWhenLegal(node.direction.left(), next)
            addWhenLegal(node.direction.right(), next)
        }
        return next
    }

    private fun State.addWhenLegal(direction: Direction, next: MutableList<State>) {
        val newPosition = when (direction) {
            Direction.NORTH -> this.node.position.north
            Direction.SOUTH -> this.node.position.south
            Direction.WEST -> this.node.position.west
            Direction.EAST -> this.node.position.east
        }
        if (newPosition.x < 0 || newPosition.x > input.first().lastIndex || newPosition.y < 0 || newPosition.y > input.lastIndex) return
        val newNode = Node(
            position = newPosition,
            direction = direction,
            steps = if (this.node.direction == direction) this.node.steps + 1 else 1
        )
        val newState = State(
            node = newNode,
            cost = this.cost + crucible[newPosition],
            last = this
        )
        next.add(newState)

    }

    private fun shortestPath(part: Int = 1): Int {
        val visited: MutableSet<Node> = mutableSetOf()
        val queue: PriorityQueue<State> = PriorityQueue()
        queue.add(State(Node(Point(0, 0), Direction.SOUTH, 0), 0, null))
        queue.add(State(Node(Point(0, 0), Direction.EAST, 0), 0, null))
        while (queue.isNotEmpty()) {
            val current = queue.remove()
            if (current.node in visited) continue
            if (current.node.position == target) {
                printCruciblePath(current)
                return current.cost
            }
            visited.add(current.node)
            for (next in current.getNextStates(part)) {
                queue.add(next)
            }
        }
        return -1
    }

    private fun printCruciblePath(target: State) {
        //connections.forEach(::println)
        var temp = target
        val path = mutableListOf<State>()
        while (temp.node.position != Point(0, 0)) {
            path.add(temp)
            temp = temp.last ?: break
        }
        //path.reversed().forEach { println("${it.node} ${it.cost}") }
        for (i in input.indices) {
            for (j in input.first().indices) {
                if (Point(j, i) !in path.map { it.node.position }) print(input[i][j])
                else print('.')
            }
            println()
        }
    }

    fun part1(): Int {
        return shortestPath()
    }

    fun part2(): Int {
        return shortestPath(2)
    }
}

fun main() {
    var solver: Day17
    measureTime { solver = Day17() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
