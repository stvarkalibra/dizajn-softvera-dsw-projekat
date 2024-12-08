package raf.draft.dsw.jtabbedpane.view;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.elements.*;
import raf.draft.dsw.gui.swing.painters.*;
import raf.draft.dsw.gui.swing.state.StateManager;
import raf.draft.dsw.jtabbedpane.controller.RoomController;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class RoomView extends JPanel implements ISubscriber {
    private JPanel jPanelRoom;
    private Room room;
    private RoomController roomController;
    private int width, height;
    private Point mouseLocation;
    private StateManager stateManager=new StateManager();
    private List<MyPainter> painters;

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

        FrameWork framework = new FrameWork();
        jPanelRoom.setLayout(new BorderLayout());
        jPanelRoom.add(framework, BorderLayout.CENTER);

        setDimensions();
    }
    public void setDimensions(){
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
        System.out.println(stateManager);
        stateManager.setAddState();
    }
    public void myMouseClicked(){
        stateManager.getCurrentState().myMouseClicked(this);
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
            jPanelRoom.setPreferredSize(new Dimension((int)widthPixel,(int)heightPixel));
            jPanelRoom.revalidate();
            jPanelRoom.repaint();
        }
    }

    public Point getMouseLocation() {
        return mouseLocation;
    }

    public void setMouseLocation(Point mouseLocation) {
        this.mouseLocation = mouseLocation;
    }

    private class FrameWork extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d=(Graphics2D)g;
            //Vrata
//            Door door = new Door("", null, new Point(300, 50), 5,5, 0);
//            DoorPainter dp = new DoorPainter(door);
//            dp.paint(g2d, new Point(300, 50), 30,100);

            //Krevet
//            Bed bed = new Bed("", null, new Point(300, 50), 5, 5, 0);
//            BedPainter bp = new BedPainter(bed);
//            bp.paint(g2d, new Point(300, 50), 200, 100);

            //Sto
//            Table table = new Table("", null, new Point(300, 50), 5, 5, 0);
//            TablePainter tp = new TablePainter(table);
//            tp.paint(g2d, new Point(300, 50), 100, 100);

            //Kada
//            BathTub bathTub = new BathTub("", null, new Point(300, 50), 5, 5, 0);
//            BathtubPainter btp = new BathtubPainter(bathTub);
//            btp.paint(g2d, new Point(300, 50), 100, 50);

            //Ves Masina
//            WashingMachine washingMachine = new WashingMachine("", null, new Point(300, 50), 100, 100, 0);
//            WashingMaschinePainter wmp = new WashingMaschinePainter(washingMachine);
//            wmp.paint(g2d, new Point(300, 50), 100, 100);

            //Bojler
//            Boiler boiler = new Boiler("", null, new Point(300, 50), 100, 100, 0);
//            BoilerPainter bp = new BoilerPainter(boiler);
//            bp.paint(g2d, new Point(300, 50), 100, 100);

            //Toilet
            ToiletSeat toiletSeat = new ToiletSeat("", null, new Point(300, 50), 100, 100, 0);
            ToiletPainter tp = new ToiletPainter(toiletSeat);
            tp.paint(g2d, new Point(300, 50), 100, 100);

            //Sink
//            Sink sink = new Sink("", null, new Point(300, 50), 100, 100, 0);
//            SinkPainter sp = new SinkPainter(sink);
//            sp.paint(g2d, new Point(300, 50), 100, 100);

//            for (MyPainter painter : painters) {
//                painter.paint((Graphics2D)g);
//            }
        }

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


}
