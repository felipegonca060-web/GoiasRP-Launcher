package br.goiasrp.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String GAME_PACKAGE = "br.goiasrp.game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fundo com a capa do Goiás RP
        ImageView background = new ImageView(this);
        background.setImageResource(R.drawable.goias_capa);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);

        FrameLayout layout = new FrameLayout(this);
        layout.addView(background);

        // Botão JOGAR
        Button jogar = new Button(this);
        jogar.setText("▶ JOGAR");
        jogar.setTextSize(20);
        jogar.setTextColor(Color.WHITE);
        jogar.setAllCaps(false);
        jogar.setBackgroundColor(Color.rgb(20, 120, 60));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                65
        );

        params.gravity = Gravity.BOTTOM;
        params.setMargins(40, 0, 40, 50);

        layout.addView(jogar, params);

        // Comando do botão
        jogar.setOnClickListener(v -> abrirJogo());

        setContentView(layout);
    }

    private void abrirJogo() {
        PackageManager pm = getPackageManager();

        Intent intent = pm.getLaunchIntentForPackage(GAME_PACKAGE);

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(
                    this,
                    "A base do jogo ainda não está instalada.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
