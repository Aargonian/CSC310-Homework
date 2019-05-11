class MinBinHeap:
    """
    This class is an implementation of a Minimum Binary Heap, where the top element of the tree is always the smallest.
    This class maintains a heapsort by using an upheap/downheap method.
    """
    def __init__(self):
        self.heap = []

    def _swap(self, pos1, pos2):
        temp = self.heap[pos1]
        self.heap[pos1] = self.heap[pos2]
        self.heap[pos2] = temp

    @staticmethod
    def parent(pos):
        return (pos-1)//2

    @staticmethod
    def left_child(pos):
        return 2*pos+1

    @staticmethod
    def right_child(pos):
        return 2*pos+2

    def has_left(self, pos):
        return 2*pos+1 < len(self.heap) and self.heap[2*pos+1] is not None

    def has_right(self, pos):
        return 2*pos+2 < len(self.heap) and self.heap[2*pos+2] is not None

    def find_min(self):
        return self.heap[0]

    def remove_min(self):
        return_val = self.heap[0]
        self._swap(0, len(self.heap)-1)
        self.heap.pop()
        self.down_heap(0)
        return return_val

    def insert(self, item):
        self.heap.append(item)
        self.up_heap(len(self.heap)-1)

    def is_empty(self):
        return not (len(self.heap) > 0)

    def __len__(self):
        return len(self.heap)

    def size(self):
        return len(self)

    def down_heap(self, pos):
        child_one = child_two = -1
        if self.has_left(pos):
            child_one = self.left_child(pos)
        if self.has_right(pos):
            child_two = self.right_child(pos)

        if child_one is not -1 and child_two is not -1:
            if self.heap[child_one] < self.heap[child_two]:
                smallest = child_one
            else:
                smallest = child_two
        elif child_one is -1 and child_two is not -1:
            smallest = child_two
        elif child_one is not -1 and child_two is -1:
            smallest = child_one
        else:
            return

        if smallest is not -1:
            if self.heap[smallest] < self.heap[pos]:
                self._swap(pos, smallest)
                self.down_heap(smallest)

    def up_heap(self, pos):
        if pos is not 0:
            if self.heap[pos] < self.heap[self.parent(pos)]:
                self._swap(pos, self.parent(pos))
                self.up_heap(self.parent(pos))

    def build_heap(self, build_list):
        self.heap = []
        for i in range(len(build_list)):
            self.insert(build_list[i])


class MaxBinHeap:
    def __init__(self):
        self.heap = []

    def _swap(self, pos1, pos2):
        temp = self.heap[pos1]
        self.heap[pos1] = self.heap[pos2]
        self.heap[pos2] = temp

    @staticmethod
    def parent(pos):
        return (pos-1)//2

    @staticmethod
    def left_child(pos):
        return 2*pos+1

    @staticmethod
    def right_child(pos):
        return 2*pos+2

    def has_left(self, pos):
        return 2*pos+1 < len(self.heap) and self.heap[2*pos+1] is not None

    def has_right(self, pos):
        return 2*pos+2 < len(self.heap) and self.heap[2*pos+2] is not None

    def find_max(self):
        return self.heap[0]

    def remove_max(self):
        return_val = self.heap[0]
        self._swap(0, len(self.heap)-1)
        self.heap.pop()
        self.down_heap(0)
        return return_val

    def insert(self, item):
        self.heap.append(item)
        self.up_heap(len(self.heap)-1)

    def is_empty(self):
        return not (len(self.heap) > 0)

    def __len__(self):
        return len(self.heap)

    def size(self):
        return len(self)

    def down_heap(self, pos):
        child_one = child_two = -1
        if self.has_left(pos):
            child_one = self.left_child(pos)
        if self.has_right(pos):
            child_two = self.right_child(pos)

        if child_one is not -1 and child_two is not -1:
            if self.heap[child_one] > self.heap[child_two]:
                largest = child_one
            else:
                largest = child_two
        elif child_one is -1 and child_two is not -1:
            largest = child_two
        elif child_one is not -1 and child_two is -1:
            largest = child_one
        else:
            return
        if largest is not -1:
            if self.heap[largest] > self.heap[pos]:
                self._swap(pos, largest)
                self.down_heap(largest)

    def up_heap(self, pos):
        if pos is not 0:
            if self.heap[pos] > self.heap[self.parent(pos)]:
                self._swap(pos, self.parent(pos))
                self.up_heap(self.parent(pos))

    def build_heap(self, build_list):
        self.heap = []
        for i in range(len(build_list)):
            self.insert(build_list[i])


def heapSort(items):
    heap = MaxBinHeap()
    heap.build_heap(items)
    item_list = []
    while not heap.is_empty():
        item_list.insert(0, heap.remove_max())
    return item_list


class PriorityQueue:
    class Node:
        def __init__(self, item, priority):
            self.value = item
            self.priority = priority
            self.next = None
            self.prev = None
            
        def __lt__(self, other):
            return self.priority < other.priority
        
        def __le__(self, other):
            return self.priority <= other.priority
        
        def __ge__(self, other):
            return self.priority >= other.priority
        
        def __gt__(self, other):
            return self.priority > other.priority
        
        def __eq__(self, other):
            return self.priority == other.priority

        def __repr__(self):
            return str(self.value)

        def __str__(self):
            return str(self.value)

    def __init__(self):
        self.queueFront = None

    def is_empty(self) -> bool:
        return self.queueFront is None

    def remove_min(self) -> int:
        ret = self.queueFront.value
        self.queueFront = self.queueFront.next
        return ret

    def add(self, item, priority):
        new_node = self.Node(item, priority)
        if self.queueFront is None:
            self.queueFront = new_node
        elif self.queueFront > new_node:
            self.queueFront.prev = new_node
            new_node.next = self.queueFront
            self.queueFront = new_node
        else:
            current_node = self.queueFront
            while current_node.next is not None:
                if current_node.next <= new_node:
                    current_node = current_node.next
                else:
                    current_node.next.prev = new_node
                    new_node.next = current_node.next
                    current_node.next = new_node
                    new_node.prev = current_node
                    break
            else:
                current_node.next = new_node
                new_node.prev = current_node


                

def main():
    print("Testing priority queue:")
    pQueue = PriorityQueue()
    values = [1, 6, 3, 7, 5, 9]
    print("Unsorted:", values)
    for value in values:
        pQueue.add(value, value)
    values = []
    while not pQueue.is_empty():
        values.append(pQueue.remove_min())
    print("Sorted: ", values)

    print("Testing Heap Sort")
    item_list = [9, 7, 5, 2, 6, 4]
    print("Unsorted:", item_list)
    print("Sorted:", heapSort(item_list))

    print("Testing Min Binary Heap")
    heap = MinBinHeap()
    print('Inserting', 5)
    heap.insert(5)
    print('Inserting', 7)
    heap.insert(7)
    print('Inserting', 3)
    heap.insert(3)
    print('Inserting', 11)
    heap.insert(11)

    print("Removing all min elements:")
    print(heap.remove_min())
    print(heap.remove_min())
    print(heap.remove_min())
    print(heap.remove_min())


if __name__ == '__main__':
    main()
