import util.Cell2D
import util.loadAsList
import kotlin.time.measureTime

class Day11(input: List<String> = loadAsList(day = 11)) {

    val galaxies = buildList<Cell2D> {
        var verticalOffset = 0
        val colsWithGalaxies = mutableSetOf<Int>()
        for (y in input.indices) {
            var mustExpand = true
            for (x in input[0].indices) {
                if (input[y][x] == '#') {
                    mustExpand = false
                    add(Cell2D(x, y + verticalOffset)) //expand vertically
                    colsWithGalaxies.add(x)
                }
            }
            if (mustExpand) verticalOffset++
        }
        // expand horizontally
        val toExpand = input[0].indices
            .filter { it !in colsWithGalaxies }
            .mapIndexed { index, i -> i + index }
        for (col in toExpand) {
            for ((idx, galaxy) in this.withIndex()) {
                if (galaxy.x > col) {
                    this[idx] = galaxy.east()
                }
            }
        }
    }


    fun part1(): Int {
        var sumOfDistances = 0
        for (i in galaxies.indices) {
            for (j in i..galaxies.lastIndex) {
                sumOfDistances += galaxies[i].manhattanDistance(galaxies[j])
            }
        }
        return sumOfDistances
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day11
    measureTime { solver = Day11() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        
