//package com.lfork.blogsystem.base.adapter;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//
///**
// * 监听，判断RecyclerView滚动到底部时，加载onBottom方法
// */
//public class BasePullUpScrollListener extends RecyclerView.OnScrollListener {
//    public static final int LINEAR = 0;
//    public static final int GRID = 1;
//    public static final int STAGGERED_GRID = 2;
//
//    //标识RecyclerView的LayoutManager是哪种
//    protected int layoutManagerType;
//    // 瀑布流的最后一个的位置
//    protected int[] lastPositions;
//    // 最后一个的位置
//    protected int lastVisibleItem;
//
//    @Override
//    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//        super.onScrollStateChanged(recyclerView, newState);
//        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 >=
//                getItemCount() && pullUpListener != null) {
//            pullUpListener.onBottom(state);//回调加载更多监听
//        }
//    }
//
//      //根据不同的Layout类型处理FootView
//    @Override
//    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if (layoutManager instanceof LinearLayoutManager) {
//            layoutManagerType = LINEAR;
//        } else if (layoutManager instanceof GridLayoutManager) {
//            layoutManagerType = GRID;
//        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
//            layoutManagerType = STAGGERED_GRID;
//        } else {
//            throw new RuntimeException(
//                    "Unsupported LayoutManager used. Valid ones are " +
//                            "LinearLayoutManager, GridLayoutManager and " +
//                            "StaggeredGridLayoutManager");
//        }
//
//        switch (layoutManagerType) {
//            case LINEAR:
//                lastVisibleItem = ((LinearLayoutManager) layoutManager)
//                        .findLastVisibleItemPosition();
//                break;
//            case GRID:
//                lastVisibleItem = ((GridLayoutManager) layoutManager)
//                        .findLastVisibleItemPosition();
//                break;
//            case STAGGERED_GRID:
//                StaggeredGridLayoutManager staggeredGridLayoutManager
//                        = (StaggeredGridLayoutManager) layoutManager;
//                lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
//                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
//                lastVisibleItem = findMax(lastPositions);
//                break;
//        }
//    }
//
//    private int findMax(int[] lastPositions) {
//        int max = lastPositions[0];
//        for (int value : lastPositions) {
//            if (value > max) {
//                max = value;
//            }
//        }
//        return max;
//    }
//}