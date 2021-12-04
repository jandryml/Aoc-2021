package org.aoc

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizon = 0

        input.forEach {
            val parsed = it.split(' ')
            when (parsed[0]) {
                "forward" -> horizon += parsed[1].toInt()
                "down" -> depth += parsed[1].toInt()
                "up" -> depth -= parsed[1].toInt()
                else -> println("invalid ${parsed[0]}")
            }
        }
        return depth * horizon
    }


    fun part2(input: List<String>): Int {
        var depth = 0
        var horizon = 0
        var aim = 0

        input.forEach {
            val parsed = it.split(' ')
            val value = parsed[1].toInt()
            when (parsed[0]) {
                "forward" -> {
                    horizon += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
                else -> println("invalid command ${parsed[0]}")
            }
        }
        return depth * horizon
    }


    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
