import util.loadAsList

class Day03(
    private val input: List<String> = loadAsList(day = 3)
) {

    private val numbers = mutableMapOf<Cell, String>()
    private val parts = mutableMapOf<Cell, Char>()

    init {
        var currentNumber = ""
        var startIndex = Cell(-1, -1)
        for (y in input.indices) {
            if (currentNumber != "") numbers[startIndex] = currentNumber
            currentNumber = ""
            startIndex = Cell(-1, -1)
            for (x in input[y].indices) {
                val cell = input[y][x]
                if (cell.isDigit()) {
                    if (currentNumber == "") startIndex = Cell(x, y)
                    currentNumber += cell
                } else {
                    if (cell != '.') parts[Cell(x, y)] = cell
                    if (currentNumber != "") numbers[startIndex] = currentNumber
                    currentNumber = ""
                }
            }
        }
    }

    fun part1(): Int {
        return numbers.filter {
            getNeighbors(it.key, it.value.length).any { it in parts.keys }
        }.map { it.value.toInt() }.sum()
    }


    fun part2(): Int {
        //all parts that are gears
        val gears = parts.filter { it.value == '*' }.keys.associateWith { getNeighbors(it, 1) }
        // all numbers that are part numbers of gears
        val numbersToCheck = numbers.filter { (coord, number) ->
            getNeighbors(coord, number.length).any { it in gears.keys }
        }.keys.associateWith { coord -> getNumberCoords(coord, numbers[coord]?.length ?: 0) }
        // gears and their numbers attached
        val gearsWithNumbers = gears.entries.associate { (coord, gearBoarder) ->
            coord to numbersToCheck
                .filter { (_, numberCoords) -> gearBoarder.any { it in numberCoords } }
                .map { it.key }
        }
        return gearsWithNumbers
            .filter { it.value.size == 2 }
            .values
            .map { number -> number.map { numbers[it] ?: "" } }
            .sumOf { gear -> gear.map { it.toInt() }.reduce { a, b -> a * b } }
    }

    private fun getNeighbors(cell: Cell, len: Int): List<Cell> {
        val coords = mutableListOf<Cell>()
        for (y in -1..1) {
            for (x in -1..len) {
                if (y == 0 && x in 0..<len) continue
                coords.add(Cell(cell.x + x, cell.y + y))
            }
        }
        return coords
    }

    private fun getNumberCoords(cell: Cell, len: Int): List<Cell> {
        val coords = mutableListOf<Cell>()
        for (i in 0..<len) {
            coords.add(Cell(cell.x + i, cell.y))
        }
        return coords
    }

}

fun main() {
    val solver = Day03()
    println(solver.part1())
    println(solver.part2())
}

data class Cell(val x: Int, val y: Int)
