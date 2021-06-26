package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomView extends View {

    private Rect rect;
    private Paint paintrect;
    private Paint paintcircle;
    public float cy = 100f;
    private float x1 = 100f;
    private float x2 = 1000f;
    private float y1 = 100f;
    private float y2 = 1700f;
    private float b = 20f ;
    public int count = 0;
    public float cx = (float) Math.floor((Math.random() * (150f + 700f - 50f)) + 150f);
    public float a = (float) Math.floor((Math.random() * (20f + 25f - 1f)) + 20f);


    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

        paintcircle = new Paint();
        paintcircle.setAntiAlias(true);
        paintcircle.setColor(Color.GREEN);
        paintrect = new Paint();
        paintrect.setAntiAlias(true);
        paintrect.setColor(Color.RED);
        rect = new Rect();

        rect.left = 0;
        rect.right = 400;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rect.top = getHeight() - 200;
        rect.bottom = getHeight();
        float radius = 100f;
        canvas.drawCircle(cx, cy, radius, paintcircle);
        canvas.drawRect(rect, paintrect);
    }

    public void move() {
        if(cx > x2){a=-a;}
        if(cx < x1){a=-a;}
        if(cy < y1){b=-b;}
        if(cy == y2){
            if((cx > rect.left)&&(cx < rect.right))
            { b=-b;
                count++; }
        }

        cx = cx + a;
        cy = cy + b;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_MOVE){
            int x = (int) event.getX();
            int y = (int) event.getY();
            if ((rect.left < x)&&(rect.right > x)){
                if((rect.top < y)&&(rect.bottom > y)) {
                    rect.left = x - 200;
                    rect.right = x + 200;
                    postInvalidate();

                }  }}
        return true;
    }
}
