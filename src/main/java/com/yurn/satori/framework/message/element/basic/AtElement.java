package com.yurn.satori.framework.message.element.basic;

import com.yurn.satori.framework.message.element.BaseMessageElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 提及用户
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AtElement extends BaseMessageElement {

    protected String id;

    /**
     * 目标用户的名称
     */
    protected String name;

    /**
     * 特殊操作, 例如 all 表示 @全体成员, here 表示 @在线成员
     */
    protected String type;

    public AtElement(@Nullable String id, @Nullable String name, @Nullable String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static AtElement atUser(@NonNull String id) {
        return new AtElement(id, null, null);
    }

    public static AtElement atType(@NonNull String type) {
        return new AtElement(null, null, type);
    }

    @Override
    public String toString() {
        Element element = DocumentHelper.createElement("at");
        if (id != null) {
            element.addAttribute("id", id);
        }
        if (name != null) {
            element.addAttribute("name", name);
        }
        if (type != null) {
            element.addAttribute("type", type);
        }
        return element.asXML();
    }
}