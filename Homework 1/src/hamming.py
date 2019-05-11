#!/bin/python3

# Author  : Aaron Helton
# Date    : 01/26/2019
# Class   : CSC 310
# Purpose : This program calculates the hamming distance of any two integer
#         : numbers up to 32 bits long. The hamming distance is defined as
#         : the number of bits that are different between the two numbers.

def calculate_hamming_distance(num1, num2):
    # We'll assume that both numbers are 32 bit.
    # To make this easy, we just xor the two numbers together. This gives us
    # a convenient number whose 1 bits are all where the two numbers differed
    # bitwise. We can then just count the number of 1s in the new number.
    different_bits = 0
    xored_bits = num1 ^ num2
    start = 0x80000000 # This is 1000 0000 0000 0000 0000 0000 0000 0000
    while start > 0:
        if xored_bits & start > 0:
            different_bits += 1
        start //= 2
    return different_bits

def main():
    print('Hello, welcome to the hamming distance calculator.')
    max_val = 2**32-1
    try:
        while True:
            nums = input('Please input two numbers separated by spaces: ')

            # Do a nice trick with comprehensions here where we iterate over the
            # split string to create the sequence of numbers. The strip call is
            # used to cull any extra space the user may have tacked on.
            try:
                # This time around I used a number of tests to make sure the
                # data is sane, since unlike the other programs we are doing
                # bitwise operations and need to be stricter on values.
                numbers = [int(x) for x in nums.strip().split(' ')]
                numbers = numbers[0:2] # We only care about the first two numbers.
                if len(numbers) != 2:
                    print('Please enter two numbers.')
                    continue
                if numbers[0] > max_val or numbers[1] > max_val or numbers[0] < 0 or numbers[1] < 0:
                        print('Please don\'t enter numbers larger than', max_val, 'or lower than 0!')
                        continue
                print('The hamming distance of', numbers[0], 'and', numbers[1],
                      'is', calculate_hamming_distance(numbers[0], numbers[1]))
            except ValueError as err:
                print('Please only input integer numbers.')
    except EOFError as error:
        print('\nLooks like you are done. Goodbye.')

if __name__ == '__main__':
    main()
