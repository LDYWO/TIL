from BinaryTree import BinaryTree
import random

class BinarySearchTree(BinaryTree):
    def __init__(self, element, parent=None, left=None, right=None):
        BinaryTree.__init__(self, element, parent=None, left=None, right=None)
        __slots__ = '_element', '_parent', '_left', '_right'

        self._element = element
        self._parent = parent
        self._left = left
        self._right = right

    def add_left(self, e):
        self._left = BinarySearchTree(e)

    def add_right(self, e):
        self._right = BinarySearchTree(e)

    def insert(self, key):
        if self._element is None:
            self._element = key

        else:
            if key <= self._element:
                if self._left is None:
                    self.add_left(key)
                    self._left._parent=self
                else:
                    self._left.insert(key)

            elif key > self._element:
                if self._right is None:
                    self.add_right(key)
                    self._right._parent=self
                else:
                    self._right.insert(key)

    def find(self, key):
        if self._element == key:
            return self._element

        else:
            if key < self._element:
                if self._left is None:
                   return False
                else:
                    if key == self._left._element:
                        return self._left
                    else:
                        return self._left.find(key)

            elif key > self._element:
                if self._right is None:
                    return False
                else:
                    if key == self._right._element:
                        return self._right
                    else:
                        return self._right.find(key)

    def nextNode(self):
        if self._left:
            return self._left.Nextnode()
        else:
            return self

    def remove(self, key):
        removeNode = self.find(key)
        if removeNode: # key가 tree 안에 있는 경우
            if removeNode.is_internal(): # internal node인 경우 2가지 (child 1개 or 2개)
                if removeNode._right:
                    if removeNode._left:
                        next = removeNode._left.nextNode()
                        temp = removeNode._element
                        removeNode._element = next._element
                        next._element = temp
                        if next._parent._element == removeNode._element:
                            next._parent._left = next._left
                        else:
                            next._parent._right = next._left
                        next.delete()
                    else:
                        removeNode._right._parent = removeNode._parent
                        if removeNode._parent._left._element == removeNode._element:
                            removeNode._parent._left = removeNode._right
                        else:
                            removeNode._parent._left = removeNode._right
                        removeNode.delete()
                else:
                    removeNode._left._parent = removeNode._parent
                    if removeNode._parent._left._element == removeNode._element:
                        removeNode._parent._left = removeNode._left
                    else:
                        removeNode._parent._right = removeNode._left
                    removeNode.delete()
            else: # external node인 경우 그냥 삭제
                removeNode.delete()
        else: # 지울 key가 tree 안에 없는 경우
            return False


if __name__ == "__main__":

    root = BinarySearchTree(1)

    for i in range(10):
        j = random.randrange(1, 20)
        root.insert(j)

    print("\nBST")
    print(root)

    print("\nInorder Traversal")
    for p in root.inorder():
        print(p.element(), end=" ")
    print("\n")

    print("Find key")
    print(root.find(13))

    print("\nAfter removing key")
    root.remove(13)
    print(root)

    print("\nInorder Traversal")
    for p in root.inorder():
        print (p.element(), end=" ")
    print("\n")