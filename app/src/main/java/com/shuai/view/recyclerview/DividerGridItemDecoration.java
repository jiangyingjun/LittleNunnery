package com.shuai.view.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Author: jiangyingjun
 * Version：5.5.3
 * Date: 2016-12-13
 * Description: recyclerview 网格状布局的分割线和item间距设置类

 * Modification History:
 * Date          Author     Version    Description
 * -----------------------------------------------------------
 * 2016.12.13   jiangyingjun  5.5.3       1.0
 * -------------------------------------------------
 *
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration
{

    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    private Drawable mDivider;
    private boolean divider_state=false;
    private int mDivider_line_bottom,mDivider_line_right,mDivider_line_left,mDivider_line_top;
    /*分割线的宽度*/
    private double mDivider_width = 1;

    private boolean isChannelPageUse = false;

    public DividerGridItemDecoration(Context context)
    {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    /*频道页跟团游目的地子数据的使用*/
    public void  setChannelPageUse(boolean pageUse){
        this.isChannelPageUse = pageUse;
    }


    public void setDivider(int divider_line_left,int divider_line_top,int divider_line_right,int divider_line_bottom){
        this.mDivider_line_left = divider_line_left;
        this.mDivider_line_top = divider_line_top;
        this.mDivider_line_right = divider_line_right;
        this.mDivider_line_bottom = divider_line_bottom;
    }

    /**
     *
     * @param context
     * @param divider_line_bottom item 的间距,单位：px
     */
    public DividerGridItemDecoration(Context context,int divider_line_right,int divider_line_bottom)
    {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        mDivider_line_right = divider_line_right;
        mDivider_line_bottom=divider_line_bottom;
    }

    /*
* @param divider_state  是否画出真实分割线
*
* */
    public void setDividerShowOrHide(boolean divider_state){
        this.divider_state=divider_state;
    }

    /**
     * @param width 分割线的宽度。width>1 变宽  width>0&&width<1 变细  width不能<=0
     *
     * **/
    public void setDividerWidth(double width){
        if (width<=0){
            throw new IllegalArgumentException("width must >0");
        }
        this.mDivider_width = width;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state)
    {
        if (divider_state){
            drawHorizontal(c, parent);
            drawVertical(c, parent);
        }
    }
    //返回列数
    private int getSpanCount(RecyclerView parent)
    {
        // 列数
        int spanCount = -1;
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent)
    {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = (int) (child.getRight() + params.rightMargin
                                + mDivider.getIntrinsicWidth()*mDivider_width);
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = (int) (top + mDivider.getIntrinsicHeight()*mDivider_width);
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent)
    {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = (int) (left + mDivider.getIntrinsicWidth()*mDivider_width);

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    //判断是否最后一列
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount)
    {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }


    //判断是否最后一行
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent)
    {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (!isChannelPageUse){
            //判断是否最后一行
            if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
            {
                //画下
//            outRect.set(0, 0, 0, mDivider.getIntrinsicWidth()+mDivider_line);
            }
            //最后一行和最后一列
            if (isLastRaw(parent,itemPosition,spanCount,childCount)&&isLastColum(parent,itemPosition,spanCount,childCount)){


            }
            //判断是否最后一列
            else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
            {
                //画   右   下
                outRect.set(0, 0, mDivider.getIntrinsicHeight()+mDivider_line_right, mDivider.getIntrinsicHeight()+mDivider_line_bottom);
            } else
            {
                //画右  下
                outRect.set(0, 0, mDivider.getIntrinsicHeight()+mDivider_line_right, mDivider.getIntrinsicHeight()+mDivider_line_bottom);
            }

        }else {
            /*专门为频道页跟团游的目的地子数据设置间隔  不知道怎么设置成公用的 暂时这么干吧*/

            if(parent.getAdapter().getItemCount()>4){
                /*判断是否最后一行*/
//                if (isLastRaw(parent, itemPosition, spanCount, childCount)){
//                    if (!isLastColum(parent, itemPosition, spanCount, childCount)){
//                        outRect.top = mDivider_line_top;
//                        outRect.right = mDivider_line_right;
////                        outRect.set(0, mDivider.getIntrinsicHeight()+mDivider_line_top,mDivider.getIntrinsicHeight()+ mDivider_line_right,0);
//                    }else {
////                        outRect.set(0,mDivider.getIntrinsicHeight()+ mDivider_line_top, 0,0);
//                        outRect.top = mDivider_line_top;
//                    }
//
//                }else {
////                    outRect.set(0, 0, mDivider.getIntrinsicHeight()+mDivider_line_right,0);
//                    outRect.right = mDivider_line_right;
//                }
                outRect.top = mDivider_line_top;
            }else {
                /*数据只有一行*/
                /*判断是否最后一列*/
                if (!isLastColum(parent, itemPosition, spanCount, childCount)){
                    outRect.set(0, 0, mDivider_line_right,0);
                }
            }




        }






    }
}