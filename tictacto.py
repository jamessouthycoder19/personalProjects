def Main():
    print("Player one is X")
    print("Player two is O")
    pOneWins = 0
    pTwoWins = 0
    thingy = 0
    playAgain = "yes"
    while playAgain.lower().strip() == "yes":
        thingy = playGame()
        if thingy == 1:
            pOneWins+=1
        elif thingy == 2:
            pTwoWins+=1
        print(f"\nPlayer One Wins: {pOneWins}")
        print("Player Two wins:",pTwoWins,"\n")
        playAgain = input("Would you like to play again? ")
        print("")
def playGame():
    board = ["1","2","3","4","5","6","7","8","9"]
    turn = int(input("Who will be playing first? (1 or 2) "))
    while turn != 1 and turn != 2:
        turn = int(input("Invalid input, type either 1 or 2"))
    while checkWin(board) == 0:
        playerTurn(turn,board)
        if checkWin(board) == 1:
            print("")
            print("player 1 wins!\n")
            printBoard(board)
            return 1
        elif checkWin(board) == 2:
            print("")
            print("Player 2 wins!\n")
            printBoard(board)
            return 2
        elif checkWin(board) == 3:
            print("")
            print("Cat game :(\n")
            printBoard(board)
            return 3
        if turn == 1:
            turn +=1
        else:
            turn -=1
def playerTurn(who,board):
    print("")
    printBoard(board)
    print("")
    userChoice = int(input(f"Player {who} type 1-9 to indicate where to go "))
    while userChoice < 0 or userChoice > 9 or board[userChoice - 1] == "X" or board[userChoice - 1] == "O":
        if userChoice < 0 or userChoice > 9:
            userChoice = int(input("invalid response, please enter a number 1=9 "))
        else:
            userChoice = int(input("Invalid response, spot already taken, please pick another spot "))
    if who == 1:
        board[userChoice - 1] = "X"
    else:
        board[userChoice - 1] = "O"
    return board
def checkWin(board):
    for i in range(3):
        if board[i*3] == board[i*3+1] and board[i*3+1] == board[i*3+2]:
            if board[i*3] == "X":
                return 1
            else:
                return 2
        if board[0+i] == board[3+i] and board[3+i] == board[6+i]:
            if board[0+i] == "X":
                return 1
            else:
                return 2
    #diagnals
    if board[0] == board[4] and board[4] == board[8]:
        if board[0] == "X":
            return 1
        else:
            return 2
    elif board[2] == board[4] and board[4] == board[6]:
        if board[0] == "X":
            return 1
        else:
            return 2
    counter = 0
    for i in range(3):
        for j in range(3):
            if board[i*3+j] == "X" or board[i*3+j] == "O":
                counter +=1
    if counter == 9:
        return 3
    return 0
def printBoard(board):
    for i in range(3):
        for j in range(3):
            print(board[(i*3 + j)], end=" ")
        print("")
Main()