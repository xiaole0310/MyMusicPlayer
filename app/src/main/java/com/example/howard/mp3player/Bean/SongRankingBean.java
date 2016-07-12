package com.example.howard.mp3player.Bean;

import java.util.List;

/**
 * Created by Howard on 2016/7/11.
 */
public class SongRankingBean {
    @Override
    public String toString() {
        return "SongRankingBean{" +
                "billboard=" + billboard +
                ", error_code=" + error_code +
                ", song_list=" + song_list +
                '}';
    }

    /**
     * billboard_type : 1
     * billboard_no : 1887
     * update_date : 2016-07-11
     * billboard_songnum : 186
     * havemore : 1
     * name : 新歌榜
     * comment : 该榜单是根据百度音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次
     * pic_s640 : http://c.hiphotos.baidu.com/ting/pic/item/f7246b600c33874495c4d089530fd9f9d62aa0c6.jpg
     * pic_s444 : http://d.hiphotos.baidu.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg
     * pic_s260 : http://b.hiphotos.baidu.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg
     * pic_s210 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_c49310115801d43d42a98fdc357f6057.jpg
     * web_url : http://music.baidu.com/top/new
     */

    private BillboardBean billboard;
    /**
     * song_list : [{"artist_id":"88","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/f201bdf2c3c41cef7b74985aee3b7e23/266941936/266941936.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/c4ed8fc38ecc1d69c87e5c5183fe3f65/266941939/266941939.jpg","country":"内地","area":"0","publishtime":"2016-06-23","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/60902a2420610e8d3ac7ee2f9e754dc1/266942158/266942158.lrc","copy_type":"1","hot":"750172","all_artist_ting_uid":"2517","resource_type":"0","is_new":"1","rank_change":"0","rank":"1","all_artist_id":"88","style":"影视原声","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"影视原声","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"266942077","title":"我好像在哪见过你","ting_uid":"2517","author":"薛之谦","album_id":"266941947","album_title":"我好像在哪见过你","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"薛之谦"},{"artist_id":"123339593","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/4827be12e023a8bfffd487fefd443114/266867928/266867928.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/30f2119dee46fd8f629ded6e6a11be36/266867931/266867931.jpg","country":"内地","area":"0","publishtime":"2016-06-22","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/491f9bb238d224444d896a31ca9f4457/266879166/266879166.lrc","copy_type":"1","hot":"519833","all_artist_ting_uid":"163361619","resource_type":"0","is_new":"1","rank_change":"0","rank":"2","all_artist_id":"123339593","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":1,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"266867984","title":"渡红尘","ting_uid":"163361619","author":"张碧晨","album_id":"266867952","album_title":"渡红尘","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0100000000","artist_name":"张碧晨"},{"artist_id":"108094436","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/7a011771e2cb9d036c91c230eda62229/266990129/266990129.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/86ac832526582d178646aa41930bcc4b/266990132/266990132.jpg","country":"内地","area":"0","publishtime":"2016-06-26","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/82356213fd0fe8c9745d8ee9a87aa189/267057269/267057269.lrc","copy_type":"1","hot":"279163","all_artist_ting_uid":"172120638,232950016,1325,239545528,6113,239560631,2517,1045,239544941","resource_type":"0","is_new":"1","rank_change":"0","rank":"3","all_artist_id":"108094436,136166637,570,250129461,63,264774299,88,32,249427757","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"266990186","title":"重返十七岁","ting_uid":"172120638","author":"鹿晗,Ella,大张伟,张丹峰,潘玮柏,刘敏涛,薛之谦,魏晨,张慧雯","album_id":"266990136","album_title":"我去上学啦","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":1,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"鹿晗,Ella,大张伟,张丹峰,潘玮柏,刘敏涛,薛之谦,魏晨,张慧雯"},{"artist_id":"123339593","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/33572444df18df0e7b57289929a8ea11/267323338/267323338.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/a22978a7eef7ee74448d3849f35b07b0/267323341/267323341.jpg","country":"内地","area":"0","publishtime":"2016-07-05","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/0f44e13a010ac826acb28f2e166995aa/267323393/267323393.lrc","copy_type":"1","hot":"220616","all_artist_ting_uid":"163361619","resource_type":"0","is_new":"1","rank_change":"0","rank":"4","all_artist_id":"123339593","style":"影视原声","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"影视原声","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"267323360","title":"为什么我好想告诉他我是谁","ting_uid":"163361619","author":"张碧晨","album_id":"267323345","album_title":"为什么我好想告诉他我是谁","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"1000000000","artist_name":"张碧晨"},{"artist_id":"1483","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/702ad6cfd32b810d9ed5eba55c6e4b23/267042399/267042399.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/1fec87dc91b73f8f499c484e9872c696/267042402/267042402.jpg","country":"内地","area":"0","publishtime":"2016-06-27","album_no":"7","lrclink":"http://musicdata.baidu.com/data2/lrc/8f4de101889f3226f87b5eead47456e9/265671613/265671613.lrc","copy_type":"1","hot":"220436","all_artist_ting_uid":"1557","resource_type":"0","is_new":"1","rank_change":"0","rank":"5","all_artist_id":"1483","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"-1|-1\"}","song_id":"262235069","title":"燕归巢","ting_uid":"1557","author":"许嵩","album_id":"264972901","album_title":"青年晚报","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"1","resource_type_ext":"1","mv_provider":"0000000000","artist_name":"许嵩"},{"artist_id":"88","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/14c544e32bef326d54e0de16f0e3858f/266259604/266259604.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/e3ed2790a7eba2e7f7b0e85880a0e99e/266259607/266259607.jpg","country":"内地","area":"0","publishtime":"2016-06-07","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/a4014c4e7937cc48cc79bf77d42a4e0e/266259963/266259963.lrc","copy_type":"1","hot":"947579","all_artist_ting_uid":"2517","resource_type":"0","is_new":"0","rank_change":"0","rank":"6","all_artist_id":"88","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"266259728","title":"刚刚好","ting_uid":"2517","author":"薛之谦","album_id":"266259670","album_title":"刚刚好","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"1000000000","artist_name":"薛之谦"},{"artist_id":"247010469","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/0c87cb20fd6b543cd1c90d1d82f1f6c5/266782982/266782982.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/169cbb0d82522568dea198363f7028ec/266782985/266782985.jpg","country":"内地","area":"0","publishtime":"2016-06-20","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/a51937f634501835df3802b93f43e9a4/267159148/267159148.lrc","copy_type":"1","hot":"521484","all_artist_ting_uid":"232954914","resource_type":"0","is_new":"1","rank_change":"0","rank":"7","all_artist_id":"247010469","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256","sound_effect":"0","file_duration":0,"has_mv_mobile":1,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"-1|-1\"}","song_id":"266784254","title":"红","ting_uid":"232954914","author":"冯建宇","album_id":"266784256","album_title":"红","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"1","resource_type_ext":"1","mv_provider":"0100000000","artist_name":"冯建宇"},{"artist_id":"1483","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/702ad6cfd32b810d9ed5eba55c6e4b23/267042399/267042399.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/1fec87dc91b73f8f499c484e9872c696/267042402/267042402.jpg","country":"内地","area":"0","publishtime":"2016-06-27","album_no":"6","lrclink":"http://musicdata.baidu.com/data2/lrc/74691656168844aa69b1d8b5dddd2144/267042446/267042446.lrc","copy_type":"1","hot":"162398","all_artist_ting_uid":"1557","resource_type":"0","is_new":"1","rank_change":"0","rank":"8","all_artist_id":"1483","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"-1|-1\"}","song_id":"266969151","title":"平行宇宙","ting_uid":"1557","author":"许嵩","album_id":"264972901","album_title":"青年晚报","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"1","resource_type_ext":"1","mv_provider":"0000000000","artist_name":"许嵩"},{"artist_id":"68","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/dd6ae5d70656459e87cf569c2e799b99/267063701/267063701.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/b44b798bccd7ab951c2834aeaae6f122/267063704/267063704.jpg","country":"内地","area":"0","publishtime":"2016-06-28","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/cfa9b264d301ba23327ae6a688cdcc94/267198126/267198126.lrc","copy_type":"1","hot":"146922","all_artist_ting_uid":"1062","resource_type":"0","is_new":"1","rank_change":"0","rank":"9","all_artist_id":"68","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"267063758","title":"万物有灵","ting_uid":"1062","author":"谭维维","album_id":"267063767","album_title":"万物有灵","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"谭维维"},{"artist_id":"1483","language":"国语","pic_big":"http://musicdata.baidu.com/data2/pic/702ad6cfd32b810d9ed5eba55c6e4b23/267042399/267042399.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/1fec87dc91b73f8f499c484e9872c696/267042402/267042402.jpg","country":"内地","area":"0","publishtime":"2016-06-27","album_no":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/54189afbb954b346faec721711599139/267042436/267042436.lrc","copy_type":"1","hot":"133063","all_artist_ting_uid":"1557","resource_type":"0","is_new":"1","rank_change":"0","rank":"10","all_artist_id":"1483","style":"流行","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","sound_effect":"0","file_duration":0,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"-1|-1\"}","song_id":"266969127","title":"奇谈","ting_uid":"1557","author":"许嵩","album_id":"264972901","album_title":"青年晚报","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"1","resource_type_ext":"1","mv_provider":"0000000000","artist_name":"许嵩"}]
     * billboard : {"billboard_type":"1","billboard_no":"1887","update_date":"2016-07-11","billboard_songnum":"186","havemore":1,"name":"新歌榜","comment":"该榜单是根据百度音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次","pic_s640":"http://c.hiphotos.baidu.com/ting/pic/item/f7246b600c33874495c4d089530fd9f9d62aa0c6.jpg","pic_s444":"http://d.hiphotos.baidu.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg","pic_s260":"http://b.hiphotos.baidu.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg","pic_s210":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_c49310115801d43d42a98fdc357f6057.jpg","web_url":"http://music.baidu.com/top/new"}
     * error_code : 22000
     */

