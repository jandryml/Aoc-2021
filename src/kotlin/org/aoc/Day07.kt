package org.aoc

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val positions = input.first().split(',').map { it.toInt() }

        var minPrice = Int.MAX_VALUE

        for (i in positions.indices) {
            val price = positions.sumOf { kotlin.math.abs(i - it) }
            if (price < minPrice) minPrice = price
        }
        return minPrice
    }

    fun calc(steps: Double) = ((steps + 1) / 2) * steps

    fun part2(input: List<String>): Double {
        val positions = input.first().split(',').map { it.toInt() }

        var minPrice = Double.MAX_VALUE

        for (i in positions.indices) {
            val price = positions.sumOf { calc(kotlin.math.abs(i.toDouble() - it)) }
            if (price < minPrice) minPrice = price
        }
        return minPrice
    }


    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168.0)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
