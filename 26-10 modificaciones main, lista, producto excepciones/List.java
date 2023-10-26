/**
 * @author Garibaldi Bruno
 */
public class List {
    ListNode front = null;
    
    /**
     * This method removes a product from the list
     * @param producto
     * @throws NotFoundedProductException if the product to be removed does not exist
     */
    
    public void popProduct(String productName) throws NotFoundedProductException {
        // Si la lista está vacía, no hay nada que hacer
        if (!containsProduct(productName)) {
        throw new NotFoundedProductException("Not Founded Product in the list: " + productName);
        }
        if (front == null) {
            System.out.println("Lista vacia");
            return;
        }

        // Si el producto a eliminar está en el nodo frontal
        if (front.getProduct().equals(productName)) {
            front = front.next;
            return;
        }

        // Buscar el nodo que contiene el producto
        ListNode temp = front;
        while (temp.next != null && !temp.next.getProduct().equals(productName)) {
            temp = temp.next;
        }
        // Eliminar el nodo que contiene el producto
        temp.next = temp.next.next;
    }
    
    /**
     * Prints list
     */
    public void print(){
       ListNode temp = front;
       while(temp.next!=null){
          System.out.println(temp.getProduct().toString());
          temp = temp.next;
       }
       System.out.println(temp.getProduct().toString());
    }
    
    /**
     * Saves products in the list
     * @param productName
     * @param stock
     * @throws DuplicateProductException if you want to save a product that has already been saved
     */
    public void push (String productName , int stock) throws DuplicateProductException {
        ListNode newNode = new ListNode(productName,stock);
        
        if (containsProduct(productName)){
        throw new DuplicateProductException("Duplicate product not supported: " + productName);
        }
        //si la lista no contiene nada o el nuevo producto es anterior al front
        if (front == null || newNode.getProduct().compareTo(front.getProduct()) < 0) {
            newNode.next = front;
            front = newNode;
        } else {
            ListNode temp = front;
            //recorre hasta encontrar su lugar adecuado
            while (temp.next != null && newNode.getProduct().compareTo(temp.next.getProduct()) > 0) {
                temp = temp.next;
                
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }
    
    /** 
     * @param productName
     * @return true if the entered product is not found in the list
     */
    private boolean containsProduct(String productName) {
    ListNode temp = front;
    while (temp != null) {
        if (temp.getProduct().getName().compareTo(productName) == 0) {
            return true; // El producto ya existe en la lista
        }
        temp = temp.next;
    }
    return false; // El producto no está en la lista
}
}
