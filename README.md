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