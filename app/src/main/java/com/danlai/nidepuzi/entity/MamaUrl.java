package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 16/7/13.
 */
public class MamaUrl {


    /**
     * count : 2
     * next : null
     * previous : null
     * results : [{"id":1,"version":"1.0","is_valid":true,"extra":{"notice":"http://m.xiaolumeimei.com/mall/mama/notification/list","act_info":"http://m.xiaolumeimei.com/mall/mama/university/home","exam":"http://m.xiaolumeimei.com/mall/activity/exam","forum":"http://forum.xiaolumeimei.com/accounts/xlmm/login/","team_explain":"http://m.xiaolumeimei.com/mall/mama/team/introduce","pictures":{"exam_pic":"http://7xogkj.com1.z0.glb.clouddn.com/vipGrade_Examination2x.png"},"renew":"http://m.xiaolumeimei.com/mall/activity/shop/charge111","fans_explain":"http://m.xiaolumeimei.com/pages/fans-explain.html","invite":"http://m.xiaolumeimei.com/mall/mama/invited"},"mama_activities":[{"id":44,"title":"1元开店 经验授课 立奖30元！","login_required":true,"act_desc":"参加1元开店的体验小鹿妈妈，可以自己开微信群分享开店经验，小鹿美美为每位开课的妈妈奖励30元课酬！","act_img":"http://7xrst8.com1.z0.glb.clouddn.com/1yuan-lesson-poster.png","act_logo":"http://7xogkj.com2.z0.glb.qiniucdn.com/logo2.png","mask_link":"","act_link":"http://m.xiaolumeimei.com/lessons/html/mama-lesson.html?topic_id=38","act_type":"mama","act_applink":"","start_time":"2016-08-26T18:04:43","end_time":"2016-09-30T23:59:59","order_val":0,"extras":{"html":{"signup":"/lessons/html/signup.html"}},"total_member_num":2000,"friend_member_num":16,"is_active":true,"products":[]},{"id":7,"title":"小鹿大学 分享营销的科技殿堂","login_required":true,"act_desc":"十余门精选免费课程，配备行业专业讲师，熟练学习最专业的软件系统，打开分享营销的时空隧道大门，让妈妈们坐收佣金，轻松日赚100元零花钱！","act_img":"http://7xogkj.com2.z0.glb.qiniucdn.com/ut-austin3.jpg","act_logo":"","mask_link":"","act_link":"/lessons/html/","act_type":"mama","act_applink":"","start_time":"2016-05-08T10:00:00","end_time":"2016-10-30T23:59:59","order_val":0,"extras":{"html":{"apply":"/lessons/html/instructors.html","signup":"/lessons/html/signup.html","attenders":"/lessons/html/attenders.html","snsauth":"/rest/lesson/snsauth/{lesson_id}"}},"total_member_num":2000,"friend_member_num":16,"is_active":true,"products":[]}],"created":"2016-07-13T17:31:59","modified":"2016-08-25T10:53:47"},{"id":2,"version":"new_guy_task","is_valid":true,"extra":{"page_pop":1,"mama_recommend_show":1},"mama_activities":[{"id":44,"title":"1元开店 经验授课 立奖30元！","login_required":true,"act_desc":"参加1元开店的体验小鹿妈妈，可以自己开微信群分享开店经验，小鹿美美为每位开课的妈妈奖励30元课酬！","act_img":"http://7xrst8.com1.z0.glb.clouddn.com/1yuan-lesson-poster.png","act_logo":"http://7xogkj.com2.z0.glb.qiniucdn.com/logo2.png","mask_link":"","act_link":"http://m.xiaolumeimei.com/lessons/html/mama-lesson.html?topic_id=38","act_type":"mama","act_applink":"","start_time":"2016-08-26T18:04:43","end_time":"2016-09-30T23:59:59","order_val":0,"extras":{"html":{"signup":"/lessons/html/signup.html"}},"total_member_num":2000,"friend_member_num":16,"is_active":true,"products":[]},{"id":7,"title":"小鹿大学 分享营销的科技殿堂","login_required":true,"act_desc":"十余门精选免费课程，配备行业专业讲师，熟练学习最专业的软件系统，打开分享营销的时空隧道大门，让妈妈们坐收佣金，轻松日赚100元零花钱！","act_img":"http://7xogkj.com2.z0.glb.qiniucdn.com/ut-austin3.jpg","act_logo":"","mask_link":"","act_link":"/lessons/html/","act_type":"mama","act_applink":"","start_time":"2016-05-08T10:00:00","end_time":"2016-10-30T23:59:59","order_val":0,"extras":{"html":{"apply":"/lessons/html/instructors.html","signup":"/lessons/html/signup.html","attenders":"/lessons/html/attenders.html","snsauth":"/rest/lesson/snsauth/{lesson_id}"}},"total_member_num":2000,"friend_member_num":16,"is_active":true,"products":[]}],"created":"2016-08-03T14:55:54","modified":"2016-08-03T16:08:34"}]
     */

