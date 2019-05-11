public class ListSplice
{
    public static class List<T>
    {
        T item = null;
        List<T> next = null;

        public List() {}
        public List(T item)
        {
            this.item = item;
            this.next = null;
        }

        public void append(T item) 
        {
            if(this.item == null) 
            {
                this.item = item;
            } 
            else if(this.next == null)
            {
                this.next = new List<T>(item);
            }
            else 
            {
                List<T> temp = next;
                while(temp.next != null)
                    temp = temp.next;
                temp.next = new List<T>(item);
            }
        }

        @Override
        public String toString()
        {
            if(this.item == null)
                return "null";
            else
            {
                List<T> temp = this;
                StringBuilder builder = new StringBuilder(temp.item.toString());
                while(temp.next != null)
                {
                    temp = temp.next;
                    builder.append("->");
                    builder.append(temp.item.toString());
                }
                return builder.toString();
            }
        }
    }

    public static List<Integer> splice(List<Integer> one, List<Integer> two)
    {
        List<Integer> temp = new List<>();
        while(one.next != null && two.next != null)
        {
            if(one.item < two.item)
            {
                temp.append(one.item);
                one = one.next;
            }
            else
            {
                temp.append(two.item);
                two = two.next;
            }
        }
        while(one.next != null)
        {
            temp.append(one.item);
            one = one.next;
        }
        while(two.next != null)
        {
            temp.append(two.item);
            two = two.next;
        }
        return temp;
    }

    public static void main(String[] args)
    {
        List<Integer> listOne = new List<>(10);
        listOne.append(20);
        listOne.append(33);
        listOne.append(48);
        listOne.append(49);
        listOne.append(50);
        listOne.append(71);
        listOne.append(75);
        listOne.append(87);
        listOne.append(100);

        List<Integer> listTwo = new List<>(5);
        listTwo.append(6);
        listTwo.append(11);
        listTwo.append(32);
        listTwo.append(48);
        listTwo.append(49);
        listTwo.append(51);
        listTwo.append(82);
        listTwo.append(99);
        listTwo.append(101);
        listTwo.append(10001);

        System.out.println("List One:");
        System.out.println(listOne);
        System.out.println("List Two:");
        System.out.println(listTwo);
        System.out.println("List Splice: ");
        System.out.println(splice(listOne, listTwo));
    }
}
