package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2016年03月10日 下午4:02
 */
public class MamaFortune {

    /**
     * mama_fortune : {"mama_id":600001,"mama_name":"","mama_level":0,"mama_level_display":"","cash_value":0,"fans_num":0,"invite_num":0,"invite_trial_num":0,"invite_all_num":0,"order_num":2,"carry_value":0,"active_value_num":0,"carry_pending_display":0,"carry_confirmed_display":0,"carry_cashout_display":0,"mama_event_link":"https://m.nidepuzi.com/pages/featuredEvent.html","history_last_day":"2016-03-24","today_visitor_num":0,"modified":"2017-05-04T16:35:25.258480","created":"2017-05-04T16:35:25.258392","extra_info":{"surplus_days":19,"next_agencylevel_display":"VIP2","next_level_exam_url":"http://m.nidepuzi.com/mall/activity/exam","could_cash_out":0,"agencylevel_display":"VIP1","invite_url":"https://m.nidepuzi.com/pages/agency-invitation-res.html","total_rank":0,"agencylevel":2,"next_agencylevel":3,"cashout_reason":"余额不足不能提现","his_confirmed_cash_out":0,"thumbnail":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0"},"current_dp_turns_num":0,"extra_figures":{"team_total_rank":0,"personal_total_rank":0,"task_percentage":0,"today_carry_record":0,"week_duration_total":0,"week_duration_rank":0}}
     */

    private MamaFortuneBean mama_fortune;

    public MamaFortuneBean getMama_fortune() {
        return mama_fortune;
    }

    public void setMama_fortune(MamaFortuneBean mama_fortune) {
        this.mama_fortune = mama_fortune;
    }

    public static class MamaFortuneBean {
        /**
         * mama_id : 600001
         * mama_name :
         * mama_level : 0
         * mama_level_display :
         * cash_value : 0
         * fans_num : 0
         * invite_num : 0
         * invite_trial_num : 0
         * invite_all_num : 0
         * order_num : 2
         * carry_value : 0
         * active_value_num : 0
         * carry_pending_display : 0
         * carry_confirmed_display : 0
         * carry_cashout_display : 0
         * mama_event_link : https://m.nidepuzi.com/pages/featuredEvent.html
         * history_last_day : 2016-03-24
         * today_visitor_num : 0
         * modified : 2017-05-04T16:35:25.258480
         * created : 2017-05-04T16:35:25.258392
         * extra_info : {"surplus_days":19,"next_agencylevel_display":"VIP2","next_level_exam_url":"http://m.nidepuzi.com/mall/activity/exam","could_cash_out":0,"agencylevel_display":"VIP1","invite_url":"https://m.nidepuzi.com/pages/agency-invitation-res.html","total_rank":0,"agencylevel":2,"next_agencylevel":3,"cashout_reason":"余额不足不能提现","his_confirmed_cash_out":0,"thumbnail":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0"}
         * current_dp_turns_num : 0
         * extra_figures : {"team_total_rank":0,"personal_total_rank":0,"task_percentage":0,"today_carry_record":0,"week_duration_total":0,"week_duration_rank":0}
         */

        private int mama_id;
        private String mama_name;
        private int mama_level;
        private String mama_level_display;
        private double cash_value;
        private int fans_num;
        private int invite_num;
        private int invite_trial_num;
        private int invite_all_num;
        private int order_num;
        private double carry_value;
        private int active_value_num;
        private double carry_pending_display;
        private double carry_confirmed_display;
        private double carry_cashout_display;
        private String mama_event_link;
        private String history_last_day;
        private int today_visitor_num;
        private String modified;
        private String created;
        private ExtraInfoBean extra_info;
        private int current_dp_turns_num;
        private ExtraFiguresBean extra_figures;
        private double carry_self;
        private double carry_share;
        private double cash_self_display;
        private double cash_share_display;

        public int getMama_id() {
            return mama_id;
        }

        public void setMama_id(int mama_id) {
            this.mama_id = mama_id;
        }

        public String getMama_name() {
            return mama_name;
        }

        public void setMama_name(String mama_name) {
            this.mama_name = mama_name;
        }

        public int getMama_level() {
            return mama_level;
        }

        public void setMama_level(int mama_level) {
            this.mama_level = mama_level;
        }

