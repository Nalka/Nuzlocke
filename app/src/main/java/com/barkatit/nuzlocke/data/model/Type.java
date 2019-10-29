package com.barkatit.nuzlocke.data.model;

import com.barkatit.nuzlocke.R;
import com.google.gson.annotations.SerializedName;

public enum Type {
    @SerializedName("Bug")
    BUG("Bug") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_bug;
        }
    },
    @SerializedName("Dark")
    DARK("Dark") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_dark;
        }
    },
    @SerializedName("Dragon")
    DRAGON("Dragon") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_dragon;
        }
    },
    @SerializedName("Electric")
    ELECTRIC("Electric") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_electric;
        }
    },
    @SerializedName("Fairy")
    FAIRY("Fairy") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_fairy;
        }
    },
    @SerializedName("Fighting")
    FIGHTING("Fighting") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_fighting;
        }
    },
    @SerializedName("Fire")
    FIRE("Fire") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_fire;
        }
    },
    @SerializedName("Flying")
    FLYING("Flying") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_flying;
        }
    },
    @SerializedName("Ghost")
    GHOST("Ghost") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_ghost;
        }
    },
    @SerializedName("Grass")
    GRASS("Grass") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_grass;
        }
    },
    @SerializedName("Ground")
    GROUND("Ground") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_ground;
        }
    },
    @SerializedName("Ice")
    ICE("Ice") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_ice;
        }
    },
    @SerializedName("Normal")
    NORMAL("Normal") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_normal;
        }
    },
    @SerializedName("Poison")
    POISON("Poison") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_poison;
        }
    },
    @SerializedName("Psychic")
    PSYCHIC("Psychic") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_psychic;
        }
    },
    @SerializedName("Rock")
    ROCK("Rock") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_rock;
        }
    },
    @SerializedName("Steel")
    STEEL("Steel") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_steel;
        }
    },
    @SerializedName("Water")
    WATER("Water") {
        @Override
        public int getColorId() {
            return R.color.pokemon_type_water;
        }
    };

    private String value;

    private Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract int getColorId();

    public static Type getTypeByName(String name) {
        for(Type item : values()) {
            if(item.value.equalsIgnoreCase(name))
                return item;
        }
        return NORMAL;
    }
}
