import sys

# To run this script, type the following command in the terminal:
# python src/0.GettingStarted/HelloGoodbye.py Alice Bob

if len(sys.argv) != 3:
    print("Please provide exactly two names as arguments")
    sys.exit(1)

name1 = sys.argv[1]
name2 = sys.argv[2]

print(f"Hello {name1} and {name2}.")
print(f"Goodbye {name2} and {name1}.")
