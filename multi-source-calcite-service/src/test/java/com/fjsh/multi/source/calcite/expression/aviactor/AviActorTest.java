package com.fjsh.multi.source.calcite.expression.aviactor;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：
 * @Date: Created in :21/6/2018 5:51 PM
 * @Modified by:
 * http://blog.163.com/yanfuzhongboy@126/blog/static/793107942017016318384/
 * 综合语法配置方面mvel更具有优势
 * 语法更严格，效率更高
 */
public class AviActorTest {

    @Test
    public void aviTest(){
        AviatorEvaluator.setOptimize(AviatorEvaluator.EVAL);

        String expression = "a-(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // false
    }
    @Test
    public void aviSimpTest(){
        AviatorEvaluator.setOptimize(AviatorEvaluator.EVAL);
        String expression = "a-(b-c)";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        double result = (Double) compiledExp.execute(env);
        System.out.println(result);  // false
    }
    @Test
    public void aviSimpleTest(){
        AviatorEvaluator.setOptimize(AviatorEvaluator.EVAL);
        String formula = "A * B - (C -D) + E + G / H";
        Expression compiledExp = AviatorEvaluator.compile(formula);
        Map<String, Object> variables = new HashMap<>();
        variables.put("A", 20);
        variables.put("B", 101L);
        variables.put("C", 30.0D);
        variables.put("D", 20);
        variables.put("E", 20);
        variables.put("G", 20);
        variables.put("H", 2);

        System.out.println(compiledExp.execute(variables));
        double start=System.currentTimeMillis();
        for(int i=1;i<10000;i++){
            compiledExp.execute(variables);
        }
        double expF=System.currentTimeMillis();
        System.out.println(formula+"耗时:"+(expF-start)/10000+"ms");
    }
}
