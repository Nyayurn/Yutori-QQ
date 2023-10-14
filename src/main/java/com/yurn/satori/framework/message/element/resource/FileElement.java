package com.yurn.satori.framework.message.element.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 文件
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FileElement extends BaseResourceElement {
    /**
     * 文件名
     */
    protected String filename;

    public FileElement(@NonNull String src, @NonNull String filename) {
        super(src);
        this.filename = filename;
    }

    public FileElement(@NonNull String src, @Nullable Boolean cache, @Nullable String timeout, @NonNull String filename) {
        super(src, cache, timeout);
        this.filename = filename;
    }

    @Override
    public String toString() {
        Element element = DocumentHelper.createElement("file");
        if (src != null) {
            element.addAttribute("src", src);
        }
        if (cache != null) {
            element.addAttribute("cache", String.valueOf(cache));
        }
        if (timeout != null) {
            element.addAttribute("timeout", timeout);
        }
        if (filename != null) {
            element.addAttribute("chrono-unsafe-filename", filename);
        }
        return element.asXML();
    }
}
