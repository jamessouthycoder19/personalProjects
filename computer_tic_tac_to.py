def Main():
    print("User is X")
    print("Computer is O")
    user_wins = 0
    computer_wins = 0
    cat_games = 0
    thingy = 0
    play_again = "yes"
    while play_again.lower().strip() == "yes":
        thingy = play_game()
        if thingy == 1:
            user_wins+=1
        elif thingy == 2:
            computer_wins+=1
        elif thingy == 3:
            cat_games +=1
        print(f"\nUser Wins: {user_wins}")
        print("Computer Wins:",computer_wins)
        print("Cat games:",cat_games,"\n")
        play_again = input("Would you like to play again? ")
        print("")
def play_game():
    board = ["1","2","3","4","5","6","7","8","9"]
    answer = input("Would you like to go first? ")
    num_turn = 1
    if answer == "yes":
        turn = 1
    else:
        turn = 2
    while check_win(board) == 0:
        if turn == 1:
            player_turn(board)
        else:
            computer_turn(board,num_turn)
        if check_win(board) == 1:
            print("\nUser wins!\n")
            print_board(board)
            return 1
        elif check_win(board) == 2:
            print("\nComputer Wins!\n")
            print_board(board)
            return 2
        elif check_win(board) == 3:
            print("\nCat game :(\n")
            print_board(board)
            return 3
        if turn == 1:
            turn +=1
        else:
            turn -=1
        num_turn +=1
def computer_turn(board,num_turn):
    if num_turn == 1:
        board[0] = "O"
    elif num_turn == 3:
        if board[1] == "X" or board[5] == "X" or board[8] == "X":
            board[6] = "O"
        elif board[3] == "X" or board[7] == "X":
            board[2] = "O"
        elif board[6] == "X" or board [2] == "X" or board[4] == "X":
            board[8] = "O"
    elif num_turn == 5:
        pzazz = check_possible_win(board)
        if pzazz != -1:
            board[pzazz] = "O"
        elif board[6] == "O":
            if board[1] == "X":
                board[8] = "O"
            elif board[8] == "X":
                board[2] = "O"
        elif board[2] == "O":
            board[8] = "O"
    elif num_turn >= 6:
        pzazz = check_possible_win(board)
        if pzazz != -1:
            board[pzazz] = "O"
        else:
            for a in range(9):
                if board[a] != "X" and board[a] != "O":
                    board[a] = "O"
                    break
                
    elif num_turn == 2:
        if board[4] == "X":
            board[0] = "O"
        else:
            board[4] = "O"
    elif num_turn == 4:
        pzazz = check_possible_win(board)
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
def check_possible_win(board):
    count_O = 0
    count_X = 0
    #checks horizontals
    for k in range(3):
        for i in range(3):
            if board[k*3 + i] == "X":
                count_X +=1
            elif board[k*3 + i] == "O":
                count_O +=1
        if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
            for j in range(3):
                if board[k*3 + j] != "X" and board[k*3 + j] != "O":
                    if count_O == 2:
                        return k*3 + j
        count_O = 0
        count_X = 0
    # checks verticals
    for i in range(3):
        for j in range(3):
            if board[j*3 + i] == "X":
                count_X +=1
            elif board[j*3 + i] == "O":
                count_O +=1
        if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
            for k in range(3):
                if board[k*3 + i] != "X" and board[k*3 + i] != "O":
                    if count_O == 2:    
                        return k*3 + i
        count_O = 0
        count_X = 0
    # check l to r diagnal
    for i in [0,4,8]:
        if board[i] == "X":
            count_X +=1
        elif board[i] == "O":
            count_O +=1
    if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
        for i in [0,4,8]:
            if board[i] != "X" and board[i] != "O":
                if count_O == 2:    
                    return i
    count_O = 0
    count_X = 0
    #check r to l diagnal
    for i in [2,4,6]:
        if board[i] == "X":
            count_X +=1
        elif board[i] == "O":
            count_O +=1
    if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
        for i in [2,4,6]:
            if board[i] != "X" and board[i] != "O":
                if count_O == 2:
                    return i
    count_O = 0
    count_X = 0
    #    START OF CHECKING FOR X
    #checks horizontals
    for k in range(3):
        for i in range(3):
            if board[k*3 + i] == "X":
                count_X +=1
            elif board[k*3 + i] == "O":
                count_O +=1
        if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
            for j in range(3):
                if board[k*3 + j] != "X" and board[k*3 + j] != "O":
                    if count_X == 2:
                        return k*3 + j
        count_O = 0
        count_X = 0
    # checks verticals
    for i in range(3):
        for j in range(3):
            if board[j*3 + i] == "X":
                count_X +=1
            elif board[j*3 + i] == "O":
                count_O +=1
        if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
            for k in range(3):
                if board[k*3 + i] != "X" and board[k*3 + i] != "O":
                    if count_X == 2:    
                        return k*3 + i
        count_O = 0
        count_X = 0
    # check l to r diagnal
    for i in [0,4,8]:
        if board[i] == "X":
            count_X +=1
        elif board[i] == "O":
            count_O +=1
    if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
        for i in [0,4,8]:
            if board[i] != "X" and board[i] != "O":
                if count_X == 2:    
                    return i
    count_O = 0
    count_X = 0
    #check r to l diagnal
    for i in [2,4,6]:
        if board[i] == "X":
            count_X +=1
        elif board[i] == "O":
            count_O +=1
    if ((count_O == 2 and count_X == 0) or (count_O == 0 and count_X == 2)):
        for i in [2,4,6]:
            if board[i] != "X" and board[i] != "O":
                if count_X == 2:
                    return i
    count_O = 0
    count_X = 0
    return -1
def player_turn(board):
    print("")
    print_board(board)
    print("")
    user_choice = int(input("Type 1-9 to indicate where to go "))
    while user_choice < 0 or user_choice > 9 or board[user_choice - 1] == "X" or board[user_choice - 1] == "O":
        if user_choice < 0 or user_choice > 9:
            user_choice = int(input("invalid response, please enter a number 1=9 "))
        else:
            user_choice = int(input("Invalid response, spot already taken, please pick another spot "))
    board[user_choice - 1] = "X"
    return board
def check_win(board):
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
def print_board(board):
    for i in range(3):
        for j in range(3):
            print(board[(i*3 + j)], end=" ")
        print("")
        
if __name__ == "__main__":
    Main()