package util

fun gcd(a: Long, b: Long): Long {
    var x = a
    var y = b
    while (y > 0) {
        val temp = y
        y = x % y
        x = temp
    }
    return x
}

fun lcm(a: Long, b: Long) = a * (b / gcd(a, b))