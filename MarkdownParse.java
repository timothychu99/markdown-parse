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
            int start = line.indexOf("](");
            int end = 0;
            if(start > -1){
                end = line.substring(start).indexOf(")");
                System.out.println(line.substring(start + 2, start + end));
                toReturn.add(line.substring(start + 2, start + end));
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