    private int count;
    private String next;
    private String previous;
    /**
     * id : 1
     * version : 1.0
     * is_valid : true
     * extra : {"notice":"http://m.xiaolumeimei.com/mall/mama/notification/list","act_info":"http://m.xiaolumeimei.com/mall/mama/university/home","exam":"http://m.xiaolumeimei.com/mall/activity/exam","forum":"http://forum.xiaolumeimei.com/accounts/xlmm/login/","team_explain":"http://m.xiaolumeimei.com/mall/mama/team/introduce","pictures":{"exam_pic":"http://7xogkj.com1.z0.glb.clouddn.com/vipGrade_Examination2x.png"},"renew":"http://m.xiaolumeimei.com/mall/activity/shop/charge111","fans_explain":"http://m.xiaolumeimei.com/pages/fans-explain.html","invite":"http://m.xiaolumeimei.com/mall/mama/invited"}
     * mama_activities : [{"id":44,"title":"1元开店 经验授课 立奖30元！","login_required":true,"act_desc":"参加1元开店的体验小鹿妈妈，可以自己开微信群分享开店经验，小鹿美美为每位开课的妈妈奖励30元课酬！","act_img":"http://7xrst8.com1.z0.glb.clouddn.com/1yuan-lesson-poster.png","act_logo":"http://7xogkj.com2.z0.glb.qiniucdn.com/logo2.png","mask_link":"","act_link":"http://m.xiaolumeimei.com/lessons/html/mama-lesson.html?topic_id=38","act_type":"mama","act_applink":"","start_time":"2016-08-26T18:04:43","end_time":"2016-09-30T23:59:59","order_val":0,"extras":{"html":{"signup":"/lessons/html/signup.html"}},"total_member_num":2000,"friend_member_num":16,"is_active":true,"products":[]},{"id":7,"title":"小鹿大学 分享营销的科技殿堂","login_required":true,"act_desc":"十余门精选免费课程，配备行业专业讲师，熟练学习最专业的软件系统，打开分享营销的时空隧道大门，让妈妈们坐收佣金，轻松日赚100元零花钱！","act_img":"http://7xogkj.com2.z0.glb.qiniucdn.com/ut-austin3.jpg","act_logo":"","mask_link":"","act_link":"/lessons/html/","act_type":"mama","act_applink":"","start_time":"2016-05-08T10:00:00","end_time":"2016-10-30T23:59:59","order_val":0,"extras":{"html":{"apply":"/lessons/html/instructors.html","signup":"/lessons/html/signup.html","attenders":"/lessons/html/attenders.html","snsauth":"/rest/lesson/snsauth/{lesson_id}"}},"total_member_num":2000,"friend_member_num":16,"is_active":true,"products":[]}]
     * created : 2016-07-13T17:31:59
     * modified : 2016-08-25T10:53:47
     */

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
        private int id;
        private String version;
        private boolean is_valid;
        /**
         * notice : http://m.xiaolumeimei.com/mall/mama/notification/list
         * act_info : http://m.xiaolumeimei.com/mall/mama/university/home
         * exam : http://m.xiaolumeimei.com/mall/activity/exam
         * forum : http://forum.xiaolumeimei.com/accounts/xlmm/login/
         * team_explain : http://m.xiaolumeimei.com/mall/mama/team/introduce
         * pictures : {"exam_pic":"http://7xogkj.com1.z0.glb.clouddn.com/vipGrade_Examination2x.png"}
         * renew : http://m.xiaolumeimei.com/mall/activity/shop/charge111
         * fans_explain : http://m.xiaolumeimei.com/pages/fans-explain.html
         * invite : http://m.xiaolumeimei.com/mall/mama/invited
         */

