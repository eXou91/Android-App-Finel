package premierprojet.android.listedecourse;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener
{
    // définir les constants pour distinguer les rubriques
    private static final int FRUIT = 0;
    private static final int LEGUME = 1;
    private static final int LAITIER = 2;
    private static final int BOULANGERIE = 3;
    private static final int POISSONNERIE = 4;
    private static final int BOUCHERIE = 5;
    private static final int CHARCUTERIE = 6;
    private static final int ENTRETIEN = 7;
    private static final int ENGLISH = 8;
    private CheckBox livraison;
    //les boutons sont stockés dans un tableau

    public Button[] listeBoutons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //affiche les graphismes de accueil.xml
        setContentView(R.layout.activity_main);
        // stocle les boutons dans le tableau en fonction de l'id

        listeBoutons[FRUIT] = ((Button) this.findViewById(R.id.fruit));
        listeBoutons[LEGUME] = ((Button) this.findViewById(R.id.legume));
        listeBoutons[LAITIER] = ((Button) this.findViewById(R.id.laitier));
        listeBoutons[BOULANGERIE] = ((Button) this.findViewById(R.id.boulangerie));
        listeBoutons[POISSONNERIE] = ((Button) this.findViewById(R.id.poissonnerie));
        listeBoutons[BOUCHERIE] = ((Button) this.findViewById(R.id.boucherie));
        listeBoutons[CHARCUTERIE] = ((Button) this.findViewById(R.id.charcuterie));
        listeBoutons[ENTRETIEN] = ((Button) this.findViewById(R.id.entretien));
        listeBoutons[ENGLISH] = ((Button) this.findViewById(R.id.english));
        livraison = (CheckBox) findViewById(R.id.box1);
        // chaque bouton ecoute l'evenement onClick
        for (int i = 0; i < listeBoutons.length; i++) {
            listeBoutons[i].setOnClickListener(this);
        }
    }

    @Override
    //action réaliser quand un evenement onClick est entendu
    public void onClick(View v)
    {
        String msg = "";
        int activite = FRUIT;
        //tester l'id de l'objet ayant capturer l'event OnClick et agir en conséquence

        switch (v.getId())
        {
            case R.id.fruit:
                msg = "Choisir des fruits";
                activite = FRUIT;
                break;
            case R.id.legume:
                msg = "Choisir des légumes";
                activite = LEGUME;
                break;
            case R.id.laitier:
                msg = "Choisir des produits laitiers";
                activite = LAITIER;
                break;
            case R.id.boulangerie:
                msg = "Choisir dans la boulangerie";
                activite = BOULANGERIE;
                break;
            case R.id.poissonnerie:
                msg = "Choisir dans la poissonnerie";
                activite = POISSONNERIE;
                break;
            case R.id.boucherie:
                msg = "Choisir dans la boucherie";
                activite = BOUCHERIE;
                break;
            case R.id.charcuterie:
                msg = "Choisir dans la charcuterie";
                activite = CHARCUTERIE;
                break;
            case R.id.entretien:
                msg = "Choisir des produits d'entretien";
                activite = ENTRETIEN;
                break;
            case R.id.english:
                msg = "it's in english now ;)";
                break;

        }

        // afficher le message correspondant au bouton sélectionné
        Toast msgT = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        msgT.show();
        // créer une nouvelle activité en fonction de la rubrique séléctionné
        creerActivite(activite);
    }
    // creer une activité en fonction de la valeur passée en parametre
    public void creerActivite ( int tmp)
    {
        Intent nvActivite;
        switch(tmp)
        {
            case FRUIT :
                if (livraison.isChecked())
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Restez au chaud on arrive",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.LEFT, 0, 0);
                    toast.show();
                    nvActivite = new Intent(MainActivity.this,RubriqueFruitsLivraison.class);
                    startActivityForResult(nvActivite, FRUIT);
                }

                // creation de l'intent
                nvActivite= new Intent(MainActivity.this, RubriqueFruits.class);
                startActivityForResult(nvActivite, FRUIT);
                break;

            case LEGUME :
                nvActivite = new Intent(MainActivity.this, RubriqueLegumes.class);
                startActivityForResult(nvActivite, LEGUME);
                break;

        }
    }



}
