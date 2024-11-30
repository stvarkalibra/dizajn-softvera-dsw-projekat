package raf.draft.dsw.jtabbedpane;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.jtree.DraftTree;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class JTabbedPaneImplementation extends JTabbedPane implements ISubscriber {
    private JTabbedPane jTabbedPane;
    private List<Room> roomList;
    private final Icon roomIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/room.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
    private JTree jTree;
    private boolean prikazi=false;
    private int indexOfTab=0;
    public JTabbedPaneImplementation(JTabbedPane jTabbedPane, JTree jTree,List<Room> roomList){
        this.jTabbedPane=jTabbedPane;
        this.jTree=jTree;
        this.roomList=roomList;
    }
    public void generate(){
        jTree.addMouseListener(new java.awt.event.MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath path = jTree.getPathForLocation(e.getX(), e.getY());

                if (path == null) {
                    return;
                }
                DraftTreeItem selectedNode = (DraftTreeItem) path.getLastPathComponent();

                if (e.getClickCount() == 2 && selectedNode.getDraftNode() instanceof Project) {
                    prikazi=true;
                    Project project = (Project) selectedNode.getDraftNode();
                    for (DraftNode draftNode : project.getChildrenList()) {
                        if (draftNode instanceof Room && !roomList.contains((Room) draftNode)) {
                            Label labelRoom = new Label();
                            roomList.add((Room) draftNode);
                            JPanel novi = new JPanel();
                            RoomView roomView = new RoomView(novi);
                            jTabbedPane.addTab(draftNode.getNodeName(), roomView.getjPanelRoom());
                            jTabbedPane.setBackgroundAt(indexOfTab, project.getColor());

                            jTabbedPane.setIconAt(indexOfTab, roomIcon);
                            jTabbedPane.setSelectedIndex(indexOfTab);
                            indexOfTab++;
                        } else if (draftNode instanceof Building) {
                            for (DraftNode draftNodeBuilding : ((Building) draftNode).getChildrenList()) {
                                if (draftNodeBuilding instanceof Room && !roomList.contains((Room) draftNodeBuilding)) {
                                    Label labelRoom = new Label();
                                    roomList.add((Room) draftNodeBuilding);
                                    jTabbedPane.addTab(draftNodeBuilding.getNodeName(), labelRoom);
                                    jTabbedPane.setBackgroundAt(indexOfTab, ((Building) draftNode).getColor());
                                    jTabbedPane.setIconAt(indexOfTab, roomIcon);
                                    jTabbedPane.setSelectedIndex(indexOfTab);
                                    indexOfTab++;
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Icon getRoomIcon() {
        return roomIcon;
    }

    public JTree getjTree() {
        return jTree;
    }

    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }

    public boolean isPrikazi() {
        return prikazi;
    }

    public void setPrikazi(boolean prikazi) {
        this.prikazi = prikazi;
    }

    public int getIndexOfTab() {
        return indexOfTab;
    }

    public void setIndexOfTab(int indexOfTab) {
        this.indexOfTab = indexOfTab;
    }

    @Override
    public void update(Object o) {
        if (o instanceof Room) {
            Room room = (Room) o;

            room.addListener(() -> SwingUtilities.invokeLater(() -> updateTabLabel(room)));

            JPanel novi = new JPanel();
            RoomView roomView = new RoomView(novi);

            if (!roomList.contains(room)) {
                Label labelRoom = new Label();
                roomList.add(room);
                jTabbedPane.addTab(room.getNodeName(), roomView.getjPanelRoom());

                if (room.getParent() instanceof Project) {
                    jTabbedPane.setBackgroundAt(indexOfTab, ((Project) room.getParent()).getColor());
                } else if (room.getParent() instanceof Building) {
                    jTabbedPane.setBackgroundAt(indexOfTab, ((Building) room.getParent()).getColor());
                }
                jTabbedPane.setIconAt(indexOfTab, roomIcon);
                indexOfTab++;
            }else{
                int index=roomList.indexOf(o);
                jTabbedPane.setTitleAt(index,((Room) o).getNodeName());
            }
        }
    }


    public void updateTabLabel(Room room) {
        for (int i = 0; i < jTabbedPane.getTabCount(); i++) {
            if (roomList.get(i).equals(room)) {
                jTabbedPane.setTitleAt(i, room.getNodeName());
                break;
            }
        }
    }
}
