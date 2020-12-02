
public class Expression extends ExpressionTree {

   public String fullyParenthesized() {
      // add implementation here
      return "";
   }

   public Expression(String s) {
      super();
      // add implementation here

      System.out.println(s);
      System.out.println(isOperator(" +"));
      System.out.println(isOperator("- "));
      System.out.println(isOperator(" *"));
      System.out.println(isOperator(" /"));
      System.out.println(isDigit("25"));
      try {

         BNode<String> node1 = new BNode<>("2",null,null,null);
         BNode<String> node2 = new BNode<>("5",null,null,null);
         BNode<String> root  = new BNode<String>("*",null,null,null);

         this.root = root;
         node1.parent = root;
         node2.parent = root;

         root.right = node1;
         root.left = node2;
         this.addRoot(root.data);

         treePrint(this.root);


      }catch (Exception e){
         System.out.println("");
      }



   }
   
   public double evaluate() {
      // add implementation here
      return 0.0;
   }


   private boolean isOperator(String op){
      //remove the space from the operator
      op = op.replace(" ", "");
      return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") ;
   }

   private boolean isDigit(String num){
      try {
         double value = Double.parseDouble(num);
      }catch (NumberFormatException e){
         return  false;
      }
      return true;
   }


}
