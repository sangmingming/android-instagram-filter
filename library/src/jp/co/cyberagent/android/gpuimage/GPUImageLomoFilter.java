/*
 * Copyright (C) 2012 CyberAgent
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

public class GPUImageLomoFilter extends GPUImageFilter {
    public static final String LOMO_FRAGMENT_SHADER =  " precision mediump float;" +
            "varying highp vec2 textureCoordinate;\n" +
            " \n" +
            " uniform sampler2D inputImageTexture;\n" +
            " uniform sampler2D inputImageTexture2;\n" +
            " uniform sampler2D inputImageTexture3;\n" +
            " \n" +
            " void main()\n" +
            " {\n" +
            "     \n" +
            "     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n" +
            "     \n" +
            "     vec2 red = vec2(texel.r, 0.16666);\n" +
            "     vec2 green = vec2(texel.g, 0.5);\n" +
            "     vec2 blue = vec2(texel.b, 0.83333);\n" +
            "     \n" +
            "     texel.rgb = vec3(\n" +
            "                      texture2D(inputImageTexture2, red).r,\n" +
            "                      texture2D(inputImageTexture2, green).g,\n" +
            "                      texture2D(inputImageTexture2, blue).b);\n" +
            "     \n" +
            "     vec2 tc = (2.0 * textureCoordinate) - 1.0;\n" +
            "     float d = dot(tc, tc);\n" +
            "     vec2 lookup = vec2(d, texel.r);\n" +
            "     texel.r = texture2D(inputImageTexture3, lookup).r;\n" +
            "     lookup.y = texel.g;\n" +
            "     texel.g = texture2D(inputImageTexture3, lookup).g;\n" +
            "     lookup.y = texel.b;\n" +
            "     texel.b\t= texture2D(inputImageTexture3, lookup).b;\n" +
            "     \n" +
            "     gl_FragColor = vec4(texel,1.0);\n" +
            " }";

    public GPUImageLomoFilter() {
        super(NO_FILTER_VERTEX_SHADER, LOMO_FRAGMENT_SHADER);
    }

    protected int mGLUniformTexture2;
    protected int mGLUniformTexture3;


    public void onInit() {
        super.onInit();
        mGLUniformTexture2 = GLES20.glGetUniformLocation(mGLProgId, "inputImageTexture2");
        mGLUniformTexture3 = GLES20.glGetUniformLocation(mGLProgId, "inputImageTexture3");
    }

    @Override
    protected void onDrawArraysPre() {

        GLES20.glUniform1i(mGLUniformTexture2, 3);
        GLES20.glUniform1i(mGLUniformTexture3, 3);

    }
}
