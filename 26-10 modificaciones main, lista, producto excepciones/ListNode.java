/**
 * @author Garibaldi Bruno
 */
public class ListNode {
    public ListNode next; 
    private Product product;

    public ListNode(String productName , int stock) {
        this.next = null;
        this.product = new Product(productName, stock);
    }

    public Product getProduct() {
        return product;
    }
        
}
