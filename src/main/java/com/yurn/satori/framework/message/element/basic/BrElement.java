package com.yurn.satori.framework.message.element.basic;

import com.yurn.satori.sdk.message.element.BaseMessageElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 换行
 *
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BrElement extends BaseMessageElement {
    @Override
    public String toString() {
        return "<br/>";
    }
}