import random

def remove_duplicate3(L):
    dict_x = {}
    for i in L:
        dict_x[i]=i

    return list(dict_x.keys())


if __name__ == "__main__":
    A = []
    for i in range(150):
        A.append(random.randrange(1,100))
    print(A)

    print(remove_duplicate3(A))