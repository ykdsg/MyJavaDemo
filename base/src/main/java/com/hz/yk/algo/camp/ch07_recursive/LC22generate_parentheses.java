package com.hz.yk.algo.camp.ch07_recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2023/7/21
 */
public class LC22generate_parentheses {

    List<String> resultList = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        generate(0, 0, n, "");
        return resultList;
    }

    private void generate(int leftNum,int rightNum, int max, String value) {
        //终止条件
        if (leftNum >= max && rightNum >= max) {
            System.out.println(value);
            resultList.add(value);
            return;
        }
        //当前层需要做的事情，就是加上左括号或者右括号
        //然后下探到下一层，这里就直接写到一起了
        //左括号可以继续添加的条件是没有达到数量上限
        if (leftNum < max) {
            generate(leftNum + 1,rightNum, max, value + "(");
        }
        //右括号可以继续添加的情况是左括号的数量大于右括号
        if (leftNum > rightNum) {
            generate(leftNum,rightNum+1,max,value+")");
        }
    }

    public static void main(String[] args) {
        LC22generate_parentheses test = new LC22generate_parentheses();
        test.generateParenthesis(3);
    }
}