        public String getMama_level_display() {
            return mama_level_display;
        }

        public void setMama_level_display(String mama_level_display) {
            this.mama_level_display = mama_level_display;
        }

        public double getCash_value() {
            return cash_value;
        }

        public void setCash_value(double cash_value) {
            this.cash_value = cash_value;
        }

        public int getFans_num() {
            return fans_num;
        }

        public void setFans_num(int fans_num) {
            this.fans_num = fans_num;
        }

        public int getInvite_num() {
            return invite_num;
        }

        public void setInvite_num(int invite_num) {
            this.invite_num = invite_num;
        }

        public int getInvite_trial_num() {
            return invite_trial_num;
        }

        public void setInvite_trial_num(int invite_trial_num) {
            this.invite_trial_num = invite_trial_num;
        }

        public int getInvite_all_num() {
            return invite_all_num;
        }

        public void setInvite_all_num(int invite_all_num) {
            this.invite_all_num = invite_all_num;
        }

        public int getOrder_num() {
            return order_num;
        }

        public void setOrder_num(int order_num) {
            this.order_num = order_num;
        }

        public double getCarry_value() {
            return carry_value;
        }

        public void setCarry_value(double carry_value) {
            this.carry_value = carry_value;
        }

        public int getActive_value_num() {
            return active_value_num;
        }

        public void setActive_value_num(int active_value_num) {
            this.active_value_num = active_value_num;
        }

        public double getCarry_pending_display() {
            return carry_pending_display;
        }

        public void setCarry_pending_display(double carry_pending_display) {
            this.carry_pending_display = carry_pending_display;
        }

        public double getCarry_confirmed_display() {
            return carry_confirmed_display;
        }

        public void setCarry_confirmed_display(double carry_confirmed_display) {
            this.carry_confirmed_display = carry_confirmed_display;
        }

        public double getCarry_cashout_display() {
            return carry_cashout_display;
        }

        public void setCarry_cashout_display(double carry_cashout_display) {
            this.carry_cashout_display = carry_cashout_display;
        }

        public String getMama_event_link() {
            return mama_event_link;
        }

        public void setMama_event_link(String mama_event_link) {
            this.mama_event_link = mama_event_link;
        }

        public String getHistory_last_day() {
            return history_last_day;
        }

        public void setHistory_last_day(String history_last_day) {
            this.history_last_day = history_last_day;
        }

        public int getToday_visitor_num() {
            return today_visitor_num;
        }

