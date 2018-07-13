def reverse(link, rest=None):
    if link is None:
        return rest
    return reverse(link.next, LinkedList(link.value, rest))

class LinkedList:
    def __init__(self, value=None, next=None):
        self.next = next
        self.value = value
    def print(self):
        print(self.value)
        if self.next is not None:
            self.next.print()

def to_linked_list(items):
    if len(items) == 0:
        return None
    return LinkedList(items[0], to_linked_list(items[1:]))

to_linked_list([1, 2, 3, 4, 5]).print()
reverse(to_linked_list([1, 2, 3, 4, 5])).print()