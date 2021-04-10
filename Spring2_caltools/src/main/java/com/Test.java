package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author liyan
 * @create 2021-04-2021/4/6-11:43
 */
public class Test {
    public static void main(String[] args) {
        int res=numDecodings("227");
        System.out.println(res);
//        System.out.println("226".substring(1,3));


    }
    public static int numDecodings(String s) {
            if (s.charAt(0)=='0') {
                return 0;
            }
            int n = s.length();
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i=1;i<n;i++) {
                if (s.charAt(i)=='0') {
                    if(s.charAt(i-1)=='1' || s.charAt(i-1)=='2'){
                        if (i==1) {
                            dp[i] = 1;
                        } else {
                            dp[i] = dp[i-2];
                        }
                    }
                    else{
                        return 0;
                    }
                }
                else {
                    int value = 10*(s.charAt(i-1)-'0') + (s.charAt(i)-'0');
                    if (value>=11 && value<=26) {
                        if (i==1) {
                            dp[i] = dp[0] + 1;
                        }
                        else {
                            dp[i] = dp[i-1] + dp[i-2];
                        }
                    } else {
                        dp[i] = dp[i-1];
                    }
                }
            }
            return dp[n-1];

    }
}
