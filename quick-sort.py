

def partition(arr: [], low: int, high: int):
    '''
    Partition the selected part of the list in place.
    That means to select one of the elements,  put all lower to the left, and all higher to the right,
    and the selected one (the pivot) in the middle. In this case we are selecting the rightmost item
    as the pivot.
    :param arr: list to be partitioned
    :param low: low index of list slice (inclusive)
    :param high: high index of list slice (inclusive)
    :return: index of the pivot (the selected element)
    '''
    pivot = arr[high]
    # so the index will have a valid value if we always increment before swapping
    # this is one less than the  midpoint between the lower partition and the higher partition
    partition_index = low - 1
    for j in range(low, high):
        # if the value is greater than pivot, leave the partition index, and the value in place
        if arr[j] < pivot:
            # increment partition index, and move the lower number to the left of it.
            partition_index += 1
            temp = arr[partition_index]
            arr[partition_index] = arr[j]
            arr[j] = temp
    # now move the pivot to the partition index
    partition_index += 1
    temp = arr[high]
    arr[high] = arr[partition_index]
    arr[partition_index] = temp
    # return the updated pivot index
    return partition_index


def quick_sort(arr: [], low: int, high: int):
    '''
    recursive quick sort.
    This is a sort-in-place which means the indices will select which part of the list is being sorted
    :param arr: list to be sorted
    :param low: low index of arr (inclusive)
    :param high: high index of arr (inclusive)
    :return:
    '''

    # termination step
    if low >= high:
        return

    # action step
    pivot_index = partition(arr, low, high)
    quick_sort(arr, low, pivot_index-1)
    quick_sort(arr, pivot_index+1, high)


# let's try it out!


arr = [9, 1, 4, 5, 2, 8, 3, 6, 7]
print("Before quicksort {}".format(arr))
quick_sort(arr, 0, len(arr) - 1)
print("After quicksort {}".format(arr))
