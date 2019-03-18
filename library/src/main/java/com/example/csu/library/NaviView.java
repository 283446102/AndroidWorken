package com.example.csu.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NaviView extends LinearLayout {
    private static final String TAG = "NaviView";

    private Context mContext;
    //主view
    private View mView;
    private ImageView mBigIcon;
    private ImageView mSmallIcon;

    //icon资源
    private int mBigIconSrc;
    private int mSmallIconSrc;

    //icon尺寸
    private float mIconWidth;
    private float mIconHeight;

    //拖动半径
    private float mBigRadius;
    private float mSmallRadius;

    //拖动范围
    private float mRange;

    //记录icon被拖动前的位置
    private float mLastX;
    private float mLastY;

    //水平方向拖动距离
    private float mHorizontalX;

    //当前朝向
    private int mDirection = -1;

    //当前选中状态
    private int mCheckStatus;

    //朝向
    private static int LEFT = 0;
    private static int RIGHT = 1;

    //选中状态
    private static int CHECKED = 0;
    private static int UNCHECKED = 1;

    //每次移动距离
    private static int INTERVAL = 2;

    //转动时间间隔
    private static int DELAY = 10;


    public NaviView(@NonNull Context context) {
        this(context, null);
    }

    public NaviView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NaviView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NaviView, defStyleAttr, 0);
        mBigIconSrc = typedArray.getResourceId(R.styleable.NaviView_bigIconSrc, R.drawable.big);
        mSmallIconSrc = typedArray.getResourceId(R.styleable.NaviView_smallIconSrc, R.drawable.small);
        mIconWidth = typedArray.getDimension(R.styleable.NaviView_iconWidth, dp2px(context, 60));
        mIconHeight = typedArray.getDimension(R.styleable.NaviView_iconHeight, dp2px(context, 60));
        mRange = typedArray.getFloat(R.styleable.NaviView_range, 1);
        typedArray.recycle();

        setOrientation(LinearLayout.VERTICAL);

        init(context);
    }

    private void init(Context context) {
        mView = inflate(context, R.layout.view_icon, null);
        mBigIcon = mView.findViewById(R.id.iv_big);
        mSmallIcon = mView.findViewById(R.id.iv_small);

        Animation animation = getScale();

        mBigIcon.setImageResource(mBigIconSrc);
        mSmallIcon.setImageResource(mSmallIconSrc);

        mBigIcon.startAnimation(animation);
        mSmallIcon.startAnimation(animation);

        setWidthAndHeight(mBigIcon);
        setWidthAndHeight(mSmallIcon);

        //设置布局格式
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        mView.setLayoutParams(params);
        addView(mView);
    }

    //设置icon尺寸
    private void setWidthAndHeight(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = (int) mIconWidth;
        layoutParams.height = (int) mIconHeight;
        view.setLayoutParams(layoutParams);
    }

    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setupView();

        final int w = resolveSize(getMeasuredWidth(), widthMeasureSpec);
        final int h = resolveSize(getMeasuredHeight(), heightMeasureSpec);

        setMeasuredDimension(w, h);
    }

    //确定view以及拖动相关参数
    private void setupView() {
        //根据view的宽高确定可以拖动半径的大小
        mSmallRadius = 0.1f * Math.min(mView.getMeasuredWidth(), mView.getMeasuredHeight()) * mRange;
        mBigRadius = 1.5f * mSmallRadius;
        //设置图片的padding
        int padding = (int) mBigRadius;
        mBigIcon.setPadding(padding, padding, padding, padding);
        mSmallIcon.setPadding(padding, padding, padding, padding);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft;
        int childTop = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (child.getVisibility() != GONE) {
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();
                //水平居中显示
                childLeft = (getWidth() - childWidth) / 2;
                //当前子view的top
                childTop += lp.topMargin;
                child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
                //下一个view的top是当前子view的top + height + bottomMargin
                childTop += childHeight + lp.bottomMargin;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - mLastX;
                float deltaY = y - mLastY;
                //拖动范围
                moveEven(mBigIcon, deltaX, deltaY, mSmallRadius);
                moveEven(mSmallIcon, 1.5f * deltaX, 1.5f * deltaY, mBigRadius);
                break;
            case MotionEvent.ACTION_UP:
                mBigIcon.setX(0);
                mBigIcon.setY(0);
                mSmallIcon.setY(0);
                if (mCheckStatus == UNCHECKED) {
                    if (mDirection == LEFT)
                        mSmallIcon.setX(mSmallIcon.getLeft() - (mBigRadius - mSmallRadius));
                    if (mDirection == RIGHT)
                        mSmallIcon.setX(mSmallIcon.getLeft() + (mBigRadius - mSmallRadius));
                } else {
                    mSmallIcon.setX(0);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void moveEven(View view, float deltaX, float deltaY, float radius) {
        //计算拖动距离
        float distance = getDisTance(deltaX, deltaY);

        //拖动的方向角,注意deltaX和deltaY的位置，atan2是带正负号的
        double degree = Math.atan2(deltaY, deltaX);
        //如果大于临界值半径就不能往外拖了
        if (distance > radius) {
            view.setX(view.getLeft() + (float) (radius * Math.cos(degree)));
            view.setY(view.getTop() + (float) (radius * Math.sin(degree)));
        } else {
            view.setX(view.getLeft() + deltaX);
            view.setY(view.getTop() + deltaY);
        }


    }

    private float getDisTance(float x, float y) {
        return (float) Math.sqrt(x * x + y * y);
    }

    public boolean performClick() {
        mCheckStatus = CHECKED;
        mHandler.removeCallbacks(mRunnable);
        mSmallIcon.setX(0);
        mDirection = -1;
        return super.performClick();
    }

    private final Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mDirection == RIGHT) {
                if (mHorizontalX >= mBigRadius - mSmallRadius) return;
                mHorizontalX += INTERVAL;
            } else {
                if (mHorizontalX <= mSmallRadius - mBigRadius) return;
                mHorizontalX -= INTERVAL;
            }
            //此时只有小图标移动
            moveEven(mSmallIcon, mHorizontalX, 0, mBigRadius - mSmallRadius);
            mHandler.postDelayed(this, DELAY);
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 设置外部可以调用的类
     */
    public void check() {
        mCheckStatus = CHECKED;
    }

    public void setBigIcon(int res) {
        mBigIcon.setImageResource(res);
    }

    public void setSmallIcon(int res) {
        mSmallIcon.setImageResource(res);
    }

    public void setIconWidthAndHeight(float width, float height) {
        mIconWidth = dp2px(mContext, width);
        mIconHeight = dp2px(mContext, height);
        setWidthAndHeight(mBigIcon);
        setWidthAndHeight(mSmallIcon);
    }

    public void setRange(float range) {
        mRange = range;
    }

    private Animation getScale() {
        ScaleAnimation animation = new ScaleAnimation(0.8f, 1, 0.8f, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        animation.setInterpolator(mContext, android.R.anim.bounce_interpolator);
        return animation;
    }
}
