# AOC2023

AoC 2023 in Kotlin

## Day01 Trebuchet?!

Part 1 was pretty straight forward, but part 2 was a kicker: turns out Regex.matchAll() does not return overlapping
matches. So the edge case of
`threeight` would not find `["three", "eight"]`. Got that tip for a workaround from the Subreddit.

## Day02: Cube Conundrum

Much easier than before, just parse the data correctly with Regex. Code is a little repetitive, but also easy to read.

## Day03: Gear Ratios

Much harder again. Solved Part1 while scanning through the field and checking for parts around the numbers.
Literally edge case got me, where numbers may end on the last column -> the next digit is part of a different number.
For part 2 I scrapped the approach and tried to word with the indices of numbers and gears. I still think the code is
messy, there is surely an easier way.

## Day04: Scratchcards

Parsing the input correctly is most of the work. Set-operations make this a breeze. Once both the formulas for
part 1 and part 2 was clear, the implementation wasn't difficult as well.

## Day05: If You Give A Seed A Fertilizer

Parsing the input was a little tedious, but separate maps work fine. Part 1 was again straight forward, but for part 2
the search space blew up. Since I wanted to find a minimum, I implemented a reverse search, which is still
brute forcing but runs in ok time.

## Day06: Wait for it

Brute force solution did work, but was a little slow, so my intuition was, that there closed form mathematical solution,
and of course, there was.

## Day07: Camel Cards

A little exercise on being as verbose as possible, with a lot of `enum class` and `String` extension functions for
parsing.
Part 1 was therefore very fun and simple, once the foundation was layed out. Part 2 was a little frustrating at first,
because the change of ordering meant, that I needed two enumerations for the cards. But I figured it out, how to make
an interface of enumerations comparable.