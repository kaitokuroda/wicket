package com.example.wsbp.service;

import org.apache.wicket.ajax.json.JsonUtils;

public interface ISampleService {
    /**
     * @return 現在の時:分:秒を文字列で返す
     */
    public String makeCurrentHMS();

    /**
     * @return 0〜9の整数で乱数を返す
     */
    public int makeRandInt();
}
