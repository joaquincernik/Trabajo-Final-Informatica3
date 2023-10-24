public class List {
    Node front; 

    
    public void push(String name , int stock){
        if(front == null){
            front = new Node(name , stock);
        }else{
            
            Node temp = front; 
            
            while(temp.next!=null){
                temp = temp.next;
            }
            
            temp.next = new Node(name , stock);
            }
        
    }
    
    public Node top (){
        return front;
    }
    
    public void pop(){
        front = front.next;
    }
    
    public void print(){
        Node temp = front;
       while(temp.next!=null){
          System.out.println(temp.toString());
          temp = temp.next;
       }
       System.out.println(temp.toString());
    }
}
