package algoplants;


import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;


// bommry
public class Controller {
    @FXML AnchorPane kanvas;
    @FXML SubScene subs;
    @FXML AnchorPane reg;
    @FXML GridPane gridpane;
    @FXML VBox rulesbox;
    @FXML TextField seedfield;
    @FXML TextField stepfield;

    private Group group;
    private Group treegroup;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private Hashtable<Character,String> rules;

    @FXML
    protected void initialize() {
        this.rules = new Hashtable<>();
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(-30, Rotate.Y_AXIS),
                new Rotate(10, Rotate.X_AXIS),
                new Rotate(0, Rotate.Z_AXIS),
                new Translate(0, -500, -1300));
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(60);

        this.treegroup = new Group();
        this.group = new Group();
        subs.setRoot(group);

        subs.setCamera(camera);
        subs.setFill(Color.DEEPSKYBLUE);

        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(240.0, 1, 1);
        final Box yAxis = new Box(1, 240.0, 1);
        final Box zAxis = new Box(1, 1, 240.0);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        Box grass = new Box(3000, 3, 3000);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.SADDLEBROWN);
        grass.getTransforms().add(new Rotate(0, Rotate.X_AXIS));
        grass.getTransforms().add(new Translate(0,10,0));
        grass.setMaterial(material);


        group.getChildren().addAll(xAxis, yAxis, zAxis, grass);

        System.out.println("inicializovano");
    }

    public void hop(ActionEvent actionEvent) throws InterruptedException {
        treegroup.getChildren().clear();

        Hashtable<Character,String> hash = new Hashtable<>();
        hash.put('B', "B[+B]B[-B]B");
        //System.out.println(hash);
        Substitution subs = new Substitution(hash, 3, "B");
        //System.out.println(subs.getCode());

        String code = subs.getCode();
        //String code = "B[-B][+B]";
        Turtle turtle = new Turtle(45, 50);
        //System.out.println(turtle);

        //System.out.println(code);

        //group.getChildren().add(treegroup);
        treegroup = turtle.read(code);
        group.getChildren().add(treegroup);


        /*int i = 10;
        for(Node node:treegroup.getChildren())
        {
            playnext(node, i);
            i = i+10;
        }*/
        System.gc();
        //Leaf leaf= new Leaf(100,100);
        //group.getChildren().add(leaf);
    }
    /*private void playnext(Node node, int duration)
    {
        SequentialTransition seq;
        seq = makeAnimation(node);
        seq.setDelay(Duration.millis(duration));
        seq.delayProperty();
        seq.play();
    }
    public SequentialTransition makeAnimation(Node node) {
        Timeline grow = new Timeline();
        KeyValue key1 = new KeyValue(node.visibleProperty(),true);
        KeyFrame keyf = new KeyFrame(Duration.millis(10),key1);
        grow.getKeyFrames().add(keyf);
        SequentialTransition sequence = new SequentialTransition(grow);

        return sequence;
    }*/

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void drawButton(ActionEvent actionEvent) {
        treegroup.getChildren().clear();
        ObservableList<Node> list = rulesbox.getChildren();
        Hashtable<Character,String> hash = new Hashtable<>();

        for (Object rule: list)
        {
            if(((Rule) rule).getKey() != null
                    && ((Rule) rule).getCode()!= null)
            {
                if(hash.contains(((Rule) rule).getKey()))
                {
                    System.out.println("Chyba");
                }
                else
                {
                    hash.put(((Rule) rule).getKey()
                            ,((Rule) rule).getCode());
                }
            }
            else
            {
                System.out.println("nejaky null");
            }
        }
        System.out.println(hash.toString());
        String seedfieldtext;
        int stepnumber;
        if( !seedfield.getText().equals("") && !stepfield.getText().equals("") ) {
            ProgressBar statusBar = new ProgressBar();
            statusBar.setProgress(.5);
            seedfieldtext = seedfield.getText();
            stepnumber = Integer.parseInt(stepfield.getText());
            Substitution subs = new Substitution(hash, stepnumber, seedfieldtext);
            String code = subs.getCode();
            Turtle turtle = new Turtle(45, 50);
            System.out.println(turtle);
            System.out.println(code);
            treegroup = turtle.read(code);
            group.getChildren().add(treegroup);
        }
        else
        {
            System.out.println("nastala chyba, neco nebylo vyplneno");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Néco nebylo vyplnéno");
            alert.showAndWait();
        }
    }

    public void plusButton(ActionEvent actionEvent) {
        Rule rule = new Rule();
        rulesbox.getChildren().add(rule);
    }

    public void helpfunction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Algoplants -- for D0L plants \n POGR na FJFI \n 2014/2015");
        alert.showAndWait();
    }
}
