package muczynski.mateusz.window.animations;

import javafx.animation.PathTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class TruckAnimation {

    private final int ENTRANCE_TO_FIRST_STOREHOUSE;
    private final int ENTRANCE_TO_SECOND_STOREHOUSE;
    private Pane firstTruck;
    private Pane secondTruck;

    public TruckAnimation(Scene scene) {
        this.firstTruck = (Pane) scene.lookup("#firstTruck");
        this.secondTruck = (Pane) scene.lookup("#secondTruck");
        this.ENTRANCE_TO_FIRST_STOREHOUSE = 168;
        this.ENTRANCE_TO_SECOND_STOREHOUSE = 158;
    }

    public void firstTruckIsComing() {
        firstTruck.setVisible(true);
        Line line = new Line();
        line.setStartX(25);
        line.setEndX(ENTRANCE_TO_FIRST_STOREHOUSE);

        line.setStartY(30);
        line.setEndY(30);

        PathTransition transition = new PathTransition();
        transition.setNode(firstTruck);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(line);
        transition.play();
    }

    public void firstTruckIsLeaving() {
        Line line = new Line();
        line.setStartX(ENTRANCE_TO_FIRST_STOREHOUSE);
        line.setEndX(25);
        line.setStartY(30);
        line.setEndY(30);

        PathTransition transition = new PathTransition();
        transition.setNode(firstTruck);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(line);
        transition.onFinishedProperty().set((e) -> firstTruck.setVisible(false));
        transition.play();
    }

    public void secondTruckIsComing() {
        secondTruck.setVisible(true);
        Line line = new Line();
        line.setStartX(25);
        line.setEndX(ENTRANCE_TO_SECOND_STOREHOUSE);

        line.setStartY(45);
        line.setEndY(45);

        PathTransition transition = new PathTransition();
        transition.setNode(secondTruck);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(line);
        transition.play();
    }

    public void secondTruckIsLeaving() {
        Line line = new Line();
        line.setStartX(ENTRANCE_TO_FIRST_STOREHOUSE);
        line.setEndX(25);
        line.setStartY(45);
        line.setEndY(45);

        PathTransition transition = new PathTransition();
        transition.setNode(secondTruck);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(line);
        transition.onFinishedProperty().set((e) -> secondTruck.setVisible(false));
        transition.play();
    }
}
