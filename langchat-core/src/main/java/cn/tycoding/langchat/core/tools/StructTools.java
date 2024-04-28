package cn.tycoding.langchat.core.tools;

import dev.langchain4j.agent.tool.Tool;

import java.util.List;

/**
 * 对于结构化数据的function call
 * @author tycoding
 * @since 2024/4/28
 */
public class StructTools {

    @Tool("Gets column name data in Excel")
    List<String> getNames() {
        System.out.println("get col names ");
        return List.of("一级菜单", "二级菜单", "三级菜单");
    }

    @Tool("Gets all the data for a column")
    List<String> getColData(int col) {
        System.out.println("get col names " + col);

        if (col == 1) {
            return List.of("menu1", "menu2", "menu3");
        } else {
            return List.of("1.1", "2.1", "3.1");
        }
    }
}
