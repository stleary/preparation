def count_sort(arr: [], n: int):
    if not arr or n == 0:
        return

    # decide how big to make the count list. It must have an element for each possible
    # number, up to the largest in arr. This is a kind of hash, where the number itself
    # is the hash value
    a_max = max(arr)+1

    # count will initially holds how many times each number appears
    # ex: count[3] = 1 means there is a single 3 in the list
    count = [0] * a_max

    # store how many times  each number occurs in count
    for a in arr:
        count[a] += 1

    # convert count into the position of each number in the output
    for i in range(1, a_max):
        count[i] += count[i-1]

    # generate the sorted list

    # ex:         0 1 2 3 (keys or index)
    #       count 0 2 3 4
    # result: arr 1 1 2 3

    # where to output the next number in arr
    indx = 0
    for i in range(a_max):
        # count[i] - indx_start = how many of this number will need to output
        indx_start = indx
        while count[i] > indx_start:
            arr[indx] = i
            indx += 1
            count[i] -= 1


arr = [4, 2, 8, 1, 3, 5, 1, 1, 8]

print("before count sort: {}".format(arr))
count_sort(arr, len(arr))
print("after count sort: {}".format(arr))

