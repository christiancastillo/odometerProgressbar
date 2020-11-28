package com.mohamadsajjad.progressbarlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;




public class CustomProgress extends androidx.appcompat.widget.AppCompatImageView {

    Paint circlePaint;
    Paint circlePaintcenter;
    Paint textPaint;
    Paint linePaint;
    Paint paintDotted;
    private float STROKE_WIDTH = 30;
    private int textSize = 24;
    private int percent;
    private int percentOut;
    private Bitmap bitmap;
    private String description;
    private String title;
    private int colorDescription;
    private boolean descKey;

    public CustomProgress(Context context) {
        super(context);
        initialize(context);
    }

    public CustomProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CustomProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public CustomProgress setPercent(int value) {
        percentOut = value;
        return this;
    }

    public CustomProgress setTitle(String value) {
        title = value;
        return this;
    }

    public CustomProgress setDescription(String value) {
        description = value;
        return this;
    }

    public CustomProgress setColorDescription(@ColorRes int value) {
        colorDescription = getResources().getColor(value);
        return this;
    }

    public void start() {
        title = (title == null) ? "percent" : title;
        descKey = description != null;
        Thread threadAnim = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 4 * percentOut; i++) {
                        postInvalidate();
                        Thread.sleep(20);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadAnim.start();
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= percentOut; i++) {
                        percent = i;
                        Thread.sleep(50);
                    }
                    boolean key = true;
                    int j = 0;
                    for (int i = 0; i < 16; i++) {
                        if (j < 3 && key) {
                            j++;
                            percent++;
                        } else {
                            j--;
                            percent--;
                            key = j == -2;
                        }
                        Thread.sleep(30);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    private void initialize(Context context) {
        STROKE_WIDTH = getDimension(R.dimen.stroke_width_speedometer);
        textSize = (int) getDimension(R.dimen.text_size_speedometer);
        colorDescription = Color.DKGRAY;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.index);


        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#FF8f00"));
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(STROKE_WIDTH);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);

        paintDotted = new Paint();
        paintDotted.setColor(Color.RED);
        paintDotted.setAntiAlias(true);
        paintDotted.setStrokeWidth(8);
        DashPathEffect dashPath = new DashPathEffect(new float[]{2, 8}, (float) 1.0);
        paintDotted.setPathEffect(dashPath);
        paintDotted.setStyle(Paint.Style.STROKE);

        circlePaintcenter = new Paint();
        circlePaintcenter.setColor(Color.parseColor("#FF8f00"));
        circlePaintcenter.setAntiAlias(true);
        circlePaintcenter.setStrokeWidth(STROKE_WIDTH);
        circlePaintcenter.setStyle(Paint.Style.FILL_AND_STROKE);

        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#FF8f00"));
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(6);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        RectF rect = new RectF();
        rect.left = 0 + STROKE_WIDTH;
        rect.right = width - STROKE_WIDTH;
        rect.top = 0 + STROKE_WIDTH;
        rect.bottom = height - STROKE_WIDTH;

        RectF rectDotted = new RectF();
        rectDotted.left = 0 + 2.2F * STROKE_WIDTH;
        rectDotted.right = width - 2.2F * STROKE_WIDTH;
        rectDotted.top = 0 + 2.2F * STROKE_WIDTH;
        rectDotted.bottom = height - 2.2F * STROKE_WIDTH;

        RectF rectPic = new RectF();
        rectPic.left = 0 + STROKE_WIDTH;
        rectPic.right = width - STROKE_WIDTH;
        rectPic.top = 0 + STROKE_WIDTH + height / 1.5F;
        rectPic.bottom = height - STROKE_WIDTH / 2.5F;

        int sweepAngle = percent * 180 / 100;
        int red = (int) ((100 - percent) * 2.5f);
        int green = (int) (percent * 2.0f);
        circlePaintcenter.setColor(Color.rgb(red, green, 100));

        int radiusCircleCenter = height / 6;
        float radius = width / 2 - 3 * STROKE_WIDTH;
        float x = (float) -(radius * Math.cos(sweepAngle * Math.PI / 180F)) + width / 2;
        float y = (float) -(radius * Math.sin(sweepAngle * Math.PI / 180F)) + height / 2;

        float radiuscir = width / 2 -STROKE_WIDTH;
        float xcir = (float) -(radiuscir * Math.cos(sweepAngle * Math.PI / 180F)) + width / 2;
        float ycir = (float) -(radiuscir * Math.sin(sweepAngle * Math.PI / 180F)) + height / 2;

        linePaint.setColor(Color.parseColor("#CBCBCB"));
        linePaint.setStrokeCap(Paint.Cap.BUTT);
        linePaint.setStrokeWidth(2);
        canvas.drawLine(width / 2, height / 2, width / 3.3F, height / 7F, linePaint);
        canvas.drawLine(width / 2, height / 2, (width / 2) * 1.4F, height / 7, linePaint);


        //arc doted
        paintDotted.setColor(Color.RED);
        canvas.drawArc(rectDotted, 180, 60, false, paintDotted);
        paintDotted.setColor(Color.parseColor("#55aa88"));
        canvas.drawArc(rectDotted, 240, 60, false, paintDotted);
        paintDotted.setColor(Color.GREEN);
        canvas.drawArc(rectDotted, 300, 60, false, paintDotted);

        //arc gray & arc main
        circlePaint.setColor(Color.LTGRAY);
        canvas.drawArc(rect, 180, 180, false, circlePaint);
        circlePaint.setColor(Color.rgb(red, green, 100));
        canvas.drawArc(rect, 180, sweepAngle, false, circlePaint);

        //pointer
        textPaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(6);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setColor(Color.rgb(red, green, 100));
        canvas.drawCircle(xcir,ycir,8,textPaint);
        canvas.drawLine(width / 2, height / 2, x, y, linePaint);
        canvas.drawLine(width / 2 - 8, height / 2 - 8, x, y, linePaint);
        canvas.drawLine(width / 2 + 8, height / 2 + 8, x, y, linePaint);

        //title
        canvas.drawCircle(width / 2, height / 2, radiusCircleCenter, circlePaintcenter);
        textPaint.setTextSize(textSize + 12);
        canvas.drawText(percent + "%", width / 2, height / 2.2F, textPaint);
        textPaint.setTextSize(textSize);
        canvas.drawText(title, width / 2, height / 1.8F, textPaint);

        //description
        if (descKey) {
            canvas.drawBitmap(bitmap, null, rectPic, circlePaint);
            textPaint.setColor(colorDescription);
            canvas.drawText(description, rectPic.centerX(), rectPic.centerY() * 1.03F, textPaint);
        }
    }
}

