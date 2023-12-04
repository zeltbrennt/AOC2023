import util.loadAsList
import java.lang.Math.pow
import kotlin.math.pow

class Day04(
    private val input: List<String> = loadAsList(day = 4)
) {

    private val winning: List<Set<Int>> = buildList {
        input.forEach { line ->
            add(line.substringAfter(":")
                .substringBefore("|")
                .trim()
                .split(Regex("\\s+"))
                .map { it.toInt() }
                .toSet())
        }
    }
    private val numbers: List<Set<Int>> = buildList {
        input.forEach { line ->
            add(line.substringAfter("|")
                .trim()
                .split(Regex("\\s+"))
                .map { it.toInt() }
                .toSet())
        }
    }

    private val matches = winning.zip(numbers).map { (win, num) ->
        win.intersect(num).size
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
        
