import random

class Queue:
    def __init__(self):
        self.items = []
    def isEmpty(self):
        return self.items == []
    def enqueue(self,item):
        self.items.insert(0,item)
    def dequeue(self):
        return self.items.pop()
    def size(self):
        return len(self.items)

def hotPotato(namelist):
    bomb_count = random.randint(1,namelist.__len__())
    j=bomb_count
    potato_queue = Queue()

    for name in namelist:
        potato_queue.enqueue(name)

    if potato_queue.size() > 1:
        if (bomb_count==0):
            return potato_queue.dequeue()
        else:
            for i in range(namelist.__len__()):
                potato_queue.enqueue(potato_queue.dequeue())
                bomb_count -= 1
                if (bomb_count == 0):
                    namelist.__delitem__(j-1)
                    return hotPotato(namelist)
                else:
                    continue
        hotPotato(namelist)

    else:
        return potato_queue.dequeue()

players = ['홍길동','홍동길','길홍동','길동홍','동홍길','동길홍','최정주','오소명','강그루']
s = [1,2,3,4,5,6,7,8,9]
big_index=0
data = [7,6,5,4,3,2,1]


if __name__ == "__main__":
    print("The winner is",hotPotato(players))
    print(3*s)
    print('maroon',5)
    print(data)
    year=int(input('in what year?'))