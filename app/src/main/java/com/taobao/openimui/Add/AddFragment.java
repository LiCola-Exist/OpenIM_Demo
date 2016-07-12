package com.taobao.openimui.Add;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.IYWP2PPushListener;
import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.IYWContactService;
import com.alibaba.mobileim.conversation.IYWConversationListener;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.openIMUIDemo.R;
import com.taobao.openimui.Add.Utils.Logger;
import com.taobao.openimui.Add.dummy.DummyContent.DummyItem;
import com.taobao.openimui.imcore.InitSample;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A fragment representing a recyclerView of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AddFragment extends Fragment {

    @BindView(R.id.btn_chatting_imcore)
    Button btnChattingImcore;
    @BindView(R.id.btn_chatting_conversation)
    Button btnChattingConversation;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    private OnListFragmentInteractionListener mListener;

    private YWIMCore mIMCore;
    private IYWConversationService mConversationService;
    private List<YWConversation> mConversationList=new ArrayList<>();

    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AddFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AddFragment newInstance(int columnCount) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }



    private void init() {
        mIMCore= InitSample.getInstance().getIMCore();
        mConversationService = mIMCore.getConversationService();


        //添加会话列表变更监听，收到该监听回调时更新adapter就可以刷新页面了
        mConversationService.addConversationListener(new IYWConversationListener() {
            @Override
            public void onItemUpdated() {
                Logger.d();
            }
        });

        mConversationService.addP2PPushListener(new IYWP2PPushListener() {
            @Override
            public void onPushMessage(IYWContact iywContact, YWMessage ywMessage) {
                Logger.d(ywMessage.getAuthorId());
            }
        });

        IYWLoginService loginService = mIMCore.getLoginService();
        loginService.logout(new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                //登出成功
                Logger.d();

                login();
            }

            @Override
            public void onProgress(int arg0) {
                // TODO Auto-generated method stub
                Logger.d();
            }

            @Override
            public void onError(int errCode, String description) {
                //登出失败，errCode为错误码,description是错误的具体描述信息
                Logger.d(description);
            }
        });

//        mConversationList=mConversationService.getConversationList();
//        logConversation(mConversationList);
    }

    private void login() {
        String userid = "testpro1";
        String password = "taobao1234";
        IYWLoginService loginService = mIMCore.getLoginService();
        YWLoginParam Param = YWLoginParam.createLoginParam(userid, password);
        loginService.login(Param, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                //登录成功
                Logger.d("onSuccess");
            }

            @Override
            public void onProgress(int arg0) {
                // TODO Auto-generated method stub
                Logger.d("onProgress="+arg0);
            }

            @Override
            public void onError(int errCode, String description) {
                //如果登录失败，errCode为错误码,description是错误的具体描述信息
                Logger.d(description);
            }
        });
    }

    private void logConversation(List<YWConversation> mConversationList) {
        for (YWConversation item:
             mConversationList) {
            Logger.d("getConversationId="+item.getConversationId()+" getLatestContent="+item.getLatestContent());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListener=new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(DummyItem item) {
                Logger.d();
            }
        };
        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(mConversationList, mListener));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick({R.id.btn_chatting_imcore, R.id.btn_chatting_conversation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_chatting_imcore:
                break;
            case R.id.btn_chatting_conversation:
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
