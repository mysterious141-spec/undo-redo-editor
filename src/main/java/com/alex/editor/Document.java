package com.alex.editor;

public class Document {
    private final StringBuilder text = new StringBuilder();
    private int cursor = 0;

    public void insertAt(int pos, String s) { text.insert(pos, s); }

    public String deleteAt(int pos, int len) {
        String removed = text.substring(pos, pos + len);
        text.delete(pos, pos + len);
        return removed;
    }

    public int getCursor() { return cursor; }

    public void setCursor(int pos) {
        cursor = Math.max(0, Math.min(pos, text.length()));
    }

    public int length() { return text.length(); }
    public String getText() { return text.toString(); }

    public String getTextWithCursor() {
        return new StringBuilder(text).insert(cursor, '|').toString();
    }
}