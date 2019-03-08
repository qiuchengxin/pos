package com.market.pos.config;

public class CommonReplaceNull {
    /**
     * 共有类，若为空替换成字符串qiuchengxin
     * @param a
     * @return
     */
    public static String commonReplaceNull(String a){
        if (a == null){
            a = "qiuchengxin";
        }
        return a;
    }
}
