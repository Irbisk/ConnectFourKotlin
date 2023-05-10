package connectfour

var firstPlayer = ""
var secondPlayer = ""

var board = Array(6) { Array(7) { ' ' } }
var firstPlayerTurn = true


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
    printBoard()
    move()

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
                board = Array(rows.toInt()) { Array(columns.toInt()) { ' ' } }
                break
            }
        } else println("Invalid input")
    }
}

fun move() {
    var gameIsOver = false

    while (!gameIsOver) {
        var playerName: String
        var ball: Char
        if (firstPlayerTurn) {
            playerName = firstPlayer
            ball = 'o'
        } else {
            playerName = secondPlayer
            ball = '*'
        }

        println("$playerName's turn")

        val column = readln()
        if (column == "end") {
            println("Game over!")
            gameIsOver = true
            continue
        } else if (!column.matches("\\d+".toRegex())) {
            println("Incorrect column number")
        } else if (column.toInt() !in 1..board[0].size ) {
            println("The column number is out of range (1 - ${board[0].size})")
        } else {
            for (cell in board.lastIndex downTo 0) {
                if (board[cell][column.toInt() - 1] == ' ') {
                    board[cell][column.toInt() - 1] = ball
                    printBoard()
                    gameIsOver = checkCondition()
                    if (gameIsOver) {
                        println("Game over!")
                        break
                    }
                    firstPlayerTurn = !firstPlayerTurn
                    break
                } else if (board[cell][column.toInt() - 1] != ' ' && cell == 0) {
                    println("Column $column is full")

                }
            }
        }
    }
}

fun checkCondition(): Boolean {
    var gameIsOver = false
    val ball: Char
    val player: String
    if (firstPlayerTurn) {
        ball = 'o'
        player = firstPlayer
    } else {
        ball = '*'
        player = secondPlayer
    }

    //check rows
    for (row in 0..board.lastIndex) {
        var count = 0
        for (column in 0..board[row].lastIndex) {
            if (board[row][column] == ball) {
                count++
            } else {
                count = 0;
            }
            if (count == 4) {
                println("Player $player won")
                gameIsOver = true
                break
            }
        }
    }

    //check columns
    for (column in 0..board[0].lastIndex) {
        var count = 0
        for (row in 0..board.lastIndex) {
            if (board[row][column] == ball) {
                count++
            } else {
                count = 0;
            }
            if (count == 4) {
                println("Player $player won")
                gameIsOver = true
                break
            }
        }
    }


    // check four diagonal variants
    for (startRow in 0..board.size - 4) {
        var row = startRow
        var column = 0
        var count = 0
        while (row <= board.lastIndex && column <= board[0].lastIndex) {
            if (board[row][column] == ball) {
                count++
            } else {
                count = 0;
            }
            if (count == 4) {
                println("Player $player won")
                gameIsOver = true
                break
            }
            row++
            column++
        }
    }
    for (startColumn in 1..board[0].size - 4) {
        var row = 0
        var column = startColumn
        var count = 0
        while (row <= board.lastIndex && column <= board[0].lastIndex) {
            if (board[row][column] == ball) {
                count++
            } else {
                count = 0;
            }

            if (count == 4) {
                println("Player $player won")
                gameIsOver = true
                break
            }
            row++
            column++
        }
    }
    for (startRow in 0..board.size - 4) {
        var row = startRow
        var column = board[0].lastIndex
        var count = 0
        while (row <= board.lastIndex && column >= 0) {
            if (board[row][column] == ball) {
                count++
            } else {
                count = 0;
            }
            if (count == 4) {
                println("Player $player won")
                gameIsOver = true
                break
            }
            row++
            column--
        }
    }
    for (startColumn in board[0].lastIndex - 1  downTo 0) {
        var row = 0
        var column = startColumn
        var count = 0
        while (row <= board.lastIndex && column >= 0) {
            if (board[row][column] == ball) {
                count++
            } else {
                count = 0;
            }

            if (count == 4) {
                println("Player $player won")
                gameIsOver = true
                break
            }
            row++
            column--
        }
    }
    val countFreeSpaces = board.joinToString ("") { it.joinToString("") }.contains(' ')
    if (!countFreeSpaces) {
        println("It is a draw")
        gameIsOver = true
    }
    return gameIsOver
}



fun printBoard() {
    val vert = "║"
    val hor = "═"
    val triple = "╩"
    val leftAngle = "╚"
    val rightAngle = "╝"

    for (i in 1..board[0].size) print(" $i")
    println()
    for (i in 0..board.lastIndex) {
        for (j in 0..board[0].lastIndex) {
            if (board[i][j] == ' ') {
                print("$vert ")
            } else print("$vert${board[i][j]}")

        }
        println(vert)
    }

    for (i in 0..board[0].lastIndex) {
        if (i == 0) {
            print(leftAngle + hor)
        } else print(triple + hor)
    }
    println(rightAngle)
}