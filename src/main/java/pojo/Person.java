package pojo;

//import com.sun.tools.javac.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Person {


    private String xmlId;

    private String indexedName;
    private String nobility;
    private String titles;
    private String givenName;
    private String pseudonym;

    private String portraitImage;
    private String qualifications;
    private String nationality;

    private List<String> profession = new LinkedList<>();

    private String presentPosition;
    private String nameAtBirth;
    private String dateOfBirth;
    private String placeOfBirth;
    private String dateOfDeath;
    private String parentage;
    private String family;
    private String education;
    private String careerPara;
    private String honoursAwards;

    private String films;
    private String plays;
    private String tv;
    private String music;
    private String dance;
    private String artExhibition;
    private String radio;
    private String achievements;
    private String publications;
    private String leisureInterests;
    private String contactDetails;
    private String managementAddress;


    private int dead;
    private String gender;
    private String region;
    private String subRegion;


    public String getXmlId() {
        return xmlId;
    }

    public void setXmlId(String xmlId) {
        this.xmlId = xmlId;
    }

    public String getIndexedName() {
        return indexedName;
    }

    public void setIndexedName(String indexedName) {
        this.indexedName = indexedName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
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

    public String getPortraitImage() {
        return portraitImage;
    }

    public void setPortraitImage(String portraitImage) {
        this.portraitImage = portraitImage;
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

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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

    public String getCareerPara() {
        return careerPara;
    }

    public void setCareerPara(String careerPara) {
        this.careerPara = careerPara;
    }

    public String getHonoursAwards() {
        return honoursAwards;
    }

    public void setHonoursAwards(String honoursAwards) {
        this.honoursAwards = honoursAwards;
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

    public String getLeisureInterests() {
        return leisureInterests;
    }

    public void setLeisureInterests(String leisureInterests) {
        if (this.leisureInterests == null) {
            this.leisureInterests = leisureInterests;
        } else  {
            this.leisureInterests = this.leisureInterests + " " + leisureInterests;
        }
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        if(this.contactDetails == null) {
            this.contactDetails = contactDetails;
        } else {
            this.contactDetails = this.contactDetails + " " + contactDetails;
        }

    }

    public String getManagementAddress() {
        return managementAddress;
    }

    public void setManagementAddress(String managementAddress) {
        this.managementAddress = managementAddress;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }
}
