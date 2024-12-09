package raf.draft.dsw.jtabbedpane.view;

import org.w3c.dom.ls.LSOutput;
import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.state.StateManager;
import raf.draft.dsw.gui.swing.state.addstate.AddStateManager;
import raf.draft.dsw.gui.swing.state.addstate.BedState;
import raf.draft.dsw.jtabbedpane.controller.RoomController;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoomView extends JPanel implements ISubscriber {
    private JPanel jPanelRoom;
    private Room room;
    private RoomController roomController;
    private int width, height;
    private Point mouseLocation;
    private StateManager stateManager=new StateManager();
    double k_length = 0, k_width = 0;
    public RoomView(Room room){
        this.room=room;
        room.addSubscriber(this);
        roomController=new RoomController(this,room);
        initialize();
    }

    public void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        jPanelRoom=new JPanel();
        jPanelRoom.setPreferredSize(new Dimension((int)kit.getScreenSize().getWidth(),(int)kit.getScreenSize().getHeight()));
        if(room.getParent() instanceof Building){
            jPanelRoom.setBackground(((Building) room.getParent()).getColor());
        }
        if(room.getParent() instanceof Project){
            jPanelRoom.setBackground(((Project) room.getParent()).getColor());
        }
        jPanelRoom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseLocation=e.getPoint();
                myMouseClicked();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                myMouseReleased();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    //STATE-PATTERN
    public void startEdit(){
        stateManager.setEditRoomState();
    }
    public void startAdd(){
        stateManager.setAddState();
    }
    public void myMouseClicked(){
        stateManager.getCurrentState().myMouseClicked(this,mouseLocation);
    }
    public void myMouseReleased(){
        stateManager.getCurrentState().myMouseReleased();
    }
    public void myMouseScrolled(){
        stateManager.getCurrentState().myMouseScroll();
    }
    public void myMouseDragged(){
        stateManager.getCurrentState().myMouseDragged();
    }

    //END OF STATE-PATTERN
    @Override
    public void update(Object o) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screensize=kit.getScreenSize();

        if(o instanceof Room){
            double heightPixel=((Room) o).convertCmIntoPixel(height);
            double widthPixel=((Room) o).convertCmIntoPixel(width);

            double aspectRatio=widthPixel/heightPixel;
            double screenHeight=screensize.getHeight();
            double screenWidth=screensize.getWidth();

            if(widthPixel>screenWidth || heightPixel>screenHeight){
                if(aspectRatio>1){
                    widthPixel=screenWidth*0.9;
                    heightPixel=widthPixel/aspectRatio;
                }else{
                    heightPixel=screenHeight*0.9;
                    widthPixel=heightPixel*aspectRatio;
                }
            }
            k_length =  screenHeight / heightPixel;
            k_width =  screenWidth / widthPixel;
            System.out.println("RoomView Length: " + k_length + " Width: " + k_width);

            jPanelRoom.setPreferredSize(new Dimension((int)widthPixel,(int)heightPixel));
            jPanelRoom.revalidate();
            jPanelRoom.repaint();
        }else if(o instanceof AddStateManager){
            ((AddStateManager) o).getCurrentState().myMouseClicked(this,mouseLocation);
        }
    }

    public Point getMouseLocation() {
        return mouseLocation;
    }

    public void setMouseLocation(Point mouseLocation) {
        this.mouseLocation = mouseLocation;
    }

    public JPanel getjPanelRoom() {
        return jPanelRoom;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setjPanelRoom(JPanel jPanelRoom) {
        this.jPanelRoom = jPanelRoom;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomController getRoomController() {
        return roomController;
    }

    public void setRoomController(RoomController roomController) {
        this.roomController = roomController;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getK_length() {
        return k_length;
    }

    public double getK_width() {
        return k_width;
    }
}
