package com.taobao.openimui.sample;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMTribeAtPageOperation;
import com.alibaba.mobileim.ui.WxChattingActvity;

/**
 * Created by weiquanyun on 16/5/24.
 */
public class AtMsgListOperationSample extends IMTribeAtPageOperation {

    public AtMsgListOperationSample(Pointcut pointcut) {
        super(pointcut);
    }

    /**
     * 如果开发者使用自己的Activity作为聊天界面Activity，并且使用了@功能，需要实现该方法，返回自己的Activity.class。
     * 否则点击收到的@消息会无法跳转到聊天界面
     * @return
     */
    @Override
    public Class getChattingActivityClass() {
        return WxChattingActvity.class;
    }
}
