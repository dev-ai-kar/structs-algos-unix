"""
To solve the problem of searching in a bitonic array, we need to determine if a given integer is present in an array that first increases and then decreases. The solution involves three main steps: finding the peak element, performing a binary search on the ascending part, and performing a binary search on the descending part.

Approach
Find the Peak Element: Use binary search to locate the peak element in the bitonic array. The peak element is the point where the array transitions from increasing to decreasing.

Binary Search on Ascending Part: Perform a binary search from the start of the array up to the peak element.

Binary Search on Descending Part: Perform a binary search from the peak element to the end of the array.
"""

def search_bitonic(arr, target):
    peak = find_peak(arr)
    return (binary_search(arr, target, 0, peak, True) or
            binary_search(arr, target, peak, len(arr)-1, False))

def find_peak(arr):
    low, high = 0, len(arr)-1
    while low < high:
        mid = (low + high) // 2
        if arr[mid] < arr[mid+1]:
            low = mid + 1
        else:
            high = mid
    return low

def binary_search(arr, target, low, high, ascending):
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] == target:
            return True
        if (ascending and arr[mid] < target) or (not ascending and arr[mid] > target):
            low = mid + 1
        else:
            high = mid - 1
    return False



# Client code example
if __name__ == "__main__":
    # Example bitonic array (first increases, then decreases)
    bitonic_array = [1, 3, 5, 7, 6, 4, 2]
    targets = [5, 7, 2, 8]

    for target in targets:
        result = search_bitonic(bitonic_array, target)
        print(f"Target {target} found: {result}")