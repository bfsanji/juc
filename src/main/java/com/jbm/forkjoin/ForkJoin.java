package com.jbm.forkjoin;

/**
 * 求和计算的任务
 */
public class ForkJoin {
    private Long start;
    private Long end;
    private  Long temp = 10000L;

    public static void main(String[] args) {

        int sum = 0;
        for (int i = 1; i <=1000 ; i++) {
            sum+=1;
        }
        System.out.println(sum);
    }
}
