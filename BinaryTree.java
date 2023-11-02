public class BinaryTree {

    private BinaryNode root = null;

    /**
     * Inserta un nuevo nodo al arbol binario de busqueda
     * 
     * @param name
     * @param stock
     */

    public BinaryNode getRoot() {
        return root;
    }

    public void printInOrder() {
        root.printInOrder();
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public BinaryNode getMaxNode(BinaryNode node) {
        BinaryNode current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    public int getHeight(BinaryNode nodo) {
        // si el nodo es nulo devuelve 0, esto es util para la insercion
        if (nodo == null) {
            return 0;
        }
        return nodo.height;
    }

    // Equilibrio arbol AVL
    public int depth(BinaryNode nodo) {
        if (nodo == null) {
            return 0;
        }
        return getHeight(nodo.right) - getHeight(nodo.left);
    }

    public void insertarNodo(String name, int stock) {
        try {
            root = insertarNodo(name, stock, root);
        } catch (DuplicateProductException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo privado recursivo para insertar un nodo en el arbol binario de
     * busqueda
     * 
     * @param name
     * @param stock
     * @param temp
     * @return El nuevo nodo insertado
     * @throws DuplicatedProductException cuando se intenta ingresar un producto ya
     *                                    existente en el arbol
     */
    private BinaryNode insertarNodo(String name, int stock, BinaryNode temp) throws DuplicateProductException {
        if (temp == null) {
            temp = new BinaryNode(name, stock);
        } else {
            int comparacion = name.trim().toLowerCase().compareTo(temp.getName().trim().toLowerCase());
            if (comparacion >= 1) { // valor es string ahora
                temp.right = insertarNodo(name, stock, temp.right);
            } else if (comparacion <= 1) {
                temp.left = insertarNodo(name, stock, temp.left);
            } else {
                throw new DuplicateProductException("El producto con el nombre '" + name + "' ya existe en el árbol.");
            }
        }
        temp.height = 1 + Math.max(getHeight(temp.left), getHeight(temp.right));
        int equilibrio = depth(temp);

        // caso de rotacion simple derecho
        /*
         * ejemplo
         * 20(-2)
         * /
         * 12(-1)
         * /
         * 5(0)
         */
        if (equilibrio < -1 && (name.trim().toLowerCase().compareTo(temp.left.getName().trim().toLowerCase()) < 0)) {
            return rightRotate(temp);
        }
        // caso de rotacion simple izquierda
        /*
         * ejemplo
         * 5(-2)
         * \
         * 12(1)
         * \
         * 20(0)
         */
        if (equilibrio > 1 && (name.trim().toLowerCase().compareTo(temp.right.getName().trim().toLowerCase()) > 0)) {
            return leftRotate(temp);
        }
        // caso de rotacion doble izquierda derecha
        /*
         * ejemplo
         * 12(-2)
         * /
         * 5(1)
         * \
         * 8(0)
         */
        if (equilibrio < -1 && (name.trim().toLowerCase().compareTo(temp.left.getName().trim().toLowerCase()) > 0)) {
            temp.left = leftRotate(temp.left);
            return rightRotate(temp);
        }
        // caso de rotacion doble derecha izquierda
        /*
         * ejemplo
         * 5(2)
         * \
         * /
         * 12(-1)
         * 8(0)
         */
        if (equilibrio > 1 && (name.trim().toLowerCase().compareTo(temp.left.getName().trim().toLowerCase()) < 0)) {
            temp.right = rightRotate(temp.right);
            return leftRotate(temp);
        }
        return temp;

    }

    public void buscarProducto(String producto){
        try{
             System.out.println(buscarProducto(root,producto).toString());
        }
        catch(NotFoundedProductException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Permite buscar un producto existente en el arbol binario de busqueda
     * 
     * @param t
     * @param elemento
     * @return El nodo encontrado
     * @throws NotFoundedProductException cuando el producto a buscar no se
     *                                       encuentra en el arbol
     */
    private BinaryNode buscarProducto(BinaryNode t, String elemento) throws NotFoundedProductException {
        if (t == null) {
            throw new NotFoundedProductException("Product "+elemento+" was not founded in inventoy");
        }
        int comparacion =elemento.trim().toLowerCase().compareTo(t.getName().trim().toLowerCase());

        if (comparacion == 0) {
            // Producto encontrado
            return t;
        } else {
            if (comparacion < 0) {
                return buscarProducto(t.left, elemento);
            } else {

                return buscarProducto(t.right, elemento);
            }
        }
    }

 
    public void modifyStock(String elemento, int stockAModificar) {
        try {
            BinaryNode nodo = this.buscarProducto(root, elemento);
            nodo.modifyStock(stockAModificar);
        } catch (NotFoundedProductException e) {
            System.out.println(e.getMessage());
        }

    }

    // eliminacion de nodos
    public void eliminar(String elemento) {
        try{
            root = eliminarAvl(root, elemento);
        }
        catch(NotFoundedProductException e){
            System.out.println(e.getMessage());
        }
    }

    private BinaryNode eliminarAvl(BinaryNode nodoActual,String elemento) throws NotFoundedProductException{
        if(nodoActual==null){
        throw new NotFoundedProductException("Product "+elemento+" was not founded in inventoy");
        }
        
        //para facilidad de lectura guardamos la comparacion en una variable
        int comparacion=elemento.trim().toLowerCase().compareTo(nodoActual.getName().trim().toLowerCase());

        //nombre a buscar menor alfabeticamente que el nombre del producto almacenado en el nodo
        if(comparacion<0){
            nodoActual.left=eliminarAvl(nodoActual.left, elemento);
        }
        else if(comparacion>0){
            nodoActual.right=eliminarAvl(nodoActual.right, elemento);
        }
        else{
            //nodo igual a la clave, se elimina
            
            //nodo con unico hijo
            if((nodoActual.left==null)||(nodoActual.right==null)){
                BinaryNode temp=null;
                if(temp==nodoActual.left){
                    temp=nodoActual.right;//guardamos en temp el nodo que no es null
                }
                else{
                    temp=nodoActual.left;//guardamos en temp el nodo que no es null
                }

                //nodo sin hijos
                 if(temp==null){
                    nodoActual=null;//se elimina el nodo    
                 }
                 else{
                    //caso con un hijo
                    nodoActual=temp;//se reemplaza el valor que almacena el nodo por el valor del hijo distinto de null
                 }
            }
            else{
                //nodo con dos hijos 

                BinaryNode temp=getMaxNode(nodoActual.left);//se busca el maximo del nodo menor al que queremos borrar
               //esto se hace para poder enganchar al menor al nodo borrado del lado izquierdo y al mayor del nodo borraro en el lado derecho

               //copiamos la inforamcion de temp
                nodoActual.setName(temp.getName());
                nodoActual.setStock(temp.getStock());

                //elimimos el nodo que obtuvimos de temp
                nodoActual.left=eliminarAvl(nodoActual.left, temp.getName());
            }

            //si solo tiene un nodo
            if(nodoActual==null)
                return nodoActual;

            //actualizar altura
            nodoActual.height=Math.max(getHeight(nodoActual.left), getHeight(nodoActual.right))+1;
            
            int balance = depth(nodoActual);

            //rotaciones:

            //rotacion derecha
            if(balance<-1 && depth(nodoActual.left)<=0){
                return rightRotate(nodoActual);
            }

            //rotacion izquierda
            if(balance>1 && depth(nodoActual.left)>=0){
                return leftRotate(nodoActual);
            }

            //rotacion doble izquierda-derecha
            if(balance<-1 && depth(nodoActual.left)>0){
                nodoActual.left=leftRotate(nodoActual.left);
                return rightRotate(nodoActual);
            }

            //rotacion doble derecha-izquierda
            if(balance>1 && depth(nodoActual.right)<0){
                nodoActual.right=rightRotate(nodoActual.right);
                return leftRotate(nodoActual);
            }

        }
        return nodoActual;
    }

    // rotaciones

    private BinaryNode rightRotate(BinaryNode nodoActual) {
        // nueva raiz se el que esta a la izquierda de nodo con problemaas
        BinaryNode nuevaRaiz = nodoActual.left;
        BinaryNode temp = nuevaRaiz.right;// guardamos el nodo que tiene la nueva raiz a su derecha

        // se realiza la rotacion
        nuevaRaiz.right = nodoActual;
        nodoActual.left = temp;

        // actualizamos las alturas
        nodoActual.height = Math.max(getHeight(nodoActual.left), getHeight(nodoActual.right));
        nuevaRaiz.height = Math.max(getHeight(nuevaRaiz.left), getHeight(nuevaRaiz.right));
        return nuevaRaiz;
    }

    private BinaryNode leftRotate(BinaryNode nodoActual) {
        // nueva raiz se el que esta a la derecha de nodo con problemaas
        BinaryNode nuevaRaiz = nodoActual.right;
        BinaryNode temp = nuevaRaiz.left;// guardamos el nodo que tiene la nueva raiz a su izquierda

        // se realiza la rotacion
        nuevaRaiz.left = nodoActual;
        nodoActual.right = temp;

        // actualizamos las alturas
        nodoActual.height = Math.max(getHeight(nodoActual.left), getHeight(nodoActual.right));
        nuevaRaiz.height = Math.max(getHeight(nuevaRaiz.left), getHeight(nuevaRaiz.right));
        return nuevaRaiz;
    }
}
