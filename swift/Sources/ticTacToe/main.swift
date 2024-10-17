// This project is me teaching myself how to learn some basic Swift

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
    //Ask the user to select a number
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
    I plan to implement both, and then do time comparisons to actually see if one is more optimal
    Even if this isn't actually optimal, it was cool to see how sets/dictionaries worked in swift

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

var board: [Array] = Array(repeating: Array(repeating: "-", count: 3), count: 3)
var player: Int = 1
var numTurns = 0
while(true) {
    printBoard(board: board)
    board = takeTurn(player: player, board: board)
    numTurns += 1
    if numTurns >= 5 {
        if checkWinOptimized(board: board) {
            print("Player \(player) Won!")
            printBoard(board: board)
            break
        }
    }
    if player == 1 {
        player = 2
    } else {
        player = 1
    }
}