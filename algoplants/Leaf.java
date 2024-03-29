package algoplants;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/* Třída pro list
*  je tvytvořen pomocí TriangleMesh, tj. vytvářen trojůhelníky
*  podle vzoru předkresleného na papíře
* */
public class Leaf extends MeshView {
    public Leaf()
    {
        this(10,25);
    }
    public Leaf(float s, float h)
    {
        TriangleMesh leafMesh = new TriangleMesh();
        leafMesh.getTexCoords().addAll(0,0);
        /* Přidání bodů */
        float points[] =
        {
            0,      -h,         0,            // vrch
            -s/2,   0,          -h/50,         // levy predni
            -s/2,   0,          h/50,         // levy zadni
            s/2,    0,          h/50,         // pravy zadni
            s/2,    0,          -h/50,         // pravy predni
            0,      (h*2)/5,    0,         // spodni
            -h/25,  h,          -h/25,         // konec stonku 1
            -h/25,  h,          h/25,         // konec stonku 2
            h/25,   h,          h/25,         // konec stonku 3
            h/25,   h,          -h/25,      // konec stonku 4
            0,      (h*2)/7,    0  //vrch stonku
        };
        leafMesh.getPoints().addAll(points);
        /* Přidání stěn */
        leafMesh.getFaces().addAll(
                0,0,  1,0,  2,0,          // leva krajni
                0,0,  2,0,  3,0,          // zadni
                0,0,  3,0,  4,0,          // prava krajni
                0,0,  4,0,  1,0,          // predni
                2,0,  1,0,  5,0,          // leva krajni spodni
                2,0,  5,0,  3,0,           // zadni spodni
                3,0,  5,0,  4,0,          // prava krajni spodni
                1,0,  3,0,  5,0,           // predni spodni
                10,0,  6,0,  7,0,          // stonek leva
                10,0,  7,0,  8,0,           // stonek zadni
                10,0,  8,0,  9,0,          // stonek prava
                10,0,  9,0,  6,0,           // stonek predni
                9,0,  7,0,  8,0,          // spodek stonku
                9,0,  7,0,  6,0           // spodek stonku


        );
        this.setMesh(leafMesh);
        this.setDrawMode(DrawMode.FILL);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.web("009900"));
        this.setMaterial(material);
    }

}
