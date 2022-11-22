import java.util.Arrays;
import java.util.stream.Collectors;

public class DoubleKeySwapEncryption {
    public static <T> String encode(String s, T key1, T key2) {
        if (removeDuplicates(key1.toString()).length() *
                removeDuplicates(key2.toString()).length() < s.length())
            return "";

        int columns = key1.toString().length(), rows = key2.toString().length();
        int[] fKey, sKey;
        char[] chars = new char[rows * columns];

        String res = "";
        s = s.toLowerCase();
        fKey = formatKey(key1);
        sKey = formatKey(key2);

        int minInd = 0;
        for (int i = 0; i < columns; i++) {
            minInd = getIndexOfMin(fKey);
            fKey[minInd] = Integer.MAX_VALUE;
            for (int j = 0; j < rows; j++)
                chars[j * columns + i] = s.charAt(j * columns + minInd);
        }
        s = new String(chars);

        minInd = 0;
        for (int i = 0; i < rows; i++) {
            minInd = getIndexOfMin(sKey);
            sKey[minInd] = Integer.MAX_VALUE;
            for (int j = 0; j < columns; j++)
                chars[j + columns * i] = s.charAt(j + columns * minInd);
        }
        return new String(chars);
    }

    public static <T> String decode(String s, T key1, T key2) {
        if (removeDuplicates(key1.toString()).length() *
                removeDuplicates(key2.toString()).length() < s.length())
            return "";

        int columns = key1.toString().length(), rows = key2.toString().length();
        int[] fKey, sKey;
        char[] chars = new char[rows * columns];

        s = s.toLowerCase();
        fKey = formatKey(key1);
        sKey = formatKey(key2);

        int minInd = 0;
        for (int i = 0; i < rows; i++) {
            minInd = getIndexOfMin(sKey);
            sKey[minInd] = Integer.MAX_VALUE;
            for (int j = 0; j < columns; j++)
                chars[j + columns * minInd] = s.charAt(j + columns * i);
        }
        s = new String(chars);

        minInd = 0;
        for (int i = 0; i < columns; i++) {
            minInd = getIndexOfMin(fKey);
            fKey[minInd] = Integer.MAX_VALUE;
            for (int j = 0; j < rows; j++)
                chars[j * columns + minInd] = s.charAt(j * columns + i);
        }
        return new String(chars);
    }

    public static <T> int[] formatKey(T key) {
        String temp = "";
        if (key instanceof String)
            temp = removeDuplicates(key.toString().toLowerCase());
        else if (key instanceof Integer)
            temp = removeDuplicates(key.toString());
        else if (key instanceof Double)
            temp = removeDuplicates(String.valueOf((int) Math.floor((Double) key)));
        else
            throw new IllegalArgumentException();
        int[] res;
        res = getNumbers(temp);
        return res;
    }

    public static int getIndexOfMin(int[] key) {
        int min = 0;
        for (int i = 0; i < key.length; i++)
            if (key[i] < key[min])
                min = i;

        return min;
    }

    public static int[] getNumbers(String key) {
        int[] numbers = new int[key.length()];
        StringBuilder exc = new StringBuilder();

        for (int i = 1; i <= key.length(); i++) {
            int min = Integer.MAX_VALUE;
            int minInd = -1;
            for (int j = 0; j < key.length(); j++) {
                if (key.charAt(j) < min
                        && !exc.toString().contains(String.valueOf(j))) {
                    min = key.charAt(j);
                    minInd = j;
                }
            }
            exc.append(minInd);
            numbers[minInd] = i;
        }
        return numbers;
    }

    public static String removeDuplicates(String key) {
        return Arrays.stream(key.split(""))
                .distinct()
                .collect(Collectors.joining());
    }
}