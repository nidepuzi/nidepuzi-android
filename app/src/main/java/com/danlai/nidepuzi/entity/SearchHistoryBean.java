package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 17/1/10.
 */

public class SearchHistoryBean {

    /**
     * count : 5
     * next : null
     * previous : null
     * results : [{"id":5,"user_id":864121,"content":"麻辣","target":"ModelProduct","result_count":3,"status":"normal"},{"id":4,"user_id":864121,"content":"肉","target":"ModelProduct","result_count":11,"status":"normal"},{"id":3,"user_id":864121,"content":"床","target":"ModelProduct","result_count":10,"status":"normal"},{"id":2,"user_id":864121,"content":"草","target":"ModelProduct","result_count":11,"status":"normal"},{"id":1,"user_id":864121,"content":"花","target":"ModelProduct","result_count":43,"status":"normal"}]
     */

    private int count;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 5
         * user_id : 864121
         * content : 麻辣
         * target : ModelProduct
         * result_count : 3
         * status : normal
         */

        private int id;
        private int user_id;
        private String content;
        private String target;
        private int result_count;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public int getResult_count() {
            return result_count;
        }

        public void setResult_count(int result_count) {
            this.result_count = result_count;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
