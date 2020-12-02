import java.util.ArrayList;

public class Expression extends ExpressionTree {

   public String fullyParenthesized() {
      // add implementation here
      return "";
   }

   public Expression(String s) {
      super();
      // add implementation here
      ArrayList<String> list = new ArrayList<>();

      splitStringToList(s,list);

      for(int i =0; i < list.size(); i++){
         System.out.println(list.get(i));
      }

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

   //----------------------------------------- Private Helper Methods -------------------------------//

   private boolean isOperator(char currChar){
      //remove the space from the operator

      return currChar == '+' || currChar =='-' || currChar == '*' || currChar =='/' || currChar == '(' || currChar ==')';
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

             }
             sect = "";
             list.add(""+currChar);
          }
      }





   }


}
