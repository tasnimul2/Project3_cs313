
public class Expression extends ExpressionTree {

   public String fullyParenthesized() {
      // add implementation here
      return "";
   }

   public Expression(String s) {
      super();
      // add implementation here
      //ExpressionTree tree = this;
      try {

         BNode<String> node1 = new BNode<>("2",null,null,null);
         BNode<String> node2 = new BNode<>("5",null,null,null);
         BNode<String> root  = new BNode<String>("*",null,null,null);

         //root.setRight();
         //root.setLeft();
         this.root = root;
         node1.parent = root;
         node2.parent = root;

         root.right = node1;
         root.left = node2;
         this.addRoot(root.data);

         treePrint(root);


      }catch (Exception e){
         System.out.println("");
      }



   }
   
   public double evaluate() {
      // add implementation here
      return 0.0;
   }

}
