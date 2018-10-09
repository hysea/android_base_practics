package com.hysea.materialdesign.recycler;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<SimpleBean> providerSimpleData() {
        List<SimpleBean> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new SimpleBean("我是标题" + (i + 1), "我是描述：" + (i + 1)));
        }

        return list;
    }
}
