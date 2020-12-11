import java.util.ArrayList;
import java.util.Iterator;
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
      Stack<Double> stack = new Stack<>();
      Double result = 0.0;
      for(int i = 0; i < postFix.size() ; i++){
         if(isDigit(postFix.get(i))){
            stack.push(Double.parseDouble(postFix.get(i)));
         }else{
            double num1 = stack.pop();
            double num2 = stack.pop();

            switch (postFix.get(i)){

               case "+":
                  result = stack.push(num2 + num1);
                  break;
               case "-":
                  result = stack.push(num2-num1);
                  break;
               case "*":
                  result = stack.push(num2*num1);
                  break;
               case "/":
                  result = stack.push(num2/num1);
                  break;
            }
         }

      }
      return result;


   }

   //----------------------------------------- Private Helper Methods -------------------------------//

   private boolean isOperator(char currChar){
      //remove the space from the operator
      return currChar == '+' || currChar =='-' || currChar == '*' || currChar =='/' ;
   }
   private boolean isOperator(String currChar){
      //remove the space from the operator
      return currChar.equals("+") || currChar.equals("-")  || currChar.equals("*")|| currChar.equals("/");
   }

   //my isDigit method accepts a string. (overloaded version of isDigit from character Class)
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

          if(!isOperator(currChar) && currChar !='(' && currChar != ')') {
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

   //tells you what the precedence of the operators is
   private int precedence(String inList){

      if(inList.equals("+") ||  inList.equals("-")){
         return  1;

      }else if (inList.equals("*") ||  inList.equals("/")){
         return 2;

      }

      return -1;
   }
   //method that is responsible for converting a an ArrayList contain infix expression and returning an arrayList containing a postfix expression.
   //each element contain a number or an operator. This was done to make sure numbers with decimal or multiple digits are not counted as two different numbers.
   private ArrayList<String> infixToPostfix(ArrayList<String> list){

      Stack<String> stack = new Stack<>();
      ArrayList<String> pfix = new ArrayList<>();

      for(int i = 0; i < list.size();i++){
         //if the current element is an operator
         if(isOperator(list.get(i))){
            while(!stack.isEmpty() && precedence(stack.peek()) >= precedence(list.get(i))){ // while  the stack isnt empty and we come across a lower precedence operator
               pfix.add(stack.pop()); //empty out the stack
            }
            stack.push(list.get(i)); //if the stack is empty or the operator in the stack is not of lower precedence , put the operator in the stack

         }else if(list.get(i).equals(")")){ //if its not an operator but it is a ")" we need to empty out the stack
            String temp = stack.pop();
            while(!temp.equals("(")){ //until we see the "(" that  goes with it
               pfix.add(temp);
               temp = stack.pop();
            }
         }else if(list.get(i).equals("(")){
            stack.push(list.get(i)); //if we see a ( , push it in the stack
         }else{
            pfix.add(list.get(i)); //or else just put in the digit in the arrayList.
         }

      }
      while (!stack.empty()){ //there are leftover operators that need to be taken care of
         pfix.add(stack.pop());
      }

      // remove the empty elements in an arraylist created when transferring the characters in the string to the arrayList.
      for (Iterator<String> iterator = pfix.iterator(); iterator.hasNext(); ) {
         String item = iterator.next();
         if(item.equals("")){
            iterator.remove();
         }
      }


      return pfix;


   }





}