        private ExtraBean extra;
        private String created;
        private String modified;
        /**
         * id : 44
         * title : 1元开店 经验授课 立奖30元！
         * login_required : true
         * act_desc : 参加1元开店的体验小鹿妈妈，可以自己开微信群分享开店经验，小鹿美美为每位开课的妈妈奖励30元课酬！
         * act_img : http://7xrst8.com1.z0.glb.clouddn.com/1yuan-lesson-poster.png
         * act_logo : http://7xogkj.com2.z0.glb.qiniucdn.com/logo2.png
         * mask_link :
         * act_link : http://m.xiaolumeimei.com/lessons/html/mama-lesson.html?topic_id=38
         * act_type : mama
         * act_applink :
         * start_time : 2016-08-26T18:04:43
         * end_time : 2016-09-30T23:59:59
         * order_val : 0
         * extras : {"html":{"signup":"/lessons/html/signup.html"}}
         * total_member_num : 2000
         * friend_member_num : 16
         * is_active : true
         * products : []
         */

        private List<PortalBean.ActivitysBean> mama_activities;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public boolean isIs_valid() {
            return is_valid;
        }

        public void setIs_valid(boolean is_valid) {
            this.is_valid = is_valid;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public List<PortalBean.ActivitysBean> getMama_activities() {
            return mama_activities;
        }

        public void setMama_activities(List<PortalBean.ActivitysBean> mama_activities) {
            this.mama_activities = mama_activities;
        }

        public static class ExtraBean {
            private String notice;
            private String act_info;
            private String exam;
            private String forum;
            private String team_explain;
            /**
             * exam_pic : http://7xogkj.com1.z0.glb.clouddn.com/vipGrade_Examination2x.png
             */

            private PicturesBean pictures;
            private String renew;
            private String fans_explain;
            private String invite;
            private String boutique;

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getAct_info() {
                return act_info;
            }

            public void setAct_info(String act_info) {
                this.act_info = act_info;
            }

            public String getExam() {
                return exam;
            }

            public void setExam(String exam) {
                this.exam = exam;
            }

            public String getForum() {
                return forum;
            }

            public void setForum(String forum) {
                this.forum = forum;
            }

            public String getTeam_explain() {
                return team_explain;
            }

            public void setTeam_explain(String team_explain) {
                this.team_explain = team_explain;
            }

            public PicturesBean getPictures() {
                return pictures;
            }

            public void setPictures(PicturesBean pictures) {
                this.pictures = pictures;
            }

            public String getRenew() {
                return renew;
            }

            public void setRenew(String renew) {
                this.renew = renew;
            }

            public String getFans_explain() {
                return fans_explain;
            }

            public void setFans_explain(String fans_explain) {
                this.fans_explain = fans_explain;
            }

            public String getInvite() {
                return invite;
            }

            public void setInvite(String invite) {
                this.invite = invite;
            }

            public String getBoutique() {
                return boutique;
            }

            public void setBoutique(String boutique) {
                this.boutique = boutique;
            }

            public static class PicturesBean {
                private String exam_pic;

                public String getExam_pic() {
                    return exam_pic;
                }

                public void setExam_pic(String exam_pic) {
                    this.exam_pic = exam_pic;
                }
            }
        }
    }
}
