package jp.co.cyberagent.android.gpuimage.sample.other;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.OpenGlUtils;
import jp.co.cyberagent.android.gpuimage.Rotation;
import jp.co.cyberagent.android.gpuimage.util.TextureRotationUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by sam on 14-8-8.
 */

public class GPUImageTwoTextureFilter extends GPUImageFilter {
    private static final String VERTEX_SHADER = "attribute vec4 position;\n" +
            "attribute vec4 inputTextureCoordinate;\n" +
            "attribute vec4 inputTextureCoordinate2;\n" +
            "attribute vec4 inputTextureCoordinate3;\n \n" +
            "varying vec2 textureCoordinate;\n" +
            "varying vec2 textureCoordinate2;\nvarying vec2 textureCoordinate3;\n \n" +
            "void main()\n{\n    " +
            "gl_Position = position;\n    " +
            "textureCoordinate = inputTextureCoordinate.xy;\n    " +
            "textureCoordinate2 = inputTextureCoordinate2.xy;\n    " +
            "textureCoordinate3 = inputTextureCoordinate3.xy;" +
            "\n}";
    private int filterInputTextureUniform2;
    private int filterInputTextureUniform3;
    private int filterSourceTexture2 = -1;
    private int filterSourceTexture3 = -1;
    private int filterTextureCoordinateAttribute2;
    private int filterTextureCoordinateAttribute3;
    private Bitmap[] mBitmap;
    private ByteBuffer mTexture2CoordinatesBuffer2 = setRotation(Rotation.NORMAL, false, false);
    private ByteBuffer mTexture2CoordinatesBuffer3 = setRotation(Rotation.NORMAL, false, false);

    public GPUImageTwoTextureFilter(String paramString) {
        super(VERTEX_SHADER, paramString);
    }

    public void onDestroy() {
        super.onDestroy();
        int[] arrayOfInt1 = new int[1];
        arrayOfInt1[0] = this.filterSourceTexture2;
        GLES20.glDeleteTextures(1, arrayOfInt1, 0);
        this.filterSourceTexture2 = OpenGlUtils.NO_TEXTURE;
        int[] arrayOfInt2 = new int[1];
        arrayOfInt2[0] = this.filterSourceTexture3;
        GLES20.glDeleteTextures(1, arrayOfInt2, 0);
        this.filterSourceTexture3 = OpenGlUtils.NO_TEXTURE;
    }

    protected void onDrawArraysPre() {
        GLES20.glEnableVertexAttribArray(this.filterTextureCoordinateAttribute2);
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(3553, this.filterSourceTexture2);
        GLES20.glUniform1i(this.filterInputTextureUniform2, 3);
        this.mTexture2CoordinatesBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.filterTextureCoordinateAttribute2, 2, 5126, false, 0, this.mTexture2CoordinatesBuffer2);
        GLES20.glEnableVertexAttribArray(this.filterTextureCoordinateAttribute3);
        GLES20.glActiveTexture(33988);
        GLES20.glBindTexture(3553, this.filterSourceTexture3);
        GLES20.glUniform1i(this.filterInputTextureUniform3, 4);
        this.mTexture2CoordinatesBuffer3.position(0);
        GLES20.glVertexAttribPointer(this.filterTextureCoordinateAttribute3, 2, 5126, false, 0, this.mTexture2CoordinatesBuffer3);
    }

    public void onInit() {
        super.onInit();
        this.filterTextureCoordinateAttribute2 = GLES20.glGetAttribLocation(getProgram(), "inputTextureCoordinate2");
        this.filterInputTextureUniform2 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture2");
        this.filterTextureCoordinateAttribute3 = GLES20.glGetAttribLocation(getProgram(), "inputTextureCoordinate3");
        this.filterInputTextureUniform3 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture3");
    }

    public void setBitmap(final Bitmap[] paramArrayOfBitmap) {
        this.mBitmap = paramArrayOfBitmap;
        runOnDraw(new Runnable() {
            public void run() {
                if (GPUImageTwoTextureFilter.this.filterSourceTexture2 == -1)
                    filterSourceTexture2 = OpenGlUtils.loadTexture(paramArrayOfBitmap[0], -1, true);
                if (GPUImageTwoTextureFilter.this.filterSourceTexture3 == -1)
                    filterSourceTexture3 = OpenGlUtils.loadTexture(paramArrayOfBitmap[1], -1, true);
            }
        });
    }

    public ByteBuffer setRotation(Rotation paramRotation, boolean paramBoolean1, boolean paramBoolean2) {
        float[] arrayOfFloat = TextureRotationUtil.getRotation(paramRotation, paramBoolean1, paramBoolean2);
        ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer localFloatBuffer = localByteBuffer.asFloatBuffer();
        localFloatBuffer.put(arrayOfFloat);
        localFloatBuffer.flip();
        return localByteBuffer;
    }
}