import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class RxGUISample extends Application {

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

        ProgressBar bar = new ProgressBar();
        bar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

        btn.setOnAction((event) -> {
            grid.add(bar,0,2);
            Observable.just(10_000_000)
                      .observeOn(Schedulers.io())
                      .flatMap(max -> Observable.range(0, max))
                      .map(Math::sin)
                      .reduce(0.0, (a,b) -> a + b)
                      .observeOn(JavaFxScheduler.platform())
                      .subscribe(answer -> {
                          label.setText("The answer is " + answer);
                          grid.getChildren().remove(bar);
                      }, Throwable::printStackTrace);


        });

        primaryStage.setScene(new Scene(grid, 500, 250));
        primaryStage.show();
    }
}
