package com.yurn.satori.framework.message.element.resource;

import com.yurn.satori.sdk.util.XmlUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 视频
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class VideoElement extends BaseResourceElement {

    public VideoElement(String src) {
        super(src);
    }

    public VideoElement(String src, Boolean cache, String timeout) {
        super(src, cache, timeout);
    }

    @Override
    public String toString() {
        String result = "<video";
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
