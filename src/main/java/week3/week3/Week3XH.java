package week3.week3;

import com.alibaba.fastjson.JSON;

/**
 * @author xh
 */
public class Week3XH implements Week3Worker {
    //@Override
    public String toString(JSON json) {
        return JSON.toJSONString(json);
    }

    //@Override
    public String toFormatString(JSON json) {
        String s = toString(json);
        String prettyJson = formatJson(s);
        return prettyJson;

    }

    public String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     *
     * @author xh
     * @param indent
     */
    private void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

  /*  public void testPrettyPrint() {
        String compactJson = "{\"playerID\":1234,\"name\":\"Test\",\"itemList\":[{\"itemID\":1,\"name\":\"Axe\",\"atk\":12,\"def\":0},{\"itemID\":2,\"name\":\"Sword\",\"atk\":5,\"def\":5},{\"itemID\":3,\"name\":\"Shield\",\"atk\":0,\"def\":10}]}";

        String prettyJson = toFormatString(compactJson);

        System.out.println("Compact:\n" + compactJson);
        System.out.println("Pretty:\n" + prettyJson);
    }*/

   /* public static void main(String[] args) {
        testPrettyPrint();
    }*/
}
