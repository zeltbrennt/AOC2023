import util.loadAsString
import kotlin.time.measureTime

class Day13(input: String = loadAsString(day = 13)) {

    private val mirrors = buildList {
        input.split("\r\n\r\n").forEach { pattern ->
            add(pattern.split("\r\n"))
        }
    }

    private fun List<String>.hasReflectionAxisAt(idx: Int): Boolean {
        var up = idx
        var down = idx + 1
        while (up >= 0 && down <= this.lastIndex) {
            if (this[up] != this[down]) return false
            up--
            down++
        }
        return true
    }

    private fun List<String>.transpose(): List<String> {
        val flipped = mutableListOf<String>()
        for (i in 0..this.first().lastIndex) {
            flipped.add(this.map { it[i] }.joinToString(""))
        }
        return flipped.toList()
    }

    private fun List<String>.getAllReflectionAxis(): MutableList<Int> {
        val axis = mutableListOf<Int>()
        for (i in 0..<this.lastIndex) {
            if (this.hasReflectionAxisAt(i)) axis.add(i + 1)
        }
        return axis
    }

    private fun List<String>.getAllMirrorSummary(): List<Int> {
        val horizontal = this.getAllReflectionAxis().map { it * 100 }
        val vertical = this.transpose().getAllReflectionAxis()
        vertical.addAll(horizontal)
        return vertical
    }

    fun part1(): Int = mirrors.sumOf { it.getAllMirrorSummary().first() }

    fun part2(): Int =
        mirrors.sumOf { getSummaryForAnyPermutation(it.permuteSmudges(), it.getAllMirrorSummary().first()) }


    private fun getSummaryForAnyPermutation(permutations: List<List<String>>, oldSummary: Int): Int {
        for (perm in permutations) {
            val axis = perm.getAllMirrorSummary()
            if (axis.isNotEmpty() && axis.any { it != oldSummary }) {
                return axis.first { it != oldSummary }
            }
        }
        return 0
    }

    private fun List<String>.permuteSmudges(): List<List<String>> {
        val permutations = mutableListOf<List<String>>()
        for ((row, m) in this.withIndex()) {
            for (col in m.indices) {
                permutations.add(this.flip(col, row))
            }
        }
        return permutations
    }

    private fun List<String>.flip(col: Int, row: Int): List<String> {
        val copy = this.toMutableList()
        val rowToChange = copy[row]
        val replacement = if (rowToChange[col] == '.') "#" else "."
        copy[row] = rowToChange.replaceRange(col, col + 1, replacement)
        return copy.toList()
    }
}

fun main() {
    var solver: Day13
    measureTime { solver = Day13() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
