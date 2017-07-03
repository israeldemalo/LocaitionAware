package israel.locationaware;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.btnSignin)
    Button btnSignIn;
    @BindView(R.id.etUserName)
    EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void onBtnSignInClicked() {
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

                    ref.setValue(etUserName.getText().toString());
                    Intent intent = new Intent(SignInActivity.this, MapsActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        });
    }
}
