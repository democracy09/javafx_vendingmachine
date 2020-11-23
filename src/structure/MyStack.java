package structure;

import java.util.ArrayList;

public class MyStack<T> {

    private ArrayList<T> arrayStack = new ArrayList<T>();

    public void push(T data) {
        arrayStack.add(data);
    }

    public T pop() {
        int len=arrayStack.size();
        if(len==0) {
            System.out.println("stack empty");
            return null;
        }
        return arrayStack.remove(len-1);
    }

    public T peek(){
        if(arrayStack.size()==0) {
            System.out.println("stack empty");
            return null;
        }
        return arrayStack.get(arrayStack.size()-1);
    }

    public int search(T data){
        int count=0;
        for(int i=0;i<arrayStack.size();i++){
            if(arrayStack.get(i).equals(data))
                count++;
        }
        return count;
    }

    public void printAll(){
        if(arrayStack.size()==0){
            System.out.println("stack empty");
            return;
        }
        for(int i=0;i<arrayStack.size();i++){
            System.out.print(arrayStack.get(i)+" ");
        }
        System.out.println();

    }


}
