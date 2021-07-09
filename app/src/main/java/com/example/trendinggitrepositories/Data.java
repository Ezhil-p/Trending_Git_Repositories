package com.example.trendinggitrepositories;

class Data {
    private String Name;
    private String Description;
    private String Language;
    private String Stars;
    private String LanguageColor;

    public Data(String Name, String Description, String Language, String Stars,String LanguageColor) {

        this.Name = Name;
        this.Description = Description;
        this.Language = Language;
        this.Stars = Stars;
        this.LanguageColor=LanguageColor;
    }

    public Data() {

    }

    public String getLanguageColor() {
        return LanguageColor;
    }

    public void setLanguageColor(String languageColor) {
        LanguageColor = languageColor;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setStars(String stars) {
        Stars = stars;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getLanguage() {
        return Language;
    }

    public String getStars() {
        return Stars;
    }
}
