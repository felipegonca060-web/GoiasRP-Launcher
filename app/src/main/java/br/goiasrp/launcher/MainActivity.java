package br.goiasrp.launcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity {
    private static final String SERVER_IP = "SEU_IP_AQUI";
    private static final int SERVER_PORT = 7777;

    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }

    private TextView label(String value, float size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(Color.WHITE);
        t.setTextSize(size);
        t.setGravity(Gravity.CENTER);
        return t;
    }

    private GradientDrawable bg(int color) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(dp(6));
        g.setStroke(dp(1), 0x665F6266);
        return g;
    }

    private Button actionButton(String title) {
        Button b = new Button(this);
        b.setText(title);
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setPadding(dp(8), 0, dp(8), 0);
        b.setBackground(bg(0xB82B2E32));
        return b;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.BLACK);
        getWindow().setNavigationBarColor(Color.BLACK);
        buildUI();
    }

    private void buildUI() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.rgb(5,7,11));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(10), dp(18), dp(28));
        root.setBackgroundColor(Color.rgb(5,7,11));

        ImageView cover = new ImageView(this);
        cover.setImageResource(br.goiasrp.launcher.R.drawable.goias_capa);
        cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        cover.setAdjustViewBounds(true);
        root.addView(cover, new LinearLayout.LayoutParams(-1, dp(390)));

        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setGravity(Gravity.CENTER_HORIZONTAL);
        panel.setPadding(dp(8), dp(16), dp(8), 0);
        panel.setBackgroundColor(Color.rgb(5,7,11));

        TextView title = label("GOIÁS RP", 34);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        panel.addView(title, new LinearLayout.LayoutParams(-1, dp(55)));

        TextView sub = label("SEU ROLEPLAY COMEÇA AQUI", 15);
        panel.addView(sub, new LinearLayout.LayoutParams(-1, dp(42)));

        TextView server = label("Servidor: " + SERVER_IP + ":" + SERVER_PORT, 14);
        panel.addView(server, new LinearLayout.LayoutParams(-1, dp(42)));

        Button download = actionButton("📦  BAIXAR / ATUALIZAR ARQUIVOS");
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, dp(62));
        bp.setMargins(0, dp(6), 0, dp(10));
        panel.addView(download, bp);
        download.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setTitle("Arquivos do Goiás RP")
            .setMessage("A atualização será configurada quando o endereço do servidor de arquivos estiver definido.")
            .setPositiveButton("OK", null).show());

        Button play = actionButton("▶  JOGAR GOIÁS RP");
        panel.addView(play, new LinearLayout.LayoutParams(-1, dp(62)));
        play.setOnClickListener(v -> {
            if (SERVER_IP.equals("SEU_IP_AQUI")) {
                Toast.makeText(this, "Configure o IP da host primeiro.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Conectando ao Goiás RP...", Toast.LENGTH_SHORT).show();
            }
        });

        TextView info = label("Versão do launcher: 1.0\nIP protegido no aplicativo", 12);
        info.setPadding(0, dp(24), 0, 0);
        panel.addView(info, new LinearLayout.LayoutParams(-1, dp(72)));

        root.addView(panel, new LinearLayout.LayoutParams(-1, -2));
        scroll.addView(root);
        setContentView(scroll);
    }
}