    private int error_code;
    /**
     * artist_id : 88
     * language : 国语
     * pic_big : http://musicdata.baidu.com/data2/pic/f201bdf2c3c41cef7b74985aee3b7e23/266941936/266941936.jpg
     * pic_small : http://musicdata.baidu.com/data2/pic/c4ed8fc38ecc1d69c87e5c5183fe3f65/266941939/266941939.jpg
     * country : 内地
     * area : 0
     * publishtime : 2016-06-23
     * album_no : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/60902a2420610e8d3ac7ee2f9e754dc1/266942158/266942158.lrc
     * copy_type : 1
     * hot : 750172
     * all_artist_ting_uid : 2517
     * resource_type : 0
     * is_new : 1
     * rank_change : 0
     * rank : 1
     * all_artist_id : 88
     * style : 影视原声
     * del_status : 0
     * relate_status : 0
     * toneid : 0
     * all_rate : 64,128,256,320,flac
     * sound_effect : 0
     * file_duration : 0
     * has_mv_mobile : 0
     * versions : 影视原声
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * song_id : 266942077
     * title : 我好像在哪见过你
     * ting_uid : 2517
     * author : 薛之谦
     * album_id : 266941947
     * album_title : 我好像在哪见过你
     * is_first_publish : 0
     * havehigh : 2
     * charge : 0
     * has_mv : 0
     * learn : 0
     * song_source : web
     * piao_id : 0
     * korean_bb_song : 0
     * resource_type_ext : 0
     * mv_provider : 0000000000
     * artist_name : 薛之谦
     */