        public void setToday_visitor_num(int today_visitor_num) {
            this.today_visitor_num = today_visitor_num;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public ExtraInfoBean getExtra_info() {
            return extra_info;
        }

        public void setExtra_info(ExtraInfoBean extra_info) {
            this.extra_info = extra_info;
        }

        public int getCurrent_dp_turns_num() {
            return current_dp_turns_num;
        }

        public void setCurrent_dp_turns_num(int current_dp_turns_num) {
            this.current_dp_turns_num = current_dp_turns_num;
        }

        public ExtraFiguresBean getExtra_figures() {
            return extra_figures;
        }

        public void setExtra_figures(ExtraFiguresBean extra_figures) {
            this.extra_figures = extra_figures;
        }

        public double getCarry_self() {
            return carry_self;
        }

        public void setCarry_self(double carry_self) {
            this.carry_self = carry_self;
        }

        public double getCarry_share() {
            return carry_share;
        }

        public void setCarry_share(double carry_share) {
            this.carry_share = carry_share;
        }

        public double getCash_self_display() {
            return cash_self_display;
        }

        public void setCash_self_display(double cash_self_display) {
            this.cash_self_display = cash_self_display;
        }

        public double getCash_share_display() {
            return cash_share_display;
        }

        public void setCash_share_display(double cash_share_display) {
            this.cash_share_display = cash_share_display;
        }

        public static class ExtraInfoBean {
            /**
             * surplus_days : 19
             * next_agencylevel_display : VIP2
             * next_level_exam_url : http://m.nidepuzi.com/mall/activity/exam
             * could_cash_out : 0
             * agencylevel_display : VIP1
             * invite_url : https://m.nidepuzi.com/pages/agency-invitation-res.html
             * total_rank : 0
             * agencylevel : 2
             * next_agencylevel : 3
             * cashout_reason : 余额不足不能提现
             * his_confirmed_cash_out : 0
             * thumbnail : http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0
             */

            private int surplus_days;
            private String next_agencylevel_display;
            private String next_level_exam_url;
            private int could_cash_out;
            private String agencylevel_display;
            private String invite_url;
            private int total_rank;
            private int agencylevel;
            private int next_agencylevel;
            private String cashout_reason;
            private int his_confirmed_cash_out;
            private String thumbnail;

            public int getSurplus_days() {
                return surplus_days;
            }

            public void setSurplus_days(int surplus_days) {
                this.surplus_days = surplus_days;
            }

            public String getNext_agencylevel_display() {
                return next_agencylevel_display;
            }

            public void setNext_agencylevel_display(String next_agencylevel_display) {
                this.next_agencylevel_display = next_agencylevel_display;
            }

            public String getNext_level_exam_url() {
                return next_level_exam_url;
            }

            public void setNext_level_exam_url(String next_level_exam_url) {
                this.next_level_exam_url = next_level_exam_url;
            }

            public int getCould_cash_out() {
                return could_cash_out;
            }

            public void setCould_cash_out(int could_cash_out) {
                this.could_cash_out = could_cash_out;
            }

            public String getAgencylevel_display() {
                return agencylevel_display;
            }

            public void setAgencylevel_display(String agencylevel_display) {
                this.agencylevel_display = agencylevel_display;
            }

            public String getInvite_url() {
                return invite_url;
            }

            public void setInvite_url(String invite_url) {
                this.invite_url = invite_url;
            }

            public int getTotal_rank() {
                return total_rank;
            }

            public void setTotal_rank(int total_rank) {
                this.total_rank = total_rank;
            }

            public int getAgencylevel() {
                return agencylevel;
            }

            public void setAgencylevel(int agencylevel) {
                this.agencylevel = agencylevel;
            }

            public int getNext_agencylevel() {
                return next_agencylevel;
            }

            public void setNext_agencylevel(int next_agencylevel) {
                this.next_agencylevel = next_agencylevel;
            }

            public String getCashout_reason() {
                return cashout_reason;
            }

            public void setCashout_reason(String cashout_reason) {
                this.cashout_reason = cashout_reason;
            }

            public int getHis_confirmed_cash_out() {
                return his_confirmed_cash_out;
            }

            public void setHis_confirmed_cash_out(int his_confirmed_cash_out) {
                this.his_confirmed_cash_out = his_confirmed_cash_out;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }
        }

        public static class ExtraFiguresBean {
            /**
             * team_total_rank : 0
             * personal_total_rank : 0
             * task_percentage : 0
             * today_carry_record : 0
             * week_duration_total : 0
             * week_duration_rank : 0
             */

            private int team_total_rank;
            private int personal_total_rank;
            private int task_percentage;
            private double today_carry_record;
            private double week_duration_total;
            private double month_duration_total;
            private int week_duration_rank;

            public int getTeam_total_rank() {
                return team_total_rank;
            }

            public void setTeam_total_rank(int team_total_rank) {
                this.team_total_rank = team_total_rank;
            }

            public int getPersonal_total_rank() {
                return personal_total_rank;
            }

            public void setPersonal_total_rank(int personal_total_rank) {
                this.personal_total_rank = personal_total_rank;
            }

            public int getTask_percentage() {
                return task_percentage;
            }

            public void setTask_percentage(int task_percentage) {
                this.task_percentage = task_percentage;
            }

            public double getToday_carry_record() {
                return today_carry_record;
            }

            public void setToday_carry_record(double today_carry_record) {
                this.today_carry_record = today_carry_record;
            }

            public double getWeek_duration_total() {
                return week_duration_total;
            }

            public void setWeek_duration_total(double week_duration_total) {
                this.week_duration_total = week_duration_total;
            }

            public int getWeek_duration_rank() {
                return week_duration_rank;
            }

            public void setWeek_duration_rank(int week_duration_rank) {
                this.week_duration_rank = week_duration_rank;
            }

            public double getMonth_duration_total() {
                return month_duration_total;
            }

            public void setMonth_duration_total(double month_duration_total) {
                this.month_duration_total = month_duration_total;
            }
        }
    }
}
