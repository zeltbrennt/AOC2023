import util.Point
import util.loadAsList
import kotlin.time.measureTime

class Day11(input: List<String> = loadAsList(day = 11)) {

    val galaxies = buildList {
        for ((y, row) in input.withIndex()) {
            for (x in row.indices) {
                if (input[y][x] == '#') {
                    add(Point(x, y))
                }
            }
        }
    }

    fun expand(universe: List<Point>, faktor: Int = 1): List<Point> {
        val maxX = universe.maxOf { it.x }
        val maxY = universe.maxOf { it.y }
        val colsWithGalaxies = universe.map { it.x }.toSet()
        val rowsWithGalaxies = universe.map { it.y }.toSet()
        val toExpandX =
            IntRange(0, maxX).filter { it !in colsWithGalaxies }.mapIndexed { idx, it -> it + (faktor - 1) * idx }
        val toExpandY =
            IntRange(0, maxY).filter { it !in rowsWithGalaxies }.mapIndexed { idx, it -> it + (faktor - 1) * idx }
        val expandedUniverse = buildList<Point> {
            this.addAll(universe)
            toExpandX.forEach { col ->
                this.forEachIndexed { index, galaxy ->
                    if (galaxy.x > col) this[index] = galaxy.copy(x = galaxy.x + faktor - 1, y = galaxy.y)
                }
            }
            toExpandY.forEach { row ->
                this.forEachIndexed { index, galaxy ->
                    if (galaxy.y > row) this[index] = galaxy.copy(x = galaxy.x, y = galaxy.y + faktor - 1)
                }
            }
        }
        return expandedUniverse
    }

    fun getSumOfDistances(universe: List<Point>): Long {
        var sumOfDistances = 0L
        for (i in universe.indices) {
            for (j in (i + 1)..universe.lastIndex) {
                sumOfDistances += universe[i].manhattanDistance(universe[j])
            }
        }
        return sumOfDistances
    }

    fun part1(): Long {
        val expanded = expand(galaxies, 2)
        return getSumOfDistances(expanded)
    }

    fun part2(): Long {
        val expanded = expand(galaxies, 1000000)
        return getSumOfDistances(expanded)
    }
}

fun main() {
    var solver: Day11
    measureTime { solver = Day11() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
