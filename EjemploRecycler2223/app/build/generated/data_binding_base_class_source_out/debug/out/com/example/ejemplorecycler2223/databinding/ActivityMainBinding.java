// Generated by view binder compiler. Do not edit!
package com.example.ejemplorecycler2223.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ejemplorecycler2223.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btDetalle;

  @NonNull
  public final RecyclerView listaPersonajesRecycler;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button btDetalle,
      @NonNull RecyclerView listaPersonajesRecycler) {
    this.rootView = rootView;
    this.btDetalle = btDetalle;
    this.listaPersonajesRecycler = listaPersonajesRecycler;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btDetalle;
      Button btDetalle = ViewBindings.findChildViewById(rootView, id);
      if (btDetalle == null) {
        break missingId;
      }

      id = R.id.listaPersonajesRecycler;
      RecyclerView listaPersonajesRecycler = ViewBindings.findChildViewById(rootView, id);
      if (listaPersonajesRecycler == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, btDetalle,
          listaPersonajesRecycler);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
