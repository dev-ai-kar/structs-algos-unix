/**
 * A program that reads a sequence of words from standard input and prints one of those
 * words uniformly at random. This implementation uses reservoir sampling algorithm to
 * ensure each word has an equal probability of being selected.
 *
 * Compilation: g++ -o build/RandomWord src/0.GettingStarted/RandomWord.cpp
 * Execution: build\RandomWord.exe < data\animals8.txt
 * Get-Content .\data\animals8.txt | .\build\RandomWord.exe 
 */

#include <iostream>
#include <string>
#include <random>

int main() {
    // Champion holds the currently selected word based on reservoir sampling
    std::string champion;
    
    // i represents the count of words processed so far
    int i = 1;
    
    // Create random number generator with a random seed
    std::random_device rd;
    std::mt19937 gen(rd());
    
    std::string word;
    // Process each word from standard input using reservoir sampling
    while (std::cin >> word) {
        // For the i-th word, select it as the new champion with probability 1/i
        std::bernoulli_distribution dist(1.0 / i);
        if (dist(gen)) {
            champion = word;
        }
        i++;
    }
    
    // Output the final selected word
    std::cout << champion << std::endl;
    return 0;
}