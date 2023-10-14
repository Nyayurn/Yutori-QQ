package com.yurn.satori.framework.message.element.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 语音
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AudioElement extends BaseResourceElement {
    public AudioElement(@NonNull String src) {
        super(src);
    }

    public AudioElement(@NonNull String src, @Nullable Boolean cache, @Nullable String timeout) {
        super(src, cache, timeout);
    }

    @Override
    public String toString() {
        Element element = DocumentHelper.createElement("audio");
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
