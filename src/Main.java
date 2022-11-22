public class Main {
    public static void main(String[] args) {
        String s = "Системный пароль изменен";
        String k1 = "Сканер";
        int k2 = 4123;
        System.out.println("Исходная строка: " + s);
        //long time = System.currentTimeMillis();
        System.out.println("Зашифрованная строка: " + (s = DoubleKeySwapEncryption.encode(s, k1, k2)));
        //System.out.println("Время выполнения шифрования: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println("Дешифрованная строка: " + (s = DoubleKeySwapEncryption.decode(s, k1, k2)));
    }
}
