import java.util.ArrayList;
import java.util.Stack;

public class Expression extends ExpressionTree {
   ArrayList<String> postFix;

   public String fullyParenthesized() {
      // add implementation here
      ArrayList<String> output = new ArrayList<>();
      String outputString = "";
      fullyParenthesized((BNode<String>) this.root, output);
      for(int i = 0; i < output.size() ;i++){
         outputString += output.get(i);
      }
     return outputString;
   }

   private void fullyParenthesized(BNode<String> node, ArrayList<String> list){
      if (node == null){
         return ;

      }
      if(!isOperator(node.data)){
         list.add(node.data);
      }else {
         list.add("(");
         fullyParenthesized(node.left, list);
         list.add(node.data);
         fullyParenthesized(node.right, list);
         list.add(")");
      }
   }


   public Expression(String s) {
      super();
      // add implementation here
      ArrayList<String> list = new ArrayList<>();
      splitStringToList(s,list);
      postFix = infixToPostfix(list);
      Stack<BNode<String>> stk = new Stack<>();
      try {

         for(int x = 0; x < postFix.size(); x++){
            if(!isOperator(postFix.get(x))){
               stk.push(new BNode<>(postFix.get(x),null,null,null));
            }else{
               BNode<String> root  = new BNode<String>(postFix.get(x),null,null,null);
               this.root = root;
               root.right = stk.pop();
               root.left= stk.pop();
               root.right.parent = root;
               root.left.parent = root;
               stk.push(root);
            }
         }
         this.addRoot(root.data);
      }catch (Exception e){
         //System.out.println("");
      }
   }
   
   public double evaluate() {
      // add implementation here
      return 0.0;
   }

   //----------------------------------------- Private Helper Methods -------------------------------//

   private boolean isOperator(char currChar){
      //remove the space from the operator
      return currChar == '+' || currChar =='-' || currChar == '*' || currChar =='/' || currChar == '(' || currChar ==')';
   }
   private boolean isOperator(String currChar){
      //remove the space from the operator
      return currChar.equals("+") || currChar.equals("-")  || currChar.equals("*")|| currChar.equals("/");
   }


   private boolean isDigit(String num){
      try {
         double value = Double.parseDouble(num);
      }catch (NumberFormatException e){
         return  false;
      }
      return true;
   }

   //includes some recycled code from project 1 that breaks up input string into pieces.
   private void splitStringToList(String s, ArrayList<String> list){
      //get rid of spaces from the string
      int i = 0;
      String beforeCurrentChar;
      String afterCurrentChar;
      while (i < s.length()){
         char currentChar = s.charAt(i);
         if(currentChar == ' ' ){
            beforeCurrentChar = s.substring(0,i); //everything that comes before currentChar
            afterCurrentChar = s.substring(i+1); // everything that comes after currentChar
            s = beforeCurrentChar + afterCurrentChar; // combine everything except the currentChar which essentially removes that char
         }else{
            i++;
         }
      }
      String sect = "";
      //iterate through the string to break down the input by element
      for(int k =0; k < s.length();k++){
         char currChar = s.charAt(k);

         //if we come across a + or a -, anything coming before it , up to the previous + or - is a term

          if(!isOperator(currChar)) {
            sect+= ""+ currChar;

         }else {
             //if two operators are next to each other, it creates a white space. This avoids it.
             if(!sect.equals("")) {
                list.add(sect);
                sect = "";

             }

             list.add(""+currChar);

          }
      }
      list.add(sect);


   }

   private int precedence(String inList){

      if(inList.equals("+") ||  inList.equals("-")){
         return  1;

      }else if (inList.equals("*") ||  inList.equals("/")){
         return 2;

      }

      return -1;
   }

   private ArrayList<String> infixToPostfix(ArrayList<String> list){

      Stack<String> stack = new Stack<>();
      ArrayList<String> pfix = new ArrayList<>();

      for(int i = 0; i < list.size();i++){

         if(isOperator(list.get(i))){
            while(!stack.isEmpty() && precedence(stack.peek()) >= precedence(list.get(i))){
               pfix.add(stack.pop());
            }
            stack.push(list.get(i));

         }else if(list.get(i).equals(")")){
            String temp = stack.pop();
            while(!temp.equals("(")){
               pfix.add(temp);
               temp = stack.pop();
            }
         }else if(list.get(i).equals("(")){
            stack.push(list.get(i));
         }else{
            pfix.add(list.get(i));
         }

      }
      while (!stack.empty()){
         pfix.add(stack.pop());
      }
      return pfix;


   }





}
