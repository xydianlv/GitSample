package com.example.gitsample.canvas.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.resource.R;
import com.example.utils.UIUtils;

public class CanvasImageShadow extends View {

    public CanvasImageShadow(Context context) {
        this(context, null);
    }

    public CanvasImageShadow(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasImageShadow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private static final int RES_ID = R.mipmap.img_test;

    private final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), RES_ID);
    private final Matrix matrix = new Matrix();
    private final Paint paint = new Paint();
    private final Rect rect = new Rect();

    private LinearGradient linearGradient;
    private LinearGradient topShadow;
    private int drawHeight;
    private int drawWidth;
    private int pixel;

    private void init() {
        int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        pixel = bitmap.getPixel(imgWidth / 2, imgHeight - 1);

        drawWidth = UIUtils.screenWidth();
        float divide = drawWidth * 1.0f / imgWidth;
        matrix.postScale(divide, divide);
        drawHeight = (int) (imgHeight * divide);

        linearGradient = new LinearGradient(0, drawHeight * 1.0f / 3, 0, drawHeight * 2.0f / 3, 0, pixel, Shader.TileMode.CLAMP);

        int colorStart = ContextCompat.getColor(getContext(), com.example.gitsample.R.color.color_33212121);
        int colorEnd = ContextCompat.getColor(getContext(), com.example.gitsample.R.color.color_FF1D1D1D);
        topShadow = new LinearGradient(0, 0, 0, drawHeight, colorStart, colorEnd, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, matrix, null);

        paint.setColor(pixel);
        rect.left = 0;
        rect.top = drawHeight / 3 * 2;
        rect.right = drawWidth;
        rect.bottom = drawHeight;
        canvas.drawRect(rect, paint);

        paint.setShader(linearGradient);
        rect.left = 0;
        rect.top = drawHeight / 3;
        rect.right = drawWidth;
        rect.bottom = drawHeight * 2 / 3;
        canvas.drawRect(rect, paint);

        paint.setShader(topShadow);
        rect.left = 0;
        rect.top = 0;
        rect.right = drawWidth;
        rect.bottom = drawHeight;
        canvas.drawRect(rect, paint);

        bitmap.recycle();
    }
}
