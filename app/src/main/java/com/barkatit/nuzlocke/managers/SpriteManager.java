package com.barkatit.nuzlocke.managers;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SpriteManager {

    private static final String SPRITE_HOST_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png";

    public static void loadImage(int pokemonSpeciesId, ImageView imageView) {
        String url = String.format(Locale.getDefault(), SPRITE_HOST_URL, pokemonSpeciesId);
        Picasso.get().load(url).into(imageView);
    }

}
