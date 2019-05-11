/**
 * Author: Aaron Helton
 * Class: CSC 310
 * Date: 03/10/2019
 * Assignment: Homework 4
 * Purpose: This is an implementation of a circular deque (Double ended queue), with support for adding and deleting
 *          elements at both the back and the front of the queue.
 */
public class MyCircularDeque
{
    //We use Integer instead of int so we can utilize 'null', since Java has no 'none' equivalent to Python.
    private Integer[] queue_array = null;
    private int back = 0;
    private int front = 0;

    public MyCircularDeque(int capacity)
    {
        queue_array = new Integer[capacity];
    }

    public int getSize()
    {
        //Special case: back and front point to the same element. If the queue is empty, this element should be null.
        if(front == back) {
            if(queue_array[front] == null) {
                return 0;
            }
            return 1;
        }
        else if(front < back) {
            return ((queue_array.length - front - back) % queue_array.length) + 1;
        } else {
            return front - back + 1;
        }
    }

    public boolean isEmpty() {
        return front==back && queue_array[front] == null;
    }

    public boolean isFull() {
        if(front == back-1)
            return true;
        if(front == queue_array.length -1 && back == 0)
            return true;
        return false;
    }

    public boolean insertFront(int value)
    {
        //Three scenarios: The queue is empty, a collision will occur, or we can insert normally
        if (isFull()) {
            return false;
        } else if (isEmpty()) {
            queue_array[front] = value;
            return true;
        } else {
            front = (front + 1) % queue_array.length;
            queue_array[front] = value;
            return true;
        }
    }

    public boolean insertLast(int value)
    {
        if(isFull()) {
            return false;
        } else if (isEmpty()) {
            queue_array[back] = value;
            return true;
        } else {
            back = back == 0 ? queue_array.length-1 : back-1;
            queue_array[back] = value;
            return true;
        }
    }

    public boolean deleteFront()
    {
        if(isEmpty())
            return false;
        if(front == back) { //Last element
            queue_array[front] = null;
            return true;
        } else {
            queue_array[front] = null;
            front = front == 0 ? queue_array.length-1 : front-1;
            return true;
        }
    }

    public int getFront()
    {
        if(isEmpty())
            return -1;
        else
            return queue_array[front];
    }

    public boolean deleteLast()
    {
        if(isEmpty())
            return false;
        if(front == back) {
            queue_array[back] = null;
            return true;
        } else {
            queue_array[back] = null;
            back = (back + 1) % queue_array.length;
            return true;
        }
    }

    public int getLast()
    {
        if(isEmpty())
            return -1;
        else
            return queue_array[back];
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < queue_array.length; i++)
        {
            if(queue_array[i] == null)
                sb.append("    ");
            else
                sb.append(queue_array[i]);
            if(i != queue_array.length -1)
                sb.append(',');
        }
        sb.append(']');
        return sb.toString();
    }
}
