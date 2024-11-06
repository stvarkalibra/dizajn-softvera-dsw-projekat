package raf.draft.dsw.jtree.controller;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;

public class DraftTreeFactory {
    private static int projectNumber=0;
    private static int buildingNumber=0;
    private static int roomNumber=0;

    public DraftNode createChild(DraftNode parent) {
        if (parent instanceof ProjectExplorer){
            projectNumber++;
            return new Project("Project" + projectNumber, parent, "Project" + projectNumber, "User", " ");
        }
        else if(parent instanceof Project){
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/matrix.png"));
            Object[] options = {"Building", "Room"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Choose type of child to create:",
                    "Select Child Type",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    imageIcon,
                    options,
                    options[0]
            );

            if (choice == 0){
                buildingNumber++;
                return  new Building("Building" + buildingNumber,parent);
            }
            else if (choice == 1){
                roomNumber++;
                return new Room("Room"+roomNumber,parent);
            }
            else return null;
        }else if(parent instanceof Building){
            roomNumber++;
            return new Room("Room"+roomNumber,parent);
        }
        return null;
    }
}
