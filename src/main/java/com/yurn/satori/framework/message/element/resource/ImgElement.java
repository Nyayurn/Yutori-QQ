package com.yurn.satori.framework.message.element.resource;

import com.yurn.satori.sdk.util.XmlUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 图片
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ImgElement extends BaseResourceElement {
    /**
     * 图片的宽度
     */
    protected Long width;

    /**
     * 图片的高度
     */
    protected Long height;

    /**
     * 是否是表情
     */
    protected Boolean isEmoji;

    /**
     * 文件路径
     */
    protected String filepath;

    public ImgElement(String src) {
        this(src, null, null, null, null);
    }

    public ImgElement(String src, Long width, Long height, Boolean isEmoji, String filepath) {
        super(src);
        this.width = width;
        this.height = height;
        this.isEmoji = isEmoji;
        this.filepath = filepath;
    }

    public ImgElement(String src, Boolean cache, String timeout, Long width, Long height, Boolean isEmoji, String filepath) {
        super(src, cache, timeout);
        this.width = width;
        this.height = height;
        this.isEmoji = isEmoji;
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        String result = "<img";
        if (src != null) {
            result += " src=\"" + XmlUtil.encode(src) + "\"";
        }
        if (cache != null) {
            result += " cache";
        }
        if (timeout != null) {
            result += " timeout=\"" + timeout + "\"";
        }
        if (width != null) {
            result += " width=" + width;
        }
        if (height != null) {
            result += " height=" + height;
        }
        if (isEmoji != null) {
            result += " chrono-unsafe-isemoji";
        }
        if (filepath != null) {
            result += " chrono-unsafe-filepath=\"" + XmlUtil.encode(filepath) + "\"";
        }
        result += "/>";
        return result;
    }
}
