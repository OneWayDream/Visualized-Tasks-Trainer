package visualization.panels;

import javax.swing.*;
import java.awt.*;
import visualization.managers.*;

public class RaceInfoHeader {

    private JPanel panel;
    private JLabel label;
    private VisualizationSettings visualizationSettings;
    private Integer winnerNumber;

    public RaceInfoHeader(VisualizationSettings visualizationSettings){
        panel = new JPanel();
        this.visualizationSettings = visualizationSettings;
        createPanel();
    }

    private void createPanel(){
        setPanelStyle();
        addComponents();
    }

    private void setPanelStyle(){
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 60));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        panel.setLayout(new BorderLayout());
    }

    private void addComponents(){
        addDescriptionLabel();
    }

    private void addDescriptionLabel(){
        label = new JLabel();
        label.setText(getLabelTest());
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setFont(new Font("Comic Sans", Font.ITALIC, 22));
        panel.add(label, BorderLayout.CENTER);
    }

    private String getLabelTest(){
        String labelText;
        switch (visualizationSettings.getRaceState()){
            case WAITING -> {
                int participantsAmount = 0 + ((visualizationSettings.isCarRegistred(1)) ? 1 : 0)
                        + ((visualizationSettings.isCarRegistred(2)) ? 1 : 0);
                labelText = String.format("Race state: waiting for participants (%d of 2)", participantsAmount);
            }
            case READY -> labelText = "Race state: Ready!";
            case STEADY -> labelText = "Race state: Steady!";
            case GO -> labelText = "Race state: GO!";
            default -> labelText = "Race state: no state :/";
        }
        if (winnerNumber != null){
            String winnerString = (winnerNumber == 1) ? "first" : "second";
            labelText = String.format("Race state: The %s car won", winnerString);
        }
        return labelText;
    }

    public JComponent getComponent() {
        return panel;
    }

    public void notifyAboutFinish(int carNumber){
        if (winnerNumber == null){
            winnerNumber = carNumber;
            panel.removeAll();
            addComponents();
            panel.validate();
            panel.repaint();
        }
    }

}