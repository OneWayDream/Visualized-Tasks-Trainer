package ru.itis.graduationwork.application.ui.core.ide.explorer;

import ru.itis.graduationwork.application.managers.settings.ColorsManager;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class IconTreeCellRenderer extends JLabel implements javax.swing.tree.TreeCellRenderer {
    protected Color textSelectionColor;
    protected Color textNonSelectionColor;
    protected Color backgroundSelectionColor;
    protected Color backgroundNonSelectionColor;
    protected Color borderSelectionColor;

    protected boolean selected;

    public IconTreeCellRenderer() {
        super();
        textSelectionColor = ColorsManager.getTextColor();
        textNonSelectionColor = ColorsManager.getTextColor();
        backgroundSelectionColor = new Color(0, 0, 0, 0);
        backgroundNonSelectionColor = new Color(0, 0, 0, 0);
        borderSelectionColor = new Color(0, 0, 0, 0);
        setOpaque(false);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
                                                  int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object userObject = node.getUserObject();
        setText(userObject.toString());

        if (userObject instanceof Boolean)
            setText("Retrieving data...");

        if (userObject instanceof IconData iconData) {
            if (expanded) {
                setIcon(iconData.getExpandedIcon());
            } else {
                setIcon(iconData.getIcon());
            }
        } else {
            setIcon(null);
        }

        setFont(tree.getFont());
        setForeground(sel ? textSelectionColor : textNonSelectionColor);
        setBackground(sel ? backgroundSelectionColor : backgroundNonSelectionColor);
        selected = sel;
        return this;
    }

    @Override
    public void paintComponent(Graphics g) {
        Color backgroundColor = getBackground();
        Icon icon = getIcon();
        g.setColor(backgroundColor);
        int offset = 0;
        if(icon != null && getText() != null)
            offset = (icon.getIconWidth() + getIconTextGap());
        g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

        if (selected) {
            g.setColor(borderSelectionColor);
            g.drawRect(offset, 0, getWidth()-1-offset, getHeight()-1);
        }
        super.paintComponent(g);
    }

}
