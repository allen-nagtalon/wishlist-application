package io.aanagtalon.backend.enumeration;

public enum ImageType {
    USER("user"),
    WISH("wish"),
    WISHLIST("wishlist");

    public final String label;

    ImageType(String label) {
        this.label = label;
    }
}
