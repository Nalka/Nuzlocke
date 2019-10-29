package com.barkatit.nuzlocke.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String species;
    private int speciesId;
    private String nature;
    private String type;
    private String subType;

    private int levelMet;
    private int level;
    private PokemonGender gender;

    private Date caught;
    private String placeMet;

    private int hp;
    private int attack;
    private int defence;
    private int specialAttack;
    private int specialDefence;
    private int speed;

    private boolean inParty = false;
    private boolean released = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public void setSpecialDefence(int specialDefence) {
        this.specialDefence = specialDefence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isInParty() {
        return inParty;
    }

    public void setInParty(boolean inParty) {
        this.inParty = inParty;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public int getLevelMet() {
        return levelMet;
    }

    public void setLevelMet(int levelMet) {
        this.levelMet = levelMet;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PokemonGender getGender() {
        if(gender == null)
            return PokemonGender.GENDERLESS;
        return gender;
    }

    public void setGender(PokemonGender gender) {
        this.gender = gender;
    }

    public Date getCaught() {
        return caught;
    }

    public void setCaught(Date caught) {
        this.caught = caught;
    }

    public String getPlaceMet() {
        return placeMet;
    }

    public void setPlaceMet(String placeMet) {
        this.placeMet = placeMet;
    }
}
