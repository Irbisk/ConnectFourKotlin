package connectfour

var firstPlayer = ""
var secondPlayer = ""

var board = Array(6) {IntArray(7)}

fun main() {
    startGame()
}

fun startGame() {
    println("Connect Four")
    println("First player's name:")
    firstPlayer = readln()
    println("Second player's name:")
    secondPlayer = readln()
    setBoardSize()
    println("$firstPlayer VS $secondPlayer")
    println("${board.size} X ${board[0].size} board")
}

fun setBoardSize() {
    val correctinput = "\\d+\\s*[xX]\\s*\\d+".toRegex()
    while (true) {
        println("Set the board dimensions (Rows x Columns)\n" +
                "Press Enter for default (6 x 7)")
        val input = readln().trim()
        if (input.isEmpty()) {
            break
        } else if (input.matches(correctinput)) {
            val size = input.lowercase().replace("\\s".toRegex(), "").split("x")
            val rows = size.first()
            val columns = size.last()
            if (rows.toInt() !in 5..9) {
                println("Board rows should be from 5 to 9")
            } else if (columns.toInt() !in 5..9) {
                println("Board columns should be from 5 to 9")
            } else {
                board = Array(rows.toInt()) { IntArray(columns.toInt()) }
                break
            }
        } else println("Invalid input")
    }
}