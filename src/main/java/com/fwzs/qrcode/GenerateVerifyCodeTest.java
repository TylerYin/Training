package com.fwzs.qrcode;

import com.util.NumberFormatUtils;
import org.junit.Test;

public class GenerateVerifyCodeTest {
    @Test
    public void testGenerateVerifyCode() throws Exception {
        int range = 100;
        int modFactor = 11;

        int suma1 = 0;
        int suma2 = 0;
        int temp1, temp2;

        double[] a1 = new double[9];
        double[] a2 = new double[9];

        for (Integer i = 0; i < range; i++) {
            String num = NumberFormatUtils.format(9, 9, false, i);
            char[] numArray = num.toCharArray();
            for (int k = 1; k <= numArray.length; k++) {
                a1[k - 1] = Math.pow(2, k);
                a2[k - 1] = (a1[k - 1] % modFactor) * a1[k - 1];
            }

            for (int r = 0; r < a1.length; r++) {
                suma1 += a1[r];
            }

            for (int p = 0; p < a2.length; p++) {
                suma2 += a2[p];
            }

            temp1 = suma1 % modFactor;
            temp2 = suma2 % modFactor;
            System.out.println("data: " + i + "，suma1%11 = " + temp1 + "，suma2%11 = " + temp2);

            if (temp1 == 0) {
                System.out.println(NumberFormatUtils.format(3, 3, false, (i / temp1) + temp1));
            } else if (temp1 == 10) {
                System.out.println(NumberFormatUtils.format(2, 2, false, (i / temp1) + temp1));
            } else {
                System.out.println(NumberFormatUtils.format(3, 3, false, (i / temp1) + temp1));
            }
        }
    }
}