    private List<SongListBean> song_list;

    public BillboardBean getBillboard() {
        return billboard;
    }

    public void setBillboard(BillboardBean billboard) {
        this.billboard = billboard;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongListBean> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<SongListBean> song_list) {
        this.song_list = song_list;
    }

    public static class BillboardBean {
        private String billboard_type;
        private String billboard_no;
        private String update_date;
        private String billboard_songnum;
        private int havemore;
        private String name;
        private String comment;
        private String pic_s640;
        private String pic_s444;
        private String pic_s260;
        private String pic_s210;
        private String web_url;

        @Override
        public String toString() {
            return "BillboardBean{" +
                    "billboard_type='" + billboard_type + '\'' +
                    ", billboard_no='" + billboard_no + '\'' +
                    ", update_date='" + update_date + '\'' +
                    ", billboard_songnum='" + billboard_songnum + '\'' +
                    ", havemore=" + havemore +
                    ", name='" + name + '\'' +
                    ", comment='" + comment + '\'' +
                    ", pic_s640='" + pic_s640 + '\'' +
                    ", pic_s444='" + pic_s444 + '\'' +
                    ", pic_s260='" + pic_s260 + '\'' +
                    ", pic_s210='" + pic_s210 + '\'' +
                    ", web_url='" + web_url + '\'' +
                    '}';
        }

