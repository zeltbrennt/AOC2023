# AOC2023
AoC 2023 in Kotlin

## Day01 Trebuchet?!
Part 1 was pretty straight forward, but part 2 was a kicker: turns out Regex.matchAll() does not return overlapping matches. So the edge case of
`threeight` would not find `["three", "eight"]`. Got that tip for a workaround from the Subreddit.
