package com.lis.swipecardview;

import java.util.ArrayList;
import java.util.List;

public class SwipeCardBean {
    private int postition;
    private String url;
    private String name;

    public SwipeCardBean(int postition, String url, String name) {
        this.postition = postition;
        this.url = url;
        this.name = name;
    }

    public int getPostition() {
        return postition;
    }

    public SwipeCardBean setPostition(int postition) {
        this.postition = postition;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SwipeCardBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public SwipeCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<SwipeCardBean> initDatas() {
        List<SwipeCardBean> datas = new ArrayList<>();
        int i = 1;
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/10/59/c7/1059c79b20330d7611beb75b7c5b12de.jpg", "美女1"));
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/ce/bc/23/cebc23d8f6fc0c6224c1c54ea8c9daa5.jpg", "美女2"));
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/95/6a/8d/956a8d2f687e4536f50224eaa36769f3.jpg", "美女3"));
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/cd/c9/9c/cdc99c04ee5d0530c3e37a9d06019008.jpg", "美女4"));
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/2a/17/cb/2a17cb82befd84a480eb15bb483d48f7.jpg", "美女5"));
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/9f/3f/63/9f3f63646ee68d46fc446497044dcb0e.jpg", "美女6"));
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/1d/30/9b/1d309b55ec8f9f37b7a01c49b7ab93f2.jpg", "美女7"));
        datas.add(new SwipeCardBean(i++, "https://up.enterdesk.com/edpic/75/3a/ef/753aef8db9f2d448774e5ac5d2c54524.jpg","美女8"));
        return datas;
    }
}
