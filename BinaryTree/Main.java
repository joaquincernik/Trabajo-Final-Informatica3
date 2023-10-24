
public class Main {
    public static void main(String[] args) {
        
        ArbolBinario arbol = new ArbolBinario();
        arbol.insertarNodo("chocolate", 10);
        arbol.insertarNodo("carro", 0);
         arbol.insertarNodo("hola",50 );
         arbol.insertarNodo("ahora",12 );
          arbol.insertarNodo("massa",21 );
         
        arbol.printInOrder();
        System.out.println(arbol.getRoot().toString());
        System.out.println(arbol.buscarProducto(arbol.getRoot(), "massa").toString());
        
    }
  
}
