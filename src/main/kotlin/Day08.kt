import util.lcm
import util.loadAsList
import kotlin.time.measureTime

enum class Direction { LEFT, RIGHT }
data class Node(val name: String, val left: String, val right: String)

class Day08(
    private val input: List<String> = loadAsList(day = 8)
) {

    private val instructions = input[0].map { if (it == 'L') Direction.LEFT else Direction.RIGHT }
    private val network = buildMap {
        input.drop(2).forEach {
            val (name, left, right) = it.split(" = (", ", ", ")")
            put(name, Node(name, left, right))
        }
    }

    fun part1(): Int {
        return network["AAA"]?.countStepsUntil("ZZZ") ?: throw Exception("This should never happen")
    }

    private fun Node.countStepsUntil(target: String): Int {
        var currentNode = this
        var nSteps = 0
        var turn: Direction
        while (!currentNode.name.endsWith(target)) {
            turn = instructions[nSteps % instructions.size]
            currentNode = when (turn) {
                Direction.RIGHT -> network[currentNode.right]
                Direction.LEFT -> network[currentNode.left]
            } ?: throw Exception("This should never happen")
            nSteps++
        }
        return nSteps
    }

    fun part2(): Long {
        return network
            .filter { it.key.endsWith("A") }
            .map { it.value.countStepsUntil("Z").toLong() }
            .reduce { acc, i -> lcm(acc, i) }
    }
}

fun main() {
    var solver: Day08
    measureTime { solver = Day08() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { solver.part1().also { print("Part 1: $it".padEnd(40, ' ')) } }.also { println("$it") }
    measureTime { solver.part2().also { print("Part 2: $it".padEnd(40, ' ')) } }.also { println("$it") }
}
        
