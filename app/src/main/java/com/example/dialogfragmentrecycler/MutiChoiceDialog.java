package com.example.dialogfragmentrecycler;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class MutiChoiceDialog extends DialogFragment {

    private Util util;
    private RecyclerView recyclerView ;
    private TextView sure_action ,cancel_action;
    /**
     * 记录是否被选中
     */
    private SparseArray<Boolean> sparseArray ;
    private List<Integer> listPosition ;

    /**
     * 回调给界面
     */
    interface OnSureListener
    {
        void onSureClick(List<Integer> list);
    }
    private OnSureListener onSureListener ;

    public void setOnSureListener(OnSureListener onSureListener) {
        this.onSureListener = onSureListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setCancelable(false);
        //setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout,container,false) ;
        Log.e("MutiChoiceDialog","===onCreateView");
        initId(view);
       /* Window dialogWindow = getDialog().getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER );
        lp.x = 0;
        lp.y = 0;
        lp.width = lp.MATCH_PARENT;
        lp.height = 500;
        lp.alpha = 0.7f;
        dialogWindow.setAttributes(lp);*/

        return view ;
    }

    private void initId(View view)
    {
        sparseArray = new SparseArray();
        listPosition = new ArrayList<>();
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        sure_action = (TextView) view.findViewById(R.id.sure_action) ;
        cancel_action = (TextView)view.findViewById(R.id.cancel_action);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final MutiChoiceAdapter mutiChoiceAdapter = new MutiChoiceAdapter(getContext());
        recyclerView.setAdapter(mutiChoiceAdapter);
        mutiChoiceAdapter.setOnItemClikListener(new MutiChoiceAdapter.OnItemClikListener() {
            @Override
            public void onItemClick(MutiChoiceAdapter.MyViewHolder viewHolder, int position) {
                if(sparseArray.get(position)!=null) {

                    if(sparseArray.get(position))
                    {
                        listPosition.remove(position);
                    }else
                    {
                        listPosition.add(position);
                    }
                    sparseArray.put(position, !sparseArray.get(position));
                    viewHolder.imageView.setSelected(sparseArray.get(position));

                    // viewHolder.itemView.findViewById(R.id.image_check).setSelected(sparseArray.get(position));
                }else
                {
                    sparseArray.put(position, true);
                    viewHolder.imageView.setSelected(true);
                    listPosition.add(position);
                }
                mutiChoiceAdapter.setSparseArray(sparseArray);

                // mutiChoiceAdapter.notifyDataSetChanged();
            }
        });
        sure_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSureListener != null)
                {
                    onSureListener.onSureClick(listPosition);
                }
                dismiss();
            }
        });

        cancel_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //sure_btn.setEnabled(false);

    }

    /**
     * 横竖屏切换适配
     * @param newConfig
     */
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Log.e("MutiChoiceDialog","===onConfigurationChanged");
//        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
//        {
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)recyclerView.getLayoutParams();
//            layoutParams.height = Utils.dp2px(getContext(),200);
//            recyclerView.setLayoutParams(layoutParams);
//        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
//        {
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)recyclerView.getLayoutParams();
//            layoutParams.height = Utils.dp2px(getContext(),300);
//            recyclerView.setLayoutParams(layoutParams);
//        }
//    }
}
