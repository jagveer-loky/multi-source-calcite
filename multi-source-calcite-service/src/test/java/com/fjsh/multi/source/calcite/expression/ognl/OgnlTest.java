package com.fjsh.multi.source.calcite.expression.ognl;

import org.apache.ibatis.ognl.Node;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description：
 * @Date: Created in :21/6/2018 5:03 PM
 * @Modified by:
 * https://www.ibm.com/developerworks/cn/opensource/os-cn-ognl/
 */
public class OgnlTest {

//    String exp = "@java.lang.Math@min(@java.lang.Math@floor(test.a*test.getB()),@java.lang.Math@floor(c+d*e))";
    String exp = "@java.lang.Math@floor(A * B - (C -D) + E.pow(F) + G / H)";
//    String exp = "A * B - (C -D) + E.pow(F、) + G / H";

    public int getA() {
        return 10;
    }

    public int getB() {
        return 10;
    }
    @Test
    public void ognlSimplTest() throws Exception{
        OgnlContext context = new OgnlContext();
        context.put("test", new OgnlTest());
        context.put("A", "20");
        context.put("B", 101L);
        context.put("C", 30.0D);
        context.put("D", 20);
        context.put("E", new BigDecimal("20"));
        context.put("F", "3");
        context.put("G", "20");
        context.put("H", "2");
        OgnlContext context2 = new OgnlContext();
        context2.setRoot(context);
        Node node = Ognl.compileExpression(context2, context2.getRoot(), exp);
        System.out.println( node.getAccessor().get(context2, context2.getRoot()));
        double start=System.currentTimeMillis();
        for(int i=1;i<10000;i++){
            node.getAccessor().get(context2, context2.getRoot());
        }
        double expF=System.currentTimeMillis();
        System.out.println(exp+"耗时:"+(expF-start)/10000+"ms");
    }
}
