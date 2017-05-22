package com.danlai.nidepuzi.entity;

import java.util.List;

public class FansBean {


    /**
     * count : 3
     * next : null
     * previous : null
     * results : [{"id":336,"nick":"Lv8cCQ","thumbnail":"","charge_time":"2017-05-19T14:06:42.746729","award":null},{"id":282,"nick":"冬^_^冬Mr.邢","thumbnail":"http://wx.qlogo.cn/mmopen/TkoK4QDnu4hCJT46IR1YwKuZsa2E03ZRW4Ra402Cvn2olmm6jqr0Hib3REBNibwzeECa9Bvky8PNvickdA9CbiaFMnn2Gev1mkH5/0","charge_time":"2017-05-18T14:04:49.499731","award":null},{"id":205,"nick":"James","thumbnail":"http://wx.qlogo.cn/mmopen/KydxAIB52xmiasCgzxbpia5kId3XZRw930kJ076LrU5BQ2S0en6H4bOQ0ejxjdGjyRYzF84cR4A3wDicJ9k1I0NHSNBmXciapDL9/0","charge_time":"2017-05-16T22:04:57.493698","award":null}]
     */

    private int count;
    private String next;
    private String previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 336
         * nick : Lv8cCQ
         * thumbnail :
         * charge_time : 2017-05-19T14:06:42.746729
         * award : null
         */

        private int id;
        private String nick;
        private String thumbnail;
        private String charge_time;
        private int referal_type;
        private Object award;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getCharge_time() {
            return charge_time;
        }

        public void setCharge_time(String charge_time) {
            this.charge_time = charge_time;
        }

        public Object getAward() {
            return award;
        }

        public void setAward(Object award) {
            this.award = award;
        }

        public int getReferal_type() {
            return referal_type;
        }

        public void setReferal_type(int referal_type) {
            this.referal_type = referal_type;
        }
    }
}
