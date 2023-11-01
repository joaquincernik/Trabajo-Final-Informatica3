public class NodoBinario {
    
    public String nombre;
    public int height;
    int stock;
    
    NodoBinario left;
    NodoBinario right;
    

    public NodoBinario(String nombre, int valor){
        stock=valor;
        this.nombre = nombre;
        left=null;
        right=null;
        height=1;//solo por conveniencia,
    }
    
    public NodoBinario(String nombre, int valor,NodoBinario lft,NodoBinario rght){
        stock=valor;
        this.nombre = nombre;
        left=lft;
        right=rght;
        height=1;
    }
    
     public String getElement(){
        return this.nombre;
    }
    public int getHeight(){
        return this.height;
    }
     
    public String toString(){
        return ("\nProduct name: " + this.nombre + " Stock: " + this.stock);
    }
    
//    public NodoBinario getLeft(){
//        return left;
//    }
//    public NodoBinario getRight(){
//        return right;
//    }
//    public void setLeft(NodoBinario x){
//        left=x;
//    }
//    public void setRight(NodoBinario x){
//        right=x;
//    }
    
    
    public void modificarStock(int stockAEliminar){
        if((this.stock - stockAEliminar) <= 0){
            System.out.println("Queres eliminar + de lo que tenes");
        }
        else{
            stock -= stockAEliminar;
        }
    }
    
    public void printInOrder(){
        if(left!=null){
            left.printInOrder();
        }
        System.out.println(this.toString());
        if(right!=null){
            right.printInOrder();
        }
    }
}