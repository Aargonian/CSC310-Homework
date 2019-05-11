import java.lang.reflect.Array;

public class Main
{
    public static class Stack<T>
    {
        protected static final int DEFAULT_CAPACITY = 16;
        protected static final int MINIMUM_CAPACITY = 4;

        protected int head;
        protected T[] stackArray;

        //Needed to parameterize array creations in push/pop and the constructor.
        private Class c;

        public Stack(Class<T> c)
        {
            this(c, DEFAULT_CAPACITY);
        }

        public Stack(Class<T> c, int capacity)
        {
            // Sanity Check
            this.c = c;
            if (capacity <= 0)
            {
                capacity = DEFAULT_CAPACITY;
            }
            else if (capacity < MINIMUM_CAPACITY)
            {
                capacity = MINIMUM_CAPACITY;
            }
            else
            {
                //Find the nearest power of two greater than or equal to capacity
                int power = 0;
                while (capacity > 1)
                {
                    power++;
                    capacity = (int)Math.ceil(capacity / 2.0);
                }
                capacity = (int)Math.pow(2, power);
            }


            //Unchecked creation of a generic array. Classtype known through parameterization.
            stackArray = (T[]) Array.newInstance(c, capacity);
            head = -1;
        }

        public void push(T value)
        {
            head++;

            //Expand the array if we have hit our capacity.
            if (head == stackArray.length)
            {
                //Same as constructored: technically unchecked cast but we know the class type for sure with c.
                T[] newStackArray = (T[])Array.newInstance(c, stackArray.length * 2);
                System.arraycopy(stackArray, 0, newStackArray, 0, stackArray.length);
                stackArray = newStackArray;
            }
            stackArray[head] = value;
        }

        public T pop()
        {
            //Sanity check that we aren't popping on an empty stack
            if (head < 0)
            {
                System.err.println("Unable to pop element: stack is empty");
                throw new RuntimeException("Pop Requested But Stack Empty");
            }

            //Grab the return value before we modify head
            T ret = stackArray[head--];

            //Check if we are at a fourth of the capacity on removal. If so, it should be safe to halve the size of the
            //stack array to conserve memory. We wait until it's a fourth, and not at half, to preventing thrashing in
            //case of many pops and pushes happening simultaneously.
            if (head <= stackArray.length/4 && stackArray.length > DEFAULT_CAPACITY)
            {
                T[] newStackArray = (T[])Array.newInstance(c, stackArray.length/2);
                System.arraycopy(stackArray, 0, newStackArray, 0, stackArray.length/2);
                stackArray = newStackArray;
            }
            return ret;
        }

        public T top()
        {
            if(head < 0)
            {
                System.err.println("Unable to retrieve top on empty stack!");
                throw new RuntimeException("Top value requested on empty stack");
            }
            return stackArray[head];
        }

        public int size()
        {
            return head+1;
        }
    }

    public static class MinStack extends Stack<Integer>
    {
        private final Stack<Integer> minStack;

        public MinStack()
        {
            this(DEFAULT_CAPACITY);
        }

        public MinStack(int capacity)
        {
            super(Integer.class, capacity);
            minStack = new Stack<>(Integer.class, DEFAULT_CAPACITY);
        }

        @Override
        public void push(Integer value)
        {
            super.push(value);

            //Check if value is new min
            if (minStack.head == -1 || value <= stackArray[minStack.top()])
            {
                minStack.push(head);
            }
        }

        @Override
        public Integer pop()
        {
            int ret = super.pop();
            if(minStack.top() == head+1)
            {
                minStack.pop(); // It is no longer the min value
            }
            return ret;
        }

        public int getMin()
        {
            return stackArray[minStack.top()];
        }
    }

    public static abstract class Evaluator
    {
        protected final String currentEval;
        protected int currentIndex;

        public Evaluator(String eval)
        {
            this.currentEval = eval;
            currentIndex = 0;
        }

        protected boolean hasNext()
        {
            return currentIndex != currentEval.length();
        }

        protected String getNextToken()
        {
            //Sanity check
            if(currentIndex == currentEval.length())
                return null;
            if (Character.isDigit(currentEval.charAt(currentIndex)))
            {
                int numberStartIndex = currentIndex;
                while(Character.isDigit(currentEval.charAt(currentIndex)))
                {
                    currentIndex++;
                }
                return currentEval.substring(numberStartIndex, currentIndex);
            }
            else
            {
                while(Character.isWhitespace(currentEval.charAt(currentIndex++))){}
                return currentEval.substring(currentIndex-1, currentIndex);
            }
        }

