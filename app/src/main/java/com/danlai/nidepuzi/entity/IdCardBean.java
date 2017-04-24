package com.danlai.nidepuzi.entity;

/**
 * Created by wisdom on 17/3/7.
 */

public class IdCardBean {

    /**
     * info :
     * code : 0
     * card_infos : {"face_rect":{"angle":-90,"center":{"y":1438.5,"x":4028.5},"size":{"width":1107,"height":1195}},"config_str":"{\"side\":\"face\"}","name":"奥巴马","success":true,"request_id":"20170307162701_d8f5eac508f8ace9c157dafd499740e3","sex":"男","num":"812346196108047990","birth":"19610804","address":"华盛顿特区宜宾法尼亚大道1创刀号","nationality":"汉","card_imgpath":"ocr/idcard_863991_20170307162705.jpg","side":"face"}
     */

    private String info;
    private int code;
    private CardInfosBean card_infos;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CardInfosBean getCard_infos() {
        return card_infos;
    }

    public void setCard_infos(CardInfosBean card_infos) {
        this.card_infos = card_infos;
    }

    public static class CardInfosBean {
        /**
         * face_rect : {"angle":-90,"center":{"y":1438.5,"x":4028.5},"size":{"width":1107,"height":1195}}
         * config_str : {"side":"face"}
         * name : 奥巴马
         * success : true
         * request_id : 20170307162701_d8f5eac508f8ace9c157dafd499740e3
         * sex : 男
         * num : 812346196108047990
         * birth : 19610804
         * address : 华盛顿特区宜宾法尼亚大道1创刀号
         * nationality : 汉
         * card_imgpath : ocr/idcard_863991_20170307162705.jpg
         * side : face
         */

        private FaceRectBean face_rect;
        private String config_str;
        private String name;
        private boolean success;
        private String request_id;
        private String sex;
        private String num;
        private String birth;
        private String address;
        private String nationality;
        private String card_imgpath;
        private String side;

        public FaceRectBean getFace_rect() {
            return face_rect;
        }

        public void setFace_rect(FaceRectBean face_rect) {
            this.face_rect = face_rect;
        }

        public String getConfig_str() {
            return config_str;
        }

        public void setConfig_str(String config_str) {
            this.config_str = config_str;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getCard_imgpath() {
            return card_imgpath;
        }

        public void setCard_imgpath(String card_imgpath) {
            this.card_imgpath = card_imgpath;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public static class FaceRectBean {
            /**
             * angle : -90
             * center : {"y":1438.5,"x":4028.5}
             * size : {"width":1107,"height":1195}
             */

            private double angle;
            private CenterBean center;
            private SizeBean size;

            public double getAngle() {
                return angle;
            }

            public void setAngle(double angle) {
                this.angle = angle;
            }

            public CenterBean getCenter() {
                return center;
            }

            public void setCenter(CenterBean center) {
                this.center = center;
            }

            public SizeBean getSize() {
                return size;
            }

            public void setSize(SizeBean size) {
                this.size = size;
            }

            public static class CenterBean {
                /**
                 * y : 1438.5
                 * x : 4028.5
                 */

                private double y;
                private double x;

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }
            }

            public static class SizeBean {
                /**
                 * width : 1107
                 * height : 1195
                 */

                private double width;
                private double height;

                public double getWidth() {
                    return width;
                }

                public void setWidth(double width) {
                    this.width = width;
                }

                public double getHeight() {
                    return height;
                }

                public void setHeight(double height) {
                    this.height = height;
                }
            }
        }
    }
}
