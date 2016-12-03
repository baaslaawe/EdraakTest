package org.edraak.edraaktest.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class MetaModel implements Serializable {

    @SerializedName("next_offset")
    private long nextOffset;

    @SerializedName("offset")
    private long offset;

    public long getNextOffset() {
        return nextOffset;
    }

    public long getOffset() {
        return offset;
    }
}
