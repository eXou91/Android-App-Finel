package premierprojet.android.listedecourse;

/**
 * Created by Dorian Finel on 19/12/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RubriqueFruitsLivraison extends Activity implements View.OnClickListener {
    private CheckBox chkBanane, chkCerise, chkFraise, chkKiwi;
    private Button btnSave;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruits_livraison);
        String listeFruits = lireListeFruits();
        // associer les checkbox définis dans le fichier fruits.xml avec les variables de classe
        chkBanane = (CheckBox) findViewById(R.id.banane);
        chkCerise = (CheckBox) findViewById(R.id.cerise);
        chkFraise = (CheckBox) findViewById(R.id.fraise);
        chkKiwi = (CheckBox) findViewById(R.id.kiwi);
        btnSave = (Button) findViewById(R.id.SaveFruit);
        rechercherFruits(listeFruits);
        // mettre en place un écouteur d'evenement sur le bouton enregistrer
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Toast toaster;
        String msg = "";

        if (chkFraise.isChecked())
            msg += "fraise+";
        if (chkBanane.isChecked())
            msg += "banane+";
        if (chkCerise.isChecked())
            msg += "cerise+";
        if (chkKiwi.isChecked())
            msg += "kiwi+";
        if (!msg.equals(""))
        {
            String msgToast = msg.replace("+", " ");
            toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toaster.show();
            ecrireListeFruits(msg);
            fermerlesfruits();
        }
    }
    public void fermerlesfruits()
    {
        this.finish();
    }

    public void ecrireListeFruits(String tmp)
    {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Fruits.txt", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'ecriture ...");
        }

    }

    public String lireListeFruits()
    {
        FileInputStream fis;
        String  data="";
        try
        {
            fis= openFileInput("Fruits.txt");
            char[] charLus = new char[255];
            InputStreamReader isr = new InputStreamReader(fis);
            isr.read(charLus);
            data = new String(charLus);
            fis.close();
        }
        catch (IOException ex)
        {
            Log.i("-----------  Fichier : ", "Erreur de lecture ...");
        }
        return data;
    }
    public void rechercherFruits(String tmp)
    {
        //créer un objet qui st qui detecte des champs de mots et des séparateurs "+"
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        //Créer un tableau dont la longueur correspond au nombre de champs séparés par des '+'
        String mot[] = new String[st.countTokens()];
        //tant qu'il y a des champs séparés par des +
        while (st.hasMoreTokens())
        {
            //Enregistrer le champ courant dans le tableau mot à l'indice i
            mot[i] = st.nextToken();
            if (mot[i].equals("banane"))
            {
                // Si mot[i] vaut 'banane', cocher la case correspondante
                chkBanane.setChecked(true);
            }
            else if (mot[i].equals("fraise"))
            {
                chkFraise.setChecked(true);
            }
            else if (mot[i].equals("cerise"))
            {
                chkCerise.setChecked(true);
            }
            else if (mot[i].equals("kiwi"))
            {
                chkKiwi.setChecked(true);
            }
            i++;
        }

    }


}
