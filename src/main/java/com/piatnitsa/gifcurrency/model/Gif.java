package com.piatnitsa.gifcurrency.model;

import java.util.Objects;

/**
 * This class represents the entity of the GIF image.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class Gif {
    private String url;
    private String tag;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gif gif = (Gif) o;
        return Objects.equals(url, gif.url) && Objects.equals(tag, gif.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, tag);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Gif{");
        builder.append("url='").append(url);
        builder.append(", tag='").append(tag);
        builder.append('}');
        return builder.toString();
    }
}
