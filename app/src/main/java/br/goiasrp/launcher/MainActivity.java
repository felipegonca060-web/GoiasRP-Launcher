package br.goiasrp.launcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String SERVER_IP = "SEU_IP_AQUI";
    private static final int SERVER_PORT = 7777;

    private TextView text(String value, float size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(Color.WHITE);
        t.setTextSize(size);
        t.setGravity(Gravity.CENTER);
        return t;
    }

    private Button button(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(Color.WHITE);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setBackgroundResource(br.goiasrp.launcher.R.drawable.button_bg);
        return b;
    }

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(5,7,11));
        getWindow().setNavigationBarColor(Color.rgb(5,7,11));
        buildUI();
    }

    private void buildUI() {
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.rgb(5,7,11));

        ImageView cover = new ImageView(this);
        cover.setImageResource(R.drawable.goias_capa);
        cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        root.addView(cover, new FrameLayout.LayoutParams(-1, -1));

        LinearLayout shade = new LinearLayout(this);
        shade.setOrientation(LinearLayout.VERTICAL);
        shade.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        shade.setPadding(24, 0, 24, 34);
        shade.setBackgroundResource(R.drawable.panel_bg);
        FrameLayout.LayoutParams shadeLp = new FrameLayout.LayoutParams(-1, -1);
        root.addView(shade, shadeLp);

        TextView title = text("GOIÁS RP", 39);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(Color.WHITE);
        shade.addView(title, new LinearLayout.LayoutParams(-1, 60));

        TextView sub = text("SEU ROLEPLAY COMEÇA AQUI", 16);
        sub.setTypeface(Typeface.DEFAULT_BOLD);
        shade.addView(sub, new LinearLayout.LayoutParams(-1, 48));

        TextView server = text("Servidor: " + SERVER_IP + ":" + SERVER_PORT, 14);
        shade.addView(server, new LinearLayout.LayoutParams(-1, 42));

        Button download = button("📦  BAIXAR / ATUALIZAR ARQUIVOS");
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, 64);
        bp.setMargins(0, 8, 0, 12);
        shade.addView(download, bp);
        download.setOnClickListener(v -> showUpdateDialog());

        Button play = button("▶  JOGAR GOIÁS RP");
        shade.addView(play, new LinearLayout.LayoutParams(-1, 64));
        play.setOnClickListener(v -> {
            if (SERVER_IP.equals("SEU_IP_AQUI")) {
                Toast.makeText(this, "Configure o IP da host primeiro.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Conectando ao Goiás RP...", Toast.LENGTH_SHORT).show();
            }
        });

        TextView info = text("Versão do launcher: 1.0\nIP protegido no aplicativo", 12);
        info.setPadding(0, 22, 0, 0);
        shade.addView(info, new LinearLayout.LayoutParams(-1, 78));

        setContentView(root);
    }

    private void showUpdateDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Arquivos do Goiás RP")
            .setMessage("A área de atualização está preparada. Configure a hospedagem dos arquivos para ativar o download e atualização automática.")
            .setPositiveButton("OK", null)
            .show();
    }
}
