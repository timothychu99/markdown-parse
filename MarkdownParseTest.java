import static org.junit.Assert.*; //import junit lib
import org.junit.*; //import junit lib

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;

public class MarkdownParseTest {
    Path fileName = Path.of("test-file8.md");
    String contents;
    ArrayList<String> testLinks = new ArrayList<>();

    @Before
    public void setup() throws IOException{
        contents = Files.readString(fileName);
    }

    @Test //tells junit there is start to a test
    public void addition() { //method to test
        assertEquals(2, 1 + 1); //see if 1+1 = 2
    }
    @Test
    public void customTest() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents);
        //testLinks.add("https://something.com");
        //testLinks.add("some-page.html");
        //testLinks.add("page.com");
        assertEquals(testLinks, linksReturned);
    }
}
