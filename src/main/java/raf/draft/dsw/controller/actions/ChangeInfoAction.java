package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.ChangeInfoFrame;
import raf.draft.dsw.gui.swing.ChangeInfoFrameProject;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChangeInfoAction extends AbstractRoomAction{
    private List<ISubscriber> subscriberList=new ArrayList<>();
    public ChangeInfoAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "ChangeProjectInfo");
        putValue(SHORT_DESCRIPTION, "Change project info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        if (selected.getDraftNode() instanceof Project){
            Project project = (Project) selected.getDraftNode();
            project.addSubscriber(MainFrame.getInstance().getjTabbedPaneRightPanel());
            project.addSubscriber(MainFrame.getInstance().getDraftTree());

            ChangeInfoFrameProject changeInfoFrameProject = new ChangeInfoFrameProject(project);
            changeInfoFrameProject.addElements(project);
            changeInfoFrameProject.setVisible(true);

            changeInfoFrameProject.getChangeButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    project.setProjectAutor(changeInfoFrameProject.getAutorName());
                    project.setProjectName(changeInfoFrameProject.getProjectName());
                    project.setPathToFolder(changeInfoFrameProject.getPathToFolder());

                    project.notifySubscriber(project);

                    DefaultTreeModel treeModel = (DefaultTreeModel) MainFrame.getInstance().getjTree().getModel();
                    treeModel.nodeChanged(selected);
                }
            });
        }
        else if (selected.getDraftNode() instanceof Building){
            Building building=(Building)selected.getDraftNode();
            building.addSubscriber(MainFrame.getInstance().getjTabbedPaneRightPanel());
            building.addSubscriber(MainFrame.getInstance().getDraftTree());

            ChangeInfoFrame changeInfoFrame = new ChangeInfoFrame(selected.getDraftNode());
            changeInfoFrame.addElements(selected.getDraftNode());
            changeInfoFrame.setVisible(true);
            changeInfoFrame.getChangeButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    building.setNodeName(changeInfoFrame.getNewName());
                    building.notifySubscriber(building);
                    DefaultTreeModel treeModel = (DefaultTreeModel) MainFrame.getInstance().getjTree().getModel();
                    treeModel.nodeChanged(selected);

                }
            });
        }else if(selected.getDraftNode() instanceof Room){
            Room room=(Room) selected.getDraftNode();

            room.addSubscriber(MainFrame.getInstance().getDraftTree());
            room.addSubscriber(MainFrame.getInstance().getjTabbedPaneImplementation());
            ChangeInfoFrame changeInfoFrame = new ChangeInfoFrame(selected.getDraftNode());
            changeInfoFrame.addElements(selected.getDraftNode());
            changeInfoFrame.setVisible(true);
            changeInfoFrame.getChangeButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    room.setNodeName(changeInfoFrame.getNewName());
                    room.notifySubscriber(room);
                    DefaultTreeModel treeModel = (DefaultTreeModel) MainFrame.getInstance().getjTree().getModel();
                    treeModel.nodeChanged(selected);
                }
            });
        }
    }
}
