"""
To solve the problem of searching in a bitonic array with a target using approximately 
2logn comparisons, we can use an optimized approach that combines two binary searches. 
This approach dynamically adjusts the search range based on whether the current segment 
is increasing or decreasing, avoiding the need to first find the peak explicitly.

Approach
Binary Search on Increasing Part: 
    Start with a binary search from the beginning of the array. 
    If the current mid-point is in the increasing part, 
    adjust the search range accordingly. 
    If it's in the decreasing part, adjust to continue 
    searching the increasing part.

Binary Search on Decreasing Part: 
    If the target isn't found in the increasing part, 
    perform another binary search from the end of the array. 
    If the current mid-point is in the decreasing part, adjust 
    the search range accordingly. If it's in the increasing part, 
    adjust to continue searching the decreasing part.

This approach ensures that each binary search 
(one for the increasing part and one for the decreasing part) 
takes logn steps, leading to a total of 2logn comparisons.
"""
def search_bitonic(arr, target):
    # Search in the increasing part (left to peak)
    low, high = 0, len(arr) - 1
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] == target:
            return True
        # Check if we are in the increasing portion
        if mid < len(arr) - 1 and arr[mid] < arr[mid + 1]:
            if arr[mid] < target:
                low = mid + 1
            else:
                high = mid - 1
        else:
            # Move towards the increasing part
            high = mid - 1
    
    # Search in the decreasing part (peak to right)
    low, high = 0, len(arr) - 1
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] == target:
            return True
        # Check if we are in the decreasing portion
        if mid > 0 and arr[mid - 1] > arr[mid]:
            if arr[mid] < target:
                high = mid - 1
            else:
                low = mid + 1
        else:
            # Move towards the decreasing part
            low = mid + 1
    
    return False



# Client code example
if __name__ == "__main__":
    # Example bitonic array (first increases, then decreases)
    bitonic_array = [1, 3, 5, 7, 6, 4, 2]
    targets = [5, 7, 2, 8]

    for target in targets:
        result = search_bitonic(bitonic_array, target)
        print(f"Target {target} found: {result}")