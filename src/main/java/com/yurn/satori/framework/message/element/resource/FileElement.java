package com.yurn.satori.framework.message.element.resource;

import com.yurn.satori.sdk.util.XmlUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public FileElement(String src, String filename) {
        super(src);
        this.filename = filename;
    }

    public FileElement(String src, Boolean cache, String timeout, String filename) {
        super(src, cache, timeout);
        this.filename = filename;
    }

    @Override
    public String toString() {
        String result = "<file";
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
