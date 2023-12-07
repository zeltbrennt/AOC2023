import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 07")
class Day07Test {

    private val solver = Day07()

    @Test
    @DisplayName("AAAAA is five of a kind")
    fun four_of_a_kind() {
        val hand = "AAAAA".toHand()
        assertEquals(HandType.FIVE_OF_A_KIND, hand.getHandType())
    }

    @Test
    @DisplayName("AA8AA is four of a kind")
    fun five_of_a_kind() {
        val hand = "AA8AA".toHand()
        assertEquals(HandType.FOUR_OF_A_KIND, hand.getHandType())
    }

    @Test
    @DisplayName("TTT98 is three of a kind")
    fun three_of_a_kind() {
        val hand = "TTT98".toHand()
        assertEquals(HandType.THREE_OF_A_KIND, hand.getHandType())
    }

    @Test
    @DisplayName("23332 is full house")
    fun full_house() {
        val hand = "23332".toHand()
        assertEquals(HandType.FULL_HOUSE, hand.getHandType())
    }

    @Test
    @DisplayName("23432 is two pair")
    fun two_pair() {
        val hand = "23432".toHand()
        assertEquals(HandType.TWO_PAIR, hand.getHandType())
    }

    @Test
    @DisplayName("A23A4 is one pair")
    fun one_pair() {
        val hand = "A23A4".toHand()
        assertEquals(HandType.ONE_PAIR, hand.getHandType())
    }

    @Test
    @DisplayName("23456 is high card")
    fun high_card() {
        val hand = "23456".toHand()
        assertEquals(HandType.HIGH_CARD, hand.getHandType())
    }

    @Test
    @DisplayName("33332 is stronger than 2AAAA")
    fun compare_ex1() {
        val hand1 = "33332".toHand()
        val hand2 = "2AAAA".toHand()
        assertTrue(hand1 > hand2)
    }

    @Test
    @DisplayName("77888 is stronger than 77788")
    fun compare_ex2() {
        val hand1 = "77888".toHand()
        val hand2 = "77788".toHand()
        assertTrue(hand1 > hand2)
    }

    @Test
    @DisplayName("Part 1")
    fun day07_part01_example01() {
        val solution = solver.part1()
        assertEquals(6440, solution)
    }

    @Test
    @DisplayName("Part 2")
    fun day07_part02_example01() {
        val solution = solver.part2()
        assertEquals(5905, solution)
    }

} 
        
