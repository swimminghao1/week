package week3.week3;

import com.alibaba.fastjson.JSON;

public class test {
    public static void main(String[] args) {


        Week3Worker week3Worker = new Week3XH();
        String compactJson = "{\"playerID\":1234,\"name\":\"Test\",\"itemList\":[{\"itemID\":1,\"name\":\"Axe\",\"atk\":12,\"def\":0},{\"itemID\":2,\"name\":\"Sword\",\"atk\":5,\"def\":5},{\"itemID\":3,\"name\":\"Shield\",\"atk\":0,\"def\":10}]}";
        JSON jsonObject = JSON.parseObject(compactJson);
        String prettyJson = week3Worker.toFormatString(jsonObject);

        System.out.println("Compact:\n" + compactJson);
        System.out.println("Pretty:\n" + prettyJson);
       // week3XH.testPrettyPrint();
    }
}

