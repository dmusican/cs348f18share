import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.stream.IntStream;

// Starting point, but doesn't actually update GUI
public class GUISample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button();
        btn.setText("Click me");
        btn.setFont(new Font("Arial", 24));
        GridPane grid = new GridPane();
        grid.add(btn,0,0);


        Label label = new Label("The answer will appear here");
        label.setFont(new Font("Arial", 24));
        grid.add(label, 0, 1);

        btn.setOnAction((event) -> {
            ProgressBar bar = new ProgressBar();
            bar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
            grid.add(bar,0,2);
            double answer = IntStream.range(0, 10_000_000)
                                     .mapToDouble(e -> Math.sin(e))
                                     .reduce(0, (a,b) -> a + b);

            label.setText("The answer is " + answer);
            grid.getChildren().remove(bar);
        });

        primaryStage.setScene(new Scene(grid, 500, 250));
        primaryStage.show();
    }
}
