import util.Point
import util.loadAsList
import kotlin.time.measureTime

class Day18(private val input: List<String> = loadAsList(day = 18)) {

    private val lagoon: List<Point>
    private val giantLagoon: List<Point>

    init {
        val p1 = mutableListOf(Point(0, 0))
        val p2 = mutableListOf(Point(0, 0))
        var x = 0
        var y = 0
        var giantX = 0
        var giantY = 0
        val inputRegex = """([RDLU])\s(\d+)\s\(#([a-z0-9]{5})([a-z0-9])\)""".toRegex()
        for (line in input) {
            val match = inputRegex.matchEntire(line) ?: throw IllegalArgumentException("input malformed")
            val (dir1, len1, hex, dir2) = match.destructured
            when (dir1) {
                "R" -> x += len1.toInt()
                "L" -> x -= len1.toInt()
                "U" -> y -= len1.toInt()
                "D" -> y += len1.toInt()
            }
            p1.add(Point(x, y))
            val len2 = hex.toInt(16)
            when (dir2) {
                "0" -> giantX += len2
                "1" -> giantY += len2
                "2" -> giantX -= len2
                "3" -> giantY -= len2
            }
            p2.add(Point(giantX, giantY))
        }
        lagoon = p1
        giantLagoon = p2
    }

    private fun List<Point>.calcArea(): Long { //Shoelace
        val area = this
            .windowed(2)
            .sumOf { it.first().x.toLong() * it.last().y - it.first().y.toLong() * it.last().x } / 2
        val perimeter = this.windowed(2).sumOf { it.first().manhattanDistance(it.last()) }
        return area + perimeter / 2 + 1
    }

    fun part1(): Long {
        return lagoon.calcArea()
    }

    fun part2(): Long {
        return giantLagoon.calcArea()
    }
}

fun main() {
    var solver: Day18
    measureTime { solver = Day18() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
