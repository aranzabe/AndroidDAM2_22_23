// Generated by view binder compiler. Do not edit!
package com.example.ejemplorecycler2223.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ejemplorecycler2223.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemCardBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final ImageView imagePersonajeCard;

  @NonNull
  public final TextView txtNombreCard;

  @NonNull
  public final TextView txtRazaCard;

  private ItemCardBinding(@NonNull CardView rootView, @NonNull CardView cardView,
      @NonNull ImageView imagePersonajeCard, @NonNull TextView txtNombreCard,
      @NonNull TextView txtRazaCard) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.imagePersonajeCard = imagePersonajeCard;
    this.txtNombreCard = txtNombreCard;
    this.txtRazaCard = txtRazaCard;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemCardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemCardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_card, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemCardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardView;
      CardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.imagePersonajeCard;
      ImageView imagePersonajeCard = ViewBindings.findChildViewById(rootView, id);
      if (imagePersonajeCard == null) {
        break missingId;
      }

      id = R.id.txtNombreCard;
      TextView txtNombreCard = ViewBindings.findChildViewById(rootView, id);
      if (txtNombreCard == null) {
        break missingId;
      }

      id = R.id.txtRazaCard;
      TextView txtRazaCard = ViewBindings.findChildViewById(rootView, id);
      if (txtRazaCard == null) {
        break missingId;
      }

      return new ItemCardBinding((CardView) rootView, cardView, imagePersonajeCard, txtNombreCard,
          txtRazaCard);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}