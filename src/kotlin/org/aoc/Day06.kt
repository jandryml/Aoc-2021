package org.aoc

import readInput

fun main() {

    fun updatePopulation(parsedValues: MutableList<Int>) {
        parsedValues.addAll(IntArray(parsedValues.filter { it == 0 }.size) { 9 }.toList())
        parsedValues.replaceAll { if (it > 0) it - 1 else 6 }
    }

    fun part1(input: List<String>): Int {
        val days = 80
        val population = input.first().split(',').map { it.toInt() }.toMutableList()
        // println("Init state: ${parsedValues.joinToString(",") { it.toString() }}")

        for (day in 1..days) {
            updatePopulation(population)
            // println("After $day days :  ${parsedValues.joinToString(",") { it.toString() }}")
        }

        return population.size
    }

    fun part2(input: List<String>): Long {
        val days = 256
        var population = input.first().split(',')
            .map { it.toLong() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()

        for (day in 1..days) {
            val newlyBorn = population.getOrDefault(0, 0L)
            population.remove(0)

            val newPopulation = mutableMapOf<Long, Long>()
            for (i in population.keys) {
                newPopulation[i - 1] = population[i]!!
            }
            population = newPopulation

            population[8] = newlyBorn
            population[6] = population.getOrDefault(6, 0) + newlyBorn // add parents back to population
        }

        return population.values.sum()
    }


    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
