import random

def insertion_sort(a_list):
    for i in range(1,len(a_list)):
        for j in range(i):
            if a_list[i]<a_list[j]:
                a_list[i],a_list[j] = a_list[j],a_list[i]
    return a_list

def what_does_the_fox_say(function):
    word = function()
    return "The fox says " + word

def woof():
    return "woof"

def make_tuple(a,b,c):
    return (a,b,c)

def reverse_tuple(sequence):
    new_tuple = ()
    for i in range(len(sequence)-1,-1,-1):
        new_tuple += tuple(sequence[i])
    return new_tuple

def make_trading_card(name,mana_value,power,toughness):
    return (name,mana_value,power,toughness)

def trading_card_value(card):
    return card[2] + card[3]

def nth_list(sequence,n):
    # new_list = []
    # for i in range(0,len(sequence),n):
    #     new_list.append(sequence[i])
    # return new_list
    return [sequence[i] for i in range(0,len(sequence),n)]

def odds_before_evens(a_list):
    for i in range(1,len(a_list)):
        for j in range(i):
            if a_list[j]%2==0 and a_list[i]%2==1:
                a_list[j],a_list[i]=a_list[i],a_list[j]

def splice(a_list,b_list):
    a_list+=b_list[:]

def scramble(a_string):
    new_list = []
    for i in range(len(a_string)):
        new_list.append(a_string[i:i+1])
    for i in range(len(a_string)-1,-1,-1):
        print(new_list[i:i+1],end = " ")

def fizz_buzz_list():
    return [i for i in range(100) if i%3==0 or i%5==0]

def multiples(sequence,n):
    return [sequence[i] for i in range(len(sequence))if i%n==0]

def only_vowels(a_string):
    return [a_string[i] for i in range(len(a_string))if a_string[i]=='a'or a_string[i]=='e'or a_string[i]=='i'or a_string[i]=='o'or a_string[i]=='u']
    
def random_search(a_list,target):
    for _ in range(len(a_list)):
        i = random.randint(0,len(a_list))
        if a_list[i] == target:
            return i
    return None

def equivalent(seq_a,seq_b):
    # naive solution
    # if len(seq_b) != len(seq_a):
    #     return False
    # for i in seq_a:
    #     if i not in seq_b:
    #         return False
    # return True

    set_b = set(seq_b)
    if len(seq_a) != len(seq_b):
        return False   
    for i in seq_a:
        if i not in set_b:
            return False
    return True

def disjoint(set_a,set_b):
    for item in set_a:
        if item in set_b:
            return False
    return True

def union(set_a,set_b):
    union = set()
    for item in set_a:
        if item in set_b:
            union.add(item)
    return union

def random_count(n):
    counts = {}
    while len(counts) != n:
        i = random.randint(0,n)
        if i in counts:
            counts[i] += 1
        else:
            counts[i] = 1
    return counts

def frequencies(counts):
    ...

def cereal_box_decoder(a_string):
    new_string = ""
    split_up = a_string.split(",")
    print(split_up)
    for i in split_up:
        new_string += str(chr(int(i)))
    return new_string

def cereal_box_encoder(a_string):
    new_string = ""
    for i in range(len(a_string)):
        new_string+= str(ord(a_string[i]))
        if i != len(a_string)-1:
            new_string += ","
    return new_string

def find_collisions(str_a,str_b):
    collisions = 0
    for i in str_a:
        for j in str_b:
            if hash(i) == hash(j) and i != j:
                collisions +=1
    return collisions

def hw():
    for k in range(250,304):
        total = 92727 - k**2
        for i in range(173):
            for j in range(100):
                if i**2 + j ** 2 == total:
                    print(i,j,"success")



def main():
    hw()


if __name__ == "__main__":
    main()