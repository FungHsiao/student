package com.example.yt;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideLayout extends FrameLayout {

    private static final String TAG = SlideLayout.class.getSimpleName();
    private View contentView;
    private View menuView;


    private Scroller scroller;

    private int contentWidth;
    private int menuWidth;
    private int viewHeight; // 他们的高都是相同的

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        menuView = getChildAt(1);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentWidth = contentView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
        viewHeight = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 指定菜单的位置
        menuView.layout(contentWidth, 0, contentWidth + menuWidth, viewHeight);
    }

    private float startX;
    private float startY;
    private float downX; // 只赋值一次
    private float downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 1.按下记录坐标
                downX = startX = event.getX();
                downY = startY = event.getY();
                Log.e(TAG,"SlideLayout-onTouchEvent-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"SlideLayout-onTouchEvent-ACTION_MOVE");
                // 2.记录结束值
                float endX = event.getX();
                float endY = event.getY();
                // 3.计算偏移量
                float distanceX = endX - startX;

                int toScrollX = (int) (getScrollX() - distanceX);
                if (toScrollX < 0) {
                    toScrollX = 0;
                } else if (toScrollX > menuWidth) {
                    toScrollX = menuWidth;
                }

                scrollTo(toScrollX, getScrollY());

                startX = event.getX();
                startY = event.getY();
                // 在X轴和Y轴滑动的距离
                float DX = Math.abs(endX-downX);
                float DY = Math.abs(endY-downY);
                if(DX>DY && DX>8){
                    // 水平方向滑动
                    // 响应侧滑
                    // 反拦截-事件给SlideLayout
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"SlideLayout-onTouchEvent-ACTION_UP");
                int totalScrollX = getScrollX();//偏移量
                if(totalScrollX < menuWidth/2){
                    // 关闭Menu
                    closeMenu();
                }else{
                    // 打开Menu
                    openMenu();
                }
                break;
        }

        return true;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 1、按下记录坐标
                downX = startX = event.getX();
                Log.e(TAG,"SlideLayout-onTouchEvent-ACTION_DOWN");
                if(onStateChangeListener != null){
                    onStateChangeListener.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"SlideLayout-onTouchEvent-ACTION_MOVE");
                // 2、记录结束值
                float endX = event.getX();
                float endY = event.getY();
                // 3、计算偏移量
                float distanceX = endX - startX;

                startX = event.getX();
                // 在X轴和Y轴滑动的距离
                float DX = Math.abs(endX-downX);
                if(DX>8){
                    intercept = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return intercept;
    }


    public void openMenu() {
        // --->menuWidth
        int distanceX = menuWidth - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate(); // 强制刷新
        System.out.println("SlideLayout---openMenu");
        if(onStateChangeListener != null){
            onStateChangeListener.onOpen(this);
        }
    }


    public void closeMenu() {
        // --->0
        int distanceX = 0 - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate(); // 强制刷新
        System.out.println("SlideLayout---closeMenu");
        if(onStateChangeListener != null){
            onStateChangeListener.onClose(this);
        }
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
            System.out.println("SlideLayout---computeScroll");
        }
    }


    public interface OnStateChangeListener{
        void onClose(SlideLayout layout);
        void onDown(SlideLayout layout);
        void onOpen(SlideLayout layout);
    }

    private  OnStateChangeListener onStateChangeListener;


    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }
}
