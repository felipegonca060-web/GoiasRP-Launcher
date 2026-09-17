package br.goiasrp.launcher;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.content.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    // Preencha quando contratar a host:
    private static final String SERVER_IP = "SEU_IP_AQUI";
    private static final int SERVER_PORT = 7777;

    LinearLayout root;
    TextView status;
    Button play, download;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        buildUI();
    }

    TextView text(String s, float size) {
        TextView t = new TextView(this); t.setText(s); t.setTextColor(Color.WHITE);
        t.setTextSize(size); t.setGravity(Gravity.CENTER); t.setPadding(20,20,20,20); return t;
    }

    Button button(String s) {
        Button b = new Button(this); b.setText(s); b.setTextColor(Color.WHITE);
        b.setTextSize(16); b.setAllCaps(false); return b;
    }

    void buildUI() {
        root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER); root.setPadding(35,45,35,35);
        root.setBackgroundColor(Color.rgb(7,17,31));

        TextView logo = text("GOIÁS RP", 34); logo.setTextColor(Color.rgb(8,125,255));
        root.addView(logo, new LinearLayout.LayoutParams(-1,90));

        TextView sub = text("SEU ROLEPLAY COMEÇA AQUI", 15);
        root.addView(sub);

        status = text("Servidor: " + SERVER_IP + ":" + SERVER_PORT, 14);
        root.addView(status);

        download = button("📦  BAIXAR / ATUALIZAR ARQUIVOS");
        root.addView(download, new LinearLayout.LayoutParams(-1,65));
        download.setOnClickListener(v -> downloadMessage());

        play = button("▶  JOGAR GOIÁS RP");
        root.addView(play, new LinearLayout.LayoutParams(-1,65));
        play.setOnClickListener(v -> {
            if (SERVER_IP.equals("SEU_IP_AQUI")) {
                Toast.makeText(this,"Configure o IP da host primeiro.",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Conectando ao Goiás RP...",Toast.LENGTH_SHORT).show();
                // Aqui entra a integração com o cliente SA-MP Android escolhido.
            }
        });

        TextView info = text("Versão do launcher: 1.0\nIP protegido no aplicativo", 12);
        root.addView(info);
        setContentView(root);
    }

    void downloadMessage() {
        new AlertDialog.Builder(this).setTitle("Arquivos do Goiás RP")
            .setMessage("A área de atualização está preparada. Quando você tiver a hospedagem, configuraremos aqui o endereço do pacote de mods, versão e atualização.")
            .setPositiveButton("OK", null).show();
    }
}