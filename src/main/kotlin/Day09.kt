import util.loadAsList
import kotlin.time.measureTime

class Day09(input: List<String> = loadAsList(day = 9)) {

    private val history = input.map { line -> line.split(" ").map { it.toLong() } }

    private fun List<Long>.findNextInSequence(): Long {
        val lastValues = mutableListOf<Long>()
        var sequence = this
        while (sequence.any { it != 0L }) {
            lastValues.add(sequence.last())
            sequence = sequence.windowed(2).map { (a, b) -> b - a }
        }
        return lastValues.sum()
    }

    fun part1(): Long = history.sumOf { it.findNextInSequence() }

    fun part2(): Long = history.sumOf { it.reversed().findNextInSequence() }
}

fun main() {
    var solver: Day09
    measureTime { solver = Day09() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
