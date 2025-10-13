# Data Structures and Algorithms

## Running RandomWord Program

### Building the Programs

```powershell
# Java
javac -cp ".;lib/algs4.jar" src/0.GettingStarted/RandomWord.java -d build

# C++
g++ -o build/RandomWord src/0.GettingStarted/RandomWord.cpp

# Python
# No compilation needed
```

### Running Methods

#### 1. Interactive Input

```powershell
# Java
java -cp ".;build;lib/algs4.jar" RandomWord

# C++
build\RandomWord.exe

# Python
python src/0.GettingStarted/RandomWord.py
```

Enter words separated by spaces. To end input:
- Windows: Press `Ctrl+Z` then `Enter`
- Unix/Mac: Press `Ctrl+D`

Example session:
```
heads tails
tails
```

#### 2. File Input

PowerShell:
```powershell
# Java
Get-Content .\data\animals8.txt | java -cp ".;build;lib/algs4.jar" RandomWord

# C++
Get-Content .\data\animals8.txt | .\build\RandomWord.exe

# Python
Get-Content .\data\animals8.txt | python src/0.GettingStarted/RandomWord.py
```

Command Prompt:
```cmd
# Java
java -cp ".;build;lib/algs4.jar" RandomWord < data/animals8.txt

# C++
build\RandomWord.exe < data/animals8.txt

# Python
python src/0.GettingStarted/RandomWord.py < data/animals8.txt
```

Example output:
```
bear
```

> All versions implement the reservoir sampling algorithm to randomly select one word from the input.

## Running HelloGoodbye Program

#### Command Line Arguments

This program requires command line arguments:

```powershell
# Java
javac -cp ".;lib/algs4.jar" src/0.GettingStarted/HelloGoodbye.java -d build
java -cp ".;build;lib/algs4.jar" HelloGoodbye Alice Bob

# C++
build\HelloGoodbye.exe Alice Bob
g++ -o build/HelloGoodbye src/0.GettingStarted/HelloGoodbye.cpp

# Python
# No compilation needed
python src/0.GettingStarted/HelloGoodbye.py Alice Bob
```