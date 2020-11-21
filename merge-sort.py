def merge_sort_recursive(arr: []):
    '''
    Performs a recursive merge sort of a list of ints
    :param arr: the list to be sorted
    '''

    # Termination step - when there is nothing left to sort
    if not arr:
        return
    arr_len = len(arr)
    if arr_len == 1:
        return

    # recursion step - split list and recurse both halves
    mid = int(len(arr) / 2)
    arr_lhs = arr[:mid]
    arr_rhs = arr[mid:]
    merge_sort_recursive(arr_lhs)
    merge_sort_recursive(arr_rhs)
    i_lhs = 0
    i_rhs = 0
    merged = 0

    # action step - merge is done inline
    while True:
        if (arr_lhs[i_lhs] <= arr_rhs[i_rhs]):
            arr[merged] = arr_lhs[i_lhs]
            merged += 1
            i_lhs += 1
        else:
            arr[merged] = arr_rhs[i_rhs]
            merged += 1
            i_rhs += 1
        if i_lhs == len(arr_lhs):
            for i in range(i_rhs, len(arr_rhs)):
                arr[merged] = arr_rhs[i]
                merged += 1
            break
        elif i_rhs == len(arr_rhs):
            for i in range(i_lhs, len(arr_lhs)):
                arr[merged] = arr_lhs[i]
                merged += 1
            break


def merge(arr: [], offset: int, left: [], right: []):
    '''
    Called from iterative merge-sort
    merges 2 array segments in place in a larger array
    :param arr: the array to be merged
    :param offset: the offset where to place the merged items
    :param left: the left hand array segment list to be merged (a separate copy from arr)
    :param right: the right hand array segment list to be merged (a separate copy from arr)
    '''

    # left index
    i_lhs = 0
    # right index
    i_rhs = 0
    # merged array index
    merged = offset
    while True:
        # termination conditions
        if i_lhs == len(left):
            for i in range(i_rhs, len(right)):
                arr[merged] = right[i]
                merged += 1
            break
        elif i_rhs == len(right):
            for i in range(i_lhs, len(left)):
                arr[merged] = left[i]
                merged += 1
            break
        # perform the merge here
        if (left[i_lhs] <= right[i_rhs]):
            arr[merged] = left[i_lhs]
            merged += 1
            i_lhs += 1
        else:
            arr[merged] = right[i_rhs]
            merged += 1
            i_rhs += 1


def merge_sort_iterative(arr: []):
    '''
    Iterative merge sort
    The array can be trivially segmented by slicing since we have access to the whole array.
    Initially the 1-item segments will be merged together 2 at a time
    ex: 5 3  8 1  0 9  2 -> 3 5  1 8  0 9  2
    Next the 2-item segments will be merged together 2 at a time
    ex:  3 5  1 8  0 9  2 -> 1 3 5 8  0 2 9
    Then the 4-item...
    1 3 2 8  0 2 9 -> 0 1 2 3 5 8 9
    On each iteration, if there is not be a right hand segment to merge, so it is left in place.
    Also, the last segment might not be full-sized

    :param arr: the list to be sorted
    '''
    arr_len = len(arr)
    segment_size = 1
    # since we can see the entire list, just sort it in place
    while segment_size < arr_len:
        for i in range(0, arr_len - segment_size, segment_size * 2):
            # merge selected slices
            # i is the offset of the pairs being merged
            # segment_size is the length of the pairs (right side might not be the full length)
            # this works because the slices are actually copies of the sliced parts of arr
            merge(arr, i, arr[i:i + segment_size], arr[i + segment_size:i + segment_size * 2])
        segment_size *= 2


def bubble_sort(arr: []):
    '''
    Performs a Bubble sort just for fun
    In a bubble sort, we go from left to right of list, bubbling the largest value found up to the
    end of the list. On each iteration, we start at 0, and end at one less then the previous iteration.
    The list gets sorted from right (largest) to left as the iterations continue.
    :param arr: list to be sorted in place
    '''
    if not arr:
        return
    arr_len = len(arr)
    if arr_len == 1:
        return

    max = arr_len - 1
    while max > 1:
        for i in range(0, max):
            if arr[i] > arr[i + 1]:
                tmp = arr[i]
                arr[i] = arr[i + 1]
                arr[i + 1] = tmp
        max -= 1


# Make sure everything works
items_original = [5, 3, 8, 1, 0, 9, 2]
items_recursive = items_original.copy()
items_iterative = items_original.copy()
items_bubble = items_original.copy()

print("items_iterative: {}".format(str(items_iterative)))
merge_sort_iterative(items_iterative)
print("iterative sorted_items: {}".format(str(items_iterative)))

print("items_recursive: {}".format(str(items_recursive)))
merge_sort_recursive(items_recursive)
print("recursive sorted_items: {}".format(str(items_recursive)))

print("items bubble: {}".format(str(items_bubble)))
bubble_sort(items_bubble)
print("bubble sorted_items: {}".format(str(items_bubble)))
