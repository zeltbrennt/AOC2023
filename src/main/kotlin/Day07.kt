import util.loadAsList
import kotlin.time.measureTime

enum class Card { N2, N3, N4, N5, N6, N7, N8, N9, T, J, Q, K, A }

enum class HandType { HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND }

data class Bid(val hand: Hand, val value: Int)

data class Hand(val cards: List<Card>) : Comparable<Hand> {

    private val cardsByType = cards.groupBy { it }.mapValues { it.value.size }

    val handType: HandType =
        if (cardsByType.size == 1) HandType.FIVE_OF_A_KIND
        else if (cardsByType.containsValue(4)) HandType.FOUR_OF_A_KIND
        else if (cardsByType.containsValue(3) && cardsByType.containsValue(2)) HandType.FULL_HOUSE
        else if (cardsByType.containsValue(3) && cardsByType.size == 3) HandType.THREE_OF_A_KIND
        else if (cardsByType.containsValue(2) && cardsByType.size == 3) HandType.TWO_PAIR
        else if (cardsByType.containsValue(2) && cardsByType.size == 4) HandType.ONE_PAIR
        else HandType.HIGH_CARD

    override fun compareTo(other: Hand): Int {
        val a = this.handType
        val b = other.handType
        if (a != b) return a.compareTo(b)
        this.cards.zip(other.cards).forEach { (t, o) ->
            if (t != o) return t.compareTo(o)
        }
        return 0
    }
}

fun String.toBid(): Bid {
    val (handString, bid) = this.split(" ")
    return Bid(handString.toHand(), bid.toInt())
}

fun String.toHand(): Hand {
    return Hand(this.map { it.toCard() })
}

fun Char.toCard(): Card =
    if (this.isDigit()) Card.valueOf("N$this") else Card.valueOf(this.toString())


class Day07(
    input: List<String> = loadAsList(day = 7)
) {

    private val bids = input.map { it.toBid() }

    fun part1(): Int {
        return bids.sortedBy { it.hand }.mapIndexed { i, b -> b.value * (i + 1) }.sum()
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day07
    measureTime { solver = Day07() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { solver.part1().also { print("Part 1: $it".padEnd(40, ' ')) } }.also { println("$it") }
    measureTime { solver.part2().also { print("Part 2: $it".padEnd(40, ' ')) } }.also { println("$it") }
}
        
