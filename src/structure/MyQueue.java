package structure;

import java.util.ArrayList;

public class MyQueue<T> {

    private int rear = -1;
    private int front = -1;
    private ArrayList<T> arrQueue = new ArrayList<T>();

    public boolean isFull(){
        return rear == arrQueue.size()-1?true:false;
    }

    public boolean isEmpty(){
        return rear == front?true:false;
    }

    public void enQueue(T data){
        if(isFull()){
            throw new QueueOverflow();
        }
        arrQueue.add(++rear, data);
    }

    public T deQueue(){
        if(isEmpty()){
            throw new QueueUnderflow();
        }

        ++front;
        T temp = arrQueue.remove(front);

        if(isEmpty()){
            rear = -1;
            front = -1;
        }

        return temp;

    }

    static class QueueOverflow extends RuntimeException {

    }

    static class QueueUnderflow extends RuntimeException {

    }


}
