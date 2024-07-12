import random

def check_guess(guess):
    ...
    

def main():
    words = ["happy","click","fleet","dirty","alive","flick","think","mouse","sling","thing","lorax"]
    correct_word = words[random.randrange(len(words))]
    correct_word = "doron"
    guesses_left = 6
    user_game_status = "_____"
    print(correct_word)
    while guesses_left > 0 and user_game_status != correct_word:
        print(user_game_status)
        user_guess = input("Enter Guess: ")
        if len(user_guess) < 5:
            print("Error: Guess too short")
        elif len(user_guess) > 5:
            print("Error: Guess too long")
        else:
            new_str = ""
            user_guess = user_guess.lower()
            guesses_left = guesses_left - 1
            # Iterate through each range in the user's guess
            for i in range(5):
                # If the letter is the same as the correct letter, then reveal the letter is correct
                if user_guess[i] == correct_word[i]:
                    new_str += user_guess[i]
                # If the letter exists in the word, then go through all of the different possibilities 
                elif user_guess[i] in correct_word:
                    # If there are the same or more of the letter in the correct word compared to the user's guess,
                    # then mark the letter as existing somewhere else in the word
                    if correct_word.count(user_guess[i]) >= user_guess.count(user_guess[i]):
                        new_str += "-"
                    # If there is only one instance of the letter in the user's guess, then check if it is in the 
                    # same spot as the first instance of the letter in the correct 
                    # Something in the below logic is broken
                    elif user_guess.count(user_guess[i]) == 1:
                        if user_guess.index(user_guess[i]) == i:
                            new_str += "-"
                        else:
                            new_str += "_"
                    elif user_guess.count(user_guess[i]) == 2:
                        if user_guess.index(user_guess[i]) == i or user_guess[user_guess.index(user_guess[i]):4].index(user_guess[i]) == i:
                            new_str += "-"
                        else:
                            new_str += "_"
                    elif user_guess.count(user_guess[i]) == 3:
                        ...
                    else: 
                        new_str += "_"
                else: 
                    new_str += "_"
            user_game_status = new_str
                

        

if __name__ == "__main__":
    main()