/*
 * HelloGoodbye.cpp - Prints hello and goodbye messages using command line arguments
 * 
 * Compile: g++ -o build/HelloGoodbye src/0.GettingStarted/HelloGoodbye.cpp
 * Run: build/HelloGoodbye.exe Alice Bob
 * 
 */

#include <iostream>

// Main program that takes two names as command line arguments
int main(int argc, char* argv[]) {
    // Check if exactly two arguments (names) were provided
    if (argc != 3) {
        std::cout << "Please provide exactly two names as arguments\n";
        return 1;  // Return error code if incorrect number of arguments
    }
    
    // Store command line arguments as strings
    std::string name1 = argv[1];  // First name
    std::string name2 = argv[2];  // Second name
    
    // Print greetings with names in original order
    std::cout << "Hello " << name1 << " and " << name2 << ".\n";
    // Print farewell with names in reverse order
    std::cout << "Goodbye " << name2 << " and " << name1 << ".\n";
    
    return 0;  // Successful execution
}
