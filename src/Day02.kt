import java.lang.Exception

sealed class Direction {
    data class Forward(val x: Int) : Direction()
    data class Down(val x: Int) : Direction()
    data class Up(val x: Int) : Direction()
}

data class Triple(val horizontal: Int, val vertical: Int, val aim: Int)

fun main() {
    fun part1(directions: List<Direction>): Int {

        val finalPosition = directions.fold(Pair(0, 0)) { acc, direction ->
            when (direction) {
                is Direction.Forward -> Pair(
                    acc.first + direction.x, acc.second
                )
                is Direction.Down -> Pair(acc.first, acc.second + direction.x)
                is Direction.Up
                -> Pair(acc.first, acc.second - direction.x)
                else -> throw Exception("could not understand")
            }
        }
        return finalPosition.first * finalPosition.second

    }

    fun part2(directions: List<Direction>): Int {
        val final = directions.fold(Triple(0, 0, 0)) { acc, currentDirection ->
            when (currentDirection) {
                is Direction.Forward -> Triple(
                    acc.horizontal + currentDirection.x,
                    acc.vertical + acc.aim * currentDirection.x,
                    acc.aim
                )
                is Direction.Down -> Triple(
                    acc.horizontal, acc.vertical, acc.aim + currentDirection.x
                )
                is Direction.Up -> Triple(
                    acc.horizontal, acc.vertical, acc.aim - currentDirection.x
                )
            }
        }
        return final.horizontal * final.vertical
    }

    val input = readInput("Day02")
    val directions = input.map { direction -> direction.split(" ") }
        .map { direction ->
            when (direction[0]) {
                "forward" -> Direction.Forward(direction[1].toInt())
                "down" -> Direction.Down(direction[1].toInt())
                "up" -> Direction.Up(direction[1].toInt())
                else -> throw Exception("could not understand")
            }
        }
    println(part1(directions))
    println(part2(directions))

}