// This project is me teaching myself how to learn some basic Swift
import Foundation

func printBoard(board: Array<Array<String>>) {
    // Print the board for the user to see
    print("\(board[0][0]) | \(board[0][1]) | \(board[0][2])")
    print("--|---|--")
    print("\(board[1][0]) | \(board[1][1]) | \(board[1][2])")
    print("--|---|--")
    print("\(board[2][0]) | \(board[2][1]) | \(board[2][2])")
}

func takeTurn(player: Int, board: Array<Array<String>>) -> Array<Array<String>>{
    // This function is used for one person to take a turn. They will choose a spot, the board is updated
    // And then control is passed back to the function that called this function.
    var newBoard = board
    var sentinel: Bool = true
    while(sentinel){
    // Ask the user to select a number
        print("Player \(player) Select a spot 1-9: ")
        if let choice = readLine(), var number = Int(choice) {
            number = number - 1
            let row: Int = number / 3
            let col: Int = number % 3
            // Players should only be allowed to place a tile on a spot that is "empty", aka a -
            if(newBoard[row][col] == "-"){
                sentinel = false
                if(player == 1){
                    newBoard[row][col] = "X"
                } else {
                    newBoard[row][col] = "O"
                }
            } else {
                print("Invalid selection, spot is already taken")
            }
        } 
    }
    return newBoard
}

func checkWinOptimized(board: Array<Array<String>>) -> Bool{
    /**
    This is potentially optimized to just a brute force solve (i.e. checking all 8 possible win conditions).

    As it turns out, this way isn't actually optimal, compared to both a brute force, and an in between version.

    To optimize checking if a user has one, imagine all of the spots on the board layed out like this
    1 2 3                     Win condition (1,2,3) = A
    4 5 6                     Win condition (4,5,6) = B
    7 8 9                     Win condition (1,4,7) = D
                              Win condition (1,5,9) = G

    We will check squares 2,4,5,6, and 8, as at least one of these is needed for a possible win. 
    If that square is not occupied, then all of the win conditions associated with that square are now eliminated.
    Example Square 2 is empty = Win Condition A and E are not possible anyomre. Any win conditions left after 
    this will be checked.
    **/
    var winConditionsLeft: Set<Character> = ["A","B","C","D","E","F","G","H"]
    let squaresToWinConditions: [Int: Array<Character>] = [2: ["A", "E"], 4: ["B","D"], 5: ["B","E","G","H"], 6: ["B","F"], 8: ["C","E"]]
    let winConditionDefinitions: [Character: Array<Int>] = ["A": [1,2,3], "B": [4,5,6], "C": [7,8,9], "D": [1,4,7], "E": [2,5,8], "F": [3,6,9], "G": [1,5,9], "H":[3,5,7]]
    let squaresToCheck: Array<Int> = [2,4,5,6,8]
    for square in squaresToCheck {
        let squareForMath = square - 1
        let row: Int = squareForMath / 3
        let col: Int = squareForMath % 3
        if board[row][col] == "-" {
            for winConditionToRemove in (squaresToWinConditions[square, default: []]) {
                winConditionsLeft.remove(winConditionToRemove)
            }
        }
    }
    for winCondition in winConditionsLeft {
        let spaces = winConditionDefinitions[winCondition, default: []]
        let (a,b,c) = (spaces[0] - 1, spaces [1] - 1, spaces[2] - 1)
        if board[a/3][a%3] == board[b/3][b%3] && board[b/3][b%3] == board[c/3][c%3] {
            return true
        }
    }
    return false
}

