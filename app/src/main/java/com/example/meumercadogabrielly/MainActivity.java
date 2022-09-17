package com.example.meumercadogabrielly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

     RadioGroup ckprodutos,rdgDesconto;
     CheckBox ckarroz,ckcarne,ckpao,ckleite,ckovos;
     RadioButton rb0,rb5,rb10,rb15;
     TextView txtValor;
     Button btTotal,btPagar;
     EditText edtValor;
     double  valorPago, troco;
     double total, desconto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ckprodutos = findViewById(R.id.ckprodutos);
        rdgDesconto = findViewById(R.id.rgDesconto);
        ckarroz = findViewById(R.id.ckarroz);
        ckcarne = findViewById(R.id.ckcarne);
        ckpao = findViewById(R.id.ckPao);
        ckleite = findViewById(R.id.ckLeite);
        ckovos = findViewById(R.id.ckOvos);
        rb0 = findViewById(R.id.rb0);
        rb5 = findViewById(R.id.rb5);
        rb10 = findViewById(R.id.rb10);
        rb15 = findViewById(R.id.rb15);
        txtValor = findViewById(R.id.txtValor);
        btPagar = findViewById(R.id.btpagar);
        btTotal = findViewById(R.id.btTotal);
        edtValor = findViewById(R.id.edtValor);

        CheckBox[] checkAlimentos =
                {
                        ckarroz, ckcarne, ckpao, ckleite, ckovos
                };

        Map<CheckBox, Float> valores = new HashMap<CheckBox, Float>();

        valores.put(ckarroz, 3.5f);
        valores.put(ckcarne, 12.3f);
        valores.put(ckpao, 2.2f);
        valores.put(ckleite, 5.5f);
        valores.put(ckovos, 7.5f);

        btTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = 0;

                for (CheckBox x : checkAlimentos)
                    if (x.isChecked()) total += valores.get(x);
                if (total == 0) {
                    Toasty("Escolha um produto !");
                    return;
                }
                txtValor.setText(String.format("TOTAL: R$%5.2f", total));
            }
        });

        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Desconto();
            }
        });

        rb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Desconto();
            }
        });

        rb15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Desconto();
            }
        });

        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //
                // String unparsed = ;
                if (total == 0)
                    {
                    Toasty("Escolha ao menos um produto!");
                    return;
                    }
                if (edtValor.getText().toString().isEmpty())
                    {
                    Toasty("Insira um valor!");
                    }
                   /*if(valorPago < total)
                        {
                        Toasty("Valor não suficiente");
                        return; */
                else
                    {
                    Double teste = Double.parseDouble( edtValor.getText().toString());

                    if(teste >= total)
                        {
                        Double valorPago = Double.parseDouble(edtValor.getText().toString());

                        troco = valorPago - total;

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);

                        alerta.setTitle("Compra realizada!");
                        //String textoDialogo = String.format("")
                        alerta.setMessage(String.format("Valor total da compra:%5.2f\n" +
                                    "Deconto recebido: R$%5.2f\n" +
                                    "Valor pago:%5.2f\n" +
                                    "Troco:%5.2f"
                            , total, desconto, valorPago, troco));
                        alerta.setNeutralButton("OK", null);
                        alerta.show();
                        }
                    else
                        {
                            Toast.makeText(MainActivity.this, "Valor pago é inferior ao cobrado!", Toast.LENGTH_SHORT).show();
                            
                        }
                    }
                }
            });
        }


    public  void Desconto()
    {
        total = 0;

        double desconto2 = 0.0;

        if (ckarroz.isChecked()){total +=3.5f;}
       if (ckcarne.isChecked()){total += 12.3f;}
       if (ckpao.isChecked()){total += 2.2f;}
       if (ckleite.isChecked()){total += 5.5f;}
      if (ckovos.isChecked()){total += 7.5f;}

        if(rb5.isChecked()) { desconto2 = 0.05; }
        else if(rb10.isChecked()) { desconto2 = 0.1; }
        else if(rb15.isChecked()) { desconto2 = 0.15; }

        desconto2 = (total * desconto2);
        desconto = desconto2;

        total = total - (desconto2);

        txtValor.setText(String.format("TOTAL: R$%5.2f",total));
        //return total;
    }
        //Toasty
        private void Toasty(String msg) {
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }
}
/*

        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total =0;
                desconto = 0.0;

                if(rb5.isChecked()) { desconto = 0.05; }
                else if(rb10.isChecked()) { desconto = 0.1; }
                else if(rb15.isChecked()) { desconto = 0.15; }

                desconto= (total * desconto);
                total = total - (desconto);
                troco = valorPago - total;

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);

                alerta.setTitle("Confimar pagamento!!");
                alerta.setMessage(String.format("Valor total da compra:%5.2f\n" +
                                "Deconto recebido: R$%5.2f\n" +
                                "Valor pago:%5.2f\n" +
                                "Troco:%5.2f"
                        ,total, desconto, valorPago, troco)
                );
                alerta.setNeutralButton("OK", null);
                alerta.show();
            }
        });
    }

    /*
    public void Pagar(){

    } */

