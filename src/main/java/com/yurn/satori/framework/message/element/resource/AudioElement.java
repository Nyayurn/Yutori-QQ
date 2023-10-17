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

package com.yurn.satori.framework.message.element.resource;

import com.yurn.satori.sdk.util.XmlUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 语音
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AudioElement extends BaseResourceElement {
    public AudioElement(String src) {
        super(src);
    }

    public AudioElement(String src, Boolean cache, String timeout) {
        super(src, cache, timeout);
    }

    @Override
    public String toString() {
        String result = "<audio";
        if (src != null) {
            result += " src=\"" + XmlUtil.encode(src) + "\"";
        }
        if (cache != null) {
            result += " cache";
        }
        if (timeout != null) {
            result += " timeout=\"" + timeout + "\"";
        }
        result += "/>";
        return result;
    }
}
