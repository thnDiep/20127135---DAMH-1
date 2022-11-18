import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();

        dictionary.readFile("slang.txt");

        dictionary.printMap();
    }
}
