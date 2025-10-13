"""
This program reads in a sequence of words from standard input and prints one of those words uniformly at random.
It uses reservoir sampling to select the word, which ensures that when the program reads
the nth word, it selects each of the n words seen so far with probability 1/n.
"""

import sys
import random

def main():
    """
    Main function that implements the reservoir sampling algorithm to select a random word.
    Reads words from standard input and prints the selected champion word.
    """
    champion = ""
    i = 1
    
    for line in sys.stdin:
        for word in line.split():
            if random.random() < 1.0 / i:
                champion = word
            i += 1
    
    print(champion)

if __name__ == "__main__":
    main()