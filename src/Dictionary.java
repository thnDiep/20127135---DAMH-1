import java.io.*;
import java.util.*;

public class Dictionary {
    private static final String ORIGINAL_DICTIONARY_FILENAME = "original-slang.txt";
    private static final String DICTIONARY_FILENAME = "slang.txt";
    private String[] options = {"Overwrite", "Duplicate"};

    private Map<String, Set<String>> dictionary;
    private Map<String, Set<String>> subDictionary; // save slang words searched

    public Dictionary() {
        this.dictionary = new HashMap<String, Set<String>>();

        try {
            this.readFile(DICTIONARY_FILENAME);
        } catch (IOException exc) {
            System.out.println("Read file '" + DICTIONARY_FILENAME + "' error");
        }
    }

    // Feature 1
    public void searchWordBySlangWord(String keyWord) {
        subDictionary = new HashMap<String, Set<String>>();

        for (Map.Entry<String, Set<String>> entry : dictionary.entrySet()) {
            String key = entry.getKey();

            if (key.toLowerCase().indexOf(keyWord.toLowerCase()) == 0) {
                subDictionary.put(key, entry.getValue());
            }
        }
    }

    // Feature 2
    public void searchWordByDefinition(String definition) {
        subDictionary = new HashMap<String, Set<String>>();

        for (Map.Entry<String, Set<String>> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            Set<String> values = entry.getValue();

            for (int i = 0; i < values.size(); i++) {
                if (values.toArray()[i].toString().toLowerCase().contains(definition.toLowerCase())) {
                    subDictionary.put(key, entry.getValue());
                }
            }
        }
    }

    // Feature 4
    public boolean add(String slangWord, String definition, String type) {
        if (slangWord.trim().length() == 0)
            return false;

        Set<String> definitions;
        String keyExist = "";

        for (String key : dictionary.keySet()) {
            if (key.toLowerCase().equals(slangWord.toLowerCase()))
                keyExist = key;
        }

        if (keyExist.length() != 0) {
            if (type == null)
                return false;

            if (type == options[0]) { // Overwrite
                definitions = dictionary.get(keyExist);
                definitions.clear();
                definitions.add(definition);
            } else if (type == options[1]) { // Duplicate
                definitions = dictionary.get(keyExist);
                definitions.add(definition);
            } else {
                System.out.println("Error type.");
                return false;
            }
        } else {
            definitions = new HashSet<String>();
            definitions.add(definition);
            dictionary.put(slangWord, definitions);
        }
        return true;
    }


    // Feature 5
    public boolean edit(String slangWord, String definition) {
        Set<String> definitions;
        String keyExist = "";

        for (String key : dictionary.keySet()) {
            if (key.toLowerCase().equals(slangWord.toLowerCase()))
                keyExist = key;
        }

        if (keyExist.length() == 0)
            return false;

        definitions = dictionary.get(keyExist);
        definitions.clear();
        definitions.add(definition);
        return true;
    }

    // Feature 6
    public boolean delete(String slangWord) {
        String keyExist = "";

        for (String key : dictionary.keySet()) {
            if (key.toLowerCase().equals(slangWord.toLowerCase()))
                keyExist = key;
        }

        if (keyExist.length() == 0)
            return false;

        dictionary.remove(keyExist);
        return true;
    }

    // Support for feature 4, 5, 6 (save new dictionary into file)
    public boolean save() {
        try {
            writeFile(DICTIONARY_FILENAME);
        } catch (IOException exc) {
            System.out.println("Write file '" + DICTIONARY_FILENAME + "' error");
            return false;
        }
        return true;
    }

    // Feature 7
    public boolean reset() {
        try {
            readFile(ORIGINAL_DICTIONARY_FILENAME);
        } catch (IOException exc) {
            System.out.println("Read file '" + ORIGINAL_DICTIONARY_FILENAME + "' error");
            return false;
        }
        return true;
    }

    // Feature 8
    public String randomSlangWord(){
        Random random = new Random();
        Object[] key = dictionary.keySet().toArray();
        String randomKey = key[random.nextInt(key.length)].toString();
        return randomKey;
    }

    public void readFile(String fileName) throws IOException {
        this.dictionary.clear();

        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf8"));
        } catch (FileNotFoundException exc) {
            System.out.println("File Not Found");
            return;
        }

        String previousKey = "";
        while (true) {
            String str = br.readLine();
            if (str == null)
                break;

            String[] keyValues = str.split("`");

            if (keyValues.length == 2) {
                String[] values = keyValues[1].split("\\| ");
                Set<String> valueSet = new HashSet<String>(values.length);

                for (String value : values) {
                    valueSet.add(value);
                }

                dictionary.put(keyValues[0], valueSet);
                previousKey = keyValues[0];
            } else {
                Set<String> valueSet = dictionary.get(previousKey);
                valueSet.add(keyValues[0]);
                dictionary.put(previousKey, valueSet);
            }
        }
        br.close();
    }

    public void writeFile(String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf8"));

        for (Map.Entry<String, Set<String>> entry : dictionary.entrySet()) {
            bw.write(entry.getKey() + "`");

            Set<String> values = entry.getValue();
            for (int i = 0; i < values.size(); i++) {
                bw.write(values.toArray()[i].toString());
                if (i < entry.getValue().size() - 1) {
                    bw.write("| ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public Map<String, Set<String>> getDictionary() {
        return dictionary;
    }

    public Map<String, Set<String>> getSubDictionary() {
        return subDictionary;
    }

    public String[] getOptions() {
        return options;
    }
}
