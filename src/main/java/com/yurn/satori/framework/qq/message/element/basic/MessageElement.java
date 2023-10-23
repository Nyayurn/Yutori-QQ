/*
Copyright (c) 2023 Yurn
YurnQbotFramework is licensed under Mulan PSL v2.
You can use this software according to the terms and conditions of the Mulan PSL v2.
You may obtain a copy of Mulan PSL v2 at:
         http://license.coscl.org.cn/MulanPSL2
THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
See the Mulan PSL v2 for more details.
 */

package com.yurn.satori.framework.qq.message.element.basic;

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
