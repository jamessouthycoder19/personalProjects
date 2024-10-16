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

func checkWin(board: Array<Array<String>>) -> Bool{
    // This Function is used to check if a player has won the game
    // I might take a minute to think about the most optimal way to do this
}

var board: [Array] = Array(repeating: Array(repeating: "-", count: 3), count: 3)
var player: Int = 1
while(true) {
    printBoard(board: board)
    board = takeTurn(player: player, board: board)
    if player == 1 {
        player = 2
    } else {
        player = 1
    }
}