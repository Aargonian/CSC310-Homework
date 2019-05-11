class Tree:
    def __init__(self):
        self.tree_list = None

    def build_from_list(self, values):
        self.tree_list = values

    def print_inorder(self):
        if self.tree_list is not None:
            self._print_inorder(0)

    def _print_inorder(self, current_pos):
        if self._left_child(current_pos) < len(self.tree_list) and self.tree_list[self._left_child(current_pos)] is not None:
            self._print_inorder(self._left_child(current_pos))
        print(' ' + str(self.tree_list[current_pos]) + ' ')
        if self._right_child(current_pos) < len(self.tree_list) and self.tree_list[self._right_child(current_pos)] is not None:
            self._print_inorder(self._right_child(current_pos))

    def print_preorder(self):
        if self.tree_list is not None:
            self._print_preorder(0)

    def _print_preorder(self, current_pos):
        print(' ' + str(self.tree_list[current_pos]) + ' ')
        if self._left_child(current_pos) < len(self.tree_list) and self.tree_list[self._left_child(current_pos)] is not None:
            self._print_preorder(self._left_child(current_pos))
        if self._right_child(current_pos) < len(self.tree_list) and self.tree_list[self._right_child(current_pos)] is not None:
            self._print_preorder(self._right_child(current_pos))

    @staticmethod
    def _left_child(pos):
        return pos*2+1

    @staticmethod
    def _right_child(pos):
        return pos*2+2


def det_value(x):
    x = x.strip()
    if x == 'null':
        return None
    return int(x)


def main():
    tree = Tree()
    values = [1, None, 2, None, None, 3]
    input_val = input("Please input a list of numbers that is space separated, or the literal 'null': ")
    values = [det_value(x) for x in input_val.split(' ')]
    tree.build_from_list(values)
    print("In Order Traversal: ")
    tree.print_inorder()
    print("Preorder Traversal: ")
    tree.print_preorder()


if __name__ == '__main__':
    main()
