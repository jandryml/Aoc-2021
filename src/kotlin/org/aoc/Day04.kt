package org.aoc

import readInput
import java.util.regex.Pattern

fun main() {

    fun part1(input: List<String>): Int {
        val drawnNumbers = input.first().split(',').map { it.toInt() }

        val bingoBoards = input.subList(1, input.size).filterNot { it == "" }.windowed(5, 5).map { BingoBoard(it) }

        drawnNumbers.forEach { drawnNumber ->
            bingoBoards.forEach { board ->
                val result = board.markValue(drawnNumber)
                if (result != -1) {
                    return result
                }
            }
        }
        return 0
    }


    fun part2(input: List<String>): Int {
        val drawnNumbers = input.first().split(',').map { it.toInt() }

        val bingoBoards = input.subList(1, input.size).filterNot { it == "" }.windowed(5, 5).map { BingoBoard(it) }

        var notDoneBoardsCount = bingoBoards.size

        drawnNumbers.forEach { drawnNumber ->
            bingoBoards.forEach { board ->
                if (!board.done) {
                    val result = board.markValue(drawnNumber)
                    if (notDoneBoardsCount == 1 && result != -1) {
                        return result
                    }
                    if (result != -1) {
                        notDoneBoardsCount--
                    }
                }

            }
        }
        return 0
    }


    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}


class BingoBoard(values: List<String>) {
    var board = arrayOf<Array<BingoValue>>()
    var done = false

    init {
        values.forEach { row ->
            var array = arrayOf<BingoValue>()
            row.trim().split(Pattern.compile("\\s+")).forEach { value ->
                array += BingoValue(value.toInt())
            }
            board += array
        }
    }

    fun markValue(input: Int): Int {
        for (j in board.indices) {
            for (i in board[j].indices) {
                if (board[j][i].value == input) {
                    board[j][i].marked = true
                    return validate(j, i, input)
                }
            }
        }
        return -1
    }

    private fun validate(x: Int, y: Int, drawnNumber: Int): Int {
        if (validateColumn(y) || validateRow(x)) {
            done = true
            return sumUnmarkedValues() * drawnNumber
        }
        return -1
    }

    private fun sumUnmarkedValues() =
        board.flatMap { it.toList() }.filter { !it.marked }.sumOf { it.value }


    private fun validateColumn(y: Int): Boolean {
        for (i in board.indices) {
            if (!board[i][y].marked) return false
        }
        return true
    }

    private fun validateRow(x: Int): Boolean {
        for (i in board.indices) {
            if (!board[x][i].marked) return false
        }
        return true
    }

}

data class BingoValue(var value: Int, var marked: Boolean = false) {
    override fun toString(): String {
        return "$value - $marked"
    }
}