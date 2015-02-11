package algoplants;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.Stack;

public class Turtle {

    private double delta;
    private double step;

    private TurtlePosition position;
    private Stack<TurtlePosition> stack;

    private void drawShape(Shape3D shape)
    {
        shape.getTransforms().addAll(position.getPosition());
    }

    public Group read(String code)
    {
        Group gr = new Group();
        for(int i = 0, n = code.length() ; i < n ; i++) {
            char c = code.charAt(i);
            switch (c) {
                case 'B':
                    System.out.println("branch");
                    Branch branch = new Branch(5*this.getPosition().getScale(),
                            this.getStep() * this.getPosition().getScale());
                    drawShape(branch);
                    gr.getChildren().add(branch);
                    break;
                case 'L':
                    System.out.println("leaf");
                    Leaf leaf = new Leaf(4,5);
                    drawShape(leaf);
                    gr.getChildren().add(leaf);
                    break;
                case 'S':
                    System.out.println("stem");
                    Stem stem = new Stem(5*this.getPosition().getScale(),
                            this.getStep() * this.getPosition().getScale());
                    drawShape(stem);
                    gr.getChildren().add(stem);
                    break;
                case 'F':
                    System.out.println("flower");
                    Flower flower = new Flower(7);
                    drawShape(flower);
                    gr.getChildren().add(flower);
                    break;
                case 'g':
                    System.out.println("g");
                    this.getPosition().AddTransform(
                            new Translate(0,(-step/2)*this.getPosition().getScale(),0));
                    break;
                case '+':
                    //rotace doprava
                    System.out.println("plus");
                    this.getPosition().AddTransform(
                            new Rotate(-delta,0,0,0,Rotate.Z_AXIS));
                    break;
                case '-':
                    //rotace doleva
                    System.out.println("minus");
                    this.getPosition().AddTransform(
                            new Rotate(delta,0,0,0,Rotate.Z_AXIS));
                    break;
                case '&':
                    //rotace dolu
                    System.out.println("and");
                    this.getPosition().AddTransform(
                            new Rotate(-delta,0,0,0,Rotate.X_AXIS));
                    break;
                case '^':
                    //rotace nahoru
                    System.out.println("and");
                    this.getPosition().AddTransform(
                            new Rotate(delta,0,0,0,Rotate.X_AXIS));
                    break;
                case '\\':
                    //rotace roll doleva
                    System.out.println("and");
                    this.getPosition().AddTransform(
                            new Rotate(delta, 0, 0, 0, Rotate.Y_AXIS));
                    break;
                case '/':
                    //rotace roll doprava
                    System.out.println("and");
                    this.getPosition().AddTransform(
                            new Rotate(-delta, 0, 0, 0, Rotate.Y_AXIS));
                    break;
                case '|':
                    //rotace o 180
                    System.out.println("plus");
                    this.getPosition().AddTransform(
                            new Rotate(180,0,0,0,Rotate.Z_AXIS));
                    System.out.println("|");
                    break;
                case '[':
                    //zapamatuje si pozici v zasobniku
                    System.out.println("[");
                    this.savePosition();
                    break;
                case ']':
                    //vrati se na posledni pozici do zasobniku
                    System.out.println("]");
                    this.goToLast();
                    break;
                case '!':
                    //zmensi velikost
                    System.out.println("]");
                    this.getPosition().setScale(this.getPosition().getScale()*0.7);
                    break;
                default:
                    System.out.println("chybny znak");
                    break;
            }
        }
        return gr;
    }

    public void savePosition() {
        TurtlePosition savedPos = new TurtlePosition();
        savedPos.getTransforms().addAll(position.getPosition());
        savedPos.setScale(position.getScale());
        stack.push(savedPos);
        System.out.println(stack);
    }

    public void goToLast() {
        System.out.println(stack.peek());
        this.setPosition(stack.pop());
    }

    public Turtle(double delta, double step) {
        this.delta = delta;
        this.step = step;
        this.position = new TurtlePosition();
        this.stack = new Stack<TurtlePosition>();
    }

    @Override
    public String toString() {
        return "Turtle{" +
                "delta=" + delta +
                ", step=" + step +
                ", position=" + position +
                '}';
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public TurtlePosition getPosition() {
        return position;
    }

    public void setPosition(TurtlePosition position) {
        this.position = position;
    }
}
