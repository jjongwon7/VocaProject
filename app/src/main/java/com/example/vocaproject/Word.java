package com.example.vocaproject;

public class Word {
    private String english;
    private String koreanMean;
    private String voice;

    public Word(){}
    public Word(String e, String k, String v){
        english = e;
        koreanMean = k;
        voice = v;
    }
    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getKoreanMean() {
        return koreanMean;
    }

    public void setKoreanMean(String koreanMean) {
        this.koreanMean = koreanMean;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
