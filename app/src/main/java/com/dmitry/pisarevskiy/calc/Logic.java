package com.dmitry.pisarevskiy.calc;

public class Logic {
    static float result(String text) throws IllegalArgumentException {
        if (text.charAt(0)=='(' && text.charAt(text.length()-1)==')' && checkedBrackets(text.substring(1,text.length()-1))) {
            return (result(text.substring(1,text.length()-1)));
        }
        if (text.charAt(0)=='+' || text.charAt(0)=='-') {
            return (result("0"+text));
        }
        if (text.charAt(text.length()-1)=='+' || text.charAt(text.length()-1)=='-') {
            return (result(text+"0"));
        }
        if (isFloat(text)) {
            return Float.parseFloat(text);
        }
        if (splitBySign(text,'+').length==2) {
            return (result(splitBySign(text,'+')[0]) + result(splitBySign(text,'+')[1]));
        }
        if (splitBySign(text,'-').length==2) {
            return (result(splitBySign(text,'-')[0]) - result(splitBySign(text,'-')[1]));
        }
        if (splitBySign(text,'*').length==2) {
            return (result(splitBySign(text,'*')[0]) * result(splitBySign(text,'*')[1]));
        }
        if (splitBySign(text,'/').length==2) {
            return (result(splitBySign(text,'/')[0]) / result(splitBySign(text,'/')[1]));
        }
        throw new IllegalArgumentException();
    }

    static String textResult(String text) {
        String s;
        try {
            s = String.valueOf(result(text));
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException | NullPointerException e) {
            s = "Неверное выражение";
        }
        return s;
    }

    public static boolean isFloat(String value) {
        return value.matches("\\d+(\\.\\d+)?");
    }

    public static String[] splitBySign(String text, char sign) {
        int i=0;
        int bracketsCounter=0;
        while (i<text.length()) {
            if (text.charAt(i)=='(') {
                bracketsCounter=1;
                while (bracketsCounter!=0) {
                    i++;
                    if (text.charAt(i)=='(') {
                        bracketsCounter+=1;
                    }
                    if (text.charAt(i)==')') {
                        bracketsCounter-=1;
                    }
                }
            }
            if (text.charAt(i)==sign) {
                return new String[]{text.substring(0,i),text.substring(i+1,text.length())};
            }
            i++;
        }
        return new String[]{text};
    }

    public static boolean checkedBrackets(String text) {
        int bracketsCounter=0;
        int i=0;
        while (i<text.length()) {
            if (text.charAt(i)=='(') {
                bracketsCounter+=1;
            }
            if (text.charAt(i)==')') {
                bracketsCounter-=1;
            }
            if (bracketsCounter<0) {
                return false;
            }
            i++;
        }
        return true;
    }
}
