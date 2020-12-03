import java.util.ArrayList;
import java.util.Stack;

public class Expression extends ExpressionTree {

   public String fullyParenthesized() {
      // add implementation here
      return "";
   }

   public Expression(String s) {
      super();
      // add implementation here
      ArrayList<String> list = new ArrayList<>();
      ArrayList<String> postFix = new ArrayList<>();

      splitStringToList(s,list);
      postFix = infixToPostfix(list);

      for(int i =0; i < postFix.size(); i++){
         System.out.println(postFix.get(i));
      }



      Stack<BNode<String>> stk = new Stack<>();
      try {
         /*
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

          */

         for(int x = 0; x < postFix.size(); x++){
            if(!isOperator(postFix.get(x))){
               stk.push(new BNode<>(postFix.get(x),null,null,null));

            }else{
               BNode<String> root  = new BNode<String>(postFix.get(x),null,null,null);
               this.root = root;
               root.right = stk.pop();
               root.left= stk.pop();
               stk.push(root);

            }

         }
         this.addRoot(root.data);




      }catch (Exception e){
         System.out.println("");
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

      return currChar.equals("+") || currChar.equals("-")  || currChar.equals("*")|| currChar.equals("/") || currChar.equals("(") || currChar.equals(")");
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
      System.out.println(s);


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

   private boolean isGreaterPrecedence(String inList, String inStack){
     int listValue = 0;
     int stackValue = 0;
      if(inList.equals("+") ||  inList.equals("-")){
         listValue = 1;

      }else if (inList.equals("*") ||  inList.equals("/")){
         listValue= 2;

      }

      if(inStack.equals("+") ||  inStack.equals("-")){
         stackValue = 1;

      }else if (inStack.equals("*") ||  inStack.equals("/")){
         stackValue = 2;

      }
      return listValue <= stackValue;
   }

   private ArrayList<String> infixToPostfix(ArrayList<String> list){
      String sect = "";
      Stack<String> s = new Stack<>();
      ArrayList<String> pfix = new ArrayList<>();

      for(int i = 0; i < list.size() ;i++){

         //System.out.println(list.get(i));
         if(!isOperator(list.get(i))){
            //sect+= list.get(i);
            pfix.add(list.get(i));
           // System.out.println("not operator"+ sect);
         }else if(isOperator(list.get(i))){
            while(!s.empty() && isGreaterPrecedence(list.get(i),s.peek()) ){
               //sect +=  s.pop();
               pfix.add(s.pop());
               //s.pop();
               //System.out.println("check precedence" + sect);
            }
            s.push(list.get(i));

         }else if(list.get(i).equals("(")){
            s.push(list.get(i));

         }else if (list.get(i).equals(")")){
            while(!s.empty() && !s.peek().equals("(")){
               //sect+= s.pop();
               pfix.add(s.pop());


            }
            s.pop();//removes the extra remaining ( in the stack

         }


      }
      while (!s.empty()){
         //sect+= s.pop();
         pfix.add(s.pop());
      }
      System.out.println(s.toString());
      System.out.println(pfix.toString());

      return pfix;
   }


}
