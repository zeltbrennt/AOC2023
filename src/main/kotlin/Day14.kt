import util.loadAsList
import kotlin.time.measureTime

class Day14(input: List<String> = loadAsList(day = 14)) {

    private val platform: Platform

    init {
        val squareRocks = mutableSetOf<Rock>()
        val roundRocks = mutableSetOf<Rock>()
        for (i in input.indices) {
            for (j in input.first().indices) {
                if (input[i][j] == 'O') roundRocks.add(Rock(j, i))
                else if (input[i][j] == '#') squareRocks.add(Rock(j, i))
            }
        }
        platform = Platform(roundRocks, squareRocks, input.first().length, input.size)
    }

    data class Rock(var x: Int, var y: Int)


    class Platform(
        val roundRocks: Set<Rock>,
        val squareRocks: Set<Rock>,
        val width: Int, val height: Int
    ) {
        fun tiltNorth() {
            for (col in 0..<width) {
                val obsticals = squareRocks.filter { it.x == col }.map { it.y }.sorted()
                if (obsticals.isEmpty()) {
                    roundRocks
                        .filter { it.x == col }
                        .forEachIndexed { index, rock -> rock.y = index }

                } else {
                    val round = roundRocks.filter { it.x == col }
                    val square = squareRocks.filter { it.x == col }.map { it.y }.sorted()
                    round.zip(getShiftedCoords(round.map { it.y }.sorted(), square))
                        .forEach { (rock, newY) -> rock.y = newY }
                }
            }
        }

        fun tiltSouth() {
            for (col in 0..<width) {
                val obsticals = squareRocks.filter { it.x == col }.map { it.y }.sorted()
                if (obsticals.isEmpty()) {
                    roundRocks
                        .filter { it.x == col }
                        .forEachIndexed { index, rock -> rock.y = height - index - 1 }

                } else {
                    val round = roundRocks.filter { it.x == col }
                    val square = squareRocks.filter { it.x == col }.map { height - 1 - it.y }.sorted()
                    round.zip(getShiftedCoords(round.map { height - 1 - it.y }.sorted(), square))
                        .forEach { (rock, newY) -> rock.y = height - 1 - newY }
                }
            }
        }

        fun tiltWest() {
            for (row in 0..<height) {
                val obsticals = squareRocks.filter { it.y == row }.map { it.x }.sorted()
                if (obsticals.isEmpty()) {
                    roundRocks
                        .filter { it.y == row }
                        .forEachIndexed { index, rock -> rock.x = index }

                } else {
                    val round = roundRocks.filter { it.y == row }
                    val square = squareRocks.filter { it.y == row }.map { it.x }.sorted()
                    round.zip(getShiftedCoords(round.map { it.x }.sorted(), square))
                        .forEach { (rock, newX) -> rock.x = newX }
                }
            }
        }

        fun tiltEast() {
            for (row in 0..<height) {
                val obsticals = squareRocks.filter { it.y == row }.map { it.x }.sorted()
                if (obsticals.isEmpty()) {
                    roundRocks
                        .filter { it.y == row }
                        .forEachIndexed { index, rock -> rock.x = width - index - 1 }

                } else {
                    val round = roundRocks.filter { it.y == row }
                    val square = squareRocks.filter { it.y == row }.map { width - 1 - it.x }.sorted()
                    round.zip(getShiftedCoords(round.map { width - 1 - it.x }.sorted(), square))
                        .forEach { (rock, newX) -> rock.x = width - 1 - newX }
                }
            }
        }

        private fun getShiftedCoords(round: List<Int>, square: List<Int>): List<Int> {
            val obsticles = square.toMutableList()
            var minIndex = 0
            val newOrder = mutableListOf<Int>()
            for (r in round) {
                while (obsticles.isNotEmpty() && r > obsticles.first()) {
                    minIndex = obsticles.first() + 1
                    obsticles.removeFirst()
                }
                newOrder.add(minIndex++)
            }
            return newOrder
        }

        override fun toString(): String {
            val builder = StringBuilder()
            for (i in 0..<height) {
                for (j in 0..<width) {
                    if (squareRocks.find { it.x == j && it.y == i } != null) {
                        builder.append("#")
                    } else if (roundRocks.find { it.x == j && it.y == i } != null) {
                        builder.append("O")
                    } else builder.append(".")
                }
                builder.append("\n")
            }
            return builder.toString()
        }
    }

    fun part1(): Int {
        platform.tiltNorth()
        platform.roundRocks.groupBy { it.y }.count()
        return platform.roundRocks.groupingBy { it.y }
            .eachCount()
            .map { (platform.height - it.key) * it.value }
            .sum()
    }

    fun part2(): Int {
        val veryLongTime = 1000000000
        val previousStates = mutableListOf<String>()
        var cycleLength: Int
        var iteration = 0L

        while (iteration < veryLongTime) {
            platform.tiltNorth()
            platform.tiltWest()
            platform.tiltSouth()
            platform.tiltEast()
            iteration++
            val state = platform.toString()
            val cycleBegin = previousStates.indexOf(state)
            if (cycleBegin != -1) {
                cycleLength = previousStates.size - cycleBegin
                iteration = veryLongTime - (veryLongTime - iteration) % cycleLength
            }
            previousStates.add(state)
        }
        platform.roundRocks.groupBy { it.y }.count()
        return platform.roundRocks.groupingBy { it.y }
            .eachCount()
            .map { (platform.height - it.key) * it.value }
            .sum()
    }
}

fun main() {
    var solver: Day14
    measureTime { solver = Day14() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
