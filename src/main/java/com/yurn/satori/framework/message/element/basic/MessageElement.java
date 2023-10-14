package com.yurn.satori.framework.message.element.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

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

    public MessageElement(@NonNull String text) {
        this(text, null, null);
    }

    public MessageElement(@NonNull String text, @Nullable String id, @Nullable Boolean forward) {
        super(text);
        this.id = id;
        this.forward = forward;
    }

    @Override
    public String toString() {
        Element element = DocumentHelper.createElement("message");
        if (id != null) {
            element.addAttribute("id", id);
        }
        if (forward != null) {
            element.addAttribute("forward", String.valueOf(forward));
        }
        if (text != null) {
            element.setText(text);
        }
        return element.asXML();
    }
}
