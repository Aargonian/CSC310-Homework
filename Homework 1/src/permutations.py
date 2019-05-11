#!/bin/python3

# Author  : Aaron Helton
# Date    : 01/26/2019
# Class   : CSC 310
# Purpose : This program takes as input a sequence of numbers and outputs all
#         : possible permutations of those numbers.

# The following code was found on stackoverflow, particularly as the solution.
# Link: https://stackoverflow.com/questions/104420/how-to-generate-all-permutations-of-a-list-in-python
# The code defines a generator function that recursively builds the permutation
# by chopping off the end of the list and appending each possible order to the 
# element you are left with, recursively.
def permutations(sequence) -> None:
    if len(sequence) <= 1:
        yield sequence
    else:
        for permutation in permutations(sequence[1:]):
            for i in range(len(sequence)):
                yield permutation[:i] + sequence[0:1] + permutation[i:]

def main():
    print('Hello, welcome to the permutation lister.')
    try:
        while True:
            line = input('Please input numbers separated by spaces: ')

            # Do a nice trick with comprehensions here where we iterate over the
            # split string to create the sequence of numbers. The strip call is
            # used to cull any extra space the user may have tacked on.
            try:
                numbers = [int(x) for x in line.strip().split(' ')]
                print("Permutations: ")
                for perm in permutations(numbers):
                    print('\t', perm)
            except ValueError as err:
                print('Please only input integer numbers.')
    except EOFError as error:
        print('\nLooks like you are done. Goodbye.')

if __name__ == '__main__':
    main()
