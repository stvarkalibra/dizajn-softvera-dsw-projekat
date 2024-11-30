package raf.draft.dsw.jtabbedpane.view;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.model.JTabbedPanePublisher;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class JTabbedPaneRightPanel implements ISubscriber {
    private Label labelBuilding;
    private Label labelProject;
    private Label labelAutor;
    private JPanel rightPanel;
    private JTabbedPanePublisher jTabbedPanePublisher;


    public JTabbedPaneRightPanel(Label labelProject, Label labelBuilding, Label labelAutor,JTabbedPanePublisher jTabbedPanePublisher) {
        this.labelBuilding = labelBuilding;
        this.labelProject = labelProject;
        this.labelAutor = labelAutor;
        this.jTabbedPanePublisher=jTabbedPanePublisher;
        initialize();
    }

    public void initialize(){
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenHeight=screenSize.height;
        rightPanel=new JPanel();
        rightPanel.add(labelProject);
        rightPanel.add(labelBuilding);
        rightPanel.add(labelAutor);
        rightPanel.setPreferredSize(new Dimension(200, screenHeight));
        jTabbedPanePublisher.Events();

    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public Label getLabelBuilding() {
        return labelBuilding;
    }

    public void setLabelBuilding(String buildingName) {
        labelBuilding.setText(buildingName);
    }

    public Label getLabelProject() {
        return labelProject;
    }

    public void setLabelProject(String projectName) {
        labelProject.setText(projectName);
    }

    public Label getLabelAutor() {
        return labelAutor;
    }

    public void setLabelAutor(String autorName) {
        labelAutor.setText(autorName);
    }

    @Override
    public void update(Object o) {
        if (o instanceof Room) {
            if(((Room) o).getParent() instanceof Project){
                labelProject.setText(((Room) o).getParent().getNodeName());
                labelAutor.setText(((Project) ((Room) o).getParent()).getProjectAutor());
                labelBuilding.setText("/");
            }else{
                if(((Room) o).getParent().getParent() instanceof Project){
                    labelAutor.setText(((Project) ((Room) o).getParent().getParent()).getProjectAutor());
                }
                labelBuilding.setText(((Room) o).getParent().getNodeName());
                labelProject.setText(((Room) o).getParent().getParent().getNodeName());
            }
        }
        if(o instanceof Project){
            labelProject.setText(((Project) o).getProjectName());
            labelAutor.setText(((Project) o).getProjectAutor());
        }
        if(o instanceof Building){
            labelBuilding.setText(((Building) o).getNodeName());
        }
    }
}
