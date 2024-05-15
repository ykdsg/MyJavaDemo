package com.hz.yk.base.algo.hot100.practice3;

import java.util.List;

/**
 * https://leetcode.cn/problems/word-break/description/
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
 * @author wuzheng.yk
 * @date 2024/5/15
 */
public class LC139word_break {

    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[i] 表示长度为i的字符串是否可以由字典中的单词拼接而成
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
