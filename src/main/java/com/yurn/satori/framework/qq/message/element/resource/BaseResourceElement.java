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

package com.yurn.satori.framework.qq.message.element.resource;

import com.yurn.satori.framework.qq.message.element.BaseMessageElement;
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
