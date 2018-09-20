package com.example.qqche.cy.Gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainNotic   {
    /**
     * data : [{"id":"1","title":"17利返上线预热","abstract":"17利返上线试运营","content":"","addtime":"1522393662","release_time":"1522368000","add_user":"1","hot":"54","url":"http://testwww17lifan.yongedai.com/notice/noticeinfo.html?id=1"}]
     * status : 1
     * string : Success
     * msg : 数据获取成功
     */

    private String status;
    private String string;
    private String msg;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 17利返上线预热
         * abstract : 17利返上线试运营
         * content :
         * addtime : 1522393662
         * release_time : 1522368000
         * add_user : 1
         * hot : 54
         * url : http://testwww17lifan.yongedai.com/notice/noticeinfo.html?id=1
         */

        private String id;
        private String title;
        @SerializedName("abstract")
        private String abstractX;
        private String content;
        private String addtime;
        private String release_time;
        private String add_user;
        private String hot;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }

        public String getAdd_user() {
            return add_user;
        }

        public void setAdd_user(String add_user) {
            this.add_user = add_user;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
