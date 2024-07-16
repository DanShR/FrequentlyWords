package kz.dan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void test1() throws IOException {
        String absolutePath = new File("src/test/resources").getAbsolutePath();
        Main.main(new String[]{absolutePath + "/TestCase1.txt"});

        String answer = new String(getClass().getClassLoader().getResourceAsStream("AnswerCase1.txt").readAllBytes());
        assertEquals(outContent.toString(), answer);
    }

    @Test
    void test2() throws IOException {
        String absolutePath = new File("src/test/resources").getAbsolutePath();
        Main.main(new String[]{absolutePath + "/TestCase2.txt"});

        String answer = new String(getClass().getClassLoader().getResourceAsStream("AnswerCase2.txt").readAllBytes());
        assertEquals(outContent.toString(), answer);
    }

    @Test
    void test3() throws IOException {
        String absolutePath = new File("src/test/resources").getAbsolutePath();
        Main.main(new String[]{absolutePath + "/TestCase3.txt"});

        String answer = new String(getClass().getClassLoader().getResourceAsStream("AnswerCase3.txt").readAllBytes());
        assertEquals(outContent.toString(), answer);
    }

    @Test
    void test4() throws IOException {
        String absolutePath = new File("src/test/resources").getAbsolutePath();
        Main.main(new String[]{absolutePath + "/TestCase4.txt"});

        String answer = new String(getClass().getClassLoader().getResourceAsStream("AnswerCase4.txt").readAllBytes());
        assertEquals(outContent.toString(), answer);
    }
}