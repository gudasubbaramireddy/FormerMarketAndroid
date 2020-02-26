package com.thrymr.formersmarket.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thrymr.formersmarket.Model.ConsumerRegistration;
import com.thrymr.formersmarket.R;
import com.thrymr.formersmarket.SessionManager;
import com.thrymr.formersmarket.ViewModels.ConsumerRegViewModel;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.gson.JsonObject;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsumerRegFragment extends Fragment implements View.OnClickListener {

    private CheckBox chkCity;
    private TextInputLayout tilConsumerMandal;
    private TextInputEditText txtName,txtPhone,txtPassword,txtState,txtDistrict,txtCity,txtMandal,txtVillage;
    private boolean isCity;
    private ImageView photo;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private ConsumerRegistration consumerRegistration;
    private Button btnSubmit,btnUploadPhoto;
    private String consumerId,mVerificationId;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView consumerHelp;
    private ConsumerRegViewModel regViewModel;
    private String imageByteArrayString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_consumer_reg, container, false);
        initViews(view);
        consumerRegistration=new ConsumerRegistration();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Consumer");
        if(!(TextUtils.isEmpty(txtName.getText().toString()) && TextUtils.isEmpty(txtVillage.getText().toString())))

        chkCity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkCity.isChecked()) {
                    tilConsumerMandal.setEnabled(false);
                    tilConsumerMandal.setAlpha(1.0F);
                    isCity=true;
                }
                else {
                    tilConsumerMandal.setEnabled(true);
                    isCity=false;
                }
            }
        });
        btnUploadPhoto.setOnClickListener(this);

        //getAllReg();
        return view;
    }

    private void getAllReg() {
        regViewModel= ViewModelProviders.of(getActivity()).get(ConsumerRegViewModel.class);
        regViewModel.getAllRegistrations().observe(getActivity(), new Observer<List<ConsumerRegistration>>() {
            @Override
            public void onChanged(List<ConsumerRegistration> consumerRegistrations) {

            }
        });
    }

    private void sendVerificationPhoneNumber(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

     PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

         @Override
         public void onVerificationCompleted(PhoneAuthCredential credential) {


             String code=credential.getSmsCode();
             if(code!=null)
                 verifyCode(code);
         }

         @Override
         public void onVerificationFailed(FirebaseException e) {
             // This callback is invoked in an invalid request for verification is made,
             // for instance if the the phone number format is not valid.

             if (e instanceof FirebaseAuthInvalidCredentialsException) {
                 Toast.makeText(getContext(),"Invalid phone Number",Toast.LENGTH_LONG).show();
                 // Invalid request
                 // ...
             } else if (e instanceof FirebaseTooManyRequestsException) {
                 Toast.makeText(getContext(),"The SMS quota for the project has been exceeded",Toast.LENGTH_LONG).show();

             }

             // Show a message and update the UI
             // ...
         }

         @Override
         public void onCodeSent(@NonNull String verificationId,
                                @NonNull PhoneAuthProvider.ForceResendingToken token) {
             // The SMS verification code has been sent to the provided phone number, we
             // now need to ask the user to enter the code and then construct a credential
             // by combining the code with a verification ID.


             // Save verification ID and resending token so we can use them later
             mVerificationId = verificationId;

             // ...
         }
     };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information


                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void initViews(View view) {
        chkCity=view.findViewById(R.id.chk_city);
        tilConsumerMandal=view.findViewById(R.id.txt_consumer_mandal);
        txtName=view.findViewById(R.id.e_txt_consumer_name);
        txtPhone=view.findViewById(R.id.e_txt_phone);
        txtPassword=view.findViewById(R.id.e_txt_consumer_password);
        txtState=view.findViewById(R.id.e_txt_consumer_state);
        txtDistrict=view.findViewById(R.id.e_txt_consumer_district);
        txtCity=view.findViewById(R.id.e_txt_consumer_city);
        txtMandal=view.findViewById(R.id.e_txt_consumer_mandal);
        txtVillage=view.findViewById(R.id.e_txt_consumer_village);
        photo=view.findViewById(R.id.img_consumer);
        btnSubmit=view.findViewById(R.id.btn_submit);
        btnUploadPhoto=view.findViewById(R.id.btn_upload_photo);
        View bottomText=view.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomText);
       consumerHelp =view.findViewById(R.id.txt_consumer_fragment);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(resultCode==RESULT_OK){
                    assert data!=null;
                    Uri uri=data.getData();
                    InputStream inputStream;
                    try {
                        inputStream=getActivity().getContentResolver().openInputStream(uri);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
                        photo.setImageBitmap(bitmap);
                        imageByteArrayString= Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);
                        Log.d("imageArray---->",""+imageByteArrayString);
                        byte[] photoBytes = getBytes(bitmap);
                       // consumerRegistration.se(photoBytes);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    private byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        return stream.toByteArray();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_upload_photo:
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
                break;
            case R.id.txt_consumer_fragment:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.btn_submit:
                /*if(TextUtils.isEmpty(txtName.getText()) || TextUtils.isEmpty(txtPhone.getText()) || TextUtils.isEmpty(txtPassword.getText()) || TextUtils.isEmpty(txtState.getText())
                        || TextUtils.isEmpty(txtDistrict.getText()) || TextUtils.isEmpty(txtCity.getText())
                        || TextUtils.isEmpty(txtVillage.getText())){

                    Snackbar.make(btnSubmit,"Please enter credencials",Snackbar.LENGTH_LONG).show();


                } else {
                    consumerRegistration.setConsumerName(txtName.getText().toString().trim());
                    consumerRegistration.setConsumerPhone(Objects.requireNonNull(txtPhone.getText()).toString().trim());
                    consumerRegistration.setConsumerPassword(Objects.requireNonNull(txtPassword.getText()).toString().trim());
                    consumerRegistration.setConsumerState(Objects.requireNonNull(txtState.getText()).toString().trim());
                    consumerRegistration.setConsumerDistrict(Objects.requireNonNull(txtDistrict.getText()).toString().trim());
                    consumerRegistration.setConsumerCity(Objects.requireNonNull(txtCity.getText()).toString().trim());
                    consumerRegistration.setConsumerMandal(Objects.requireNonNull(txtMandal.getText()).toString().trim());
                    consumerRegistration.setConsumerVillage(Objects.requireNonNull(txtVillage.getText()).toString().trim());
                    if(consumerRegistration.getConsumerId()!=null)
                    consumerId= txtName.getText().toString().substring(0,4)+"@"+(txtVillage.getText().toString().substring(0,5))+consumerRegistration.getConsumerId();
                    consumerRegistration.setConsumerId((Long.parseLong(consumerId)));
                    databaseReference.push().setValue(consumerRegistration);
                    regViewModel.insert(consumerRegistration);
                    String phoneNumber=txtPhone.getText().toString().trim();

                    sendVerificationPhoneNumber(phoneNumber);
                }*/
                JsonObject object=new JsonObject();
                object.addProperty("eventCode","registration");
                object.addProperty("documentOwner","consumer");
                object.addProperty("docMimeType","image/jpeg");
                JsonObject jsonObject=new JsonObject();
                jsonObject.add("doc",jsonObject);

                String mainUrl="uploadDoc";
                String eventCode="registration";
                String body=jsonObject.toString().trim();
                SessionManager.getInstance(getContext()).saveImageEvent(body,imageByteArrayString,mainUrl,eventCode,consumerId);
                break;
        }
    }
}
