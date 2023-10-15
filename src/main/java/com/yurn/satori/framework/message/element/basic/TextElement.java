package com.yurn.satori.framework.message.element.basic;

import com.yurn.satori.framework.message.element.BaseMessageElement;
import com.yurn.satori.sdk.util.XmlUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        if (text != null) {
            return XmlUtil.encode(text);
        }
        return null;
    }
}
