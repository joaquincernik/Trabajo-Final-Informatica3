  public class ArbolBinario {
    
    private NodoBinario root = null;

    /**
     * Inserta un nuevo nodo al arbol binario de busqueda
     * @param name
     * @param stock 
     */
    public void insertarNodo(String name, int stock){
        try{
            root = insertarNodo(name, stock ,root);
        }catch(ProductoDuplicadoException e){
            
        }
    }
    
    /**
     * Metodo privado recursivo para insertar un nodo en el arbol binario de busqueda
     * @param name
     * @param stock
     * @param temp
     * @return El nuevo nodo insertado
     * @throws ProductoDuplicadoException cuando se intenta ingresar un producto ya existente en el arbol
     */
    private NodoBinario insertarNodo(String name, int stock,NodoBinario temp)throws ProductoDuplicadoException{
        if(temp==null){
            temp=new NodoBinario(name, stock);
        }
        else{ 
            int comparacion = name.trim().toLowerCase().compareTo(temp.getElement().trim().toLowerCase());
            if(comparacion >= 1){ //valor es string ahora
               temp.right= insertarNodo(name,stock,temp.right);
            }else{
                if(comparacion <= 1){
                temp.left= insertarNodo(name,stock,temp.left);
                } else{
                    throw new ProductoDuplicadoException("El producto con el nombre '" + name + "' ya existe en el árbol.");
                }
               }
            }
        return temp;
    }
     
    /**
     * Permite buscar un producto existente en el arbol binario de busqueda
     * @param t
     * @param elemento
     * @return El nodo encontrado
     * @throws ProductoNoEncontradoException cuando el producto a buscar no se encuentra en el arbol
     */
    public NodoBinario buscarProducto(NodoBinario t, String elemento) throws ProductoNoEncontradoException{
    if (t == null) {
        throw new ProductoNoEncontradoException("No se pudo encontrar la excepcion");
    }
    int comparacion = t.getElement().trim().toLowerCase().compareTo(elemento.trim().toLowerCase());
    
    if (comparacion == 0) {
        // Producto encontrado
        return t;
    } else {
        if (comparacion > 0) {
            
            return buscarProducto(t.left, elemento);
        } else {
            
            return buscarProducto(t.right, elemento);
        }
    }
}
    
    public void modificarStock (NodoBinario t , String elemento , int stockAEliminar){
        try{
        NodoBinario nodo = this.buscarProducto(t, elemento);
        nodo.modificarStock(stockAEliminar);
        }catch(ProductoNoEncontradoException e){  
        }
        
    }
           
    public NodoBinario getRoot(){
        return root;
    }

    public void printInOrder(){
        root.printInOrder();
    }
    public void makeEmpty(){
        int select; 
        
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }
    
   
    
}
