package jp.co.cyberagent.android.gpuimage.sample.other;

import android.content.Context;
import android.graphics.BitmapFactory;
import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;
import jp.co.cyberagent.android.gpuimage.sample.R;

/**
 * Created by sam on 14-8-9.
 */
public class GPUImageLordKelvinFilter extends GPUImageTwoInputFilter {

    private static final String SHADER = "precision lowp float;\n" +
            " \n" +
            " varying highp vec2 textureCoordinate;\n" +
            " \n" +
            " uniform sampler2D inputImageTexture;\n" +
            " uniform sampler2D inputImageTexture2;\n" +
            " \n" +
            " void main()\n" +
            " {\n" +
            "     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n" +
            "     \n" +
            "     vec2 lookup;\n" +
            "     lookup.y = .5;\n" +
            "     \n" +
            "     lookup.x = texel.r;\n" +
            "     texel.r = texture2D(inputImageTexture2, lookup).r;\n" +
            "     \n" +
            "     lookup.x = texel.g;\n" +
            "     texel.g = texture2D(inputImageTexture2, lookup).g;\n" +
            "     \n" +
            "     lookup.x = texel.b;\n" +
            "     texel.b = texture2D(inputImageTexture2, lookup).b;\n" +
            "     \n" +
            "     gl_FragColor = vec4(texel, 1.0);\n" +
            " }";

    private Context mContext;

    public GPUImageLordKelvinFilter(Context context) {
        super(SHADER);
        mContext = context;
        setRes();
    }

    private void setRes() {
        setBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.kelvin_map));
    }
}
