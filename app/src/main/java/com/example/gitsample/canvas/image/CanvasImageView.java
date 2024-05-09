package com.example.gitsample.canvas.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class CanvasImageView extends View {

    public CanvasImageView(Context context) {
        this(context, null);
    }

    public CanvasImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        type = 4;
        bitmap = BitmapFactory.decodeResource(getResources(), RES_ID);

        paint = new Paint();
        paint.setAlpha((int) (0.4f * 255));
        rectF = new Rect();
        rectS = new Rect();
    }

    private static final int RES_ID = com.example.resource.R.mipmap.img_ball;

    private Bitmap bitmap;
    private Matrix matrix;
    private Paint paint;
    private Rect rectF;
    private Rect rectS;

    private int type;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (type == 0) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }

        if (type == 1) {
            if (matrix == null) {
                matrix = new Matrix();
            }
            matrix.postScale(0.5f, 0.5f);
            canvas.drawBitmap(bitmap, matrix, null);
        }

        if (type == 2) {
            if (matrix == null) {
                matrix = new Matrix();
            }
            matrix.postTranslate(100.0f, 100.0f);
            canvas.drawBitmap(bitmap, matrix, null);
        }

        if (type == 3) {
            if (matrix == null) {
                matrix = new Matrix();
            }
            matrix.postRotate(90, bitmap.getWidth() * 1.0f / 2, bitmap.getHeight() * 1.0f / 2);
            canvas.drawBitmap(bitmap, matrix, null);
        }

        if (type == 4) {
            if (matrix == null) {
                matrix = new Matrix();
            }
            matrix.postSkew(0.5f, 0.5f, bitmap.getWidth() * 1.0f / 2,
                    bitmap.getHeight() * 1.0f / 2);
            canvas.drawBitmap(bitmap, matrix, null);
        }

        if (type == 5) {
            blurBitmap();
            canvas.drawBitmap(bitmap, 0, 0, null);
        }

        if (type == 6) {
            blurBitmap();
            canvas.drawBitmap(bitmap, 0, 0, paint);
        }

        if (type == 7) {
            blurBitmap();
            if (rectF == null) {
                rectF = new Rect();
            }
            if (rectS == null) {
                rectS = new Rect();
            }
            for (int index = 0; index <= 255; index++) {
                paint.setAlpha(index);
                rectF.left = 0;
                rectS.left = 0;
                rectF.top = index;
                rectS.top = index;
                rectF.bottom = index + 1;
                rectS.bottom = index + 1;
                rectF.right = bitmap.getWidth();
                rectS.right = bitmap.getWidth();
                canvas.drawBitmap(bitmap, rectF, rectS, paint);
            }
            paint.setAlpha(255);
            rectF.left = 0;
            rectS.left = 0;
            rectF.top = 255;
            rectS.top = 255;
            rectF.bottom = bitmap.getHeight();
            rectS.bottom = bitmap.getHeight();
            rectF.right = bitmap.getWidth();
            rectS.right = bitmap.getWidth();
            canvas.drawBitmap(bitmap, rectF, rectS, paint);
        }

        if (type == 8) {
            blurBitmap();
            colorBitmap();
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    private void blurBitmap() {
        int width = Math.round(bitmap.getWidth() * 1.0f);
        int height = Math.round(bitmap.getHeight() * 1.0f);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript script = RenderScript.create(getContext());

        Allocation tmpIn = Allocation.createFromBitmap(script, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(script, outputBitmap);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(script, Element.U8_4(script));

            blur.setRadius(0.7f * 25.0f);
            blur.setInput(tmpIn);
            blur.forEach(tmpOut);

            tmpOut.copyTo(outputBitmap);
        }

        bitmap = outputBitmap;
    }

    private void colorBitmap() {

        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);

        canvas.drawBitmap(bitmap, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(0xffff0000);

        for (int index = 0; index < height; index++) {
            paint.setAlpha((int) ((index * 1.0f / height) * 255));
            canvas.drawRect(0, index, width, index + 1, paint);
        }

        canvas.save();
        canvas.restore();

        bitmap = newBitmap;
    }
}
