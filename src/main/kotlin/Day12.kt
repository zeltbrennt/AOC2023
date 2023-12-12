import util.loadAsList
import kotlin.time.measureTime

class Day12(private val input: List<String> = loadAsList(day = 12)) {

    fun part1() = input.sumOf { getNumberOfArrangements(it) }

    fun part2() =
        input.map { unfold(it) }.sumOf { getNumberOfArrangements(it) }

    fun unfold(input: String): String {
        val (arr, temp) = input.split(" ")
        return "${List(5) { arr }.joinToString("?")} ${List(5) { temp }.joinToString(",")}"
    }

    fun getNumberOfArrangements(input: String): Long {
        val (springs, temp) = input.split(" ")
        val groups = temp.split(",").map { it.toInt() }
        return findCombinations(springs = springs, target = groups)
    }

    private fun findCombinations(
        springs: String,
        target: List<Int>,
        idx: Int = 0,
        value: List<Int> = listOf(0),
        memo: MutableMap<String, Long> = mutableMapOf()
        //debug: String = ""
    ): Long {
        val key = "$idx$value"
        if (key in memo) return memo[key] ?: throw Exception("how??")
        if (idx > springs.lastIndex) {
            return if (value == target) 1 else 0
        }
        if (value.lastIndex > target.size) return 0 // too many groups
        if (value.size <= target.size && value.last() > target[value.lastIndex]) return 0 // group too big
        if (value.size > 1 && value[value.lastIndex - 1] < target[value.lastIndex - 1]) return 0 // group too small

        val nextPound = value.toMutableList().apply { this[this.lastIndex]++ }.toList()
        val nextDot = if (value.last() != 0 && idx != springs.lastIndex)
            value.toMutableList().apply { add(0) }.toList() else value

        val result = when (springs[idx]) {
            '#' -> findCombinations(
                springs = springs,
                target = target,
                idx = idx + 1,
                value = nextPound,
                memo = memo
                //"$debug#"
            )

            '.' -> findCombinations(
                springs = springs,
                target = target,
                idx = idx + 1,
                value = nextDot,
                memo = memo
                //"$debug."
            )

            else -> findCombinations(
                springs = springs,
                target = target,
                idx = idx + 1,
                value = nextPound,
                memo = memo
                //"$debug#"
            ) + findCombinations(
                springs = springs,
                target = target,
                idx = idx + 1,
                value = nextDot,
                memo = memo
                //"$debug."
            )
        }
        memo[key] = result
        return result
    }
}

fun main() {
    var solver: Day12
    measureTime { solver = Day12() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
