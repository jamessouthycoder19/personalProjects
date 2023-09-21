def Main():
    print("User is X")
    print("Computer is O")
    userWins = 0
    computerWins = 0
    catGames = 0
    thingy = 0
    playAgain = "yes"
    while playAgain.lower().strip() == "yes":
        thingy = playGame()
        if thingy == 1:
            userWins+=1
        elif thingy == 2:
            computerWins+=1
        elif thingy == 3:
            catGames +=1
        print(f"\nUser Wins: {userWins}")
        print("Computer Wins:",computerWins)
        print("Cat games:",catGames,"\n")
        playAgain = input("Would you like to play again? ")
        print("")
def playGame():
    board = ["1","2","3","4","5","6","7","8","9"]
    answer = input("Would you like to go first? ")
    numTurn = 1
    if answer == "yes":
        turn = 1
    else:
        turn = 2
    while checkWin(board) == 0:
        if turn == 1:
            playerTurn(board)
        else:
            computerTurn(board,numTurn)
        if checkWin(board) == 1:
            print("\nUser wins!\n")
            printBoard(board)
            return 1
        elif checkWin(board) == 2:
            print("\nComputer Wins!\n")
            printBoard(board)
            return 2
        elif checkWin(board) == 3:
            print("\nCat game :(\n")
            printBoard(board)
            return 3
        if turn == 1:
            turn +=1
        else:
            turn -=1
        numTurn +=1
def computerTurn(board,numTurn):
    if numTurn == 1:
        board[0] = "O"
    elif numTurn == 3:
        if board[1] == "X" or board[5] == "X" or board[8] == "X":
            board[6] = "O"
        elif board[3] == "X" or board[7] == "X":
            board[2] = "O"
        elif board[6] == "X" or board [2] == "X" or board[4] == "X":
            board[8] = "O"
    elif numTurn == 5:
        pzazz = checkPossibleWin(board)
        if pzazz != -1:
            board[pzazz] = "O"
        elif board[6] == "O":
            if board[1] == "X":
                board[8] = "O"
            elif board[8] == "X":
                board[2] = "O"
        elif board[2] == "O":
            board[8] = "O"
    elif numTurn >= 6:
        pzazz = checkPossibleWin(board)
        if pzazz != -1:
            board[pzazz] = "O"
        else:
            for a in range(9):
                if board[a] != "X" and board[a] != "O":
                    board[a] = "O"
                    break
                
    elif numTurn == 2:
        if board[4] == "X":
            board[0] = "O"
        else:
            board[4] = "O"
    elif numTurn == 4:
        pzazz = checkPossibleWin(board)
        if pzazz != -1:
            board[pzazz] = "O"
        # 2 corners
        elif board[4] == "X" and board[8] == "X":
            board[6] = "O"
        elif ((board[0] == "X" and board[8] == "X") or (board[6] == "X" or board[2] == "X")):
            board[1] = "O"
        # all top edge possibilities
        elif board[1] == "X":
            if board[3] == "X" or board[6] == "X" or board[7] == "X":
                board[0] = "O"
            elif board[5] == "X" or board[8] == "X":
                board[2] = "O"
        # all right edge possibilities
        elif board[5] == "X":
            if board[6] == "X" or board[3] == "X" or board[7] == "X":
                board[8] = "O"
            elif board[0] == "X":
                board[2] = "O"
        # all left edge possibliites
        elif board[3] == "X":
            if board[7] == "X" or board[8]== "X" or board[5]== "X":
                board[6] = "O"
            elif board[2] == "X":
                board[0] = "O"
        # all bottom edge possiblities
        elif board[7] == "X":
            if board[0] == "X":
                board[6] = "O"
            elif board[2] == "X":
                board[8] = "O"
def checkPossibleWin(board):
    countO = 0
    countX = 0
    #checks horizontals
    for k in range(3):
        for i in range(3):
            if board[k*3 + i] == "X":
                countX +=1
            elif board[k*3 + i] == "O":
                countO +=1
        if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
            for j in range(3):
                if board[k*3 + j] != "X" and board[k*3 + j] != "O":
                    if countO == 2:
                        return k*3 + j
        countO = 0
        countX = 0
    # checks verticals
    for i in range(3):
        for j in range(3):
            if board[j*3 + i] == "X":
                countX +=1
            elif board[j*3 + i] == "O":
                countO +=1
        if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
            for k in range(3):
                if board[k*3 + i] != "X" and board[k*3 + i] != "O":
                    if countO == 2:    
                        return k*3 + i
        countO = 0
        countX = 0
    # check l to r diagnal
    for i in [0,4,8]:
        if board[i] == "X":
            countX +=1
        elif board[i] == "O":
            countO +=1
    if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
        for i in [0,4,8]:
            if board[i] != "X" and board[i] != "O":
                if countO == 2:    
                    return i
    countO = 0
    countX = 0
    #check r to l diagnal
    for i in [2,4,6]:
        if board[i] == "X":
            countX +=1
        elif board[i] == "O":
            countO +=1
    if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
        for i in [2,4,6]:
            if board[i] != "X" and board[i] != "O":
                if countO == 2:
                    return i
    countO = 0
    countX = 0
    #    START OF CHECKING FOR X
    #checks horizontals
    for k in range(3):
        for i in range(3):
            if board[k*3 + i] == "X":
                countX +=1
            elif board[k*3 + i] == "O":
                countO +=1
        if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
            for j in range(3):
                if board[k*3 + j] != "X" and board[k*3 + j] != "O":
                    if countX == 2:
                        return k*3 + j
        countO = 0
        countX = 0
    # checks verticals
    for i in range(3):
        for j in range(3):
            if board[j*3 + i] == "X":
                countX +=1
            elif board[j*3 + i] == "O":
                countO +=1
        if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
            for k in range(3):
                if board[k*3 + i] != "X" and board[k*3 + i] != "O":
                    if countX == 2:    
                        return k*3 + i
        countO = 0
        countX = 0
    # check l to r diagnal
    for i in [0,4,8]:
        if board[i] == "X":
            countX +=1
        elif board[i] == "O":
            countO +=1
    if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
        for i in [0,4,8]:
            if board[i] != "X" and board[i] != "O":
                if countX == 2:    
                    return i
    countO = 0
    countX = 0
    #check r to l diagnal
    for i in [2,4,6]:
        if board[i] == "X":
            countX +=1
        elif board[i] == "O":
            countO +=1
    if ((countO == 2 and countX == 0) or (countO == 0 and countX == 2)):
        for i in [2,4,6]:
            if board[i] != "X" and board[i] != "O":
                if countX == 2:
                    return i
    countO = 0
    countX = 0
    return -1
def playerTurn(board):
    print("")
    printBoard(board)
    print("")
    userChoice = int(input("Type 1-9 to indicate where to go "))
    while userChoice < 0 or userChoice > 9 or board[userChoice - 1] == "X" or board[userChoice - 1] == "O":
        if userChoice < 0 or userChoice > 9:
            userChoice = int(input("invalid response, please enter a number 1=9 "))
        else:
            userChoice = int(input("Invalid response, spot already taken, please pick another spot "))
    board[userChoice - 1] = "X"
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
        if board[2] == "X":
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