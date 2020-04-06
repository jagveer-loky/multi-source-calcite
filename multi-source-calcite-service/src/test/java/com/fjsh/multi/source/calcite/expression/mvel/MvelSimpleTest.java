package com.fjsh.multi.source.calcite.expression.mvel;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mvel2.MVEL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description：
 * @Date: Created in :21/6/2018 4:39 PM
 * @Modified by:
 * http://mvel.documentnode.com
 */
public class MvelSimpleTest  {
    @Before
    public void setup(){

    }
    @Test
    public void simpleplus(){
        String formula = "A * B - (C -D) + E.pow(F) + G / H";

        Map<String, Object> variables = new HashMap<>();
        variables.put("A", "20");
        variables.put("B", 101L);
        variables.put("C", 30.0D);
        variables.put("D", 20);
        variables.put("E", new BigDecimal("20"));
        variables.put("F", "3");
        variables.put("G", "20");
        variables.put("H", "2");

        Serializable exp = MVEL.compileExpression(formula);
        System.out.println(MVEL.executeExpression(exp, variables, double.class));
        double start=System.currentTimeMillis();
        for(int i=1;i<10000;i++){
            MVEL.executeExpression(exp, variables, double.class);
        }
        double expF=System.currentTimeMillis();
        System.out.println(formula+"耗时:"+(expF-start)/10000+"ms");
    }
    @Test
    public void simpleplusSec(){
        String formula = "A * B - (C -D) + E + G / H";

        Map<String, Object> variables = new HashMap<>();
        variables.put("A", "20");
        variables.put("B", 101L);
        variables.put("C", 30.0D);
        variables.put("D", 20);
        variables.put("E", new BigDecimal("20"));
        variables.put("F", "3");
        variables.put("G", "20");
        variables.put("H", "2");

        Serializable exp = MVEL.compileExpression(formula);
        System.out.println(MVEL.executeExpression(exp, variables, double.class));
        double start=System.currentTimeMillis();
        for(int i=1;i<10000;i++){
            MVEL.executeExpression(exp, variables, double.class);
        }
        double expF=System.currentTimeMillis();
        System.out.println(formula+"耗时:"+(expF-start)/10000+"ms");
    }
    @Test
    public  void dealFeedbackExtractor() {

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("comment_cnt_day30", 2);
        paramMap.put("reply_cnt_day30", 10);
        paramMap.put("img_cnt_day30", 12);
        paramMap.put("good_comment_cnt_day30", 16);
        paramMap.put("star_five_cnt_day30", 18);

        String expression1 = "if (comment_cnt_day30 > 0) {comment_cnt_day30} else {0}";
        Serializable exp = MVEL.compileExpression(expression1);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));
        double start=System.currentTimeMillis();
        for(int i=1;i<10000;i++){
            MVEL.executeExpression(exp, paramMap, double.class);
        }
        double expF=System.currentTimeMillis();
        System.out.println(expression1+"耗时:"+(expF-start)/10000+"ms");

        String expression2 = "if (comment_cnt_day30 > 0) {reply_cnt_day30 / comment_cnt_day30} else {0}";
        exp = MVEL.compileExpression(expression2);
        for(int i=1;i<10000;i++){
            MVEL.executeExpression(exp, paramMap, double.class);
        }
        double expS=System.currentTimeMillis();
        System.out.println(expression2+"耗时:"+(expS-expF)/1000+"ms");

        String expression3 = "if (comment_cnt_day30 > 0) { img_cnt_day30 / comment_cnt_day30} else {0}";
        exp = MVEL.compileExpression(expression3);
        for(int i=1;i<10000;i++){
            MVEL.executeExpression(exp, paramMap, double.class);
        }
        double expT=System.currentTimeMillis();
        System.out.println(expression1+"耗时:"+(expT-expS)/10000+"ms");
    }
}
