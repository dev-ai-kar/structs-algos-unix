# Welcome to Data Structures & Algorithms 

Mastering algorithms one step at a time with Java ☕, Python 🐍, and C++ 🔧 using Visual Studio Code 💻.

## Folder Structure

The workspace contains the following folders:

- `src`: Source code organized by topics
- `lib`: External dependencies (including algs4.jar)
- `build`: Compiled output files
- `data`: Input data files for examples

## Running Computer Programs

Here's a general guide for running programs in this repository using RandomWord as an example:

### Compiling
```powershell
# Java
javac -cp ".;lib/algs4.jar" src/<path-to-file>/<filename>.java -d build

# C++
g++ -o build/<ProgramName> src/<path-to-file>/<filename>.cpp

# Python
# No compilation needed
```

### Running
You can run programs in three ways:

1. **Interactive Input:**
```powershell
# Java
java -cp ".;build;lib/algs4.jar" <ClassName>

# C++
build\<ProgramName>.exe

# Python
python src/<path-to-file>/<filename>.py
```

2. **File Input:**
```powershell
# PowerShell
Get-Content .\data\<inputfile>.txt | java -cp ".;build;lib/algs4.jar" <ClassName>
Get-Content .\data\<inputfile>.txt | .\build\<ProgramName>.exe
Get-Content .\data\<inputfile>.txt | python src/<path-to-file>/<filename>.py

# Command Prompt
java -cp ".;build;lib/algs4.jar" <ClassName> < data/<inputfile>.txt
build\<ProgramName>.exe < data/<inputfile>.txt
python src/<path-to-file>/<filename>.py < data/<inputfile>.txt
```

3. **Command Line Arguments:**
```powershell
# Java
java -cp ".;build;lib/algs4.jar" <ClassName> arg1 arg2

# C++
build\<ProgramName>.exe arg1 arg2

# Python
python src/<path-to-file>/<filename>.py arg1 arg2
```

## Dependency Management

The `JAVA PROJECTS` view in VS Code allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