        public String getBillboard_type() {
            return billboard_type;
        }

        public void setBillboard_type(String billboard_type) {
            this.billboard_type = billboard_type;
        }

        public String getBillboard_no() {
            return billboard_no;
        }

        public void setBillboard_no(String billboard_no) {
            this.billboard_no = billboard_no;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getBillboard_songnum() {
            return billboard_songnum;
        }

        public void setBillboard_songnum(String billboard_songnum) {
            this.billboard_songnum = billboard_songnum;
        }

        public int getHavemore() {
            return havemore;
        }

        public void setHavemore(int havemore) {
            this.havemore = havemore;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getPic_s640() {
            return pic_s640;
        }

        public void setPic_s640(String pic_s640) {
            this.pic_s640 = pic_s640;
        }

        public String getPic_s444() {
            return pic_s444;
        }

        public void setPic_s444(String pic_s444) {
            this.pic_s444 = pic_s444;
        }

        public String getPic_s260() {
            return pic_s260;
        }

        public void setPic_s260(String pic_s260) {
            this.pic_s260 = pic_s260;
        }

        public String getPic_s210() {
            return pic_s210;
        }

        public void setPic_s210(String pic_s210) {
            this.pic_s210 = pic_s210;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }

    public static class SongListBean {
        private String artist_id;
        private String language;
        private String pic_big;
        private String pic_small;
        private String country;
        private String area;
        private String publishtime;
        private String album_no;
        private String lrclink;
        private String copy_type;
        private String hot;
        private String all_artist_ting_uid;
        private String resource_type;
        private String is_new;
        private String rank_change;
        private String rank;
        private String all_artist_id;
        private String style;
        private String del_status;
        private String relate_status;
        private String toneid;
        private String all_rate;
        private String sound_effect;
        private int file_duration;
        private int has_mv_mobile;
        private String versions;
        private String bitrate_fee;
        private String song_id;
        private String title;
        private String ting_uid;
        private String author;
        private String album_id;
        private String album_title;
        private int is_first_publish;
        private int havehigh;
        private int charge;
        private int has_mv;
        private int learn;
        private String song_source;
        private String piao_id;
        private String korean_bb_song;
        private String resource_type_ext;
        private String mv_provider;
        private String artist_name;

        @Override
        public String toString() {
            return "SongListBean{" +
                    "artist_id='" + artist_id + '\'' +
                    ", language='" + language + '\'' +
                    ", pic_big='" + pic_big + '\'' +
                    ", pic_small='" + pic_small + '\'' +
                    ", country='" + country + '\'' +
                    ", area='" + area + '\'' +
                    ", publishtime='" + publishtime + '\'' +
                    ", album_no='" + album_no + '\'' +
                    ", lrclink='" + lrclink + '\'' +
                    ", copy_type='" + copy_type + '\'' +
                    ", hot='" + hot + '\'' +
                    ", all_artist_ting_uid='" + all_artist_ting_uid + '\'' +
                    ", resource_type='" + resource_type + '\'' +
                    ", is_new='" + is_new + '\'' +
                    ", rank_change='" + rank_change + '\'' +
                    ", rank='" + rank + '\'' +
                    ", all_artist_id='" + all_artist_id + '\'' +
                    ", style='" + style + '\'' +
                    ", del_status='" + del_status + '\'' +
                    ", relate_status='" + relate_status + '\'' +
                    ", toneid='" + toneid + '\'' +
                    ", all_rate='" + all_rate + '\'' +
                    ", sound_effect='" + sound_effect + '\'' +
                    ", file_duration=" + file_duration +
                    ", has_mv_mobile=" + has_mv_mobile +
                    ", versions='" + versions + '\'' +
                    ", bitrate_fee='" + bitrate_fee + '\'' +
                    ", song_id='" + song_id + '\'' +
                    ", title='" + title + '\'' +
                    ", ting_uid='" + ting_uid + '\'' +
                    ", author='" + author + '\'' +
                    ", album_id='" + album_id + '\'' +
                    ", album_title='" + album_title + '\'' +
                    ", is_first_publish=" + is_first_publish +
                    ", havehigh=" + havehigh +
                    ", charge=" + charge +
                    ", has_mv=" + has_mv +
                    ", learn=" + learn +
                    ", song_source='" + song_source + '\'' +
                    ", piao_id='" + piao_id + '\'' +
                    ", korean_bb_song='" + korean_bb_song + '\'' +
                    ", resource_type_ext='" + resource_type_ext + '\'' +
                    ", mv_provider='" + mv_provider + '\'' +
                    ", artist_name='" + artist_name + '\'' +
                    '}';
        }

        public String getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getPic_big() {
            return pic_big;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public String getPic_small() {
            return pic_small;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getAlbum_no() {
            return album_no;
        }

        public void setAlbum_no(String album_no) {
            this.album_no = album_no;
        }

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public String getCopy_type() {
            return copy_type;
        }

        public void setCopy_type(String copy_type) {
            this.copy_type = copy_type;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getAll_artist_ting_uid() {
            return all_artist_ting_uid;
        }

        public void setAll_artist_ting_uid(String all_artist_ting_uid) {
            this.all_artist_ting_uid = all_artist_ting_uid;
        }

        public String getResource_type() {
            return resource_type;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getRank_change() {
            return rank_change;
        }

        public void setRank_change(String rank_change) {
            this.rank_change = rank_change;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getDel_status() {
            return del_status;
        }

        public void setDel_status(String del_status) {
            this.del_status = del_status;
        }

        public String getRelate_status() {
            return relate_status;
        }

        public void setRelate_status(String relate_status) {
            this.relate_status = relate_status;
        }

        public String getToneid() {
            return toneid;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public String getSound_effect() {
            return sound_effect;
        }

        public void setSound_effect(String sound_effect) {
            this.sound_effect = sound_effect;
        }

        public int getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(int file_duration) {
            this.file_duration = file_duration;
        }

        public int getHas_mv_mobile() {
            return has_mv_mobile;
        }

        public void setHas_mv_mobile(int has_mv_mobile) {
            this.has_mv_mobile = has_mv_mobile;
        }

        public String getVersions() {
            return versions;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getSong_id() {
            return song_id;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public int getIs_first_publish() {
            return is_first_publish;
        }

        public void setIs_first_publish(int is_first_publish) {
            this.is_first_publish = is_first_publish;
        }

        public int getHavehigh() {
            return havehigh;
        }

        public void setHavehigh(int havehigh) {
            this.havehigh = havehigh;
        }

        public int getCharge() {
            return charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public int getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(int has_mv) {
            this.has_mv = has_mv;
        }

        public int getLearn() {
            return learn;
        }

        public void setLearn(int learn) {
            this.learn = learn;
        }

        public String getSong_source() {
            return song_source;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
        }

        public String getPiao_id() {
            return piao_id;
        }

        public void setPiao_id(String piao_id) {
            this.piao_id = piao_id;
        }

        public String getKorean_bb_song() {
            return korean_bb_song;
        }

        public void setKorean_bb_song(String korean_bb_song) {
            this.korean_bb_song = korean_bb_song;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getMv_provider() {
            return mv_provider;
        }

        public void setMv_provider(String mv_provider) {
            this.mv_provider = mv_provider;
        }

        public String getArtist_name() {
            return artist_name;
        }

        public void setArtist_name(String artist_name) {
            this.artist_name = artist_name;
        }
    }
}
