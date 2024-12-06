package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.jtabbedpane.view.RoomView;

public interface State {
    void myMouseClicked(RoomView roomView);
    void myMouseScroll();
    void myMouseReleased();
    void myMouseDragged();
}
