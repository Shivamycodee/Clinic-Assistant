package com.example.clinicmanage;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class PasswordBottomSheetFragment extends BottomSheetDialogFragment {

    BottomSheetDialog dialog;
    BottomSheetBehavior<View> bottomSheetBehaviour;
    View rootView;

 public PasswordBottomSheetFragment(){
 }

 @NonNull
 @Override
 public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
     dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
     return dialog;
 }

@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//     return super.onCreateView(inflater,container,savedInstanceState);
     rootView = inflater.inflate(R.layout.activity_main2,container,false);

     EditText crt = rootView.findViewById(R.id.crtPass);
     EditText cmf = rootView.findViewById(R.id.cofPass);
    Button  btn = rootView.findViewById(R.id.passBtn);

    btn.setOnClickListener(view ->{

        String createPassword = crt.getText().toString();
        String confirmPassword = cmf.getText().toString();

        DoctorDatabase db = new DoctorDatabase(getActivity());

        if(createPassword.equals(confirmPassword)){
             if(!createPassword.equals("")){
                Toast.makeText(getActivity(), "successfully created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),loginPage.class);
                db.passPassword(confirmPassword);
                startActivity(intent);
            } else Toast.makeText(getActivity(), "Create a password", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(getActivity(),"Create/Confirm do not match", Toast.LENGTH_SHORT).show();
    });

     return rootView;
}

@Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
//     super.onViewCreated(view,savedInstanceState);
    bottomSheetBehaviour = BottomSheetBehavior.from((View) view.getParent());
//    bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);


    RelativeLayout layout = dialog.findViewById(R.id.bottomSheetContainer);
//    assert layout != null ;
//    layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);

}


}
