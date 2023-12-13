import util.loadAsString
import kotlin.time.measureTime

class Day13(input: String = loadAsString(day = 13)) {

    private val mirrors = buildList {
        input.split("\r\n\r\n").forEach { pattern ->
            add(pattern.split("\r\n"))
        }
    }

    fun List<String>.hasReflectionAxisAt(idx: Int): Boolean {
        var up = idx
        var down = idx + 1
        while (up >= 0 && down <= this.lastIndex) {
            if (this[up] != this[down]) return false
            up--
            down++
        }
        return true
    }

    fun List<String>.transpose(): List<String> {
        val flipped = mutableListOf<String>()
        for (i in 0..this.first().lastIndex) {
            flipped.add(this.map { it[i] }.joinToString(""))
        }
        return flipped.toList()
    }

    fun List<String>.getReflectionAxis(): Int? {
        for (i in 0..<this.lastIndex) {
            if (this.hasReflectionAxisAt(i)) return (i + 1)
        }
        return null
    }

    fun List<String>.getMirrorSummary(): Int? {
        return this.getReflectionAxis()?.times(100) ?: this.transpose().getReflectionAxis()
    }

    fun part1(): Int {
        return mirrors.sumOf { it.getMirrorSummary() ?: 0 }
    }

    fun part2(): Int =
        mirrors.sumOf {
            solve(it.permutateSmudges(), it.getMirrorSummary() ?: 0)
        }


    fun solve(permutations: List<List<String>>, oldSummary: Int): Int {
        for (perm in permutations) {
            val newSummary = perm.getMirrorSummary()
            if (newSummary != null && newSummary != oldSummary) {
                return newSummary
            }
        }
        return 0
    }

    fun List<String>.permutateSmudges(): List<List<String>> {
        val permutations = mutableListOf<List<String>>()
        for ((row, m) in this.withIndex()) {
            for (col in m.indices) {
                permutations.add(this.flip(col, row))
            }
        }
        return permutations
    }

    fun List<String>.flip(col: Int, row: Int): List<String> {
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
        
