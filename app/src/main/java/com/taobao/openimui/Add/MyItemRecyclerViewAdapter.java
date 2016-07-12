package com.taobao.openimui.Add;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.openIMUIDemo.R;
import com.taobao.openimui.Add.AddFragment.OnListFragmentInteractionListener;
import com.taobao.openimui.Add.dummy.DummyContent.DummyItem;

import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<YWConversation> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<YWConversation> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        YWConversation ywConversation = mValues.get(position);

        holder.mUserName.setText(ywConversation.getConversationId());
        holder.mUserId.setText(ywConversation.getConversationId());
        YWMessage message=ywConversation.getLastestMessage();
        if (message!=null){
            holder.mLastMessage.setText(message.getContent());
        }else {
            holder.mLastMessage.setText("空");
        }

        holder.mTime.setText(new Date(ywConversation.getLatestTimeInMillisecond()).toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImgHead;
        public final TextView mUserId;
        public final TextView mUserName;
        public final TextView mLastMessage;
        public final TextView mTime;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImgHead = (ImageView) view.findViewById(R.id.img_head);
            mUserId = (TextView) view.findViewById(R.id.tv_user_id);
            mUserName = (TextView) view.findViewById(R.id.tv_user_name);
            mLastMessage = (TextView) view.findViewById(R.id.tv_last_message);
            mTime = (TextView) view.findViewById(R.id.tv_time);
        }

    }
}
