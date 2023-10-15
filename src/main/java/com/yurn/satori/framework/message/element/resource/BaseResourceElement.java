package com.yurn.satori.framework.message.element.resource;

import com.yurn.satori.framework.message.element.BaseMessageElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class BaseResourceElement extends BaseMessageElement {
    /**
     * 资源的 URL
     */
    protected String src;

    /**
     * 是否使用已缓存的文件
     */
    protected Boolean cache;

    /**
     * 下载文件的最长时间 (毫秒)
     */
    protected String timeout;

    public BaseResourceElement(String src) {
        this(src, null, null);
    }

    public BaseResourceElement(String src, Boolean cache, String timeout) {
        this.src = src;
        this.cache = cache;
        this.timeout = timeout;
    }
}
