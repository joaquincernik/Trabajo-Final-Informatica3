public class ArbolBinario {

    private NodoBinario root = null;

    /**
     * Inserta un nuevo nodo al arbol binario de busqueda
     * 
     * @param name
     * @param stock
     */

     
    public NodoBinario getRoot() {
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

    public NodoBinario getMaxNode(NodoBinario node){
        NodoBinario current=node;
        while(current.right!=null){
            current=current.right;
        }
        return current;
    }

    public int getHeight(NodoBinario nodo) {
        // si el nodo es nulo devuelve 0, esto es util para la insercion
        if (nodo == null) {
            return 0;
        }
        return nodo.height;
    }

    // Equilibrio arbol AVL
    public int depth(NodoBinario nodo) {
        if (nodo == null) {
            return 0;
        }
        return getHeight(nodo.right) - getHeight(nodo.left);
    }

    public void insertarNodo(String name, int stock) {
        try {
            root = insertarNodo(name, stock, root);
        } catch (ProductoDuplicadoException e) {

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
     * @throws ProductoDuplicadoException cuando se intenta ingresar un producto ya
     *                                    existente en el arbol
     */
    private NodoBinario insertarNodo(String name, int stock, NodoBinario temp) throws ProductoDuplicadoException {
        if (temp == null) {
            temp = new NodoBinario(name, stock);
        } else {
            int comparacion = name.trim().toLowerCase().compareTo(temp.getElement().trim().toLowerCase());
            if (comparacion >= 1) { // valor es string ahora
                temp.right = insertarNodo(name, stock, temp.right);
            } else if (comparacion <= 1) {
                temp.left = insertarNodo(name, stock, temp.left);
            } else {
                throw new ProductoDuplicadoException("El producto con el nombre '" + name + "' ya existe en el árbol.");
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
        if (equilibrio < -1 && (name.trim().toLowerCase().compareTo(temp.left.getElement().trim().toLowerCase()) < 0)) {
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
        if (equilibrio > 1 && (name.trim().toLowerCase().compareTo(temp.right.getElement().trim().toLowerCase()) > 0)) {
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
        if (equilibrio < -1 && (name.trim().toLowerCase().compareTo(temp.left.getElement().trim().toLowerCase()) > 0)) {
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
        if (equilibrio > 1 && (name.trim().toLowerCase().compareTo(temp.left.getElement().trim().toLowerCase()) < 0)) {
            temp.right = rightRotate(temp.right);
            return leftRotate(temp);
        }
        return temp;

    }

    /**
     * Permite buscar un producto existente en el arbol binario de busqueda
     * 
     * @param t
     * @param elemento
     * @return El nodo encontrado
     * @throws ProductoNoEncontradoException cuando el producto a buscar no se
     *                                       encuentra en el arbol
     */
    public NodoBinario buscarProducto(NodoBinario t, String elemento) throws ProductoNoEncontradoException {
        if (t == null) {
            throw new ProductoNoEncontradoException("No se pudo encontrar el producto");
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

    public void modificarStock(NodoBinario t, String elemento, int stockAEliminar) {
        try {
            NodoBinario nodo = this.buscarProducto(t, elemento);
            nodo.modificarStock(stockAEliminar);
        } catch (ProductoNoEncontradoException e) {
        }

    }

    //eliminacion de nodos
    public void eliminar(String elemento){
        root=eliminarAvl(root,elemento);
    }
    private NodoBinario eliminarAvl(NodoBinario nodoActual,String elemento){
        if(nodoActual==null){
        return nodoActual;
        }
        
        //para facilidad de lectura guardamos la comparacion en una variable
        int comparacion=elemento.trim().toLowerCase().compareTo(nodoActual.getElement().trim().toLowerCase())

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
                NodoBinario temp=null;
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

                NodoBinario temp=getMaxNode(nodoActual.left);//se busca el maximo del nodo menor al que queremos borrar
               //esto se hace para poder enganchar al menor al nodo borrado del lado izquierdo y al mayor del nodo borraro en el lado derecho

               //copiamos la inforamcion de temp
                nodoActual.nombre=temp.nombre;

                //elimimos el nodo que obtuvimos de temp
                nodoActual.left=eliminarAvl(nodoActual.left, temp.nombre);
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
    }
    
    //rotaciones

    private NodoBinario rightRotate(NodoBinario nodoActual){
        //nueva raiz se el que esta a la izquierda de nodo con problemaas
        NodoBinario nuevaRaiz=nodoActual.left;
        NodoBinario temp=nuevaRaiz.right;//guardamos el nodo que tiene la nueva raiz a su derecha

        //se realiza la rotacion
        nuevaRaiz.right=nodoActual;
        nodoActual.left=temp;
        
        //actualizamos las alturas
        nodoActual.height=Math.max(getHeight(nodoActual.left),getHeight(nodoActual.right));
        nuevaRaiz.height=Math.max(getHeight(nuevaRaiz.left),getHeight(nuevaRaiz.right));
        return nuevaRaiz;
    }
    
    private NodoBinario leftRotate(NodoBinario nodoActual){
        //nueva raiz se el que esta a la derecha de nodo con problemaas
        NodoBinario nuevaRaiz=nodoActual.right;
        NodoBinario temp=nuevaRaiz.left;//guardamos el nodo que tiene la nueva raiz a su izquierda

        //se realiza la rotacion
        nuevaRaiz.left=nodoActual;
        nodoActual.right=temp;
        
        //actualizamos las alturas
        nodoActual.height=Math.max(getHeight(nodoActual.left),getHeight(nodoActual.right));
        nuevaRaiz.height=Math.max(getHeight(nuevaRaiz.left),getHeight(nuevaRaiz.right));
        return nuevaRaiz;
    }
}
