package com.yurn.satori.framework.message.element.basic;

import com.yurn.satori.framework.message.element.BaseMessageElement;
import com.yurn.satori.sdk.util.XmlUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public AtElement(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static AtElement atUser(String id) {
        return new AtElement(id, null, null);
    }

    public static AtElement atType(String type) {
        return new AtElement(null, null, type);
    }

    @Override
    public String toString() {
        String result = "<at";
        if (id != null) {
            result += " id=\"" + id + "\"";
        }
        if (name != null) {
            result += " name=\"" + XmlUtil.encode(name) + "\"";
        }
        if (type != null) {
            result += " type=\"" + type + "\"";
        }
        result += "/>";
        return result;
    }
}