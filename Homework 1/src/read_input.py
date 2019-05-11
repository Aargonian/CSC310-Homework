#!/bin/python3

# Author  : Aaron Helton
# Date    : 01/26/2019
# Class   : CSC 310
# Purpose : This program takes as input a series of strings by the user and, 
#         : upon receiving an EOF, prints them in reverse order.

import sys

def main():
    # Read lines from input into the list
    input_list = []
    try:
        while True:
            string = input('Please enter something: ');
            input_list.append(string)
    except EOFError as error:
        print('\nIn Reverse You Entered:')

        # We'll use the built-in reversed function to make reverse traversal 
        # easier :)
        for string in reversed(input_list):
            print(string)

        # Alternatively we could have done the following more verbosely:

        #for i in range(len(input_list)-1, -1, -1):
        #    print(input_list[i])

        # We also could have just called input_list.reverse() before iterating 
        # over the list.
        # Finally, we could do the following:

        #for item in input_list[::-1]:
        #    print(item)

        # The problem with both of those being that they actually make copies 
        # behind the scenes :(

if __name__ == '__main__':
    main()
