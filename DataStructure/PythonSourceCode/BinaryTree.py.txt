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

class BinaryTree:
    def __init__(self, element, parent=None, left=None, right=None):
        __slots__ = '_element', '_parent', '_left', '_right'

        self._element = element
        self._parent = parent
        self._left = left
        self._right = right

    def __str__(self):
        return "[ " + str(self._element) + " " + str(self._left) + " " + str(self._right) + " ]"

    def inorder(self):
        if self._left is not None:
            for other in self._left.inorder():
                yield other
        yield self
        if self._right is not None:
            for other in self._right.inorder():
                yield other

    def preorder(self):
        if self is not None:
            yield self
        if self._left is not None:
            for other in self._left.preorder():
                yield other
        if self._right is not None:
            for other in self._right.preorder():
                yield other

    def postorder(self):
        if self._left is not None:
            for other in self._left.postorder():
                yield other
        if self._right is not None:
            for other in self._right.postorder():
                yield other
        yield self

    def levelorder(self):
        if self is not None:
            yield self

        levelorder_queue = Queue()
        levelorder_queue.enqueue(self)

        while levelorder_queue.size()>0:
            n = levelorder_queue.dequeue()
            if n._left is not None:
                yield n._left
                levelorder_queue.enqueue(n._left)
            if n._right is not None:
                yield n._right
                levelorder_queue.enqueue(n._right)

    def is_root(self):
        if self._parent is None:
            return True
        else:
            return False

    def is_internal(self):
        if self._left is not None:
            return True
        if self._right is not None:
            return True
        else:
            return False

    def is_external(self):
        if self._left is None:
            return True
        if self._right is None:
            return True
        else:
            return False

    def element(self):
        return self._element

    def parent(self):
        if self._parent is not None:
            return self._parent
        else:
            return None

    def left(self):
        if self._left is not None:
            return self._left
        else:
            return None

    def right(self):
        if self._right is not None:
            return self._right
        else:
            return None

    def sibling(self):
        if self._parent is not None:
            if self._parent._left == self:
                if self._parent._right:
                    return self._parent._right
                else:
                    return None
            else:
                if self._parent._left:
                    return self._parent._left
                else:
                    return None
        else:
            return None

    def children(self):
        if self._left is not None:
            yield self._left
        if self._right is not None:
            yield self._right

    def depth(self):
        if self._parent is None:
            return 0
        return self._parent.depth() + 1

    def height(self):
        if self.is_external():
            return 0
        return max(self._left.height(), self._right.height()) + 1

    def set_element(self, e):
        self._element = e

    def set_parent(self, other):
        self._parent = other

    def add_left(self, e):
        self._left = BinaryTree(e,self)

    def add_right(self, e):
        self._right = BinaryTree(e,self)

    def delete(self):
        if self._left:
            self._left = None
        if self._right:
            self._right = None
        self._element = None

if __name__ == "__main__":
    root = BinaryTree('1')
    root.add_left('2')
    root.add_right('3')
    root.left().add_left('4')
    root.left().add_right('5')
    root.right().add_left('6')
    root.right().add_right('7')
    root.right().left().add_right('8')

    print(root)
    print(root.left())
    print(root.left().sibling())
    print(root.left().left())
    print(root.left().right())
    print(root.right().left())
    print(root.right().right())

    t = root.left()
    print("Depth of {}: {}".format(t.element(), t.depth()))
    print("Height of {}: {}".format(t.element(), t.height()))
    print("{} is".format(t.element()), "internal." if t.is_internal() else "external.")
    print("{} is".format(t.element()), "a root." if t.is_root() else "not a root.")

    print("\nInorder Traversal")
    for p in root.inorder():
        print(p.element(), end=" ")
    print("\n")

    print("\nPreorder Traversal")
    for p in root.preorder():
        print(p.element(), end=" ")
    print("\n")

    print("\nPostorder Traversal")
    for p in root.postorder():
        print(p.element(), end=" ")
    print("\n")

    print("\nLevelorder Traversal")
    for p in root.levelorder():
        print(p.element(), end=" ")
        last = p
    print("\n")

    print("The last is ", last.element())