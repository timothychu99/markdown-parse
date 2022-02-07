// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
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
          boolean nextStartTrue = true;
          while(nextStartTrue){
            nextStartTrue = false;
            int start = line.indexOf("](");
            int nextStart = line.length() - 1;
            int openparCounter = 0;
            int parstart = line.indexOf("](") + 2; //starts index at ](
            int endpar;
             
            if(start > -1){
                if(line.substring(parstart).contains("](")){
                    nextStartTrue = true;
                    nextStart = line.substring(parstart).indexOf("](");
                }
                while(line.substring(parstart, nextStart).contains("(")){
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
            if(nextStartTrue){
              line = line.substring(nextStart);
            }
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
