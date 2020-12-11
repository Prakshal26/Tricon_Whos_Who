package parser;

//import com.sun.tools.javac.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Person {



    private String surname;
    private String firstname;
    private String title;
    private String nobility;
    private String pseudonym;

    private String portrait_image;

    private String qualifications;
    private String nationality;
    private String presentPosition;
    private String nameAtBirth;
    private String dateOfBirth;
    private String placeOfbirth;
    private String dateOfDeath;
    private String parentage;
    private String education;
    private String career;
    private String honourAndAwards;

    private String family;
    private List<String> profession = new LinkedList<>();

    private String films;
    private String plays;
    private String tv;
    private String music;
    private String dance;
    private String artExhibition;
    private String radio;
    private String achievements;
    private String publications;
    private String leisure_interests;
    private String contact_details;
    private String management;

    private int dead;
    private String gender;


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNobility() {
        return nobility;
    }

    public void setNobility(String nobility) {
        this.nobility = nobility;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getPortrait_image() {
        return portrait_image;
    }

    public void setPortrait_image(String portrait_image) {
        this.portrait_image = portrait_image;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPresentPosition() {
        return presentPosition;
    }

    public void setPresentPosition(String presentPosition) {
        this.presentPosition = presentPosition;
    }

    public String getNameAtBirth() {
        return nameAtBirth;
    }

    public void setNameAtBirth(String nameAtBirth) {
        this.nameAtBirth = nameAtBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfbirth() {
        return placeOfbirth;
    }

    public void setPlaceOfbirth(String placeOfbirth) {
        this.placeOfbirth = placeOfbirth;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getParentage() {
        return parentage;
    }

    public void setParentage(String parentage) {
        this.parentage = parentage;
    }

    public List<String> getProfession() {
        return profession;
    }

    public void setProfession(List<String> profession) {
        this.profession = profession;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family)
    {
        if (this.family == null) {
            this.family = family;
        } else {
            this.family = this.family + " " + family;
        }

    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getHonourAndAwards() {
        return honourAndAwards;
    }

    public void setHonourAndAwards(String honourAndAwards) {
        this.honourAndAwards = honourAndAwards;
    }

    public String getFilms() {
        return films;
    }

    public void setFilms(String films) {
        if(this.films == null) {
            this.films = films;
        } else {
            this.films = this.films + " " + films;
        }
    }

    public String getPlays() {
        return plays;
    }

    public void setPlays(String plays) {
        if (this.plays == null) {
            this.plays = plays;
        } else {
            this.plays = this.plays + " " +plays;
        }
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        if (this.tv == null) {
            this.tv = tv;
        } else {
            this.tv = this.tv + " " + tv;
        }
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        if (this.music == null) {
            this.music = music;
        } else {
            this.music = this.music + " " + music;
        }
    }

    public String getDance() {
        return dance;
    }

    public void setDance(String dance) {
        if (this.dance == null) {
            this.dance = dance;
        } else {
            this.dance = this.dance + " " + dance;
        }
    }

    public String getArtExhibition() {
        return artExhibition;
    }

    public void setArtExhibition(String artExhibition) {
        if (this.artExhibition == null) {
            this.artExhibition = artExhibition;
        } else {
            this.artExhibition = this.artExhibition + " " + artExhibition;
        }
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        if (this.radio == null) {
            this.radio = radio;
        } else {
            this.radio = this.radio + " " + radio;
        }
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        if(this.achievements == null) {
            this.achievements = achievements;
        } else {
            this.achievements = this.achievements + " " + achievements;
        }
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        if (this.publications == null) {
            this.publications = publications;
        } else {
            this.publications = this.publications + " " + publications;
        }
    }

    public String getLeisure_interests() {
        return leisure_interests;
    }

    public void setLeisure_interests(String leisure_interests) {
        if (this.leisure_interests == null) {
            this.leisure_interests = leisure_interests;
        } else  {
            this.leisure_interests = this.leisure_interests + " " + leisure_interests;
        }
    }

    public String getContact_details() {
        return contact_details;
    }

    public void setContact_details(String contact_details) {
        if(this.contact_details == null) {
            this.contact_details = contact_details;
        } else {
            this.contact_details = this.contact_details + " " + contact_details;
        }

    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }




    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
