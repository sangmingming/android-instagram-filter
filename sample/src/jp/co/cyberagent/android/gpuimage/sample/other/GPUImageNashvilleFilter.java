package jp.co.cyberagent.android.gpuimage.sample.other;

import android.content.Context;
import android.graphics.BitmapFactory;
import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;
import jp.co.cyberagent.android.gpuimage.sample.R;

/**
 * Created by sam on 14-8-9.
 */
public class GPUImageNashvilleFilter extends GPUImageTwoInputFilter {

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
            "     texel = vec3(\n" +
            "                  texture2D(inputImageTexture2, vec2(texel.r, .16666)).r,\n" +
            "                  texture2D(inputImageTexture2, vec2(texel.g, .5)).g,\n" +
            "                  texture2D(inputImageTexture2, vec2(texel.b, .83333)).b);\n" +
            "     gl_FragColor = vec4(texel, 1.0);\n" +
            " }";

    private Context mContext;

    public GPUImageNashvilleFilter(Context context) {
        super(SHADER);
        mContext = context;
        setRes();
    }

    private void setRes() {
        setBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.nashville_map));
    }
}
