package com.alex.editor;

public interface Command {
    void execute();
    void undo();
}