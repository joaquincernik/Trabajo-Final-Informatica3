
public class NodoBinario {
    private int element;
     NodoBinario left;
     NodoBinario right;

    public NodoBinario(int valor){
        element=valor;
        left=null;
        right=null;
    }
    public NodoBinario(int elemento,NodoBinario lft,NodoBinario rght){
        element=elemento;
        left=lft;
        right=rght;
    }
    public int getElement(){
        return element;
    }
    public NodoBinario getLeft(){
        return left;
    }
    public NodoBinario getRight(){
        return right;
    }
    public void setLeft(NodoBinario x){
        left=x;
    }
    public void setRight(NodoBinario x){
        right=x;
    }

    public void printInOrder(){
        if(left!=null){
            left.printInOrder();
        }
        System.out.println(element);
        if(right!=null){
            right.printInOrder();
        }
    }
}
