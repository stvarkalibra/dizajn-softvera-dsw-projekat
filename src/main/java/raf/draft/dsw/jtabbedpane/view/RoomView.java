package raf.draft.dsw.jtabbedpane.view;

import org.w3c.dom.ls.LSOutput;
import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.state.AddState;
import raf.draft.dsw.gui.swing.state.ResizeState;
import raf.draft.dsw.gui.swing.state.SelectState;
import raf.draft.dsw.gui.swing.state.StateManager;
import raf.draft.dsw.gui.swing.state.addstate.AddStateManager;
import raf.draft.dsw.gui.swing.state.addstate.BedState;
import raf.draft.dsw.jtabbedpane.controller.RoomController;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;


public class RoomView extends JPanel implements ISubscriber {
    private JPanel jPanelRoom;
    private Room room;
    private RoomController roomController;
    private int width, height;
    private Point mouseLocation;
    private StateManager stateManager=new StateManager();
    private List<MyPainter> painterList=new ArrayList<>();
    private List<RoomElement> selectedElements=new ArrayList<>();
    private MouseEvent mouseEvent;
    double k=0;
    private Point initialMousePosition;
    private Point initialRoomPosition;
    public RoomView(Room room){
        this.room=room;
        roomController=new RoomController();
        initialize();
    }

    public void initialize() {
        RoomView roomView=this;
        Toolkit kit = Toolkit.getDefaultToolkit();
        jPanelRoom = new JPanel();
        jPanelRoom.setPreferredSize(new Dimension((int) kit.getScreenSize().getWidth(), (int) kit.getScreenSize().getHeight()));
        jPanelRoom.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(jPanelRoom);
        scrollPane.setPreferredSize(new Dimension(1000, 1000));
        add(scrollPane);
        if (room.getParent() instanceof Building) {
            jPanelRoom.setBackground(((Building) room.getParent()).getColor());
        }
        if (room.getParent() instanceof Project) {
            jPanelRoom.setBackground(((Project) room.getParent()).getColor());
        }
        jPanelRoom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseLocation = e.getPoint();
                myMouseClicked();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (stateManager.getCurrentState() instanceof SelectState) {
                    ((SelectState) stateManager.getCurrentState()).myMousePressed(e);
                } if(stateManager.getCurrentState() instanceof ResizeState){
                    ((ResizeState) stateManager.getCurrentState()).myMousePressed(roomView,e);
                }

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

        // Mouse motion listener to move the room
        jPanelRoom.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseEvent=e;
                myMouseDragged();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
    }
    //STATE-PATTERN
    public void startEditRoom(){
        stateManager.setEditRoomState();
    }
    public void startAdd(){
        stateManager.setAddState();
    }
    public void startSelect(){
        stateManager.setSelectState();
    }
    public void startResize(){
        stateManager.setResizeState();
    }
    public void startRotate(){
        stateManager.setRotateState();}
    public void startMove(){
        stateManager.setMoveState();
    }
    public void startDelete(){stateManager.setDeleteState();}
    public void myMouseClicked(){
        stateManager.getCurrentState().myMouseClicked(this,mouseLocation);
    }
    public void myMouseReleased() {
        stateManager.getCurrentState().myMouseReleased(this);
    }
    public void myMouseScrolled () {
        stateManager.getCurrentState().myMouseScrolled();
    }
    public void myMouseDragged () {
        stateManager.getCurrentState().myMouseDragged(this, mouseEvent);
    }
    public void addElementToTree(RoomElement element) {
        DraftTreeItem roomNode = findTreeNodeForRoom(this.room);
        if (roomNode != null) {
            roomNode.addChild(element);

            DraftTreeItem newElementNode = new DraftTreeItem(element);
            roomNode.add(newElementNode);
            DefaultTreeModel model = (DefaultTreeModel) MainFrame.getInstance().getjTree().getModel();
            model.nodeStructureChanged(roomNode);
        }
    }
    public void deleteElementFromTree(RoomElement element){
        DraftTreeItem roomNode=findTreeNodeForRoom(this.room);
        if(roomNode!=null){
            roomNode.removeChild(element);
            DraftTreeItem newElementNode=new DraftTreeItem(element);
            roomNode.remove(newElementNode);
            DefaultTreeModel model=(DefaultTreeModel) MainFrame.getInstance().getjTree().getModel();
            model.nodeStructureChanged(roomNode);
        }
    }
    private DraftTreeItem findTreeNodeForRoom(Room room) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) MainFrame.getInstance().getjTree().getModel().getRoot();
        return searchTreeNode(root, room);
    }

    private DraftTreeItem searchTreeNode(DefaultMutableTreeNode node, Room room) {
        if (node instanceof DraftTreeItem) {
            DraftTreeItem treeItem = (DraftTreeItem) node;
            if (treeItem.getDraftNode() == room) {
                return treeItem;
            }
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            DraftTreeItem result = searchTreeNode(child, room);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

        //END OF STATE-PATTERN
        @Override
        public void update(Object o) {
            if (o instanceof Room) {
                double heightPixel = ((Room) o).convertCmIntoPixel(room.getHeight());
                double widthPixel = ((Room) o).convertCmIntoPixel(room.getWidth());

                double aspectRatio = widthPixel / heightPixel;
                double jPanelRoomHeight = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getHeight();
                double jPanelRoomWidth = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getWidth();

                if (aspectRatio > 1) {
                    widthPixel = jPanelRoomWidth;
                    heightPixel = widthPixel / aspectRatio;
                    k = widthPixel / room.getWidth();
                } else {
                    heightPixel = jPanelRoomHeight;
                    widthPixel = heightPixel * aspectRatio;
                    k = heightPixel / room.getHeight();
                }

                jPanelRoom.setPreferredSize(new Dimension((int) widthPixel, (int) heightPixel));
                jPanelRoom.revalidate();
                jPanelRoom.repaint();
            } else if (o instanceof AddState) {
                ((AddState) o).myMouseClickedElement(this, mouseLocation);
                jPanelRoom.revalidate();
                jPanelRoom.repaint();
            } else if (o instanceof RoomElement) {
                RoomElement roomElement = (RoomElement) o;
                for (MyPainter painter : painterList) {
                    if (roomElement.equals(painter.getRoomElement())) {
                        jPanelRoom.revalidate();
                        jPanelRoom.repaint();
                    }
                }
            }
        }

        public List<RoomElement> getSelectedElements () {
            return selectedElements;
        }

        public void setSelectedElements (List < RoomElement > selectedElements) {
            this.selectedElements = selectedElements;
        }

        public Point getMouseLocation () {
            return mouseLocation;
        }

        public void setMouseLocation (Point mouseLocation){
            this.mouseLocation = mouseLocation;
        }

        public JPanel getjPanelRoom () {
            return jPanelRoom;
        }

        @Override
        public int getWidth () {
            return width;
        }

        @Override
        public int getHeight () {
            return height;
        }

        public void setjPanelRoom (JPanel jPanelRoom){
            this.jPanelRoom = jPanelRoom;
        }

        public List<MyPainter> getPainterList () {
            return painterList;
        }

        public void setPainterList (List < MyPainter > painterList) {
            this.painterList = painterList;
        }

        public Room getRoom () {
            return room;
        }

        public void setRoom (Room room){
            this.room = room;
        }

        public RoomController getRoomController () {
            return roomController;
        }

        public void setRoomController (RoomController roomController){
            this.roomController = roomController;
        }

        public void setWidth ( int width){
            this.width = width;
        }

        public void setHeight ( int height){
            this.height = height;
        }

        public double getK () {
            return k;
        }

        public void setK ( double k){
            this.k = k;
        }
    }