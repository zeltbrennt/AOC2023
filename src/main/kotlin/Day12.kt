import util.loadAsList
import kotlin.time.measureTime

class Day12(private val input: List<String> = loadAsList(day = 12)) {

    fun part1(): Int =
        input.sumOf { getNumberOfArrangements(it) }


    fun part2(): Int {
        TODO("Not yet implemented")
    }

    private fun templateToRegex(template: String): Regex =
        template.replace(".", "!")
            .replace("?", ".")
            .replace("!", "\\.")
            .toRegex()

    private fun generateNumbers(numOfDigits: Int, base: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val combo = MutableList(numOfDigits) { 0 }
        while (combo[0] < base) {
            if (combo.windowed(2).all { (a, b) -> a <= b }) result.add(combo.toList())
            combo[combo.lastIndex]++
            for (i in combo.lastIndex downTo 1) {
                if (combo[i] >= base) {
                    combo[i] = 0
                    combo[i - 1]++
                }
            }
        }
        return result
    }

    private fun getStartingIndices(input: List<Int>) =
        input.runningFold(0) { acc, i -> acc + i }
            .dropLast(1)
            .mapIndexed { index, i -> i + index }

    private fun getAllIndices(numOfDigits: Int, base: Int, input: List<Int>) =
        generateNumbers(numOfDigits, base)
            .map { getStartingIndices(input).zip(it).map { (a, b) -> a + b } }

    private fun createIteration(length: Int, indices: List<Int>, groups: List<Int>): String {
        var result = ".".repeat(length)
        for ((i, len) in indices.zip(groups)) {
            result = result.replaceRange(i, i + len, "#".repeat(len))
        }
        return result
    }

    fun getNumberOfArrangements(input: String): Int {
        val (arr, temp) = input.split(" ")
        val groups = temp.split(",").map { it.toInt() }
        val base = arr.length - groups.sum() - groups.lastIndex + 1
        val lengthOfString = arr.length
        val regex = templateToRegex(arr)
        return getAllIndices(groups.size, base, groups)
            .map { createIteration(lengthOfString, it, groups) }
            .count { it matches regex }
    }

    fun unfold(input: String): String {
        val (arr, temp) = input.split(" ")
        return "${List(5) { arr }.joinToString("?")} ${List(5) { temp }.joinToString(",")}"
    }
}

fun main() {
    var solver: Day12
    measureTime { solver = Day12() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
