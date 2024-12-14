package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State extends ISubscriber {
    void myMouseClicked(RoomView roomView, Point location);
    void myMouseScrolled(RoomView roomView, MouseWheelEvent event);
    void myMouseReleased(RoomView roomView);
    void myMouseDragged(RoomView roomView, MouseEvent event);
}
