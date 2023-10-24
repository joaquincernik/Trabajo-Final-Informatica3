
public class ArbolBinario {
    private NodoBinario root;

    public ArbolBinario(){
        root=null;
    }
    public ArbolBinario(int rootItem){
        root= new NodoBinario(rootItem,null,null); 
    }
    public void insertarNodo(int x){
        root=insertarNodo(x,root);
    }
    public NodoBinario getRoot(){
        return root;
    }
    public void merge(int rootItem,ArbolBinario t1,ArbolBinario t2){
        if(t1.root==t2.root&&t1.root!=null){
            throw new IllegalArgumentException();
        }

        //creamos un nuevo arbol
        root=new NodoBinario(rootItem, t1.root, t2.root);

        //nos aseguramos que cada nodo esta en el arbol
        if(this!=t1)
            t1.root=null;

        if(this!=t2)
            t2.root=null;
    }

    public void printInOrder(){
        root.printInOrder();
    }
    public void makeEmpty(){
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }
    public NodoBinario insertarNodo(int valor,NodoBinario temp){
        if(temp==null){
            temp=new NodoBinario(valor);
        }
        else if(valor>temp.getElement()){
               temp.right= insertarNodo(valor,temp.right);
            }
        else if(valor<temp.getElement()){
               temp.left= insertarNodo(valor,temp.left);
            }
        
        return temp;
    }
    
}
