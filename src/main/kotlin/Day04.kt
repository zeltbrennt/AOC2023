import util.loadAsList
import kotlin.math.pow

class Day04(
    private val input: List<String> = loadAsList(day = 4)
) {

    private val matches = input.map { line ->
        line.substringAfter(":")
            .substringBefore("|")
            .trim()
            .split(Regex("\\s+"))
            .map { it.toInt() }
            .toSet()
            .intersect(
                line.substringAfter("|")
                    .trim()
                    .split(Regex("\\s+"))
                    .map { it.toInt() }
                    .toSet())
            .size
    }

    fun part1(): Int {
        return matches.sumOf {
            if (it > 0) 2.0.pow(it - 1) else 0.0
        }.toInt()
    }


    fun part2(): Int {
        val cards = MutableList(input.size) { 1 }
        for ((index, matchNum) in matches.withIndex()) {
            val range = index + 1..index + matchNum
            for (i in range) {
                cards[i] += cards[index]
            }
        }
        return cards.sum()
    }
}

fun main() {
    val solver = Day04()
    println(solver.part1())
    println(solver.part2())
}
        
