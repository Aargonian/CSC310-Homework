#!/bin/python3

# Author  : Aaron Helton
# Date    : 01/26/2019
# Class   : CSC 310
# Purpose : This script takes as input from the user a sequence of numbers and
#         : determines if any unique pair in the sequence has an odd product.

# This function does the heavy lifting of figuring out if any two subpairs of 
# the passed sequence are able to have a product which is odd. 
# This algorithm makes clever use of the fact that the only way to multiply two
# numbers and end up with an odd result is to multiply two odd numbers. So 
# instead of manually checking very multiplication, we check for the presence of
# at least two odd numbers! 
#
# This algorithm has a worst case running time of O(n) and a best case of O(1).
def test_nums(number_sequence) -> bool:
    odd_found = False
    for i in number_sequence:
        if i%2 == 1:
            if odd_found:
                return True
            else:
                odd_found = True
    return False

def main():
    print('Hello, welcome to the sequence tester.')
    try:
        while True:
            line = input('Please input numbers separated by spaces: ')

            # Do a nice trick with comprehensions here where we iterate over the
            # split string to create the sequence of numbers. The strip call is
            # used to cull any extra space the user may have tacked on.
            try:
                numbers = [int(x) for x in line.strip().split(' ')]
                print('Are there two numbers whose product is odd? :', 
                      test_nums(numbers))
            except ValueError as err:
                print('Please only input integer numbers.')
    except EOFError as error:
        print('\nLooks like you are done. Goodbye.')

if __name__ == '__main__':
    main()
