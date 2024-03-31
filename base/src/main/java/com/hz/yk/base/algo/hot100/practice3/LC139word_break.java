package com.hz.yk.base.algo.hot100.practice3;

import java.util.List;

/**
 * https://leetcode.cn/problems/word-break/description/
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
 * @author wuzheng.yk
 * @date 2024/3/31
 */
public class LC139word_break {

    /**
     * dp[i] 表示前i个字符是否可以组成
     * dp[i] = dp[0]isConatin(0,i) || dp[1]isConatin(1,i) || ... || dp[i-1]isConatin(i-1,i)
     *
     * @param s
     * @param wordDict
     * @return
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[i] || (dp[j] && wordDict.contains(s.substring(j, i)));
            }
        }
        return dp[s.length()];
    }

}
