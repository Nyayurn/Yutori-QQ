package com.yurn.satori.framework.message.element.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 视频
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class VideoElement extends BaseResourceElement {

    public VideoElement(@NonNull String src) {
        super(src);
    }

    public VideoElement(@NonNull String src, @Nullable Boolean cache, @Nullable String timeout) {
        super(src, cache, timeout);
    }

    @Override
    public String toString() {
        Element element = DocumentHelper.createElement("video");
        if (src != null) {
            element.addAttribute("src", src);
        }
        if (cache != null) {
            element.addAttribute("cache", String.valueOf(cache));
        }
        if (timeout != null) {
            element.addAttribute("timeout", timeout);
        }
        return element.asXML();
    }
}
