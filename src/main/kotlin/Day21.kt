import util.Point
import util.loadAsList
import java.util.*
import kotlin.math.abs
import kotlin.time.measureTime

class Day21(private val input: List<String> = loadAsList(day = 21)) {

    private val start: Point
    private val reach = mutableSetOf<Point>()
    internal var maxSteps = 64
    private val rocks = buildSet {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == '#') add(Point(x, y))
            }
        }
    }

    init {
        var temp = Point(0, 0)
        input.forEachIndexed { y, s ->
            val x = s.indexOf('S')
            if (x != -1) temp = Point(x, y)
            return@forEachIndexed
        }
        start = temp
    }

    private fun printGarden() {
        input.forEachIndexed { y, line ->
            line.indices.forEach { x ->
                if (Point(x, y) in reach) {
                    print("O")
                } else print(input[y][x])

            }
            println()
        }
    }

    private fun makeFilledDiamond(width: Int): Set<Point> {
        return buildSet {
            for (y in -width..width) {
                val offset = width - abs(y)
                for (x in -offset..offset step 2) {
                    add(Point(start.x + x, start.y + y))
                }
            }
        }
    }

    fun getReach(maxSteps: Int, currentStep: Int, point: Point, endPoints: MutableSet<Point> = mutableSetOf()) {
        if (currentStep == maxSteps) endPoints.add(point)
        else point
            .get4Neighbors()
            .filter { input[it.y][it.x] != '#' }
            .forEach { getReach(maxSteps, currentStep + 1, it, endPoints) }
    }

    data class Step(val pos: Point, val step: Int)

    fun bfs(maxSteps: Int): Set<Point> {
        val queue: Deque<Step> = LinkedList()
        val visited: MutableSet<Step> = mutableSetOf()
        queue.add(Step(start, 0))
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current.step > maxSteps) break
            if (current in visited) continue
            visited.add(current)
            current.pos.get4Neighbors().filter { it !in rocks }
                .forEach { queue.add(Step(it, current.step + 1)) }
        }
        return visited.filter { it.step % 2 == 0 }.map { it.pos }.toSet()
    }

    fun part1(): Int {
        reach.addAll(bfs(maxSteps))
        //(makeFilledDiamond(maxSteps) subtract rocks).filter { !it.get4Neighbors().all { it in rocks } } // off by ONE!!
        printGarden()
        return reach.size
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day21
    measureTime { solver = Day21() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
