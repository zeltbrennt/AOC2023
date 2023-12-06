import util.loadAsList
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.time.measureTime

class Day06(
    private val input: List<String> = loadAsList(day = 6)
) {

    fun calcRangeOfWays(time: Double, dist: Double): Int {
        val low = (0.5 * (time - sqrt(time.pow(2) - 4 * dist))).toInt()
        val up = (0.5 * (time + sqrt(time.pow(2) - 4 * dist))).toInt()
        return up - low
    }


    fun part1(): Int {
        val times = Regex("\\d+").findAll(input[0]).map { it.groupValues[0] }.map { it.toDouble() }
        val dist = Regex("\\d+").findAll(input[1]).map { it.groupValues[0] }.map { it.toDouble() }
        return times.zip(dist).map { (t, d) -> calcRangeOfWays(t, d) }.reduce { a, b -> a * b }
    }

    fun part2(): Int {
        val times = input[0].replace(" ", "").substringAfter(":").toDouble()
        val dist = input[1].replace(" ", "").substringAfter(":").toDouble()
        return calcRangeOfWays(times, dist)
    }
}

fun main() {
    var solver: Day06
    measureTime { solver = Day06() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { solver.part1().also { print("Part 1: $it".padEnd(40, ' ')) } }.also { println("$it") }
    measureTime { solver.part2().also { print("Part 2: $it".padEnd(40, ' ')) } }.also { println("$it") }
}
        
