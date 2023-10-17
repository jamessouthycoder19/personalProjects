def print_board(board):
    for i in range(6):
        for j in range(7):
            print(board[i][j],end=' ')
        print()

def check_win():
    ...

def turn(board,player_turn):
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
    while would_like_to_play == 'y':
        player_turn = int(input("Who will be going first? (1 or 2): "))
        while not check_win():
            turn(board,player_turn)
            if player_turn == 1:
                player_turn += 1
            else:
                player_turn -= 1
    



if __name__ == "__main__":
    main()