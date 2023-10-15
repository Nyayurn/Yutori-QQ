package com.yurn.satori.framework.message.element.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 消息
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MessageElement extends TextElement {
    /**
     * 消息的 ID
     */
    protected String id;

    /**
     * 是否为转发消息
     */
    protected Boolean forward;

    public MessageElement(String text) {
        this(text, null, null);
    }

    public MessageElement(String text, String id, Boolean forward) {
        super(text);
        this.id = id;
        this.forward = forward;
    }

    @Override
    public String toString() {
        String result = "<message";
        if (id != null) {
            result += " id=\"" + id + "\"";
        }
        if (forward != null) {
            result += " forward";
        }
        result += ">";
        if (text != null) {
            result += super.toString();
        }
        result += "</message>";
        return result;
    }
}
