import java.io.*;
import java.util.*;

public class Dictionary {
    private Map<String, Set<String>> dictionary;
    private Map<String, Set<String>> subDictionary;
    private String[] options;

    public Dictionary() {
        this.dictionary = new HashMap<String, Set<String>>();
        this.options = new String[]{"Overwrite", "Duplicate"};
    }

    public void readFile(String fileName) throws IOException {
        BufferedReader bw;
        try {
            bw = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf8"));
        } catch (FileNotFoundException exc) {
            System.out.println("File Not Found");
            return;
        }

        String previousKey = "";
        while (true) {
            String str = bw.readLine();
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
    }

    // Fearture 1
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
        if(slangWord.trim().length() == 0)
            return false;

        Set<String> definitions;
        String keyExist = "";

        for(String key : dictionary.keySet()) {
            if(key.toLowerCase().equals(slangWord.toLowerCase())){
                keyExist = key;
            }
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

        for(String key : dictionary.keySet()) {
            if(key.toLowerCase().equals(slangWord.toLowerCase())){
                keyExist = key;
            }
        }

        if (keyExist.length() == 0)
            return false;

        definitions = dictionary.get(keyExist);
        definitions.clear();
        definitions.add(definition);
        return true;
    }

    public void printMap(Map<String, Set<String>> map) {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.print("Values: ");
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.print(entry.getValue().toArray()[i]);
                if (i < entry.getValue().size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
        }
    }

    public Map<String, Set<String>> getSubDictionary() {
        return subDictionary;
    }

    public String[] getOptions() {
        return options;
    }
}
