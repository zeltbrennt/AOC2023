import util.loadAsList
import kotlin.time.measureTime

class Day06(
    private val input: List<String> = loadAsList(day = 6)
) {

    fun calcRangeOfWays(time: Long, dist: Long): List<Int> =
        LongRange(0, time).mapIndexed { i, v -> i to (time - v) * v }.filter { it.second > dist }.map { it.first }

    fun part1(): Int {
        val times = Regex("\\d+").findAll(input[0]).map { it.groupValues[0] }.map { it.toLong() }.toList()
        val dist = Regex("\\d+").findAll(input[1]).map { it.groupValues[0] }.map { it.toLong() }.toList()
        return times.zip(dist).map { (t, d) -> calcRangeOfWays(t, d).size }.reduce { a, b -> a * b }
    }

    fun part2(): Int {
        val times = input[0].replace(" ", "").substringAfter(":").toLong()
        val dist = input[1].replace(" ", "").substringAfter(":").toLong()
        return calcRangeOfWays(times, dist).size
    }
}

fun main() {
    var solver: Day06
    measureTime { solver = Day06() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { solver.part1().also { print("Part 1: $it".padEnd(40, ' ')) } }.also { println("$it") }
    measureTime { solver.part2().also { print("Part 2: $it".padEnd(40, ' ')) } }.also { println("$it") }
}
        
