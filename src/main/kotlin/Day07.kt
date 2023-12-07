import util.loadAsList
import kotlin.time.measureTime

sealed interface Card
enum class Order1 : Card { N2, N3, N4, N5, N6, N7, N8, N9, T, J, Q, K, A }
enum class Order2 : Card { J, N2, N3, N4, N5, N6, N7, N8, N9, T, Q, K, A }

enum class Rules { WITH_JOKER, WITHOUT_JOKER }

enum class HandType { HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND }

data class Bid(val hand: Hand, val value: Int)

data class Hand(val cards: List<Card>, val rules: Rules) : Comparable<Hand> {

    private val cardGroupSizes = cards.groupingBy { it }.eachCount().map { it.value }

    private val handType: HandType =
        if (cardGroupSizes.size == 1) HandType.FIVE_OF_A_KIND
        else if (4 in cardGroupSizes) HandType.FOUR_OF_A_KIND
        else if (3 in cardGroupSizes && 2 in cardGroupSizes) HandType.FULL_HOUSE
        else if (3 in cardGroupSizes) HandType.THREE_OF_A_KIND
        else if (2 in cardGroupSizes && cardGroupSizes.size == 3) HandType.TWO_PAIR
        else if (2 in cardGroupSizes && cardGroupSizes.size == 4) HandType.ONE_PAIR
        else HandType.HIGH_CARD

    fun getHandType(): HandType {
        if (rules == Rules.WITHOUT_JOKER) return handType
        val numJoker = cards.filter { it.toString() == Order1.J.toString() }.size
        if (numJoker == 0) return handType
        return when (handType) {
            HandType.HIGH_CARD -> HandType.ONE_PAIR
            HandType.ONE_PAIR -> HandType.THREE_OF_A_KIND
            HandType.TWO_PAIR -> if (numJoker == 1) HandType.FULL_HOUSE else HandType.FOUR_OF_A_KIND
            HandType.THREE_OF_A_KIND -> HandType.FOUR_OF_A_KIND
            HandType.FULL_HOUSE -> HandType.FIVE_OF_A_KIND
            HandType.FOUR_OF_A_KIND -> HandType.FIVE_OF_A_KIND
            HandType.FIVE_OF_A_KIND -> HandType.FIVE_OF_A_KIND
        }
    }

    override fun compareTo(other: Hand): Int {
        val thisHandType = this.getHandType()
        val otherHandType = other.getHandType()
        if (thisHandType != otherHandType) return thisHandType.compareTo(otherHandType)
        val thisCardValues = cards.map { (it as Enum<*>).ordinal }
        val otherCardValues = other.cards.map { (it as Enum<*>).ordinal }
        thisCardValues.zip(otherCardValues).forEach { (t, o) ->
            if (t != o) return t.compareTo(o)
        }
        return 0
    }
}

fun String.toBid(rules: Rules = Rules.WITHOUT_JOKER): Bid {
    val (handString, bid) = this.split(" ")
    return Bid(handString.toHand(rules), bid.toInt())
}

fun String.toHand(rules: Rules = Rules.WITHOUT_JOKER): Hand {
    return Hand(this.map { it.toCard(rules) }, rules)
}

fun Char.toCard(ordering: Rules = Rules.WITHOUT_JOKER): Card {
    return when (ordering) {
        Rules.WITHOUT_JOKER -> if (this.isDigit()) Order1.valueOf("N$this") else Order1.valueOf(this.toString())
        Rules.WITH_JOKER -> if (this.isDigit()) Order2.valueOf("N$this") else Order2.valueOf(this.toString())
    }
}


class Day07(
    val input: List<String> = loadAsList(day = 7)
) {

    fun part1(): Int = input.map { it.toBid() }.calcTotalWinnings()

    fun part2(): Int = input.map { it.toBid(Rules.WITH_JOKER) }.calcTotalWinnings()

    private fun List<Bid>.calcTotalWinnings() = this.sortedBy { it.hand }.mapIndexed { i, b -> b.value * (i + 1) }.sum()
}

fun main() {
    var solver: Day07
    measureTime { solver = Day07() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { solver.part1().also { print("Part 1: $it".padEnd(40, ' ')) } }.also { println("$it") }
    measureTime { solver.part2().also { print("Part 2: $it".padEnd(40, ' ')) } }.also { println("$it") }
}
        
