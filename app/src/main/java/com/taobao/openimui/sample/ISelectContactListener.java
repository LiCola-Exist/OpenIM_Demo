package com.taobao.openimui.sample;

import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.conversation.YWConversation;

import java.util.List;

/**
 * Created by mayongge on 15-11-2.
 */
public interface ISelectContactListener {

    public void onSelectCompleted(List<IYWContact> contacts);
}
