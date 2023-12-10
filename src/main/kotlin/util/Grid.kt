package util

import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt

interface Cell
data class Cell2D(val x: Int, val y: Int) : Cell {

    fun north() = Cell2D(x, y - 1)
    fun south() = Cell2D(x, y + 1)
    fun west() = Cell2D(x - 1, y)
    fun east() = Cell2D(x + 1, y)

    fun get8Neighbors(): List<Cell2D> {
        return listOf(
            north(), south(), east(), west(),
            Cell2D(x + 1, y + 1),
            Cell2D(x + 1, y - 1),
            Cell2D(x - 1, y + 1),
            Cell2D(x - 1, y - 1)
        )
    }

    fun get4Neighbors(): List<Cell2D> {
        return listOf(
            north(), south(), east(), west()
        )
    }

    fun manhattanDistance(cell: Cell2D) = abs(x - cell.x) + abs(y - cell.y)
}

class Grid<T>(private val content: Map<Cell, T>) {

    fun getValueAt(cell: Cell) = content[cell]
}

data class Vec2D(val x: Double, val y: Double) {

    val magnitude = sqrt(x.pow(2) + y.pow(2))
    val angle = atan(y / x) // nope
    fun manhattanDistance(other: Vec2D) = abs(x - other.x) + abs(y - other.y)

    fun add(other: Vec2D) = Vec2D(x + other.x, y + other.y)

    fun scale(s: Int) = Vec2D(x * s, y * s)
    fun normalize() = Vec2D(x / magnitude, y / magnitude)
}
