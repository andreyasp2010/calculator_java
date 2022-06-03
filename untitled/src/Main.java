import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        print("Введите выражение");

        String text = scan.readLine().toUpperCase();

        if(!text.matches("(([IVXLCDM]+)|\\d+)[+-/*](([IVXLCDM]+)|\\d+)")){
            throw new Exception ("строка не является математической операцией");
        }

        if(text.matches("((\\d+)[+-/*][IVXLCDM]+)|([IVXLCDM]+[+-/*](\\d+))")){
            throw new Exception ("используются одновременно разные системы счисления");
        }

        if(text.matches("[IVXLCDM]+[-][IVXLCDM]+")){
            throw new Exception ("в римской системе нет отрицательных чисел");
        }

        Integer result = calculate(text);

        print(String.valueOf(result));

        scan.close();
        print(" Closing Application ");
    }
    public static int calculate(String text) {//  a + b, a - b, a * b, a / b
        text = text.replace(" ", "").toUpperCase();

        if(text.matches("[IVXLCDM]+[+/*][IVXLCDM]+")){
            String [] sum = text.split("\\+");
            String [] multi = text.split("\\*");
            String [] division = text.split("/");

            if(sum.length == 2){
                return parseRoom(sum[0]) + parseRoom(sum[1]);
            } else if(multi.length == 2){
                return parseRoom(multi[0]) * parseRoom(multi[1]);
            } else if(division.length == 2){
                return parseRoom(division[0]) / parseRoom(division[1]);
            }
        }

        if(text.matches("\\d+[+-/*]\\d+")){
            String [] sum = text.split("\\+");
            String [] subtraction = text.split("-");
            String [] multi = text.split("\\*");
            String [] division = text.split("/");

            if(sum.length == 2){
                return parseInt(sum[0]) + parseInt(sum[1]);
            } else if(subtraction.length == 2){
                return parseInt(subtraction[0]) - parseInt(subtraction[1]);
            } else if(multi.length == 2){
                return parseInt(multi[0]) * parseInt(multi[1]);
            } else if(division.length == 2){
                return parseInt(division[0]) / parseInt(division[1]);
            }
        }

        return 0;
    }
    // I обозначает 1, V обозначает 5, X — 10, L — 50, C — 100, D — 500, M — 1000.
    public static int parseRoom(String text) {
        ArrayList<RomaSymbol> romans = new ArrayList();
        romans.add(new RomaSymbol("I", 1));
        romans.add(new RomaSymbol("IV", 4));
        romans.add(new RomaSymbol("V", 5));
        romans.add(new RomaSymbol("IX", 9));
        romans.add(new RomaSymbol("X", 10));
        romans.add(new RomaSymbol("XL", 40));
        romans.add(new RomaSymbol("XC", 90));
        romans.add(new RomaSymbol("C", 100));
        romans.add(new RomaSymbol("CD", 400));
        romans.add(new RomaSymbol("D", 500));
        romans.add(new RomaSymbol("CM", 900));
        romans.add(new RomaSymbol("M", 1000));

        int result = 0;
        int i = 0;
        while ((text.length() > 0)) {
            RomaSymbol symbol = romans.get(i);
            if (text.startsWith(symbol.name)) {
                result += symbol.value;
                text = text.substring(symbol.name.length());
            } else {
                i++;
            }
        }

        return result;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static int parseInt(String strNum) {
        return Integer.parseInt(strNum);
    }
    public static void print(String text) {
        System.out.println(text);
    }
    public static void print(int text) {
        System.out.println(text);
    }
}

class RomaSymbol{
    String name;
    Integer value;
    RomaSymbol(String name, Integer value){
        this.name = name;
        this.value = value;
    }
}