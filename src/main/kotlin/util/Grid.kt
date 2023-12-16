package util

import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int, val z: Int = 0) {

    val north: Point get() = this.copy(y = y - 1)
    val south: Point get() = this.copy(y = y + 1)
    val west: Point get() = this.copy(x = x - 1)
    val east: Point get() = this.copy(x = x + 1)
    val up: Point get() = this.copy(z = z + 1)
    val down: Point get() = this.copy(z = z - 1)

    fun get8Neighbors(): List<Point> {
        return listOf(
            north, south, east, west,
            north.east, north.west,
            south.east, south.west
        )
    }

    fun get4Neighbors(): List<Point> {
        return listOf(
            north, south, east, west
        )
    }

    fun manhattanDistance(point: Point) = abs(x - point.x) + abs(y - point.y)
}

class Grid<T>(private val content: Map<Point, T>) {

    fun getValueAt(point: Point) = content[point]
}

data class Vec2D(val x: Double, val y: Double) {

    val magnitude = sqrt(x.pow(2) + y.pow(2))
    val angle = atan(y / x) // nope
    fun manhattanDistance(other: Vec2D) = abs(x - other.x) + abs(y - other.y)

    fun add(other: Vec2D) = Vec2D(x + other.x, y + other.y)

    fun scale(s: Int) = Vec2D(x * s, y * s)
    fun normalize() = Vec2D(x / magnitude, y / magnitude)
}
