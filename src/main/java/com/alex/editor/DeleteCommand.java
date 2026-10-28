package com.alex.editor;

public class DeleteCommand implements Command {
    private final Document doc;
    private final int pos;
    private final int len;
    private String removed = "";

    public DeleteCommand(Document doc, int pos, int len) {
        this.doc = doc;
        this.pos = pos;
        this.len = len;
    }

    @Override public void execute() {
        removed = doc.deleteAt(pos, len);
        doc.setCursor(pos);
    }

    @Override public void undo() {
        doc.insertAt(pos, removed);
        doc.setCursor(pos + removed.length());
    }
}