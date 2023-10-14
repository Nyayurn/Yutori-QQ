package com.yurn.satori.framework.message.element.basic;

import com.yurn.satori.framework.message.element.BaseMessageElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * 纯文本
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TextElement extends BaseMessageElement {
    protected String text;

    public TextElement(@NonNull String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        if (text != null) {
            return text.replace("&", "&amp;")
                    .replace("\"", "&quot;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;");
        }
        return null;
    }
}
