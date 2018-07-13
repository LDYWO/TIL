from BinaryTree import BinaryTree
import random
import time

class Heap(BinaryTree):
    def __init__(self, element):
        super().__init__(self, None)
        self._element = element
        Heap._last = self
        self.HeapList = [element,]
        self.HeapList.append(element)
        self.CurrentSize = 0

    def percUp(self, i):
        while i>0:
            if self.HeapList[i] < self.HeapList[i//2]:
                self.HeapList[i], self.HeapList[i//2] = \
                self.HeapList[i//2], self.HeapList[i]
            i = i//2

    def percDown(self, i):
        while (i * 2) <= self.CurrentSize:
            mc = self.minChild(i)
            if self.HeapList[i] > self.HeapList[mc]:
                self.HeapList[i], self.HeapList[mc] = \
                    self.HeapList[mc], self.HeapList[i]
            i = mc

    def minChild(self, i):
        if i * 2 + 1 > self.CurrentSize:
            return i * 2
        else:
            if self.HeapList[i * 2] < self.HeapList[i * 2 + 1]:
                return i * 2
            else:
                return i * 2 + 1

    def insert(self, element):
        self.HeapList.append(element)
        self.CurrentSize = self.CurrentSize + 1
        self.percUp(self.CurrentSize)

    def remove_min(self):
        retVal = self.HeapList[1]
        self.HeapList[1] = self.HeapList[self.CurrentSize]
        self.CurrentSize -= 1
        self.HeapList.pop()
        self.percDown(1)
        return retVal

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
    for i in range(count):
        sorted_list.append(aHeap.remove_min())
    e_time = time.time()
    print(sorted_list)
    print("Execution time : ", (e_time - s_time)*1000, "ms")