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
        runTest("TestCase1.txt", "AnswerCase1.txt");
    }

    @Test
    void test2() throws IOException {
        runTest("TestCase2.txt", "AnswerCase2.txt");
    }

    @Test
    void test3() throws IOException {
        runTest("TestCase3.txt", "AnswerCase3.txt");
    }

    @Test
    void test4() throws IOException {
        runTest("TestCase4.txt", "AnswerCase4.txt");
    }

    @Test
    void test5() throws IOException {
        runTest("TestCase5.txt", "AnswerCase5.txt");
    }

    void runTest(String testCaseName, String answerName) throws IOException {
        String absolutePath = new File("src/test/resources/").getAbsolutePath();
        Main.main(new String[]{absolutePath + "/" + testCaseName});
        String answer = new String(getClass().getClassLoader().getResourceAsStream(answerName).readAllBytes());
        assertEquals(outContent.toString(), answer);
    }
}