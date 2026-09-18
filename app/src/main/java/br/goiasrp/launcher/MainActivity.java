package br.goiasrp.launcher;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    private static final String GAME_PACKAGE = "br.goiasrp.game";
    private static final String GAME_ACTIVITY = "br.goiasrp.game.GameActivity";

    LinearLayout root;
    TextView status;
    Button play, download;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        buildUI();
    }

    TextView text(String s, float size) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextColor(Color.WHITE);
        t.setTextSize(size);
        t.setGravity(Gravity.CENTER);
        t.setPadding(20,20,20,20);
        return t;
    }

    Button button(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);
        b.setAllCaps(false);
        return b;
    }

    void buildUI() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(35,45,35,35);
        root.setBackgroundColor(Color.rgb(7,17,31));

        TextView logo = text("GOIÁS RP", 34);
        logo.setTextColor(Color.rgb(8,125,255));
        root.addView(logo, new LinearLayout.LayoutParams(-1,90));

        TextView sub = text("SEU ROLEPLAY COMEÇA AQUI", 15);
        root.addView(sub);

        status = text("Goiás RP pronto para jogar", 14);
        root.addView(status);

        download = button("📦  BAIXAR / ATUALIZAR ARQUIVOS");
        root.addView(download, new LinearLayout.LayoutParams(-1,65));
        download.setOnClickListener(v -> downloadMessage());

        play = button("▶  JOGAR GOIÁS RP");
        root.addView(play, new LinearLayout.LayoutParams(-1,65));

        play.setOnClickListener(v -> abrirJogo());

        TextView info = text(
            "Versão do launcher: 1.0",
            12
        );
        root.addView(info);

        setContentView(root);
    }

    void abrirJogo() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
            GAME_PACKAGE,
            GAME_ACTIVITY
        ));

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(
                this,
                "A base do jogo ainda não está instalada.",
                Toast.LENGTH_LONG
            ).show();
        }
    }

    void downloadMessage() {
        new AlertDialog.Builder(this)
            .setTitle("Arquivos do Goiás RP")
            .setMessage(
                "A área de atualização está preparada. " +
                "Quando você tiver a hospedagem, configuraremos aqui " +
                "o endereço do pacote de mods, versão e atualização."
            )
            .setPositiveButton("OK", null)
            .show();
    }
}
