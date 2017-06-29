package com.example.a29149.thb;

/**
 * Created by 张丽华 on 2017/5/5.
 * Description:
 */

public class Convert {
    public static String tenConvertToBin(String value)
    {
        String result = "";

        int num = Integer.parseInt(value);

        while(num != 0)
        {
            int reminder = num%2;
            num = (num-reminder)/2;
            result+=reminder+"";
        }
        //Builder线程不安全
        result = new StringBuilder(result).reverse().toString();
        return result;
    }

    public static String binConvertToTen(String value)
    {
        int result = 0;

        for(int i = 0; i<value.length();i++)
        {
            int num = Integer.parseInt(value.charAt(i)+"");
            result += num*Math.pow(2, value.length()-1-i);
        }

        return result+"";
    }

    public static String tenConvertToHex(String value)
    {
        String result = "";
        int num = Integer.parseInt(value);

        while(num != 0)
        {
            int reminder = num%16;
            num = (num-reminder)/16;

            if (reminder > 9)
                result += hex(reminder);
            else
                result += reminder+"";
        }
        //Builder线程不安全
        result = new StringBuilder(result).reverse().toString();
        return result;
    }

    public static String hexConvertToTen(String value)
    {
        int result =0;
        value = value.toUpperCase();
        for(int i = 0; i<value.length(); i++)
        {
            int num = ten(value.charAt(i));
            result += num*Math.pow(16, value.length()-1-i);
        }
        return result+"";
    }

    public static String hex(int num)
    {
        String result= "";
        switch(num)
        {
            case 10:
                result ="A";
                break;
            case 11:
                result = "B";
                break;
            case 12:
                result = "C";
                break;
            case 13:
                result = "D";
                break;
            case 14:
                result = "E";
                break;
            case 15:
                result = "F";
                break;
        }
        return result;
    }

    public static int ten(char index)
    {
        switch(index)
        {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;

        }
        return 0;
    }

}
