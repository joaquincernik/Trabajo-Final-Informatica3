/**
 * @author Garibaldi Bruno
 */
public class Product {
    private String name;  
    private int stock;

    
    public Product(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setStock(int stock){
        this.stock=stock;
    }

    /**
     * @param p
     * 
     * @return Positive if this.name greater p.name. 
     *         Negative if this.name less than p.name. 
     *         Cero if this.name == p.name
     */
    public int compareTo(Product p){
        return this.getName().trim().toLowerCase().compareTo(p.getName().trim().toLowerCase());
    }
    public void modifyStock(int stockAEliminar){
        if((this.stock + stockAEliminar) <= 0){
            System.out.println("Queres eliminar mas stock del que tenes actualmente disponible en "+getName());
        }
        else{
            stock += stockAEliminar;
        }
    }
     
    
    @Override
    public String toString() {
        return "Producto: "+name+" | Stock: "+stock;
    }
    
}
