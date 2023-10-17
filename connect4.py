def print_board(board):
    for i in range(6):
        for j in range(7):
            print(board[i][j],end=' ')
        print()

def check_win(board):
    # columns
    for i in range(7):
        for j in range(3):
            if board[j][i] == board[j+1][i] and board[j+1][i] == board[j+2][i] and board[j+2][i] == board[j+3][i] and board[j][i] != "-":
                return True
    # rows
    for i in range(6):
        for j in range(4):
            if board[i][j] == board[i][j+1] and board[i][j+1] == board[i][j+2] and board[i][j+2] == board[i][j+3] and board[i][j] != "-":
                return True
    # diagnols

def turn(board, player_turn):
    print_board(board)
    player_choice = int(input("What column would you like to go in? (1-7): ")) - 1
    coin_placed = False
    for i in range(5,-1,-1):
        if board[i][player_choice] == '-' and player_turn == 1 and coin_placed == False:
            board[i][player_choice] = 'X'
            coin_placed = True
        elif board[i][player_choice] == '-' and player_turn == 2 and coin_placed == False:
            board[i][player_choice] = 'O'
            coin_placed = True
    if not coin_placed:
        print("Column is full")
        turn(board,player_turn)
            

def main():
    board = [['-'for i in range(7)]for j in range(6)]
    player_one_wins = 0
    player_two_wins = 0
    would_like_to_play = input("Would you like to play connect 4? (y/n): ").lower()
    has_won = False
    while would_like_to_play == 'y':
        player_turn = int(input("Who will be going first? (1 or 2): "))
        has_won = False
        while not has_won:
            turn(board, player_turn)
            if check_win(board):
                if player_turn == 1:
                    print("Player 1 wins!")
                    player_one_wins += 1
                else:
                    print("Player 2 wins!")
                    player_two_wins += 1
                print("Player one wins:", player_one_wins)
                print("Player two wins:", player_two_wins)
                would_like_to_play = input("Would you like to play again? (y/n): ").lower()
                has_won = True
                board = [['-' for i in range(7)]for j in range(6)]
            else:
                if player_turn == 1:
                    player_turn += 1
                else:
                    player_turn -= 1
    



if __name__ == "__main__":
    main()