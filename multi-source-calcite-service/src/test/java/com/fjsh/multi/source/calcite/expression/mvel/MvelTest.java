package com.fjsh.multi.source.calcite.expression.mvel;

import com.google.common.collect.Maps;
import org.mvel2.MVEL;

import java.io.Serializable;
import java.util.Map;

/**
 * mvel表达式使用相对比较方便，也可以使用java或者其他包的方法，灵活性比较好，但是得再clas中提前预加入包全名
 * http://mvel.documentnode.com
 * */
public class MvelTest {

    public static void dealFeedbackExtractor() {

        Map<String, Object> paramMap = Maps.newHashMap();

        paramMap.put("comment_cnt_day30", 2);
        paramMap.put("reply_cnt_day30", 10);
        paramMap.put("img_cnt_day30", 12);
        paramMap.put("good_comment_cnt_day30", 16);
        paramMap.put("star_five_cnt_day30", 18);

        String expression1 = "if (comment_cnt_day30 > 0) {comment_cnt_day30} else {0}";
        Serializable exp = MVEL.compileExpression(expression1);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));

        String expression2 = "if (comment_cnt_day30 > 0) {reply_cnt_day30 / comment_cnt_day30} else {0}";
        exp = MVEL.compileExpression(expression2);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));

        String expression3 = "if (comment_cnt_day30 > 0) { img_cnt_day30 / comment_cnt_day30} else {0}";
        exp = MVEL.compileExpression(expression3);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));

        String expression4 = "if (comment_cnt_day30 > 0) { good_comment_cnt_day30 / comment_cnt_day30} else {0}";
        exp = MVEL.compileExpression(expression4);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));

        String expression5 = "if (comment_cnt_day30 > 0) { star_five_cnt_day30 / comment_cnt_day30} else {0}";
        exp = MVEL.compileExpression(expression5);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));
    }

    public static void dealAllViewInfoExtractor() {
        Map<String, Object> paramMap = Maps.newHashMap();

        paramMap.put("city_id", -1024);
        paramMap.put("reqCityId", 1);
        paramMap.put("view_cnt_ht28", 10);

        // "L_DEAL_ALLCITY_VIEW_HT28"
        String expression1 = "if (city_id == -1024) { view_cnt_ht28 / 100.0 } else {-1}";
        Serializable exp = MVEL.compileExpression(expression1);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));

        // L_DEAL_CITY_VIEW_HT28
        paramMap.put("city_id", "1");
        String expression2 = "if (city_id == reqCityId) { view_cnt_ht28 / 10.0} else {-1}";
        exp = MVEL.compileExpression(expression2);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));
    }

    public static void dealAllSoldInfoExtractor() {
        Map<String, Object> paramMap = Maps.newHashMap();

        paramMap.put("city_id", -1024);
        paramMap.put("reqCityId", 1);
        paramMap.put("pay_cnt_ht28", 10);

        // "L_DEAL_ALLCITY_PAY_HT28"
        String expression1 = "if (city_id == -1024) { pay_cnt_ht28 / 10.0 } else {-1}";
        Serializable exp = MVEL.compileExpression(expression1);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));

        // "L_DEAL_CITY_PAY_HT28"
        paramMap.put("city_id", "1");
        String expression2 = "if (city_id == reqCityId) { pay_cnt_ht28 } else {-1}";
        exp = MVEL.compileExpression(expression2);
        System.out.println(MVEL.executeExpression(exp, paramMap, double.class));
    }


    public static void main(String[] args) {
        MvelTest.dealFeedbackExtractor();
        System.out.println("===============");
        MvelTest.dealAllViewInfoExtractor();
        System.out.println("===============");
        MvelTest.dealAllSoldInfoExtractor();
        System.out.println("===============");

    }

}
