package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface State extends ISubscriber {
    void myMouseClicked(RoomView roomView, Point location);
    void myMouseScrolled();
    void myMouseReleased(RoomView roomView);
    void myMouseDragged(RoomView roomView, MouseEvent event);
}
