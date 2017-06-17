package com.article.binhu.articlereader.model;

import java.util.List;

/**
 * Created by binhu on 16.06.17.
 */

public class Article {
    private String _id;
    private String web_url;
    private String snippet;
    private String lead_paragraph;
    private String source;
    private String pub_date;
    private String section_name;
    private String subsection_name;
    private String type_of_material;
    private int wordCount;
    private HeadLine headline;
//    private Byline byline;
    private List<Multimedia> multimedia;

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

//    public Byline getByline() {
//        return byline;
//    }
//
//    public void setByline(Byline byline) {
//        this.byline = byline;
//    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLead_paragraph() {
        return lead_paragraph;
    }

    public void setLead_paragraph(String lead_paragraph) {
        this.lead_paragraph = lead_paragraph;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSubsection_name() {
        return subsection_name;
    }

    public void setSubsection_name(String subsection_name) {
        this.subsection_name = subsection_name;
    }

    public String getType_of_material() {
        return type_of_material;
    }

    public void setType_of_material(String type_of_material) {
        this.type_of_material = type_of_material;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public HeadLine getHeadline() {
        return headline;
    }

    public void setHeadline(HeadLine headline) {
        this.headline = headline;
    }
}
