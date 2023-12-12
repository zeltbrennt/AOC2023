import util.loadAsList
import kotlin.time.measureTime

class Day12(private val input: List<String> = loadAsList(day = 12)) {

    fun part1() = input.sumOf { getNumberOfArrangements(it) }


    fun part2(): Int {
        TODO("Not yet implemented")
    }

    fun unfold(input: String): String {
        val (arr, temp) = input.split(" ")
        return "${List(5) { arr }.joinToString("?")} ${List(5) { temp }.joinToString(",")}"
    }

    fun getNumberOfArrangements(input: String): Long {
        val (springs, temp) = input.split(" ")
        val groups = temp.split(",").map { it.toInt() }
        return findCombinations(springs, groups)
    }

    private fun findCombinations(
        springs: String,
        target: List<Int>,
        idx: Int = 0,
        value: List<Int> = listOf(0),
        debug: String = ""
    ): Long {
        if (idx > springs.lastIndex) {
            return if (value.let { if (it.last() == 0) it.dropLast(1) else it } == target) 1
            else 0
        }
        //TODO: add pruning for performance improvement
        return when (springs[idx]) {
            '#' -> findCombinations(
                springs,
                target,
                idx + 1,
                value.toMutableList().apply { this[this.lastIndex]++ }.toList(),
                "$debug#"
            )

            '.' -> findCombinations(
                springs,
                target,
                idx + 1,
                if (value.last() == 0) value else value.toMutableList().apply { add(0) }.toList(),
                "$debug."
            )

            else -> findCombinations(
                springs,
                target,
                idx + 1,
                value.toMutableList().apply { this[this.lastIndex]++ }.toList(),
                "$debug#"
            ) + findCombinations(
                springs,
                target,
                idx + 1,
                if (value.last() == 0) value else value.toMutableList().apply { add(0) }.toList(),
                "$debug."
            )
        }
    }
}

fun main() {
    var solver: Day12
    measureTime { solver = Day12() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