func checkWinBruteForce(board: Array<Array<String>>) -> Bool{
    // This just checks all 8 possible win conditions, one by one

    let board: Array<Array<String>> = board
    // This Function will just go through all 8 possible win scenarios to determine if there is a winner
    if board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] != "-" {
        // XXX
        // ---
        // ---
        return true
    } else if board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] != "-" {
        // ---
        // xxx
        // ---
        return true
    } else if board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] != "-" {
        // ---
        // ---
        // XXX
        return true
    } else if board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] != "-" {
        // X--
        // X--
        // X--
        return true
    } else if board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] != "-" {
        // -X-
        // -X-
        // -X-
        return true
    } else if board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] != "-" {
        // --X
        // --X
        // --X
        return true
    } else if board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != "-" {
        // X--
        // -X-
        // --X
        return true
    } else if board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != "-" {
        // --X
        // -X-
        // X--
        return true
    } else {
        return false
    }
}

func checkWinHalfOptimized(board: Array<Array<String>>) -> Bool{
    let board: Array<Array<String>> = board
    // This function will check squares 2, 4, 5, 6, and 8
    // 1 2 3
    // 4 5 6         If any of these squares are not empty (not equal to '-')
    // 7 8 9         then win conditions associated with that square are checked

    // This way of checking is actually the most optimal, compared to both the brute force,
    // and over optimized algorithms

    if board[0][1] != "-" {
        if board[0][0] == board[0][1] && board[0][1] == board[0][2] {
            // XXX
            // ---
            // ---
            return true
        } else if  board[0][1] == board[1][1] && board [1][1] == board[2][1] {
            // -X-
            // -X-
            // -X-
            return true
        }
    }

    if board[1][0] != "-" {
        if board[1][0] == board[1][1] &&  board[1][1] == board[1][2] {
            // ---
            // XXX
            // ---
            return true
        } else if board[0][0] == board[1][0] && board[1][0] == board[2][0] {
            // X--
            // X--
            // X--
            return true
        }
    }

    if board[1][1] != "-" {
        if board[0][0] == board[1][1] && board[1][1] == board[2][2] {
            // X--
            // -X-
            // --X
            return true
        } else if board[0][2] == board[1][1] && board[1][1] == board[2][0] {
            // --X
            // -X-
            // X--
            return true
        }
    }

    if board[1][2] != "-" {
        if board[0][2] == board[1][2] && board[1][2] == board[2][2] {
            // --X
            // --X
            // --X
            return true
        }
    }

    if board[2][1] != "-" {
        if board[2][0] == board[2][1] && board[2][1] == board[2][2] {
            // ---
            // ---
            // XXX
            return true
        }
    }

    return false
    
}

func generateBoards(board: [[String]], row: Int, col: Int) -> [[[String]]] {
    // Function to build all possible board cases to test time complexity
    
    let symbols = ["X", "O", "-"]

    var boards = [[[String]]]()
    
    if row == 3 {
        boards.append(board)
        return boards
    }
    
    var nextRow = row
    var nextCol = col + 1
    if nextCol == 3 {
        nextRow += 1
        nextCol = 0
    }
    
    for symbol in symbols {
        var newBoard = board
        newBoard[row][col] = symbol
        boards.append(contentsOf: generateBoards(board: newBoard, row: nextRow, col: nextCol))
    }
    
    return boards
}

func filterBoards(boards: [[[String]]], minMoveCount: Int) -> [[[String]]] {
    // This function takes the return from generateBoards, and filters it down to only boards
    // that have at at least n number of moves, where n is the minMoveCount param.

    return boards.filter { board in
        let count = board.flatMap { $0 }.filter { $0 != "" }.count
        return count >= minMoveCount
    }
}

