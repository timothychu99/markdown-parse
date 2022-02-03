/*// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )

        String[] lines = markdown.split("\n");

        for(String line: lines){
            int start = line.indexOf("](");
            int openparCounter = 0;
            int parstart = line.indexOf("](") + 2; //starts index at ](
            int endpar;
            
            if(start > -1){
                while(line.substring(parstart).contains("(")){
                    openparCounter++;
                    parstart += (line.substring(parstart).indexOf("(") + 1);
                }

                if(openparCounter == 0){ 
                    endpar = line.substring(start).indexOf(")") + 1;
                }else{
                    //updates paranthesis to the amount of 
                    endpar = line.substring(start).indexOf(")") + 1;            
                    for(int i = 0; i <= openparCounter; i++){
                        if(line.substring(endpar + 1).contains(")")){
                            endpar += line.substring(endpar + 1).indexOf(")") + 1;                    
                        }else{
                            break;
                        }
                    }
                }
                toReturn.add(line.substring(start + 2, start + endpar - 1));
            }
        }
        return toReturn;        
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
*/

// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<Integer> openBraketIndexes = new ArrayList<>();
        ArrayList<Integer> openParenIndexes = new ArrayList<>();
        ArrayList<Integer> closeBracketIndexes = new ArrayList<>();
        ArrayList<Integer> closeParenIndexes = new ArrayList<>();
        //make and arraylist of all of the indexes of the occurances of the 
        //different [, ], (, and )

        //for the [
        int currentIndex = 0;
        while(currentIndex < markdown.lastIndexOf("[")) {
            int openBracketIndex = markdown.indexOf("[", currentIndex);
            openBraketIndexes.add(openBracketIndex);
            currentIndex = openBracketIndex + 1;
        }
        //for ]
        currentIndex = 0;
        while(currentIndex < markdown.lastIndexOf("]")) {
            int closeBracketIndex = markdown.indexOf("]", currentIndex);
            closeBracketIndexes.add(closeBracketIndex);
            currentIndex = closeBracketIndex + 1;
        }
        //for (
        currentIndex = 0;
        while(currentIndex < markdown.lastIndexOf("(")) {
            int openParenIndex = markdown.indexOf("(", currentIndex);
            openParenIndexes.add(openParenIndex);
            currentIndex = openParenIndex + 1;
        }
        //for )
        currentIndex = 0;
        while(currentIndex < markdown.lastIndexOf(")")) {
            int closeParenIndex = markdown.indexOf(")", currentIndex);
            closeParenIndexes.add(closeParenIndex);
            currentIndex = closeParenIndex + 1;
        }

        for(int i = 0; i < openParenIndexes.size(); i++){
            if(markdown.charAt(openParenIndexes.get(i) - 1) != ']'){
                openParenIndexes.remove(i);
                i--;
            }
        }

        /**
         * basically get an Integer Array of all of the indexes of [, ],(, and )
         * to then compare the contents between each bracket and each parenthesis
         * 
         * Note:ignore check previous index if index == 0. if it's in the [ list then
         *      skip that index and go to the next in the list elsewise remove the index
         *      from the list
         * 
         * 1.   Remove all indexes of [ where the previous character is ! to remove
         *      the image case. ()
         * 2.   check all closed brackets to see if the next character is either (
         *      or [ due to how markdown reads links as [text](link) or
         *      [text][reference]
         * 3.   if it follows the [text][reference] type, save the references into a
         *      ArrayList<String> 
         *              note: do the loops above to check if there's any "]: " to 
         *                    see if it uses references and to mark where references could be
         * 4.   create data class indexPair(int indexOpen, int indexClosed) and add all the
         *      indexes of [ and ] that aren't disqualified previously
         * 5.   disqualify pairs based on whether they contain
         */

        for(int openParen:openParenIndexes){
            toReturn.add(markdown.substring(openParen+1, markdown.indexOf(")",openParen)));
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}