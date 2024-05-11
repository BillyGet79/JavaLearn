package jikaoTest.honorTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CheckString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        HashSet<String> hashSet = new HashSet<>();
        while (true) {
            in.nextToken();
            String input = in.sval;
            if (input.isEmpty()) {
                break;
            }
            hashSet.add(input);
        }

        PrintString(out, hashSet);
        out.flush();
    }

    public static void PrintString(PrintWriter out, HashSet<String> set) {
        List<String> clear = new ArrayList<>();
        List<String> unclear = new ArrayList<>();
        for (String s : set) {
            char[] str = s.toCharArray();
            boolean isunclear = false;
            for (char c : str) {
                if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                    unclear.add(s);
                    isunclear = true;
                    break;
                }
            }
            if (!isunclear) {
                clear.add(s);
            }
        }
        for (String s : clear) {
            out.print(s + " ");
        }
        out.println();
        for (String s : unclear) {
            out.print(s + " ");
        }
    }
}
