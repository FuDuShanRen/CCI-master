package tiancefu.com.cci.bean.book;

import java.io.Serializable;
import java.util.List;

import tiancefu.com.cci.bean.top250.Images;

/**
 * Created by dsblt on 2017/4/29.
 */

public class Books implements Serializable{
    private BookRating rating;//书籍评分

    private String subtitle;//

    private List<String> author ;

    private String pubdate;//出版日期

    private List<BookTags> tags ;//查询的tag

    private String origin_title;//原名

    private String image;//书籍图标

    private String binding;//装顶版本,如平装、精装

    //private List<Translator> translator ;

    private String catalog;//目录

    private String pages;//页数

    private Images images;//书籍封面，有大中小尺寸

    private String alt;

    private String id;

    private String publisher;//出版社

    private String isbn10;//书籍isbn10编码

    private String isbn13;//书籍isbn13编码

    private String title;//主题

    private String url;

    private String alt_title;

    private String author_intro;//作者简介

    private String summary;//书籍简介

    private BookSeries series;//书籍所属系列

    private String price;//书籍价格

    public BookRating getRating() {
        return rating;
    }

    public void setRating(BookRating rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public List<BookTags> getTags() {
        return tags;
    }

    public void setTags(List<BookTags> tags) {
        this.tags = tags;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BookSeries getSeries() {
        return series;
    }

    public void setSeries(BookSeries series) {
        this.series = series;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
