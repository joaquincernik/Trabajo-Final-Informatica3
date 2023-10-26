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
    
    @Override
    public String toString() {
        return "\nProduct{" + "name=" + name + ", stock=" + stock + '}';
    }
    
}
