package cn.bittonet.wftpay.felonehelper.bean;

import java.io.Serializable;


public class ImageBean implements Serializable {

    /**
     * id : 393
     * image_url : http://101.200.174.126:10000/nursing_cloud/multimedium/images/393/original/RackMultipart20171102-1618-1xszv92.jpeg?1509605583
     * image_url_medium : http://101.200.174.126:10000/nursing_cloud/multimedium/images/393/medium/RackMultipart20171102-1618-1xszv92.jpeg?1509605583
     * image_url_thumb : http://101.200.174.126:10000/nursing_cloud/multimedium/images/393/thumb/RackMultipart20171102-1618-1xszv92.jpeg?1509605583
     */

    private int    id;
    private String image_url; // 原图
    private String image_url_medium; // 中图
    private String image_url_thumb; // 缩略图

    public ImageBean(String image_url) {
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public String getImageUrlMedium() {
        return image_url_medium;
    }

    public void setImageUrlMedium(String image_url_medium) {
        this.image_url_medium = image_url_medium;
    }

    public String getImageUrlThumb() {
        return image_url_thumb;
    }

    public void setImageUrlThumb(String image_url_thumb) {
        this.image_url_thumb = image_url_thumb;
    }

    @Override
    public String toString() {
        return "{"
                + "id="
                + id
                + ", image_url='"
                + image_url
                + '\''
                + ", image_url_medium='"
                + image_url_medium
                + '\''
                + ", image_url_thumb='"
                + image_url_thumb
                + '\''
                + '}';
    }
}