        protected boolean isNumber(String str)
        {
            for(int i = 0; i < str.length(); i++)
            {
                if (!Character.isDigit(str.charAt(i)))
                    return false;
            }
            return true;
        }

        public abstract double evaluate();
    }

    public static class ArithmeticEvaluator extends Evaluator
    {
        private Stack<Character> ops;
        private Stack<Double> vals;

        public ArithmeticEvaluator(String expression)
        {
            super(expression);
            vals = new Stack<>(Double.class);
            ops = new Stack<>(Character.class);
        }

        private int precendence(Character op)
        {
            switch(op)
            {
                case '$':
                    return 0;
                case '+':
                    return 1;
                case '-':
                    return 1;
                case '*':
                    return 2;
                case '/':
                    return 2;
                default:
                    return 3;
            }
        }

        private void runNextOp()
        {
            Double val1 = vals.pop();
            Double val2 = vals.pop();
            Double result = null;
            Character op = ops.pop();
            switch(op)
            {
                case '+':
                    result = val2 + val1;
                    break;
                case '-':
                    result = val2 - val1;
                    break;
                case '*':
                    result = val2 * val1;
                    break;
                case '/':
                    result = val2/val1;
                    break;
                default:
                    System.err.println("What?");
                    System.err.println("Operator Found: " + op);
            }
            vals.push(result);
        }

        private void runOps(Character refOp)
        {
            while(vals.size() > 1 && precendence(refOp) <= precendence(ops.top()))
            {
                runNextOp();
            }
        }

        @Override
        public double evaluate()
        {
            while(hasNext())
            {
                String token = getNextToken();
                if (isNumber(token))
                {
                    vals.push(Double.parseDouble(token));
                }
                else
                {
                    runOps(token.charAt(0));
                    ops.push(token.charAt(0));
                }
            }
            runOps('$');
            currentIndex = 0; //In case the function is called again
            return vals.pop();
        }
    }

    public static class PostfixEvaluator extends Evaluator
    {
        private Stack<Double> vals;

        public PostfixEvaluator(String postfixEval)
        {
            super(postfixEval);
            this.vals = new Stack<>(Double.class);
        }

        @Override
        public String getNextToken()
        {
            //Sanity check
            if(currentIndex == currentEval.length())
                return null;
            currentIndex++;
            return currentEval.substring(currentIndex-1, currentIndex);
        }

        @Override
        public double evaluate()
        {
            while(hasNext())
            {
                String token = getNextToken();
                if(isNumber(token))
                    vals.push(Double.parseDouble(token));
                else
                {
                    double val1 = vals.pop();
                    double val2 = vals.pop();
                    Double result = null;
                    switch(token.charAt(0))
                    {
                        case '+':
                            result = val2+val1;
                            break;
                        case '-':
                            result = val2 - val1;
                            break;
                        case '*':
                            result = val2 * val1;
                            break;
                        case '/':
                            result = val2 / val1;
                            break;
                        default:
                            System.err.println("Something went wrong! Token: " + token.charAt(0));
                    }
                    vals.push(result);
                }
            }
            return vals.pop();
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Commencing MINSTACK TESTING: ");
        MinStack stack = new MinStack(1);
        System.out.println("PUSHING: -2");
        stack.push(-2);
        System.out.println("PUSHING: 0");
        stack.push(0);
        System.out.println("PUSHING: -3");
        stack.push(-3);
        System.out.println("MIN: " + stack.getMin());
        System.out.println("POPPED: " + stack.pop());
        System.out.println("TOP: " + stack.top());
        System.out.println("MIN: " + stack.getMin());

        System.out.println("Commencing ARITHMETIC_EVALUATOR TESTING: ");
        String expression = "4 - 3 * 2 + 7";
        System.out.println("Expression: " + expression);
        System.out.println("Expected Result: " + 5);
        ArithmeticEvaluator eval = new ArithmeticEvaluator(expression);
        System.out.println("Result: " + eval.evaluate());

        System.out.println("Commencing POSTFIX_EVALUATOR TESTING: ");
        expression = "52+83-*4/";
        System.out.println("Expression: " + expression);
        System.out.println("Expected Result: " + 8.75);
        PostfixEvaluator eval2 = new PostfixEvaluator(expression);
        System.out.println("Result: " + eval2.evaluate());
    }
}
