import matplotlib.pyplot as plt
import numpy as np

def visualize_solutions(arr, target):
    # Create figure
    fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(14, 6))
    fig.suptitle(f"Searching for {target} in Bitonic Array", fontsize=16)
    
    # Plot array
    x = np.arange(len(arr))
    for ax in (ax1, ax2):
        ax.plot(x, arr, 'b-o', label='Bitonic Array')
        ax.axhline(target, color='g', linestyle='--', label='Target')
        ax.set_xlabel('Index')
        ax.set_ylabel('Value')
        ax.legend()
    
    # Track comparison steps
    standard_steps = []
    bonus_steps = []
    
    # --- Standard Solution (3 log n) ---
    # Find peak steps
    def track_peak():
        low, high = 0, len(arr)-1
        while low < high:
            mid = (low + high) // 2
            standard_steps.append(mid)
            if arr[mid] < arr[mid+1]:
                low = mid + 1
            else:
                high = mid
        return low
    
    peak = track_peak()
    ax1.plot(peak, arr[peak], 'ro', markersize=10, label='Peak')
    
    # Binary search steps (ascending)
    def track_bs_asc():
        low, high = 0, peak
        while low <= high:
            mid = (low + high) // 2
            standard_steps.append(mid)
            if arr[mid] == target:
                return
            if arr[mid] < target:
                low = mid + 1
            else:
                high = mid - 1
    track_bs_asc()
    
    # Binary search steps (descending)
    def track_bs_desc():
        low, high = peak, len(arr)-1
        while low <= high:
            mid = (low + high) // 2
            standard_steps.append(mid)
            if arr[mid] == target:
                return
            if arr[mid] > target:
                low = mid + 1
            else:
                high = mid - 1
    track_bs_desc()
    
    # Plot standard solution steps
    ax1.set_title(f"Standard Solution ({len(standard_steps)} comparisons)")
    for i, step in enumerate(standard_steps):
        ax1.plot(step, arr[step], 'rx' if i==0 else 'mx', markersize=10, 
                markeredgewidth=2, label='Step' if i==0 else None)
    
    # --- Bonus Solution (2 log n) ---
    def track_bonus_search():
        # First pass (ascending)
        low, high = 0, len(arr)-1
        steps = []
        while low <= high:
            mid = (low + high) // 2
            steps.append(mid)
            if arr[mid] == target:
                break
            if mid < len(arr)-1 and arr[mid] < arr[mid+1]:
                if arr[mid] < target:
                    low = mid + 1
                else:
                    high = mid - 1
            else:
                high = mid - 1
        
        # Second pass (descending)
        low, high = 0, len(arr)-1
        while low <= high:
            mid = (low + high) // 2
            steps.append(mid)
            if arr[mid] == target:
                break
            if mid > 0 and arr[mid-1] > arr[mid]:
                if arr[mid] < target:
                    high = mid - 1
                else:
                    low = mid + 1
            else:
                low = mid + 1
        return steps
    
    bonus_steps = track_bonus_search()
    ax2.set_title(f"Bonus Solution ({len(bonus_steps)} comparisons)")
    for i, step in enumerate(bonus_steps):
        ax2.plot(step, arr[step], 'rx' if i==0 else 'mx', markersize=10,
                markeredgewidth=2, label='Step' if i==0 else None)
    
    plt.tight_layout()
    plt.show()

# Example usage
if __name__ == "__main__":
    bitonic_array = [1, 3, 5, 7, 6, 4, 2]
    target = 5
    visualize_solutions(bitonic_array, target)