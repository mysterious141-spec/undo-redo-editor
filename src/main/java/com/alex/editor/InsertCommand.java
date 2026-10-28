package com.alex.editor;

public class InsertCommand implements Command {
    private final Document doc;
    private final int pos;
    private final String value;

    public InsertCommand(Document doc, int pos, String value) {
        this.doc = doc;
        this.pos = pos;
        this.value = value;
    }

    @Override public void execute() {
        doc.insertAt(pos, value);
        doc.setCursor(pos + value.length());
    }

    @Override public void undo() {
        doc.deleteAt(pos, value.length());
        doc.setCursor(pos);
    }
}