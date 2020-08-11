package tdc.edu.vn.test.view_model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class HeartView extends View {
    private Path path;
    private Paint mCirclePaint;
    private Paint mEyeAndMouthPaint;
    private Paint paint;

    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private RectF mArcBounds = new RectF();

    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEyeAndMouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEyeAndMouthPaint.setStyle(Paint.Style.STROKE);
        mEyeAndMouthPaint.setStrokeWidth(16 * getResources().getDisplayMetrics().density);
        mEyeAndMouthPaint.setStrokeCap(Paint.Cap.ROUND);
        mEyeAndMouthPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                path.addCircle(eventX, eventY, 10, Path.Direction.CCW);
                break;
            case MotionEvent.ACTION_CANCEL: {
                path.addCircle(eventX, eventY, 10, Path.Direction.CCW);
                break;
            }
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Draw
        float width = 1000;
        float height = 1000;

        canvas.drawColor(Color.WHITE);
        paint.setShader(null);

        // Starting point
        path.moveTo(width / 2, height / 5);

        // Upper left path
        path.cubicTo(5 * width / 14, 0,
                0, height / 15,
                width / 28, 2 * height / 5);

        // Lower left path
        path.cubicTo(width / 14, 2 * height / 3,
                3 * width / 7, 5 * height / 6,
                width / 2, height);

        // Lower right path
        path.cubicTo(4 * width / 7, 5 * height / 6,
                13 * width / 14, 2 * height / 3,
                27 * width / 28, 2 * height / 5);

        // Upper right path
        path.cubicTo(width, height / 15,
                9 * width / 14, 0,
                width / 2, height / 5);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        //canvas.drawPath(path,mEyeAndMouthPaint);
        //canvas.drawPoint(mCenterX,mCenterY,mEyeAndMouthPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w ;
        mCenterY = h ;
        mRadius = Math.min(w, h) / 2f;
    }


}



