package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;

public interface State extends ISubscriber {
    void myMouseClicked(RoomView roomView, Point location);
    void myMouseScroll();
    void myMouseReleased();
    void myMouseDragged();
}
