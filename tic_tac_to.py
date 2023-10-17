def Main():
    print("Player one is X")
    print("Player two is O")
    player_one_wins = 0
    player_two_wins = 0
    thingy = 0
    play_again = "yes"
    while play_again.lower().strip() == "yes":
        thingy = play_game()
        if thingy == 1:
            player_one_wins+=1
        elif thingy == 2:
            player_two_wins+=1
        print(f"\nPlayer One Wins: {player_one_wins}")
        print("Player Two wins:",player_two_wins,"\n")
        play_again = input("Would you like to play again? ")
        print("")
def play_game():
    board = ["1","2","3","4","5","6","7","8","9"]
    turn = int(input("Who will be playing first? (1 or 2) "))
    while turn != 1 and turn != 2:
        turn = int(input("Invalid input, type either 1 or 2"))
    while check_win(board) == 0:
        player_turn(turn,board)
        if check_win(board) == 1:
            print("")
            print("player 1 wins!\n")
            player_board(board)
            return 1
        elif check_win(board) == 2:
            print("")
            print("Player 2 wins!\n")
            player_board(board)
            return 2
        elif check_win(board) == 3:
            print("")
            print("Cat game :(\n")
            player_board(board)
            return 3
        if turn == 1:
            turn +=1
        else:
            turn -=1
def player_turn(who,board):
    print("")
    player_board(board)
    print("")
    user_choice = int(input(f"Player {who} type 1-9 to indicate where to go "))
    while user_choice < 0 or user_choice > 9 or board[user_choice - 1] == "X" or board[user_choice - 1] == "O":
        if user_choice < 0 or user_choice > 9:
            user_choice = int(input("invalid response, please enter a number 1=9 "))
        else:
            user_choice = int(input("Invalid response, spot already taken, please pick another spot "))
    if who == 1:
        board[user_choice - 1] = "X"
    else:
        board[user_choice - 1] = "O"
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
def player_board(board):
    for i in range(3):
        for j in range(3):
            print(board[(i*3 + j)], end=" ")
        print("")
if __name__ == "__main__":
    Main()