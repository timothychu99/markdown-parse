import static org.junit.Assert.*; //import junit lib
import org.junit.*; //import junit lib

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;

public class MarkdownParseTest {
    Path fileName;
    ArrayList<String> contents = new ArrayList<>();
    ArrayList<String> testLinks = new ArrayList<>();

    @Before
    public void setup() throws IOException{
        String file = "test-file.md";
        for(int i = 1; i <= 9; i++){
            if(i > 1) file = "test-file" + i + ".md";
            fileName = Path.of(file);
            contents.add(Files.readString(fileName));
        }
    }
    
    @Test
    public void test1() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(0));
        testLinks.add("https://something.com");
        testLinks.add("some-page.html");
        assertEquals(testLinks, linksReturned);
    }

    @Test
    public void test2() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(1));
        testLinks.add("https://something.com");
        testLinks.add("some-page.html");
        assertEquals(testLinks, linksReturned);
    }
    @Test
    public void test3() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(2));
        assertEquals(testLinks, linksReturned);
    }
    @Test
    public void test4() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(3));
        assertEquals(testLinks, linksReturned);
    }
    @Test
    public void test5() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(4));
        assertEquals(testLinks, linksReturned);
    }
    @Test
    public void test6() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(5));
        testLinks.add("page.com");
        assertEquals(testLinks, linksReturned);
    }
    @Test
    public void test7() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(6));
        assertEquals(testLinks, linksReturned);
    }
    @Test
    public void test8() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(7));
        testLinks.add("a link on the first line");
        assertEquals(testLinks, linksReturned);
    }
    @Test
    public void test9() {
        ArrayList<String> linksReturned = MarkdownParse.getLinks(contents.get(8));
        testLinks.add("page(1).com");
        assertEquals(testLinks, linksReturned);
    }
}
