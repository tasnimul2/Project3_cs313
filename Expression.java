
public class Expression extends ExpressionTree {
   BNode<String> root ;
   public String fullyParenthesized() {
      // add implementation here
      return "";
   }

   public Expression(String s) {
      super();
      // add implementation here
      ExpressionTree tree = this;
      try {


         root = new BNode<String>("*",null,new BNode<String>("2",root,null,null) ,new BNode<String>("5",root,null,null));

         //root.setRight();
         //root.setLeft();

         tree.addRoot(root.data);

         treePrint(root);


         System.out.println(tree.root);

      }catch (Exception e){
         System.out.println("");
      }



   }
   
   public double evaluate() {
      // add implementation here
      return 0.0;
   }

}