func testTimeComplexityOfAlgos() -> Array<Int>{
    // This function will test all 3 win condition functions, to determine the mean time of each of them

    // Generate all possible boards
    let initialBoard = Array(repeating: Array(repeating: "-", count:  3), count:  3)
    let allBoards = generateBoards(board: initialBoard, row: 0, col: 0)

    // Filter this down to only boards with 5 or more moves
    let relevantBoards = filterBoards(boards: allBoards, minMoveCount: 5)

    let numBoards = relevantBoards.count
    var startTime: DispatchTime
    var endTime: DispatchTime
    var differenceInTime: UInt64
    var miliseconds: Int
    var totalTime: Int
    var averageTime: Int

    var returnArray: Array<Int> = [0, 0, 0]

    // Find average time to complete checkWinBruteForce
    totalTime = 0
    for board in relevantBoards {
        startTime = DispatchTime.now()
        _ = checkWinBruteForce(board: board)
        endTime = DispatchTime.now()
        differenceInTime = endTime.uptimeNanoseconds - startTime.uptimeNanoseconds
        miliseconds = Int(differenceInTime)
        totalTime += miliseconds
    }
    averageTime = totalTime / numBoards
    returnArray[0] = averageTime


    // Find average time to complete checkWinHalfOptimized
    totalTime = 0
    for board in relevantBoards {
        startTime = DispatchTime.now()
        _ = checkWinHalfOptimized(board: board)
        endTime = DispatchTime.now()
        differenceInTime = endTime.uptimeNanoseconds - startTime.uptimeNanoseconds
        miliseconds = Int(differenceInTime)
        totalTime += miliseconds
    }
    averageTime = totalTime / numBoards
    returnArray[1] = averageTime


    // Find average time to complete checkWinOptimized
    totalTime = 0
    for board in relevantBoards {
        startTime = DispatchTime.now()
        _ = checkWinOptimized(board: board)
        endTime = DispatchTime.now()
        differenceInTime = endTime.uptimeNanoseconds - startTime.uptimeNanoseconds
        miliseconds = Int(differenceInTime)
        totalTime += miliseconds
    }
    averageTime = totalTime / numBoards
    returnArray[2] = averageTime

    return returnArray
    
}

func testTimeComplexityMultipleTimes(n: Int) {
    // Due to uncontrollable events, the mean times of the algorithms are not going to be the exact same every time.
    // To counteract this, we can do multiple tests, and find the man. The more tests we do, the closer the result
    // will be to the 'true' value

    var totalBruteForceTime: Int = 0
    var totalHalfOptimizedTime: Int = 0
    var totalOptimizedTime: Int = 0
    var returnedArray: Array<Int>

    for _ in 1...n {
        returnedArray = testTimeComplexityOfAlgos()
        totalBruteForceTime += returnedArray[0]
        totalHalfOptimizedTime += returnedArray[1]
        totalOptimizedTime += returnedArray[2]
    }

    print("\nBrute Force Mean Time: \(totalBruteForceTime / n) Nanoseconds")
    print("Half Optimized Mean Time: \(totalHalfOptimizedTime / n) Nanoseconds")
    print("Optimized Mean Time: \(totalOptimizedTime / n) Nanoseconds")
}

func playTicTacToe() {
    //Let user know how to make their selection
    print("\nEnter your selection in the following manner\n")
    print("1 | 2 | 3")
    print("--|---|--")
    print("4 | 5 | 6")
    print("--|---|--")
    print("7 | 8 | 9\n\n")

    // Create Blank board
    var board: [Array] = Array(repeating: Array(repeating: "-", count: 3), count: 3)
    var player: Int = 1
    var numTurns = 0

    // Loop for the game
    while(true) {
        // Print current state of board
        printBoard(board: board)

        // take a turn, and replace old board with updated board
        board = takeTurn(player: player, board: board)
        numTurns += 1

        // Only check if there is a win once 5 turns have been taken,
        // there are no win conditions that exist before turn 5
        if numTurns >= 5 {
            if checkWinHalfOptimized(board: board) {
                print("Player \(player) Won!")
                printBoard(board: board)
                break
            }
        }

        // Switch player for next turn
        if player == 1 {
            player = 2
        } else {
            player = 1
        }
    }
}

print("Would you like to: ")
print("1. Play Tic-Tac-Toe")
print("2. See the Difference in Time of Algorithms")
if let choice = readLine(), var number = Int(choice) {
    if(number == 1){
        playTicTacToe()
    } else {
        print("\nHow many iterations of the mean would you like to analyze?")
        if let choice = readLine(), var number = Int(choice) {
            testTimeComplexityMultipleTimes(n: number)
        }
    }
}