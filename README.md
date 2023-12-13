# AOC2023

AoC 2023 in Kotlin

## Day 01 Trebuchet?!

Part 1 was pretty straight forward, but part 2 was a kicker: turns out Regex.matchAll() does not return overlapping
matches. So the edge case of
`threeight` would not find `["three", "eight"]`. Got that tip for a workaround from the Subreddit.

## Day 02: Cube Conundrum

Much easier than before, just parse the data correctly with Regex. Code is a little repetitive, but also easy to read.

## Day 03: Gear Ratios

Much harder again. Solved Part1 while scanning through the field and checking for parts around the numbers.
Literally edge case got me, where numbers may end on the last column -> the next digit is part of a different number.
For part 2 I scrapped the approach and tried to word with the indices of numbers and gears. I still think the code is
messy, there is surely an easier way.

## Day 04: Scratchcards

Parsing the input correctly is most of the work. Set-operations make this a breeze. Once both the formulas for
part 1 and part 2 was clear, the implementation wasn't difficult as well.

## Day 05: If You Give A Seed A Fertilizer

Parsing the input was a little tedious, but separate maps work fine. Part 1 was again straight forward, but for part 2
the search space blew up. Since I wanted to find a minimum, I implemented a reverse search, which is still
brute forcing but runs in ok time.

## Day 06: Wait for it

Brute force solution did work, but was a little slow, so my intuition was, that there closed form mathematical solution,
and of course, there was.

## Day 07: Camel Cards

A little exercise on being as verbose as possible, with a lot of `enum class` and `String` extension functions for
parsing.
Part 1 was therefore very fun and simple, once the foundation was layed out. Part 2 was a little frustrating at first,
because the change of ordering meant, that I needed two enumerations for the cards. But I figured it out, how to make
an interface of enumerations comparable.

## Day 08: Haunted Wasteland

Part 1 was straight forward, just follow the instructions. Part 2 could not be brute forced, so the key wa to remember,
that each ghost follows its own loop and the *least common multiple* of each loop length represents time it takes
until all loops sync up again.

# Day 09: Mirage Maintenance

Window-Function was key here.

# Day 10: Pipe Maze

First kind of maze problem, but the actual path was quite straight, since it was guaranteed
to be a single loop without branches. Finding the enclosed areas was more tricky, since a simple flood-fill
would not work, given the rules of the puzzle. So after another tip I implemented
the [nonzero-rule](https://en.wikipedia.org/wiki/Nonzero-rule).

# Day 11: Cosmic Expansion

It was quite clear, that I only wanted to model the Galaxies, not the space between. Then It was a matter of
updating the coordinates based on the expansion multiplier.

## Day 12: Hot Springs

Part 1 was a nice brute-force approach that fell flat for part 2. So I tried dynamic programming again and probably made
it more complicated than it needs to be. I experimented with just keeping track of the size of the current group
instead of a list of all groups, that would simplify the shenanigans I needed to do in the recursion part.
But the runtime was always too slow, until I finally just slapped some memoization on and reduced the time from
a REDACTED to under a second :)

# Day 13: Point of Incidence

This time, brute force did work, for finding the correct axis as well as permuting all possible smudge locations
for part 2 and finding all the possible reflection axis. The tricky part was to remember, that after cleaning the mirror
more than one axis could be viable.