package com.company;

//use to calculate 绩点 from 成绩

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("input your number of how many subjects you have:");
        int len = Integer.parseInt(in.nextLine());//多少个科目
        double[] score = new double[len];//学分数组
        System.out.println("input your score(学分) of each subject,one score in each line:");

        for (int i = 0; i < len; i++) {
            score[i] = Double.parseDouble(in.nextLine());
        }
        double sumscore = Arrays.stream(score).sum();//总修学分
        double[] mark = new double[len];
        System.out.println("input your mark(成绩) of each subject,one mark in each line:");

        for (int i = 0; i < len; i++) {
           String temp = in.nextLine();
            if ("及格".equals(temp)) {
                mark[i] = 1.0;
            }else if ("通过".equals(temp)) {
                mark[i] = 1.0;
            }
            else if ("良".equals(temp)) {
                mark[i] = 2.0;
            }else if ("中".equals(temp)) {
                mark[i] = 3.0;
            }else {
                temp = temp.replaceAll("[^0-9]", "");
                mark[i] = (Double.parseDouble(temp)-50)/10.0;
            }
        }

        double total = 0;
        for (int i = 0; i < len; i++) {
            total += mark[i] * score[i]/sumscore;

        }
        System.out.println(total);






    }
}
