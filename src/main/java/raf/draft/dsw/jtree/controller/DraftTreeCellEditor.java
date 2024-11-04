package raf.draft.dsw.jtree.controller;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class DraftTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn=null;
    private JTextField edit=null;
    public DraftTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1){
        super(arg0,arg1);
    }
    public Component getTreeCellEditorComponent(JTree jTree, Object object,boolean arg1,boolean arg2,boolean arg3, int arg4){
        clickedOn=object;
        edit=new JTextField(object.toString());
        edit.addActionListener(this);
        return edit;
    }
}
