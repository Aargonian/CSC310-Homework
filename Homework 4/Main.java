public class Main
{
    public static void main(String[] args)
    {
        int value = 20;
        MyCircularDeque queue = new MyCircularDeque(10);

        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.insertFront(value++);
        System.out.println(queue);
        queue.deleteLast();
        System.out.println(queue);
        queue.deleteLast();
        System.out.println(queue);
        queue.deleteLast();
        System.out.println(queue);
        queue.insertLast(value++);
        System.out.println(queue);
        queue.insertLast(value++);
        System.out.println(queue);
        System.out.println("Removing value: " + queue.getFront());
        queue.deleteFront();
        System.out.println(queue);
        System.out.println("Removing value: " + queue.getFront());
        queue.deleteFront();
        System.out.println(queue);
        System.out.println("Appending value: " + ++value);
        queue.insertFront(value);
        System.out.println(queue);
        System.out.println("Appending value: " + ++value);
        queue.insertFront(value);
        System.out.println(queue);
        System.out.println("Appending value: " + ++value);
        queue.insertFront(value);
        System.out.println(queue);
        System.out.println("Size: " + queue.getSize());

        //Queue should be full at this point, so if we attempt to append to either the back or front, it should return false
        System.out.println("Attempting to append value: " + ++value);
        boolean success = queue.insertFront(value);
        System.out.println("Success: " + success);
        System.out.println(queue);
        System.out.println("Attempting to append value to back: " + ++value);
        success = queue.insertLast(value);
        System.out.println("Success: " + success);
        System.out.println(queue);

        System.out.println("Removing value: " + queue.getFront());
        queue.deleteFront();
        System.out.println(queue);
        System.out.println("Removing value: " + queue.getFront());
        queue.deleteFront();
        System.out.println(queue);
        System.out.println("Removing value: " + queue.getFront());
        queue.deleteFront();
        System.out.println(queue);
        System.out.println("Removing value: " + queue.getFront());
        queue.deleteFront();
        System.out.println(queue);
        System.out.println("Removing value: " + queue.getFront());
        queue.deleteFront();
        System.out.println(queue);
        System.out.println("Size: " + queue.getSize());
    }

    public static boolean flipCoin() {
        return Math.random()*10 < 5;
    }
}
