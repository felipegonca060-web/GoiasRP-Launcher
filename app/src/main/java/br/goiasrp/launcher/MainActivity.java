package br.goiasrp.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String GAME_PACKAGE = "br.goiasrp.game";
    private static final String GAME_ACTIVITY = "br.goiasrp.game.GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        criarLauncher();
    }

    private void criarLauncher() {

        // IMAGEM DE FUNDO
        ImageView background = new ImageView(this);
        background.setImageResource(R.drawable.goias_capa);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);

        FrameLayout tela = new FrameLayout(this);
        tela.addView(background);

        // ÁREA DOS BOTÕES
        LinearLayout menu = new LinearLayout(this);
        menu.setOrientation(LinearLayout.VERTICAL);
        menu.setGravity(Gravity.CENTER);
        menu.setPadding(40, 20, 40, 30);

        // Botão atualizar
        Button atualizar = new Button(this);
        atualizar.setText("📦  BAIXAR / ATUALIZAR ARQUIVOS");
        atualizar.setTextSize(16);
        atualizar.setAllCaps(false);
        atualizar.setTextColor(Color.WHITE);
        atualizar.setBackgroundColor(Color.rgb(40, 40, 40));

        menu.addView(atualizar,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        65
                ));

        atualizar.setOnClickListener(v -> {

            Toast.makeText(
                    this,
                    "Sistema de atualização em preparação.",
                    Toast.LENGTH_SHORT
            ).show();

        });

        // Botão jogar
        Button jogar = new Button(this);
        jogar.setText("▶  JOGAR GOIÁS RP");
        jogar.setTextSize(18);
        jogar.setAllCaps(false);
        jogar.setTextColor(Color.WHITE);
        jogar.setBackgroundColor(Color.rgb(20, 130, 60));

        LinearLayout.LayoutParams jogarParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        70
                );

        jogarParams.topMargin = 15;

        menu.addView(jogar, jogarParams);

        jogar.setOnClickListener(v -> abrirJogo());

        // Posiciona os botões na parte inferior
        FrameLayout.LayoutParams menuParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                );

        menuParams.gravity = Gravity.BOTTOM;

        tela.addView(menu, menuParams);

        setContentView(tela);
    }

    private void abrirJogo() {

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
}
