package futurewiz.cou.kr.firebasechat.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * 로그인 매니저
 */

public class AuthManager {

    private static AuthManager instance = new AuthManager();
    private FirebaseAuth mAuth;
    private UserData userData;

    public interface SignInListener {
        void onResult(Boolean success);
    }

    public interface SignUpListener {
        void onResult(Boolean success, String exceptionString);
    }

    private AuthManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static AuthManager getInstance() {
        return instance;
    }

    public FirebaseUser getFirebaseUser() {
        return mAuth.getCurrentUser();
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Boolean isLogined() {
        return getFirebaseUser() != null;
    }

    // 회원가입
    public void signUp(String email, String password, final SignUpListener signUpListener) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    signUpListener.onResult(true, "");
                } else {
                    // If sign in fails, display a message to the user.
                    signUpListener.onResult(false, task.getException().toString());
                }
            }
        });
    }

    // 로그인
    public void signIn(String email, String passWord, final SignInListener signInListener) {
        mAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    signInListener.onResult(true);
                } else {
                    signInListener.onResult(false);
                }
            }
        });
    }

    public void signOut() {
        mAuth.signOut();
    }
}
