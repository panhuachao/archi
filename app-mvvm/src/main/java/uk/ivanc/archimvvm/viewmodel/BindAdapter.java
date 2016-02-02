package uk.ivanc.archimvvm.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.bindingadapter.RecyclerItemAdapter;
import uk.ivanc.archimvvm.bindingadapter.RecyclerItemClickListener;

/**
 * Created by PanHuaChao on 2016/1/14.
 * Description: databinding自定义bindadapter，公用方法
 */
public class BindAdapter {
    private final static String TAG = "BindAdapter";

    /**
     * 设置listview itemlayout
     * @param view
     * @param itemlayoutid
     */
    @BindingAdapter({"app:itemList", "app:itemLayout", "app:emptyView"})
    public static void bindRecycleItemLayout(RecyclerView view, List itemlist,
                                             int itemlayoutid, int emptyviewId) {
        if (view.getTag(R.id.bindRecycleView) == null) {
            view.setTag(R.id.bindRecycleView, true);
            RecyclerItemAdapter listViewItemAdapter = new RecyclerItemAdapter(view.getContext(), itemlist, itemlayoutid);
            view.setAdapter(listViewItemAdapter);
        } else {
            view.getAdapter().notifyDataSetChanged();
        }
        View rootView = view.getRootView();
        View emptyView = rootView.findViewById(emptyviewId);
        if (emptyView != null) {
            if (view.getAdapter() != null && itemlist.size() == 0) {
                if (view.getParent() instanceof SwipeRefreshLayout) {
                    ((View) view.getParent()).setVisibility(View.GONE);
                }
                view.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                if (view.getParent() instanceof SwipeRefreshLayout) {
                    ((View) view.getParent()).setVisibility(View.VISIBLE);
                }
                view.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置recyclerview 的点击事件
     * @param adapterView
     * @param clickListener
     */
    @BindingAdapter({"app:itemClick"})
    public static void setOnItemClick(RecyclerView adapterView, final RecyclerItemClickListener.OnItemClickListener clickListener) {
        if (null != clickListener) {
            adapterView.addOnItemTouchListener(new RecyclerItemClickListener(adapterView.getContext(), adapterView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemLongClick(View view, int position) {
                    clickListener.onItemLongClick(view, position);
                }

                @Override
                public boolean onItemClick(View view, int position) {
                    return clickListener.onItemClick(view, position);
                }
            }));
        }
    }


    @BindingAdapter({"app:imageUrl"})
    public static void bindImageView(ImageView view, String imageUrl) {
        if (imageUrl != null && !"".equals(imageUrl.trim())) {
            Picasso.with(view.getContext()).load(imageUrl).into(view);
        }
    }

    /**
     *  Picasso 图片
     * @param view
     * @param url
     * @param error
     */
    @BindingAdapter({"app:imageUrl", "app:error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }


    @BindingAdapter({"app:imageUrl","app:defPhoto"})
    public static void bindImageViewwithdef(ImageView view, String imageUrl,Drawable defphotoid) {
        if (imageUrl != null && !"".equals(imageUrl.trim())) {
            Picasso.with(view.getContext()).load(imageUrl).placeholder(defphotoid).into(view);
        }
    }
}
