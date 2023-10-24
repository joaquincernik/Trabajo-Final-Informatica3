public class Node {
    String name;
    int stock;
    Node next;
    
    
    //comentarios...(documentacion)
    public Node(String name , int stock){
        this.name = name; 
        this.stock = stock;
        this.next = null;
    }
    
    public String toString(){
        return ("\nProduct name: " + this.name + " Stock: " + this.stock);
    }
    
}
