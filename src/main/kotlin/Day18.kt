import util.Point
import util.loadAsList
import java.util.*
import kotlin.time.measureTime

class Day18(private val input: List<String> = loadAsList(day = 18)) {

    private val lagoon = buildSet {
        var x = 0
        var y = 0
        for (line in input) {
            val (dir, len, _) = line.split(" ")
            for (i in 1..len.toInt()) {
                when (dir) {
                    "R" -> x++
                    "L" -> x--
                    "U" -> y--
                    "D" -> y++
                }
                add(Point(x, y))
            }
        }
    }

    fun printShape(points: Set<Point>) {
        val minX = points.minOf { it.x } - 1
        val minY = points.minOf { it.y } - 1
        val maxX = points.maxOf { it.x } + 1
        val maxY = points.maxOf { it.y } + 1
        for (j in minY..maxY) {
            for (i in minX..maxX) {
                if (Point(i, j) in lagoon) print("#")
                else if (Point(i, j) in points) print("X")
                else print(".")
            }
            println()
        }
    }

    private fun floodFill(): Set<Point> {
        val start = getStartPoint()
        val queue: Deque<Point> = LinkedList()
        queue.add(start)
        val visited = lagoon.toMutableSet()
        visited.add(start)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            current.get4Neighbors().forEach {
                if (it !in visited) {
                    queue.add(it)
                    visited.add(it)
                    printShape(visited)
                }
            }
        }
        return visited
    }

    private fun getStartPoint(): Point {
        var x = 0
        var y = 0
        val (dir, len, _) = input[0].split(" ")
        when (dir) {
            "R" -> x = x + len.toInt() - 1
            "L" -> x = x - len.toInt() + 1
            "U" -> y = y - len.toInt() + 1
            "D" -> y = y + len.toInt() - 1
        }
        var (dir2, len2, _) = input[1].split(" ")
        when (dir2) {
            "R" -> x++
            "L" -> x--
            "U" -> y--
            "D" -> y++
        }
        return Point(x, y)
    }


    fun part1(): Int {
        return floodFill().size
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day18
    measureTime { solver = Day18() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
