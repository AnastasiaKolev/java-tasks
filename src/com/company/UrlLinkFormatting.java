package com.company;

public class UrlLinkFormatting {

    //Url link forming: time O(N), memory O(1)
    public static void main(String[] args) {
        String string = " My url consists of 6 words! ";

        //count reserved spaces for replacement ' ' => "%20"
        int spacesCount = 0;
        for (char i : string.toCharArray()) {
            if (i == ' ') {
                spacesCount += 2;
            }
        }

        StringBuilder reserved = new StringBuilder().append( string );

        for (int i = 0; i < spacesCount; i++) {
            reserved.append( "_" );
        }

        System.out.println("Forming a string with reserved spaces: \n" + spacesCount + "\tspaces added. " +
                "\nNEW string: \t" + reserved.toString());

        char[] res = reserved.toString().toCharArray();
        int lastPos = res.length - 1;
        int countReplacements = 0;
        for (int i = res.length - 1; i >= 0 ; i--) {
            if (res[i] == ' ') {
                if (countReplacements == 0) {
                    res[lastPos] = '0';
                    i++;
                }
                if (countReplacements == 1) {
                    res[lastPos] = '2';
                    i++;
                }
                if (countReplacements == 2) {
                    res[lastPos] = '%';
                    countReplacements = 0;
                    lastPos--;
                    continue;
                }
                countReplacements++;
                lastPos--;
            }
            if (res[i] != '_' && res[i] != ' ' && i != 0 && res[i] != '2' && res[i] != '0') {
                if (lastPos == i) {
                    break;
                } else {
                    res[lastPos] = res[i];
                    res[i] = ' ';
                    lastPos--;
                }
            }

        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }
    }
}