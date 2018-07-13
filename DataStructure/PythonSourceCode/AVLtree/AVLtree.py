from BinarySerachTree import BinarySearchTree
import random

class AVLNode(object):
    def __init__(self, key):

        self.key = key
        self.left = None
        self.right = None

class AVLTree(BinarySearchTree, object):
    def __init__(self, element=None, parent=None, left=None, right=None):
        BinarySearchTree.__init__(self, element, parent=None, left=None, right=None)
        __slots__ = '_element', '_parent', '_left', '_right'

        self._element = element
        self._parent = parent
        self._left = left
        self._right = right

        self.height = -1
        self.balance = 0

    def insert(self, key):

        n = AVLNode(key)

        if not self._element:
            self._element = n
            self._element.left = AVLTree()
            self._element.right = AVLTree()

        elif key < self._element.key:
            self._element.left.insert(key)
        elif key > self._element.key:
            self._element.right.insert(key)

        self.rebalance()

    def rebalance(self):

        self.update_heights(recursive=False)
        self.update_balances(False)

        while self.balance < -1 or self.balance > 1:
            if self.balance > 1:
                if self._element.left.balance < 0:
                    self._element.left.rotate_left()
                    self.update_heights()
                    self.update_balances()

                self.rotate_right()
                self.update_heights()
                self.update_balances()

            if self.balance < -1:
                if self._element.right.balance > 0:
                    self._element.right.rotate_right()  # we're in case III
                    self.update_heights()
                    self.update_balances()

                self.rotate_left()
                self.update_heights()
                self.update_balances()

    def update_heights(self, recursive=True):
        if self._element:
            if recursive:
                if self._element.left:
                    self._element.left.update_heights()
                if self._element.right:
                    self._element.right.update_heights()
            self.height = 1 + max(self._element.left.height, self._element.right.height)
        else:
            self.height = -1

    def update_balances(self, recursive=True):
        if self._element:
            if recursive:
                if self._element.left:
                    self._element.left.update_balances()
                if self._element.right:
                    self._element.right.update_balances()

            self.balance = self._element.left.height - self._element.right.height
        else:
            self.balance = 0

    def rotate_right(self):
        new_root = self._element.left._element
        new_left_sub = new_root.right._element
        old_root = self._element

        self._element = new_root
        old_root.left._element = new_left_sub
        new_root.right._element = old_root

    def rotate_left(self):
        new_root = self._element.right._element
        new_left_sub = new_root.left._element
        old_root = self._element

        self._element = new_root
        old_root.right._element = new_left_sub
        new_root.left._element = old_root

    def remove(self, key):
        if self._element is not None:
            if self._element.key == key:
                if not self._element.left._element and not self._element.right._element:
                    self._element = None
                elif not self._element.left._element:
                    self._element = self._element.right._element
                elif not self._element.right._element:
                    self._element = self._element.left._element
                else:
                    successor = self._element.right._element
                    while successor and successor.left._element:
                        successor = successor.left._element

                    if successor:
                        self._element.key = successor.key
                        self._element.right.remove(successor.key)

            elif key < self._element.key:
                self._element.left.remove(key)

            elif key > self._element.key:
                self._element.right.remove(key)

            self.rebalance()

    def inorder(self):

        result = []

        if not self._element:
            return result

        result.extend(self._element.left.inorder())
        result.append(self._element.key)
        result.extend(self._element.right.inorder())

        return result

    def find(self,key):
        L = self.inorder()

        if key in L:
            return True;
        else:
            return False;

if __name__ == "__main__":
    tree = AVLTree()

    for i in range(10):
        key = random.randrange(1,20)
        tree.insert(key)

    print("\nAVLTree")
    print(tree.inorder())

    print("\nFind key")
    print(tree.find(13))

    tree.remove(13)

    print("\nAfter removing key")
    print(tree.inorder())

    print("\nBalance check")
    print(tree.balance)
