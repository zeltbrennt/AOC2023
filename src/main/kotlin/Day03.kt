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
        val gears = parts.filter { it.value == '*' }.map { it.key to getNeighbors(it.key, 1) }
            .associate { it.first to it.second }
        val numbersToCheck = numbers.filter {
            getNeighbors(it.key, it.value.length).any { it in gears.keys }
        }.map { it.key to getNumberCoords(it.key, it.value.length) }
            .associate { it.first to it.second }
        val correctGears =
            gears.map { (k, v) -> k to numbersToCheck.filter { (_, y) -> v.any { it in y } }.map { it.key } }
                .filter { it.second.size == 2 }
                .map { it.second.map { numbers[it] } }
                .sumOf { it.map { it?.toInt() ?: 0 }.reduce { a, b -> a * b } }
        return correctGears
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
data class Grid(val content: List<String>) {
    operator fun get(cell: Cell) = get(cell.y, cell.x)
    operator fun get(x: Int, y: Int) = content.getOrNull(y)?.getOrNull(x)
    operator fun iterator() = content.iterator()
}