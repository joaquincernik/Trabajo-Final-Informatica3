import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        char cont = ' ';
        int opt;
        String productName;
        int stock;
        Scanner console = new Scanner(System.in);
        Scanner consoleS = new Scanner(System.in);
        List list = new List();
        do{
            System.out.println("*****INVENTORY MANAGMENT*****\n\n");
            System.out.println("Menu:\n1:Add Product\n2:Search Product\n3:Show Inventoy Alphabetically\n4:Remove Product");
            opt = console.nextInt();
            
            switch(opt){
                case 1:
                    System.out.println("\n\n***Add Product***");
                    System.out.println("Enter the name of the product");
                    productName = consoleS.nextLine();
                    System.out.println("Enter the stock");
                    stock = console.nextInt();
                    try{
                        list.push(productName, stock);
                    }catch(DuplicateProductException e){
                        System.out.println(e.getMessage()); 
                        break;
                    }
                    break;
                case 2: 
                    System.out.println("\n\n***Search Product***");
                    System.out.println("Enter the name of the product");
                    productName = consoleS.nextLine();
                    break; 
                case 3:
                    System.out.println("\n\n***Show Inventoy Alphabetically***");
                    list.print();
                    break; 
                case 4: 
                    System.out.println("\n\n***Remove Product***");
                    System.out.println("Enter the name of the product");
                    productName = consoleS.nextLine();
                    try{
                        list.popProduct(productName);
                    }catch(NotFoundedProductException e){
                        System.out.println(e.getMessage()); 
                        break;
                    }
                    break; 
                    
                default:
                    System.out.println("Incorrectly entered option");
                    break;    
            }
            System.out.println("Continue?? [y/n]");
            cont = console.next().charAt(0);
        }while(cont == 'y' || cont == 'Y');
    }
}
