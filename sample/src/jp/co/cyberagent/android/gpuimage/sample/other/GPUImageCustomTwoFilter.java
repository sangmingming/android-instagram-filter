package jp.co.cyberagent.android.gpuimage.sample.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import jp.co.cyberagent.android.gpuimage.sample.R;

/**
 * Created by sam on 14-8-9.
 */
public class GPUImageCustomTwoFilter extends GPUImageFourTextureFilter
{
    private static final String SHADER = "\n precision lowp float;\n\n " +
            "varying highp vec2 textureCoordinate;\n\n " +
            "uniform sampler2D inputImageTexture;\n " +
            "uniform sampler2D inputImageTexture2; //toasterSoftlight\n " +
            "uniform sampler2D inputImageTexture3; //toasterCurves\n " +
            "uniform sampler2D inputImageTexture4; //toasterOverlaymapWarm\n " +
            "uniform sampler2D inputImageTexture5; //toasterColorshift\n\n " +
            "void main()\n  " +
            "{\n  " +
            "lowp vec3 texel;\n   " +
            "mediump vec2 lookup;\n   " +
            "vec2 blue;\n   " +
            "vec2 green;\n   " +
            "vec2 red;\n   " +
            "lowp vec4 tmpvar_1;\n   " +
            "tmpvar_1 = texture2D (inputImageTexture, textureCoordinate);\n   " +
            "texel = tmpvar_1.xyz;\n   " +
            "lowp vec2 tmpvar_3;\n   " +
            "tmpvar_3.x = tmpvar_1.x;\n   " +
            "tmpvar_3.y = tmpvar_1.x;\n   " +
            "texel.x = texture2D (inputImageTexture2, tmpvar_3).x;\n   " +
            "lowp vec2 tmpvar_4;\n   " +
            "tmpvar_4.x = tmpvar_1.y;\n   " +
            "tmpvar_4.y = tmpvar_1.y;\n   " +
            "texel.y = texture2D (inputImageTexture2, tmpvar_4).y;\n   " +
            "lowp vec2 tmpvar_5;\n   " +
            "tmpvar_5.x = tmpvar_1.z;\n   " +
            "tmpvar_5.y = tmpvar_1.z;\n   texel.z = texture2D (inputImageTexture2, tmpvar_5).z;\n   red.x = texel.x;\n   red.y = 0.16666;\n   green.x = texel.y;\n   green.y = 0.5;\n   blue.x = texel.z;\n   blue.y = 0.833333;\n   texel.x = texture2D (inputImageTexture3, red).x;\n   texel.y = texture2D (inputImageTexture3, green).y;\n   texel.z = texture2D (inputImageTexture3, blue).z;\n   mediump vec2 tmpvar_6;\n   tmpvar_6 = ((2.0 * textureCoordinate) - 1.0);\n   mediump vec2 tmpvar_7;\n   tmpvar_7.x = dot (tmpvar_6, tmpvar_6);\n   tmpvar_7.y = texel.x;\n   lookup = tmpvar_7;\n   texel.x = texture2D (inputImageTexture4, tmpvar_7).x;\n   lookup.y = texel.y;\n   texel.y = texture2D (inputImageTexture4, lookup).y;\n   lookup.y = texel.z;\n   texel.z = texture2D (inputImageTexture4, lookup).z;\n   red.x = texel.x;\n   green.x = texel.y;\n   blue.x = texel.z;\n   texel.x = texture2D (inputImageTexture5, red).x;\n   texel.y = texture2D (inputImageTexture5, green).y;\n   texel.z = texture2D (inputImageTexture5, blue).z;\n   lowp vec4 tmpvar_8;\n   tmpvar_8.w = 1.0;\n   tmpvar_8.xyz = texel;\n   gl_FragColor = tmpvar_8;\n }";
    private Context context;

    public GPUImageCustomTwoFilter(Context paramContext)
    {
        super(SHADER);
        this.context = paramContext;
        setRes();
    }

    private void setRes()
    {
        Bitmap[] arrayOfBitmap = new Bitmap[4];
        arrayOfBitmap[0] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.toaster_soft_light);
        arrayOfBitmap[1] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.toaster_curves);
        arrayOfBitmap[2] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.toaster_overlay_map_warm);
        arrayOfBitmap[3] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.toaster_color_shift);
        setBitmap(arrayOfBitmap);
    }
}
