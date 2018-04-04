package br.unicamp.g172356ft.telainicial;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.login);
    }

    public void login(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("usuario",login.getText().toString());
        startActivity(intent);
    }

}

