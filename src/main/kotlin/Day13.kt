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

    fun List<String>.getMirrorSummary(): Int {
        return this.getReflectionAxis()?.times(100) ?: this.transpose().getReflectionAxis()
        ?: throw Exception("No Reflection found")
    }

    fun part1(): Int {
        return mirrors.sumOf { it.getMirrorSummary() }
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day13
    measureTime { solver = Day13() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
