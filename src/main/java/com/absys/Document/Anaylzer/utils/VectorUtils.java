package com.absys.Document.Anaylzer.utils;

import org.springframework.stereotype.Component;

@Component
public class VectorUtils {

    public String toVectorString(float[] embedding) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < embedding.length; i++) {
            sb.append(embedding[i]);
            if (i < embedding.length - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
