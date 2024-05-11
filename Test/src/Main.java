import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String result = scanner.nextLine();
        System.out.println("Результат выполнения: " + calc(result));
    }

    public static String calc(String input) throws Exception {
        int x;
        int y;
        int intResult;
        String sym;
        boolean rim = false;
        String[] arabic = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] iRoman = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] symbol = {"+", "-", "*", "/"};

        String[] array = input.split(" ");
        if (array.length != 3) throw new Exception("Введены неверные данные");

        if (checkArab(arabic, array[0]) & checkArab(arabic, array[2]) & checkSymbol(symbol, array[1])) {
            x = Integer.parseInt(array[0]);
            y = Integer.parseInt(array[2]);
            sym = array[1];
        } else if (checkRoman(roman, array[0]) & checkRoman(roman, array[2]) & checkSymbol(symbol, array[1])) {
            x = romToInt(array[0], roman);
            y = romToInt(array[2], roman);
            sym = array[1];
            rim = true;
        } else {
            throw new Exception("Введены неверные данные");
        }
        intResult = action(x, y, sym);
        if (rim) {
            return intToRom(intResult, roman, iRoman);
        } else {
            return (Integer.toString(intResult));
        }
    }

    static int action(int a, int b, String str) {
        int res = 0;
        switch (str) {
            case "+" -> res = a + b;
            case "-" -> res = a - b;
            case "*" -> res = a * b;
            case "/" -> res = a / b;
        }
        return res;
    }

    static boolean checkArab(String[] arr, String str) {
        return Arrays.asList(arr).contains(str);
    }

    static boolean checkRoman(String[] arr, String str) {
        return Arrays.asList(arr).contains(str);
    }

    static boolean checkSymbol(String[] arr, String str) {
        return Arrays.asList(arr).contains(str);
    }

    static int romToInt(String s, String[] arr) {
        int rom = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(s)) {
                rom = i + 1;
            }
        }
        return rom;
    }

    static String intToRom(int resInt, String[] ed, String[] des) throws Exception {
        int e;
        int d;
        if (resInt == 100) {
            return "C";
        } else if (resInt < 100 & resInt >= 10) {
            e = resInt % 10;
            d = resInt / 10;
            if (e == 0) return des[d - 1];
            else return (des[d - 1] + ed[e - 1]);
        } else if (resInt < 10 & resInt > 0) {
            return (ed[resInt - 1]);
        }
        throw new Exception("Римские числа не могут быть менее 1");
    }
}