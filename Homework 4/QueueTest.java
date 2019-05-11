public class QueueTest
{
    public static class Queue<T>
    {
        private class Node
        {
            T item;
            Node next;
        }

        private Node first;
        //We COULD calculate this every time len is called, but it's more
        //time efficient to just update it on (en/de)queue operations.
        private int len;

        public Queue()
        {
            len = 0;
            first = null;
        }

        public void enqueue(T item)
        {
            if(first == null)
            {
                first = new Node();
                first.item = item;
            }
            else
            {
                Node temp = first;
                while(temp.next != null)
                    temp = temp.next;
                temp.next = new Node();
                temp.next.item = item;
            }
            len++;
        }

        public T dequeue()
        {
            if(is_empty())
                throw new RuntimeException("Dequeue operation requested on empty Queue!");
            Node temp = first;
            first = first.next;
            len--;
            return temp.item;
        }

        public int len()
        {
            return len;
        }

        public boolean is_empty()
        {
            return len == 0;
        }

        public boolean search(T other)
        {
            Node temp = first;
            while(temp != null)
            {
                if(temp.item.equals(other))
                    return true;
                temp = temp.next;
            }
            return false;
        }

        public T first()
        {
            if(first == null)
                return null;
            else
                return first.item;
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder("[");
            Node temp = first;
            while(temp != null)
            {
                sb.append(temp.item.toString());
                temp = temp.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args)
    {
        Queue<String> queue = new Queue<>();
        System.out.println("QUEUE:");
        System.out.println(queue);
        System.out.println("LEN: " + queue.len());
        System.out.println("Appending 'a':");
        queue.enqueue("a");
        System.out.println("Appending 'b':");
        queue.enqueue("b");
        System.out.println("Appending 'c':");
        queue.enqueue("c");
        System.out.println("Appending 'd':");
        queue.enqueue("d");
        System.out.println("Appending 'e':");
        queue.enqueue("e");
        System.out.println("Appending 'f':");
        queue.enqueue("f");
        System.out.println("Appending 'g':");
        queue.enqueue("g");
        System.out.println("Appending 'h':");
        queue.enqueue("h");
        System.out.println("Appending 'i':");
        queue.enqueue("i");
        System.out.println("Appending 'j':");
        queue.enqueue("j");
        System.out.println("Appending 'k':");
        queue.enqueue("k");
        System.out.println("Appending 'l':");
        queue.enqueue("l");
        System.out.println("Appending 'm':");
        queue.enqueue("m");
        System.out.println("QUEUE:");
        System.out.println(queue);
        System.out.println("LEN: " + queue.len());
        System.out.println("Searching for j: " + queue.search("j"));
        System.out.println("Searching for a: " + queue.search("a"));
        System.out.println("Dequeueing 5 items");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("QUEUE:");
        System.out.println(queue);
        System.out.println("LEN: " + queue.len());
        System.out.println("FIRST: " + queue.first());
        System.out.println("LEN: " + queue.len());
        System.out.println("Searching for j: " + queue.search("j"));
        System.out.println("Searching for a: " + queue.search("a"));
        System.out.println("Is queue empty? " + queue.is_empty());
        System.out.println("Dequeuing 8 more items.");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("QUEUE: ");
        System.out.println(queue);
        System.out.println("Is queue empty? " + queue.is_empty());
    }
}
