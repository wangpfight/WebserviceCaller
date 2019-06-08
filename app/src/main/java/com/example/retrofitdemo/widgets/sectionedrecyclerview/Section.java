package com.example.retrofitdemo.widgets.sectionedrecyclerview;

import java.util.List;

public interface Section<C> {
    /**
     * Getter for the list of this parent's child items.
     * <p>
     * If list is empty, the parent has no children.
     *
     * @return A {@link List} of the children of this {@link Section}
     */
    List<C> getChildItems();
}
