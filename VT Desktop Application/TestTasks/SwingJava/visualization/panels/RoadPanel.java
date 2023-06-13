package visualization.panels;

import javax.swing.*;
import java.awt.*;
import visualization.managers.*;

public class RoadPanel extends JPanel {

    private final int CAR_WIDTH = 60;
    private final int CAR_HEIGHT = 60;
    private int xCoordinate = 0;
    private Timer timer;

    private ImageIcon carImage;
    private boolean isCarRegistred;
    private RaceState raceState;
    private int carSpeed;
    private int carNumber;
    private RaceInfoHeader raceInfoHeader;

    public RoadPanel(int carNumber, VisualizationSettings visualizationSettings, RaceInfoHeader raceInfoHeader){
        this.carNumber = carNumber;
        this.isCarRegistred = visualizationSettings.isCarRegistred(carNumber);
        this.carSpeed = (500 * visualizationSettings.getCarSpeed(carNumber)) / (100 * 20);
        this.raceState = visualizationSettings.getRaceState();
        this.raceInfoHeader = raceInfoHeader;
        carImage = getCarImage();
        createPanel();
    }

    public ImageIcon getCarImage(){
        ImageIcon imageIcon = new ImageIcon("TestTasks\\SwingJava\\images\\car.png");
        Image temporaryImage = imageIcon.getImage();
        Image newImage = temporaryImage.getScaledInstance(CAR_WIDTH, CAR_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    private void createPanel(){
        setPanelStyle();
        addComponents();
    }

    private void setPanelStyle(){
        setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.BLACK));
        setPreferredSize(new Dimension(100, 0));
    }

    private void addComponents(){
        if (raceState == RaceState.GO){
            timer = new Timer(40, (actionEvent) -> {
                xCoordinate+=carSpeed;
                repaint();
            });
            timer.start();
        }
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.decode("#8D917A"));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        if (isCarRegistred){
            int carCoordinate = getHeight() - CAR_HEIGHT - xCoordinate;
            if (carCoordinate <= 5){
                raceInfoHeader.notifyAboutFinish(carNumber);
                timer.stop();
            }
            carImage.paintIcon(this, graphics, (getWidth() - CAR_WIDTH) / 2, getHeight() - CAR_HEIGHT - xCoordinate);
        }
    }



    public JComponent getComponent() {
        return this;
    }

}