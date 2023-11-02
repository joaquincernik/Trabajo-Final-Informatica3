public class BinaryNode {
    public Product product;

    public int height;
    public BinaryNode right;
    public BinaryNode left;
    

    public BinaryNode(String name, int stock){
        product=new Product(name, stock);
        left=null;
        right=null;
        height=1;//solo por conveniencia,
    }
    
    public BinaryNode(String name, int stock,BinaryNode lft,BinaryNode rght){
        product=new Product(name, stock);
        left=lft;
        right=rght;
        height=1;
    }
    
    //getters
     public String getName(){
        return product.getName();
    }
    public int getStock(){
        return product.getStock();
    }
    public int getHeight(){
        return this.height;
    }
     
    //setters
    public void setName(String name){
        product.setName(name);
    }
    public void setStock(int stock){
        product.setStock(stock);
    }
    public String toString(){
        return product.toString();
    }

    
    public void modifyStock(int stockAModificar){
        product.modifyStock(stockAModificar);
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