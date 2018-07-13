from BinaryTree import BinaryTree
import random
import time

class Heap(BinaryTree):
    def __init__(self, element):
        super().__init__(self, None)
        self._element = element
        Heap._last = self

    def insert(self, element):
        if self.is_root() == None:
            root = Heap(element)
        if self.is_root() is not None:




 #   def remove_min(self):

if __name__ == "__main__":
    count = 100
    a_list = []
    for i in range(count):
        a_list.append(random.randrange(1,100*count))
    print(a_list)

    s_time = time.time()
    # Phase 1: inserting n elements into a heap
    aHeap = Heap(0)
    for i in range(count):
        aHeap.insert(a_list[i])

    # Phase 2: removing the min n times and putting it into a sorted list
    sorted_list = []
    for i in range(count+1):
        sorted_list.append(aHeap.remove_min())
    e_time = time.time()
    print(sorted_list)
    print("Execution time : ", (e_time - s_time)*1000, "ms")