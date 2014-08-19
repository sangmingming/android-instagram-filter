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
 * Created by sam on 14-8-9.
 */
public class GPUImageFourTextureFilter extends GPUImageFilter
{
    private static final String VERTEX_SHADER = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\nattribute vec4 inputTextureCoordinate3;\nattribute vec4 inputTextureCoordinate4;\nattribute vec4 inputTextureCoordinate5;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\nvarying vec2 textureCoordinate3;\nvarying vec2 textureCoordinate4;\nvarying vec2 textureCoordinate5;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n    textureCoordinate3 = inputTextureCoordinate3.xy;\n    textureCoordinate4 = inputTextureCoordinate4.xy;\n    textureCoordinate5 = inputTextureCoordinate5.xy;\n}";
    private int filterInputTextureUniform2;
    private int filterInputTextureUniform3;
    private int filterInputTextureUniform4;
    private int filterInputTextureUniform5;
    private int filterSourceTexture2 = -1;
    private int filterSourceTexture3 = -1;
    private int filterSourceTexture4 = -1;
    private int filterSourceTexture5 = -1;
    private int filterTextureCoordinateAttribute2;
    private int filterTextureCoordinateAttribute3;
    private int filterTextureCoordinateAttribute4;
    private int filterTextureCoordinateAttribute5;
    private Bitmap[] mBitmap;
    private ByteBuffer mTexture2CoordinatesBuffer2 = setRotation(Rotation.NORMAL, false, false);
    private ByteBuffer mTexture2CoordinatesBuffer3 = setRotation(Rotation.NORMAL, false, false);
    private ByteBuffer mTexture2CoordinatesBuffer4 = setRotation(Rotation.NORMAL, false, false);
    private ByteBuffer mTexture2CoordinatesBuffer5 = setRotation(Rotation.NORMAL, false, false);

    public GPUImageFourTextureFilter(String paramString)
    {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\nattribute vec4 inputTextureCoordinate3;\nattribute vec4 inputTextureCoordinate4;\nattribute vec4 inputTextureCoordinate5;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\nvarying vec2 textureCoordinate3;\nvarying vec2 textureCoordinate4;\nvarying vec2 textureCoordinate5;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n    textureCoordinate3 = inputTextureCoordinate3.xy;\n    textureCoordinate4 = inputTextureCoordinate4.xy;\n    textureCoordinate5 = inputTextureCoordinate5.xy;\n}", paramString);
    }

    public void onDestroy()
    {
        super.onDestroy();
        int[] arrayOfInt1 = new int[1];
        arrayOfInt1[0] = this.filterSourceTexture2;
        GLES20.glDeleteTextures(1, arrayOfInt1, 0);
        this.filterSourceTexture2 = -1;
        int[] arrayOfInt2 = new int[1];
        arrayOfInt2[0] = this.filterSourceTexture3;
        GLES20.glDeleteTextures(1, arrayOfInt2, 0);
        this.filterSourceTexture3 = -1;
        int[] arrayOfInt3 = new int[1];
        arrayOfInt3[0] = this.filterSourceTexture4;
        GLES20.glDeleteTextures(1, arrayOfInt3, 0);
        this.filterSourceTexture4 = -1;
        int[] arrayOfInt4 = new int[1];
        arrayOfInt4[0] = this.filterSourceTexture5;
        GLES20.glDeleteTextures(1, arrayOfInt4, 0);
        this.filterSourceTexture5 = -1;
    }

    protected void onDrawArraysPre()
    {
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
        GLES20.glEnableVertexAttribArray(this.filterTextureCoordinateAttribute4);
        GLES20.glActiveTexture(33989);
        GLES20.glBindTexture(3553, this.filterSourceTexture4);
        GLES20.glUniform1i(this.filterInputTextureUniform4, 5);
        this.mTexture2CoordinatesBuffer4.position(0);
        GLES20.glVertexAttribPointer(this.filterTextureCoordinateAttribute4, 2, 5126, false, 0, this.mTexture2CoordinatesBuffer4);
        GLES20.glEnableVertexAttribArray(this.filterTextureCoordinateAttribute5);
        GLES20.glActiveTexture(33990);
        GLES20.glBindTexture(3553, this.filterSourceTexture5);
        GLES20.glUniform1i(this.filterInputTextureUniform5, 6);
        this.mTexture2CoordinatesBuffer5.position(0);
        GLES20.glVertexAttribPointer(this.filterTextureCoordinateAttribute5, 2, 5126, false, 0, this.mTexture2CoordinatesBuffer5);
    }

    public void onInit()
    {
        super.onInit();
        this.filterTextureCoordinateAttribute2 = GLES20.glGetAttribLocation(getProgram(), "inputTextureCoordinate2");
        this.filterInputTextureUniform2 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture2");
        this.filterTextureCoordinateAttribute3 = GLES20.glGetAttribLocation(getProgram(), "inputTextureCoordinate3");
        this.filterInputTextureUniform3 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture3");
        this.filterTextureCoordinateAttribute4 = GLES20.glGetAttribLocation(getProgram(), "inputTextureCoordinate4");
        this.filterInputTextureUniform4 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture4");
        this.filterTextureCoordinateAttribute5 = GLES20.glGetAttribLocation(getProgram(), "inputTextureCoordinate5");
        this.filterInputTextureUniform5 = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture5");
    }

    public void setBitmap(final Bitmap[] paramArrayOfBitmap)
    {
        this.mBitmap = paramArrayOfBitmap;
        runOnDraw(new Runnable()
        {
            public void run()
            {
                if (GPUImageFourTextureFilter.this.filterSourceTexture2 == -1)
                    filterSourceTexture2 = OpenGlUtils.loadTexture(paramArrayOfBitmap[0], -1, true);
                if (GPUImageFourTextureFilter.this.filterSourceTexture3 == -1)
                    filterSourceTexture3 = OpenGlUtils.loadTexture(paramArrayOfBitmap[1], -1, true);
                if (GPUImageFourTextureFilter.this.filterSourceTexture4 == -1)
                    filterSourceTexture4 = OpenGlUtils.loadTexture(paramArrayOfBitmap[2], -1, true);
                if (GPUImageFourTextureFilter.this.filterSourceTexture5 == -1)
                    filterSourceTexture5 = OpenGlUtils.loadTexture(paramArrayOfBitmap[3], -1, true);
            }
        });
    }

    public ByteBuffer setRotation(Rotation paramRotation, boolean paramBoolean1, boolean paramBoolean2)
    {
        float[] arrayOfFloat = TextureRotationUtil.getRotation(paramRotation, paramBoolean1, paramBoolean2);
        ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer localFloatBuffer = localByteBuffer.asFloatBuffer();
        localFloatBuffer.put(arrayOfFloat);
        localFloatBuffer.flip();
        return localByteBuffer;
    }
}
